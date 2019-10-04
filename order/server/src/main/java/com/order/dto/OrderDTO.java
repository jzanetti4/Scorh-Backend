package com.order.dto;

import com.order.dataobject.OrderDetail;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;
import java.util.List;


@Data
@Builder
public class OrderDTO {

    //没有


    //name
    private String buyerOpenid;

    private String buyerName;
    //phone

    private String buyerPhone;
    //address

    private String buyerAddress;
    //opneid

//    private String buyerOpenid;
    //null


    //items: [{
    //    productId: "1423113435324",
    //    productQuantity: 2 //购买数量

    private List<OrderDetail> orderDetailList;


}
