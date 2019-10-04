

package com.order.controller;





import com.example.client.ProductClient;
import com.example.common.CartDTO;
import com.example.common.ProductInfo;
import com.order.dataobject.OrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@Slf4j
@RestController
public class ClientContoller {
    /**
     * 使用feign发送http请求
     */

    @Autowired
     private ProductClient productClient;

    /**
     * 使用RestTemplate发送http请求
     */
//    @Autowired
//    RestTemplate restTemplate;
//    public String getProduct(){
//       String response=restTemplate.getForObject("http://PRODUCT/msg",String.class);
//       return response;
//    }



    @PostMapping("/listProductsByList")
    public List<ProductInfo> getProdcutList() {
        List<ProductInfo> productInfoList = productClient.listProductsByList(Arrays.asList("157875196366160022", "157875227953464068"));
        return productInfoList;
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @GetMapping("/product/decStock")
    public void decStock() {
        productClient.decStock(Arrays.asList(new CartDTO("157875227953464068", 2), new CartDTO("157875196366160022", 3)));
    }

}
