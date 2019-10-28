/**
 * package info
 */
package com.order.controller;

import com.order.convert.FormToDTO;
import com.order.dataobject.OrderMaster;
import com.order.dto.OrderDTO;
import com.order.enums.OrderResult;
import com.order.exception.OrderException;
import com.order.form.OrderForm;
import com.order.service.OrderService;
import com.order.util.ResultUtil;
import com.order.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;

/**
 * Done参数校验+异常处理
 * Done form转换dto
 * Todo查询商品价格（调用商品服务）
 * product服务提供查询商品价格
 * <p>
 * Todo算总价
 * Todo扣库存
 * Done入库
 * ```
 */

@RestController
@RequestMapping(value = "/order")
@Slf4j
public class OrderController {


    @Autowired
    private OrderService orderService;

    /**
     * @param orderForm
     * @param bindingResult,OrderException
     * @return ResultVO
     * @throws OrderException
     */

    @PostMapping(value = "/create")
    public ResultVO creat(@Valid OrderForm orderForm, BindingResult bindingResult)
            throws OrderException {

        //校验
        if (bindingResult.hasErrors()) {
            log.error("表单信息错误,string={}", orderForm);
            throw new OrderException(OrderResult.ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
//            return ResultUtil.falure(bindingResult.getFieldError().getDefaultMessage());
        }
        //form转换为dto
        OrderDTO orderDTO = null;
        try {
            orderDTO = FormToDTO.transferFormToDTO(orderForm);
            if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
                log.error("购物车为空");
                throw new OrderException(OrderResult.EMPTY_CART);
            }
        } catch (OrderException e) {
            log.error("orderForm转换orderDTO错误,string={}", orderForm);
        }


        log.info("orderDTO is,String={}", orderDTO);
        OrderMaster orderMaster = orderService.saveOrder(orderDTO);
        HashMap<String, String> data = new HashMap<>();
        data.put("orderID", orderMaster.getOrderId());
        return ResultUtil.success(data);
    }

    @PostMapping("/finish")
    public ResultVO finish(@RequestParam("orderId") String orderId) throws OrderException {
        log.info("orderId is{},",orderId);
        return ResultUtil.success(orderService.finish(orderId));
    }
}
