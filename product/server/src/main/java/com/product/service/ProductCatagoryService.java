package com.product.service;

import com.product.mapper.ProductsCategoryMapper;
import com.product.pojo.ProductInfo;
import com.product.pojo.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductCatagoryService {
    @Autowired
    ProductsCategoryMapper productsCategoryMapper;

    public List<ProductCategory> getProductListByCatagoryType(List list){
        return productsCategoryMapper.getProductListByCatagoryType(list);
    }
}
