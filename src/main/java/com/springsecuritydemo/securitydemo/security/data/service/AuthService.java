package com.springsecuritydemo.securitydemo.security.data.service;

import com.springsecuritydemo.securitydemo.security.data.repo.AuthorityRepo;
import com.springsecuritydemo.securitydemo.security.data.repo.UserRepo;
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
}
