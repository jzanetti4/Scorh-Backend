package com.user.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

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
