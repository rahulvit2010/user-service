package com.poc.auth.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Users  extends BaseEntity{

  private static final long serialVersionUID=1;

  @Column(name="USERNAME",updatable = false)
  private String userName;

  @Column(name="PASSWORD")
  private String password;


  public static final Users of(String userName,String password)
  {
    Users user= new Users();
    user.setUserName(userName);
    user.setPassword(password);
    return user;
  }


}
