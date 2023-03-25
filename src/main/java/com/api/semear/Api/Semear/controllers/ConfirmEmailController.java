package com.api.semear.Api.Semear.controllers;


import com.api.semear.Api.Semear.domain.user.model.User;
import com.api.semear.Api.Semear.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/confirmar-email")
public class ConfirmEmailController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{code}")
    public ResponseEntity<String> confirmEmail(@PathVariable("code") String code){
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByCode(code));
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setConfirmed(true);
            userRepository.save(user);
            return ResponseEntity.ok("E-mail confirmado com sucesso!");
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
