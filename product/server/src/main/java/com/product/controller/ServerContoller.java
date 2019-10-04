package com.product.controller;

import com.example.common.CartDTO;
import com.product.pojo.ProductInfo;
import com.product.service.ProductInfoService;
import com.product.utils.ResultVOUtils;
import com.product.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 该服务仅供order调用
 */

@RestController
@RequestMapping("/product")
public class ServerContoller {
    @Autowired
    ProductInfoService productInfoService;

    @GetMapping("/msg")
    public String getProduct(){
        return "got it";
    }

    @PostMapping("/listProductsByList")
    public List<ProductInfo> getProductsByList(@RequestBody List<String> productList){
        return productInfoService.findByProductIdIn(productList);
    }

    @PostMapping("/decStock")
    public void decStock(@RequestBody List<CartDTO> cartDTOList){
        productInfoService.decStock(cartDTOList);
    }

}
