package com.springsecuritydemo.securitydemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.springsecuritydemo.securitydemo.security.Permission.*;
import static com.springsecuritydemo.securitydemo.security.UserRole.*;

@Configuration
@EnableWebSecurity
public class MySecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails sepehr =
                User.builder().username("sepehr")
                .password(passwordEncoder().encode("12345"))
                        .roles(USER.name())
                        .build();

        UserDetails maryam =
                User.builder().username("maryam")
                        .password(passwordEncoder().encode("12345"))
//                        .roles(ADMIN.name())
                        .authorities(ADMIN.getGrantedAuthority())
                        .build();

        return new InMemoryUserDetailsManager(sepehr, maryam);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST,"/api/students").hasAuthority(ADMIN_WRITE.getName())
                .antMatchers("/api/students").hasAnyRole(ADMIN.name(), USER.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll()
                .and()
                .httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
