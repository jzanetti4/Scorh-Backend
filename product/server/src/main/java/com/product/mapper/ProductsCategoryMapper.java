package com.product.mapper;

import com.product.pojo.ProductInfo;
import com.product.pojo.ProductCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ProductsCategoryMapper {
    @Select({"<script>",
            "SELECT *",
            "FROM product_category",
            "WHERE category_type IN",
            "<foreach item='item' index='index' collection='categoryType'",
            "open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            "</script>"})
    List<ProductCategory> getProductListByCatagoryType(@Param("categoryType") List<Integer> categoryType);
}
