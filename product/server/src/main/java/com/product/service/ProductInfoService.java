package com.product.service;


import com.example.common.CartDTO;
import com.product.enums.CartStatus;
import com.product.exception.ProductException;
import com.product.mapper.ProductsMapper;

import com.product.pojo.ProductInfo;
import com.product.repository.ProductRepository;
import com.product.utils.JsonUtil;
import enums.ProductStatusEnum;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductInfoService {

    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    ProductsMapper productsMapper;

    @Autowired
    ProductRepository productRepository;

    public List<ProductInfo> getProductListByStatus() {
        return productsMapper.getProductListByStatus(ProductStatusEnum.UP.getCode());
    }


    public ProductInfo getProductById(String id) {
        return productsMapper.getProductById(id);
    }

    public List<ProductInfo> findByProductIdIn(List<String> productList) {
        return productRepository.findByProductIdIn(productList).stream()
                .map(e -> {
                    ProductInfo output = new ProductInfo();
                    BeanUtils.copyProperties(e, output);
                    return output;
                })
                .collect(Collectors.toList());
    }


    public void decStock(List<CartDTO> list) {
        List<ProductInfo> productInfoList = decreaseStockProcess(list);
        //发送mq
        amqpTemplate.convertAndSend("productInfo", JsonUtil.toJson(productInfoList));
    }



    @Transactional
    public List<ProductInfo> decreaseStockProcess(List<CartDTO> list) {
        List<ProductInfo> productInfoList = new ArrayList<>();
        for (CartDTO cartDTOS : list) {
            Optional<ProductInfo> product = productRepository.findById(cartDTOS.getProductID());
            //判断商品是否存在
            if (!product.isPresent()) {
                throw new ProductException(CartStatus.NOT_FOUND);
            }
            ProductInfo productInfo = product.get();
            Integer result = productInfo.getProductStock() - cartDTOS.getQuantity();
            //判断库存
            if (result < 0) {
                throw new ProductException(CartStatus.OUT_OF_STOCK);
            }
            productInfo.setProductStock(result);
            productRepository.save(productInfo);
            productInfoList.add(productInfo);
        }
        return productInfoList;
    }


}
