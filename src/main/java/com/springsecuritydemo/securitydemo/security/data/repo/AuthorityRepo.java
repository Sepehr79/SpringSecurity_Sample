package com.springsecuritydemo.securitydemo.security.data.repo;

import com.springsecuritydemo.securitydemo.security.data.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepo extends JpaRepository<Authority, Integer> {

}
