package com.api.semear.Api.Semear.domain.user.model;


import com.api.semear.Api.Semear.core.validation.UserInsert;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@UserInsert
public class UserApiRequest implements Serializable {


    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 2, max = 120, message = "O nome deve ter entre 2 a 120 caracteres")
    private String name;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String password;
    @NotEmpty(message = "Preenchimento obrigatório")
    private String address;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String city;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String state;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String CEP;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String church;
}
