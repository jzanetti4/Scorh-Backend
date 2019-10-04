package com.product.pojo;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class ProductInfo {
    @Id
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productStock;
    private String productDescription;
    private String productIcon;
    private Integer productStatus;
    private String categoryType;
    private Date createTime;
    private Date updateTime;
}
