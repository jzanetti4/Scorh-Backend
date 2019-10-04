package com.product.repository;

import com.product.pojo.ProductInfo;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductInfo,String> {
    List<ProductInfo> findByProductIdIn(List<String> productList);
    void deleteByProductStock(Integer number);
}
