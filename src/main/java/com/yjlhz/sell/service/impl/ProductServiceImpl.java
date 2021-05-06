package com.yjlhz.sell.service.impl;

import com.yjlhz.sell.dataobject.ProductInfo;
import com.yjlhz.sell.dto.CartDTO;
import com.yjlhz.sell.enums.ProductStatusEnum;
import com.yjlhz.sell.enums.ResultEnum;
import com.yjlhz.sell.exception.SellException;
import com.yjlhz.sell.repository.ProductInfoRepository;
import com.yjlhz.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        return repository.findById(productId).get();
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {

    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList){
            ProductInfo productInfo = repository.findById(cartDTO.getProductId()).get();
            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0){
                throw new SellException(ResultEnum.product_stock_error);
            }
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }
}
