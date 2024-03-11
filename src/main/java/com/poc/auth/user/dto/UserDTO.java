package com.poc.auth.user.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class UserDTO {
  private String userName;
  private String password;
}
