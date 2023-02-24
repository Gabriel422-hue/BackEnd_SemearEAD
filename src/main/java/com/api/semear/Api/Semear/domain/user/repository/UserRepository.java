package com.api.semear.Api.Semear.domain.user.repository;

import com.api.semear.Api.Semear.domain.user.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
//    Optional<User> findByUsername (String username);
    List<User> findByAddress(String address);

    User findByEmail (String email);

}
