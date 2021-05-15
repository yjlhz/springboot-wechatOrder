package com.yjlhz.sell.service;

import com.yjlhz.sell.dataobject.ProductInfo;
import com.yjlhz.sell.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductInfo findOne(String productId);

    /** 查询在架商品(c端功能) */
    List<ProductInfo> findUpAll();

    /** 管理端查询所有商品 */
    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    /** 加库存 */
    void increaseStock(List<CartDTO> cartDTOList);

    /** 减库存 */
    void decreaseStock(List<CartDTO> cartDTOList);

    /** 上架 */
    ProductInfo onSale(String productId);

    /** 上架 */
    ProductInfo offSale(String productId);

}
