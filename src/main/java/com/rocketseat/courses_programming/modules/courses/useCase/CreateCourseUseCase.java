package com.rocketseat.courses_programming.modules.courses.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocketseat.courses_programming.modules.courses.CourseEntity;
import com.rocketseat.courses_programming.modules.courses.CourseRepository;

@Service
public class CreateCourseUseCase {
    @Autowired
    private CourseRepository courseRepository;

    public CourseEntity execute(CourseEntity courseEntity) {
        System.out.println("Curso: " + courseEntity.getName());
        System.out.println(courseEntity.getCategory());
        var result = this.courseRepository.save(courseEntity);
        return result;
    }

}
