package com.api.semear.Api.Semear.controllers;

import com.api.semear.Api.Semear.domain.user.model.User;
import com.api.semear.Api.Semear.domain.user.repository.UserRepository;
import com.api.semear.Api.Semear.domain.user.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/users")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/info")
    public ResponseEntity<Map<String, String>> getAuthenticatedUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String authenticatedUserEmail = authentication.getName();
        User user = userRepository.findByEmail(authenticatedUserEmail);
        if (user == null) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        String authenticatedUserName = user.getName();
        Map<String, String> response = new HashMap<>();
        response.put("authenticatedUserName", authenticatedUserName);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable UUID id) {
        User user = this.userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    @Validated(User.CreateUser.class)
    public ResponseEntity<Void> create(@Valid @RequestBody User user) throws MessagingException {
        this.userService.create(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @PutMapping("/{id}")
    @Validated(User.UptadeUser.class)
    public ResponseEntity<Void> update(@Valid @RequestBody User user, @PathVariable UUID id) {
        user.setId(id);
        this.userService.uptade(user);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{email}/updateToTeacher")
    public ResponseEntity<?> updateToTeacher(@PathVariable("email") String email) {
        User updatedUser = userService.updateToTeacher(email);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}