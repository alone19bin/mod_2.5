package com.maxim.spring_security_rest_api_app.model;


public enum Role {
    USER, MODERATOR, ADMIN;

    public String getAuthority() {
        return "ROLE_" + name();
    }
}
