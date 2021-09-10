package com.springsecuritydemo.securitydemo.data.service;

import com.springsecuritydemo.securitydemo.data.entity.User;
import com.springsecuritydemo.securitydemo.data.repo.AuthorityRepo;
import com.springsecuritydemo.securitydemo.data.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final UserRepo userRepo;
    private final AuthorityRepo authorityRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.getByUserName(username);
    }

    public User saveUser(User user){
        return userRepo.save(user);
    }
}
