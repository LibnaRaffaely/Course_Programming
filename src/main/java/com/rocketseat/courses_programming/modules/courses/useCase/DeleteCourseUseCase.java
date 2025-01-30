package com.rocketseat.courses_programming.modules.courses.useCase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rocketseat.courses_programming.modules.courses.CourseRepository;
import com.rocketseat.courses_programming.modules.courses.dto.ResponseCourseDTO;

@Service
public class DeleteCourseUseCase {
    @Autowired
    private CourseRepository courseRepository;

    public ResponseCourseDTO execute(UUID id) {
        var courseForDelete = this.courseRepository.findById(id)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("Course Not Found");
                });
        var courseResponse = ResponseCourseDTO.builder()
                .id(courseForDelete.getId())
                .name(courseForDelete.getName())
                .active(courseForDelete.isActive())
                .status(courseForDelete.getStatus())
                .category(courseForDelete.getCategory())
                .build();

        this.courseRepository.deleteById(id);

        return courseResponse;
    }
}
