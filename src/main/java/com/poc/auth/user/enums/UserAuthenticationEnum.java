package com.poc.auth.user.enums;

public enum UserAuthenticationEnum {
  RSA("RSA"),
  RSA_PADDING("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");

  private String value;

  private UserAuthenticationEnum(String value){this.value=value;}

  public String getValue(){
    return this.value;
  }
}
