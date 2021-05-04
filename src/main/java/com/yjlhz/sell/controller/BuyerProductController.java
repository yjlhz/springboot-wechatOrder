package com.yjlhz.sell.controller;

import com.yjlhz.sell.VO.ProductInfoVO;
import com.yjlhz.sell.VO.ProductVO;
import com.yjlhz.sell.VO.ResultVO;
import com.yjlhz.sell.dataobject.ProductCategory;
import com.yjlhz.sell.dataobject.ProductInfo;
import com.yjlhz.sell.service.CategoryService;
import com.yjlhz.sell.service.ProductService;
import com.yjlhz.sell.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list(){
        //1、查询所有上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        //2、查询类目（一次性查询，为了性能）
//        List<Integer> categoryTypeList = new ArrayList<>();
        //获取categoryTypeList
//        //传统方法
//        for(ProductInfo productInfo : productInfoList){
//            categoryTypeList.add(productInfo.getCategoryType());
//        }
        //利用Java8新特性-lambda表达式
        List<Integer> categoryTypeList = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        //3、数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList){
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    //将相同属性的值复制一遍,比for循环取值简洁
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);
    }
}
