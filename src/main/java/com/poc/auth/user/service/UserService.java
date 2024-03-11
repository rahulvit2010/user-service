package com.poc.auth.user.service;

import com.poc.auth.user.model.Users;
import java.util.Optional;

public interface UserService  {

  Optional<Users> findByUserName(String userName);

  Users saveUser(Users user);

  String encryptPassword(String password) throws Exception;

}
