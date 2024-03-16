package com.poc.auth.user.controller;

import com.poc.auth.user.dto.UserDTO;
import com.poc.auth.user.model.Users;
import com.poc.auth.user.service.UserService;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/user")
public class UserController {

  @Autowired
  UserService userService;


  @PostMapping
  public ResponseEntity<?> saveUser(@RequestBody UserDTO dto) {
    try {
      if (userService.findByUserName(dto.getUserName()).isPresent()) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate UserName");
      }
      Users user = Users.of(dto.getUserName(), userService.encryptPassword(dto.getPassword()));
      userService.saveUser(user);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    } catch (Exception ex) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("An unexpected error occurred at server side, please contact technical support");
    }
  }

  @GetMapping(value ="/{userName}")
  public ResponseEntity<?> getUser(@PathVariable("userName") String userName)
  {
    Optional<Users> optUser= userService.findByUserName(userName);
    if(optUser.isPresent()) {
      return  ResponseEntity.ok(optUser.get());
    }
    return ResponseEntity.notFound().build();
  }

}
