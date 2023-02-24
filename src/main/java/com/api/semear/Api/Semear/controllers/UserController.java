package com.api.semear.Api.Semear.controllers;

import com.api.semear.Api.Semear.domain.security.SecurityUtil;
import com.api.semear.Api.Semear.domain.security.modal.UserSS;
import com.api.semear.Api.Semear.domain.user.model.UserProfileApiResponse;
import com.api.semear.Api.Semear.domain.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserProfileApiResponse> getUserProfile() {
        UserSS userSS = SecurityUtil.getUserSS();
        var dto = userService.getUserProfile(userSS);
        return ResponseEntity.ok(dto);
    }
}