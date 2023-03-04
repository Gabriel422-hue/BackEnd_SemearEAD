package com.api.semear.Api.Semear.domain.user.service;


import com.api.semear.Api.Semear.core.exception.AuthorizationException;
import com.api.semear.Api.Semear.core.exception.ObjectNotFoundException;
import com.api.semear.Api.Semear.domain.enums.Profile;
import com.api.semear.Api.Semear.domain.security.modal.UserSS;
import com.api.semear.Api.Semear.domain.user.model.User;
import com.api.semear.Api.Semear.domain.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    
    public User findById(Long id) {
        UserSS userSS = authenticated();
        if (!Objects.nonNull(userSS) || userSS.hasRole(Profile.ADMIN) && !id.equals(userSS.getId()))
            throw new AuthorizationException("Acesso Negado!");
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException(
                "Usuário não encontrado! Id: " + id + ", Tipo: " + User.class.getName()));
    }

    @Transactional
    public User create(User user) {
        user.setId(null);
        user.setName(user.getName());
        user.setLastname(user.getLastname());
        user.setEmail(user.getEmail());
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        user.setProfile(Stream.of(Profile.USER.getCod()).collect(Collectors.toSet()));
        user.setAddress(user.getAddress());
        user.setCity(user.getCity());
        user.setState(user.getState());
        user.setCEP(user.getCEP());
        user.setChurch(user.getChurch());
        user = this.userRepository.save(user);
        return user;

    }

    @Transactional
    public User uptade(User user) {
        User newPassword = findById(user.getId());
        newPassword.setPassword(user.getPassword());
        newPassword.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        return this.userRepository.save(newPassword);
    }

    public static UserSS authenticated(){
        try {
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch (Exception e) {
            return null;
        }
    }
}