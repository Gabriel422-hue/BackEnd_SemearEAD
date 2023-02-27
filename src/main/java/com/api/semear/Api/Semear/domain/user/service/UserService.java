package com.api.semear.Api.Semear.domain.user.service;


import com.api.semear.Api.Semear.domain.user.model.User;
import com.api.semear.Api.Semear.domain.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findById(Long id){
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException(
                "Usuário não encontrado! Id: " + id + ", Tipo: " + User.class.getName()));
    }

    @Transactional
    public User create (User user) {
        user.setId(null);
        user = this.userRepository.save(user);
        return user;

    }

    @Transactional
    public User uptade (User user){
        User newUser = findById(user.getId());
        newUser.setPassword(user.getPassword());
        return this.userRepository.save(newUser);
    }



//    public UserDetails authenticate(User user){
//        UserDetails userDetails = loadUserByUsername(user.getEmail());
//        boolean senhasCoincidem = passwordEncoder.matches(user.getPassword(), userDetails.getPassword());
//        if (senhasCoincidem){
//            return userDetails;
//        }
//
//        try {
//            throw new SenhaInvalidaException("Senha inválida");
//        } catch (SenhaInvalidaException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//       User user = userRepository.findByEmail(email)
//               .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o e-mail informado"));
//       return (UserDetails) User.builder()
//               .email(user.getEmail())
//               .password(user.getPassword())
//               .build();
//    }

//    public void validarEmail(String email) throws EmailEmUsoException {
//        boolean emailEmUso = userRepository.findByEmail(email).isPresent();
//        if (emailEmUso){
//            throw new EmailEmUsoException("E-mail já está em uso: " + email);
//        }
//    }
}
