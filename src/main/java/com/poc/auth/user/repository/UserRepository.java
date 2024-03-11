package com.poc.auth.user.repository;

import com.poc.auth.user.model.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<Users,Long> {

  Optional<Users> findByUserName(String userName);

}
