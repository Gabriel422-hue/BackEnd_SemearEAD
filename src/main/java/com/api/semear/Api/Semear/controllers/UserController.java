package com.api.semear.Api.Semear.controllers;

import com.api.semear.Api.Semear.domain.user.model.User;
import com.api.semear.Api.Semear.domain.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping(value = "/users")
@Validated
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping
    public ResponseEntity<User> findById(@PathVariable Long id){
        User user = this.userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    @Validated(User.CreateUser.class)
    public ResponseEntity<Void> create (@Valid @RequestBody User user){
        this.userService.create(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @PutMapping("/{id}")
    @Validated(User.UptadeUser.class)
    public ResponseEntity<Void> update (@Valid @RequestBody User user, @PathVariable Long id){
        user.setId(id);
        this.userService.uptade(user);
        return ResponseEntity.noContent().build();
    }




}