package com.api.semear.Api.Semear.domain.user.model;

import com.api.semear.Api.Semear.domain.course.model.Course;
import com.api.semear.Api.Semear.domain.enums.Profile;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.*;
import java.util.stream.Collectors;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_users")
public class User {


    public interface CreateUser {

    }

    public interface UptadeUser{

    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "Nome não pode estar em branco")
    private String name;

    private String lastname;

    @Email(message = "E-mail inválido")
    @NotBlank(groups = CreateUser.class, message = "E-mail não pode estar em branco")
    private String email;

    @NotBlank(groups = UptadeUser.class, message = "Senha não pode estar em branco")
    @Size(min = 8, message = "Senha deve ter pelo menos 8 caracteres")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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
    @Column(nullable = false)
    private boolean confirmed;

    @Column(name = "code")
    private String code;

    @ElementCollection(fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @CollectionTable(name = "Tb_user_profile")
    private Set<Integer> profile = new HashSet<>();

    public Set<Profile> getProfile() {
        return this.profile.stream().map(Profile::toEnum).collect(Collectors.toSet());
    }

    public void addProfile(Profile profile){
        this.profile.add(profile.getCod());
    }


}
