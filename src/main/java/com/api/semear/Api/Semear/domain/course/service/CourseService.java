package com.api.semear.Api.Semear.domain.course.service;


import com.api.semear.Api.Semear.core.exception.ObjectNotFoundException;
import com.api.semear.Api.Semear.domain.course.model.Course;
import com.api.semear.Api.Semear.domain.course.repository.CourseRepository;
import com.api.semear.Api.Semear.domain.user.model.User;
import com.api.semear.Api.Semear.domain.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {


    private final CourseRepository courseRepository;

    private final UserService userService;

    public CourseService(CourseRepository courseRepository, UserService userService) {
        this.courseRepository = courseRepository;
        this.userService = userService;
    }

    public Course findById(Long id){
        Optional<Course> course = this.courseRepository.findById(id);
        return course.orElseThrow(() -> new ObjectNotFoundException("Curso não encontrado!" + ", tipo" + Course.class.getName()));
    }

    public List<Course> findAllByUserId(Long userid){
       List<Course> courses = this.courseRepository.findbyUser_Id(userid);
       return courses;
    }

    @Transactional
    public Course create(Course course){
        User user = this.userService.findById(course.getUser().getId());
        course.setId(null);
        course.setUser(user);
        course = this.courseRepository.save(course);
        return course;
    }

    @Transactional
    public Course uptade(Course course){
        Course newCourse = findById(course.getId());
        newCourse.setDescription(course.getDescription());
        newCourse.setPrice(course.getPrice());
        return this.courseRepository.save(newCourse);
    }

}
