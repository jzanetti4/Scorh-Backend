package com.example.group.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * this is the orm class for corresponding to the table Scorhgroup in mysql
 * Created by Hangqi Yu
 * 2019-10-10 16:57
 */
@Entity
@Data
public class Scorhgroup {

  @Id
  private long id;
  private String groupName;
  private String creator;
  private Date createTime;
  private String groupCategory;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public String getCreator() {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }

  public String getGroupCategory() {
    return groupCategory;
  }

  public void setGroupCategory(String groupCategory) {
    this.groupCategory = groupCategory;
  }
}
