package com.example.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
public class CartDTO {
    @Autowired

    private String productID;
    private Integer quantity;
}
