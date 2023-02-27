package com.api.semear.Api.Semear.domain.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String nome;
    private String email;
    private String password;

    public UserDTO(){

    }

    public UserDTO(String nome, String email, String password) {
        this.nome = nome;
        this.email = email;
        this.password = password;
    }
}
