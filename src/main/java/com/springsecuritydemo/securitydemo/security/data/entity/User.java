package com.springsecuritydemo.securitydemo.security.data.entity;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Setter
@NoArgsConstructor
@Entity
public class User implements UserDetails {

    @Id
    private int id;

    @OneToMany(mappedBy = "user")
    private Collection<Authority> authorities;

    @Column(name = "user_name", unique = true)
    private String userName;

    private String password;

    @Column(name = "account_non_Expired")
    private boolean accountNonExpired;

    @Column(name = "account_non_Locked")
    private boolean accountNonLocked;

    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired;

    private boolean enabled;

    public User(Collection<Authority> authorities,
                String userName,
                String password,
                boolean accountNonExpired,
                boolean accountNonLocked,
                boolean credentialsNonExpired,
                boolean enabled) {
        this.authorities = authorities;
        this.userName = userName;
        this.password = password;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }

    @Override
    public Collection<Authority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
