package com.springsecuritydemo.securitydemo.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.springsecuritydemo.securitydemo.security.Permission.*;

public enum UserRole {
    ADMIN(Sets.newHashSet(ADMIN_READ, ADMIN_WRITE, USER_READ, USER_WRITE)), USER(Sets.newHashSet());

    private final Set<Permission> permissions;

    UserRole(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthority(){
        Set<SimpleGrantedAuthority> collect = getPermissions().stream().map(
                permission -> new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toSet());

        collect.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return collect;
    }
}
