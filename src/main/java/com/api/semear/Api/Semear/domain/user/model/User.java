package com.api.semear.Api.Semear.domain.user.model;

import com.api.semear.Api.Semear.domain.course.model.Course;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "tb_users")
public class User {

    public interface CreateUser {

    }

    public interface UptadeUser{

    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome não pode estar em branco")
    private String name;

    private String lastname;

    @Email(message = "E-mail inválido")
    @NotBlank(groups = CreateUser.class, message = "E-mail não pode estar em branco")
    private String email;

    @NotBlank(groups = UptadeUser.class, message = "Senha não pode estar em branco")
    @Size(min = 8, message = "Senha deve ter pelo menos 8 caracteres")
    @JsonIgnore
    private String password;

    @NotBlank(message = "Endereço não pode estar em branco")
    private String address;

    @NotBlank(message = "Cidade não pode estar em branco")
    private String city;

    @NotBlank(message = "Estado não pode estar em branco")
    private String state;

    @NotBlank(message = "CEP não pode estar em branco")
    private String CEP;

    @NotBlank(message = "Igreja não pode estar em branco")
    private String church;


    @OneToMany(mappedBy = "user")
    private List<Course> courses = new ArrayList<Course>();


}
