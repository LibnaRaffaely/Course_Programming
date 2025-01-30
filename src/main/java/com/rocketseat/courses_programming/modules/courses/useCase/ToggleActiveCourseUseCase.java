package com.rocketseat.courses_programming.modules.courses.useCase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rocketseat.courses_programming.modules.courses.CourseRepository;
import com.rocketseat.courses_programming.modules.courses.dto.ResponseCourseDTO;

@Service
public class ToggleActiveCourseUseCase {
    @Autowired
    private CourseRepository courseRepository;

    public ResponseCourseDTO execute(UUID id) {
        // conferir se tem esse curso com id
        var courseUpdate = this.courseRepository.findById(id)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("Course not found");
                });

        // alternar o active
        courseUpdate.alternActive();

        this.courseRepository.save(courseUpdate);

        // response
        var courseResponse = ResponseCourseDTO.builder()
                .id(courseUpdate.getId())
                .name(courseUpdate.getName())
                .active(courseUpdate.isActive())
                .status(courseUpdate.getStatus())
                .category(courseUpdate.getCategory())
                .build();

        return courseResponse;

    }
}
