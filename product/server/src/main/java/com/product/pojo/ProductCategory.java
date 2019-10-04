package com.product.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class ProductCategory {
    @Id
    private Integer categoryId;
    private String categoryName;
    private String categoryType;
    private Date createTime;
    private Date updateTime;
}
