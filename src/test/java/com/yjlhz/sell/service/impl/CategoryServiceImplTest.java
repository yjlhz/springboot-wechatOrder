package com.yjlhz.sell.service.impl;

import com.yjlhz.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    void findOne() {
        ProductCategory productCategory = categoryService.findOne(1);
        System.out.println(productCategory.toString());
    }

    @Test
    void findAll() {
        List<ProductCategory> productCategoryList = categoryService.findAll();
        Assert.assertNotEquals(0,productCategoryList.size());
    }

    @Test
    void findByCategoryTypeIn() {
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(Arrays.asList(2,3,10));
        Assert.assertNotEquals(0,productCategoryList.size());
    }

    @Test
    void save() {
        ProductCategory productCategory = new ProductCategory("男生专享",4);
        ProductCategory result = categoryService.save(productCategory);
        Assert.assertNotNull(result);
    }
}