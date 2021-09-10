package com.springsecuritydemo.securitydemo.data.repo;

import com.springsecuritydemo.securitydemo.data.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepo extends JpaRepository<Authority, Integer> {

}
