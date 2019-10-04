package com.example.client;


import com.example.common.CartDTO;
import com.example.common.ProductInfo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



import java.util.List;

@FeignClient(name = "product")
@Component
public interface ProductClient {

    @PostMapping(value = "/product/listProductsByList")
    List<ProductInfo> listProductsByList(@RequestBody List<String> productList);

    @PostMapping(value = "/product/decStock")
    void decStock(@RequestBody List<CartDTO> cartDTOList);

}
