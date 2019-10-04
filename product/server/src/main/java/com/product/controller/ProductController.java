package com.product.controller;

import com.product.pojo.ProductCategory;
import com.product.pojo.ProductInfo;

import com.product.service.ProductCatagoryService;
import com.product.service.ProductInfoService;
import com.product.utils.ResultVOUtils;
import com.product.vo.ProductInfoVO;
import com.product.vo.ProductVO;
import com.product.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 查询在架商品列表
 * 类目type
 * 查询类目
 * 构造数据
 */
@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductInfoService productService;

    @Autowired
    ProductCatagoryService catagoryService;


    @RequestMapping(value = "/list")
    public ResultVO<ProductInfo> getProductList() {

        //查询商品列表
        List<ProductInfo> productList = productService.getProductListByStatus();

        //获得商品类目
        List<String> typeList = productList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());
        //从数据库中查询类目
        List<ProductCategory> categoryList = catagoryService.getProductListByCatagoryType(typeList);

        List<ProductVO> productVOList = new ArrayList<>();


        for (ProductCategory productCategory : categoryList) {
            //productVO对应data
            // ProductInfoVO对应foods
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo product : productList) {
                if (product.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    //拷贝性质
                    BeanUtils.copyProperties(product, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }


            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtils.success(productVOList);
    }


    @GetMapping ("/getPriceByID")
    public ResultVO<String> getPriceByID(@RequestParam(value = "id") String id){
        log.info("String is {}",id);
        ProductInfo product= productService.getProductById(id);
       BigDecimal price=product.getProductPrice();
       return ResultVOUtils.successQuery(price);
    }
}
