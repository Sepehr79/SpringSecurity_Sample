package com.springsecuritydemo.securitydemo;

import com.springsecuritydemo.securitydemo.data.entity.Authority;
import com.springsecuritydemo.securitydemo.data.entity.User;
import com.springsecuritydemo.securitydemo.data.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SecuritydemoApplicationTests {

    @Autowired
    private AuthService authService;

    @Test
    void contextLoads() {
        Authority authority = new Authority("ROLE_ADMIN");

        User user = new User();
        user.setUserName("sepehr");
        user.setPassword("1234");
        user.addAuthority(authority);

        authority.setUser(user);

        authService.saveUser(user);
    }

}
