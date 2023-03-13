package com.api.semear.Api.Semear.domain.course.service;


import com.api.semear.Api.Semear.core.exception.AuthorizationException;
import com.api.semear.Api.Semear.core.exception.ObjectNotFoundException;
import com.api.semear.Api.Semear.domain.course.model.Course;
import com.api.semear.Api.Semear.domain.course.repository.CourseRepository;
import com.api.semear.Api.Semear.domain.enums.Profile;
import com.api.semear.Api.Semear.domain.security.modal.UserSS;
import com.api.semear.Api.Semear.domain.user.model.User;
import com.api.semear.Api.Semear.domain.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CourseService {


    private final CourseRepository courseRepository;
    private final UserDetailsService userDetailsService;
    private final UserService userService;


    public Course findById(Long id) {
        Course course = this.courseRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Curso não encontrado!" + ", tipo" + Course.class.getName()));
        UserSS userSS = UserService.authenticated();
        if (Objects.isNull(userSS) || !userSS.hasRole(Profile.ADMIN) && !userSS.hasRole(Profile.TEACHER) && !userHasCourse(userSS, course))
            throw new AuthorizationServiceException("Acesso negado!");
        return course;
    }

    public List<Course> findAllByUser() {
        UserSS userSS = UserService.authenticated();
        if (Objects.isNull(userSS))
            throw new AuthorizationServiceException("Acesso negado!");
        List<Course> courses = this.courseRepository.findByUser_Id(userSS.getId());
        return courses;
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
    public Course uptade(Course course) {
        Course newCourse = findById(course.getId());
        newCourse.setDescription(course.getDescription());
        newCourse.setPrice(course.getPrice());
        return this.courseRepository.save(newCourse);
    }

    private Boolean userHasCourse(UserSS userSS, Course course) {
        return course.getUser().getId().equals(userSS.getId());
    }

}
