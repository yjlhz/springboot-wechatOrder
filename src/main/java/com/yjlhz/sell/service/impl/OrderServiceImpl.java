package com.yjlhz.sell.service.impl;

import com.yjlhz.sell.dataobject.OrderDetail;
import com.yjlhz.sell.dataobject.OrderMaster;
import com.yjlhz.sell.dataobject.ProductInfo;
import com.yjlhz.sell.dto.CartDTO;
import com.yjlhz.sell.dto.OrderDTO;
import com.yjlhz.sell.enums.OrderStatusEnum;
import com.yjlhz.sell.enums.PayStatusEnum;
import com.yjlhz.sell.enums.ResultEnum;
import com.yjlhz.sell.exception.SellException;
import com.yjlhz.sell.repository.OrderDetailRepository;
import com.yjlhz.sell.repository.OrderMasterRepository;
import com.yjlhz.sell.service.OrderService;
import com.yjlhz.sell.service.ProductService;
import com.yjlhz.sell.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.genUniqueKey();

        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        //1、查询商品（数量，价格）
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()){
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2、计算订单总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            //订单详情入库
            orderDetail.setOrderId(KeyUtil.genUniqueKey());
            orderDetail.setDetailId(orderId);
            //拷贝属性
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetailRepository.save(orderDetail);
        }
        //3、写入订单数据库（orderMaster）
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        //4、扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList()
                .stream().map(e -> new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
