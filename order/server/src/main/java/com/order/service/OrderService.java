package com.order.service;

import com.example.client.ProductClient;

import com.example.common.CartDTO;
import com.example.common.ProductInfo;
import com.order.dataobject.OrderDetail;
import com.order.dataobject.OrderMaster;
import com.order.dto.OrderDTO;
import com.order.enums.OrderStatusEnum;
import com.order.enums.PayStatusEnum;
import com.order.enums.ResultEnum;
import com.order.exception.OrderException;
import com.order.reponsitory.OrderDetailRepository;
import com.order.reponsitory.OrderMasterReponsitory;
import com.order.util.KeyUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderService {
    @Autowired
    OrderMasterReponsitory orderMasterReponsitory;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductClient productClient;


    /**
     * 参数校验
     * 查询商品价格（调用商品服务）
     * 算总价
     * 扣库存
     * 入库
     * ```
     *
     * @return
     */
    @SuppressWarnings("checkstyle:JavadocMethod")

    public OrderMaster saveOrder(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();
        List<String> productIDList = orderDTO.getOrderDetailList().stream()
                .map(OrderDetail::getProductId)
                .collect(Collectors.toList());

        //获取商品详情
        List<ProductInfo> productInfoList = productClient.listProductsByList(productIDList);


        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            for (ProductInfo productInfo : productInfoList) {
                if (orderDetail.getProductId().equals(productInfo.getProductId())) {
                    orderAmount = productInfo.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmount)
                    ;
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    orderDetail.setCreateTime(new Timestamp(new Date().getTime()));
                    orderDetail.setUpdateTime(new Timestamp(new Date().getTime()));
                    orderDetailRepository.save(orderDetail);

                }
            }
        }

        List<CartDTO> decreaseStockInputList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decStock(decreaseStockInputList);


        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderStatus(OrderStatusEnum.SUCCESS.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderId("123");
        orderMaster.setUpdateTime(new Date());
        orderMaster.setCreateTime(new Date());
        log.info("the orderMaster is,string={}", orderMaster);
        OrderMaster result = orderMasterReponsitory.save(orderMaster);
        log.info("the result is {},", result);
        return result;
    }


    public OrderDTO finish(String orderId) throws OrderException {
        //1. 先查询订单
        Optional<OrderMaster> orderMasterOptional = orderMasterReponsitory.findById(orderId);
        if (!orderMasterOptional.isPresent()) {
            throw new OrderException(ResultEnum.ORDER_NOT_EXIST);
        }

        //2. 判断订单状态
        OrderMaster orderMaster = orderMasterOptional.get();
        if (OrderStatusEnum.NEW.getCode() != orderMaster.getOrderStatus()) {
            throw new OrderException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //3. 修改订单状态为完结
        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderMasterReponsitory.save(orderMaster);

        //查询订单详情
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new OrderException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }


}
