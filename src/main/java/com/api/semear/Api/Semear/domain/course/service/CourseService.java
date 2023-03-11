package com.api.semear.Api.Semear.domain.course.service;


import com.api.semear.Api.Semear.core.exception.AuthorizationException;
import com.api.semear.Api.Semear.core.exception.ObjectNotFoundException;
import com.api.semear.Api.Semear.domain.course.model.Course;
import com.api.semear.Api.Semear.domain.course.repository.CourseRepository;
import com.api.semear.Api.Semear.domain.enums.Profile;
import com.api.semear.Api.Semear.domain.security.modal.UserSS;
import com.api.semear.Api.Semear.domain.user.model.User;
import com.api.semear.Api.Semear.domain.user.repository.UserRepository;
import com.api.semear.Api.Semear.domain.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CourseService {


    private final CourseRepository courseRepository;

    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final UserService userService;


    public Course findById(Long id){
        Optional<Course> course = this.courseRepository.findById(id);
        return course.orElseThrow(() -> new ObjectNotFoundException("Curso não encontrado!" + ", tipo" + Course.class.getName()));
    }

    public List<Course> findAllByUserId(Long userid){
        return this.courseRepository.findByUser_Id(userid);
    }

    @Transactional
    public Course create(Course course) throws AccessDeniedException {

        UserSS userSS = UserService.authenticated();
        if (Objects.isNull(userSS)) {
            throw new AuthorizationException("Acesso negado!");
        }
        UserSS userS = (UserSS) userDetailsService.loadUserByUsername(userSS.getUsername());

        if (!userS.getAuthorities().contains(new SimpleGrantedAuthority(Profile.TEACHER.getDescription()))) {
            throw new AccessDeniedException("Usuário não tem permissão para criar um curso");
        }
        User user = this.userService.findById(userSS.getId());
        var courseToSave = new Course();
        courseToSave.setUser(user);
        courseToSave.setName(course.getName());
        courseToSave.setDescription(course.getDescription());
        courseToSave.setPrice(course.getPrice());
        return this.courseRepository.save(courseToSave);
    }

    @Transactional
    public Course uptade(Course course){
        Course newCourse = findById(course.getId());
        newCourse.setDescription(course.getDescription());
        newCourse.setPrice(course.getPrice());
        return this.courseRepository.save(newCourse);
    }

}
