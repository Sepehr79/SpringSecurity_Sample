package com.springsecuritydemo.securitydemo.data.repo;

import com.springsecuritydemo.securitydemo.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Integer> {
    public User getByUserName(String userName);
}
