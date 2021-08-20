package com.springsecuritydemo.securitydemo.security;

public enum Permission {
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write"),
    USER_READ("user:read"),
    USER_WRITE("user:write");

    private final String name;

    Permission(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
