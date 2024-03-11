package com.poc.auth.user.service.impl;

import com.poc.auth.user.model.Users;
import com.poc.auth.user.repository.UserRepository;
import com.poc.auth.user.service.UserService;
import com.poc.auth.user.util.RsaEncryptorDecryptor;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Value("${encryption.secret-key.public-key}")
  private String publicKey;
  @Autowired
  UserRepository userRepository;

  @Override
  public Optional<Users> findByUserName(String userName) {
    return userRepository.findByUserName(userName);
  }

  @Override
  public Users saveUser(Users user) {
    return  userRepository.save(user);
  }

  @Override
  public String encryptPassword(String password) throws Exception {
    return RsaEncryptorDecryptor.encrypt(password,publicKey);
  }
}
