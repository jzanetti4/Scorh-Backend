package com.user.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;



/**
 * class  mapped to the User in mysql
 * @description: User entity
 * @author: Hangqi Yu
 * @date: Created in 2019-10-10 16:21
 * @version: V1.0
 * @modified: Hangqi Yu
 */
@Data
@Entity
public class User {

  @Id
  private long id;
  private String username;
  private String password;
  private String email;
  private String role;
  private String organization;
  private String background;
  private long isActive;
  private Date createTime;;




}
