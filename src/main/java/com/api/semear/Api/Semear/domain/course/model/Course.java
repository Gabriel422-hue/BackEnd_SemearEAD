package com.api.semear.Api.Semear.domain.course.model;

import com.api.semear.Api.Semear.domain.user.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "tb_courses")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    @NotBlank(message = "A decrição não pode ficar em branco")
    private String description;


    @NotNull(message = "O preço não pode ficar em branco")
    private double price;


    @NotNull(message = "O nome do curso é obrigatório")
    private String name;

    @NotNull(message = "O nome do professor é obrigatório")
    private String teacher;

}
