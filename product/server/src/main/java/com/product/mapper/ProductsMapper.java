package com.product.mapper;

import com.product.pojo.ProductInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;


import java.util.List;

@Mapper
@Component
public interface ProductsMapper {
    @Select("select * from product_info;")
    List<ProductInfo> getAllProductList();

    @Select("select * from product_info where product_status=${productStatus} ;")
    List<ProductInfo> getProductListByStatus(@Param("productStatus") int productStatus);



    @Select("select * from product_info where product_id=${productId};")
    ProductInfo getProductById(@Param("productId") String productId);
}
