package com.order.convert;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.order.dataobject.OrderDetail;
import com.order.dto.OrderDTO;
import com.order.enums.OrderResult;
import com.order.exception.OrderException;
import com.order.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FormToDTO {
    public static OrderDTO transferFormToDTO(OrderForm orderForm) throws OrderException {
        List<OrderDetail> orderDetailList;
        try {
            //使用Gson转换
            Gson gson = new Gson();
            orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());
        } catch (Exception e) {
            log.error("json转换出错，string={}", orderForm);
            throw new OrderException(OrderResult.ERROR);
        }

        OrderDTO orderDTO = OrderDTO.builder()
                .buyerAddress(orderForm.getAddress())
                .buyerName(orderForm.getName())
                .buyerPhone(orderForm.getPhone())
                .buyerOpenid(orderForm.getOpenid())
                .orderDetailList(orderDetailList).build();
        return orderDTO;
    }
}
