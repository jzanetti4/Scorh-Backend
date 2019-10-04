package com.order.reponsitory;

import com.order.OrderApplicationTests;
import com.order.dataobject.OrderDetail;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import static org.junit.Assert.*;

@Component
public class OrderDetailRepositoryTest extends OrderApplicationTests {
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Test
    public void testSave() {
        OrderDetail orderDetail = OrderDetail.builder()
                .orderId("123")
                .productIcon("123")
                .productId("157875196366160022")
                .productName("皮蛋粥")
                .productPrice(0.01)
                .productQuantity(2)
                .updateTime(new Date())
                .detailId("123")
                .createTime(new Date()).build();

        OrderDetail result = orderDetailRepository.save(orderDetail);
        assertTrue(result != null);
    }

}