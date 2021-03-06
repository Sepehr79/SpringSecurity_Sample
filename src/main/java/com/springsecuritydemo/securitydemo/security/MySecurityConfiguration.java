package com.springsecuritydemo.securitydemo.security;

import com.springsecuritydemo.securitydemo.data.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.concurrent.TimeUnit;

import static com.springsecuritydemo.securitydemo.security.Permission.*;
import static com.springsecuritydemo.securitydemo.security.UserRole.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class MySecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AuthService authService;

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
//        UserDetails sepehr =
//                User.builder().username("sepehr")
//                .password(passwordEncoder().encode("12345"))
//                        .roles(USER.name())
//                        .build();
//
//        UserDetails maryam =
//                User.builder().username("maryam")
//                        .password(passwordEncoder().encode("12345"))
////                        .roles(ADMIN.name())
//                        .authorities(ADMIN.getGrantedAuthority())
//                        .build();
//
//        return new InMemoryUserDetailsManager(sepehr, maryam);
        return authService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/api/students").hasAuthority(ADMIN_WRITE.getName())
                .antMatchers("/api/students").hasAnyRole(ADMIN.name(), USER.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().permitAll()
                .defaultSuccessUrl("/api", true)
                .and()
                .logout().permitAll()
                .and()
                .rememberMe().tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(31));

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        // No encoding password
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return charSequence.toString().equals(s);
            }
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(authService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}
