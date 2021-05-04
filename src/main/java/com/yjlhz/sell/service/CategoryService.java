package com.yjlhz.sell.service;

import com.yjlhz.sell.dataobject.ProductCategory;

import java.util.List;

public interface CategoryService {
    /** 单条查询 */
    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);

}
