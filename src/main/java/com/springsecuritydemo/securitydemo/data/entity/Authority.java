package com.springsecuritydemo.securitydemo.data.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@AllArgsConstructor
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user")
    private User user;

    @NonNull
    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }

    public User getUser() {
        return user;
    }
}
