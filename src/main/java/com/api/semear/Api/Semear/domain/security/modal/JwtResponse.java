package com.api.semear.Api.Semear.domain.security.modal;

import java.io.Serializable;

public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String token;
    private  Long id;
    private  String email;

    public JwtResponse(String token) {
        this.token = token;
        this.id = id;
        this.email = email;
    }

    public String getToken() {
        return this.token;
    }

    public Long getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }
}

