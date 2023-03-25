package com.api.semear.Api.Semear.domain.user.repository;

import com.api.semear.Api.Semear.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByCode(String code);


    User findByEmailAndConfirmed(String email, boolean b);
}

