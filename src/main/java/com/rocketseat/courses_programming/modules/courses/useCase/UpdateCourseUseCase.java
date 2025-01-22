package com.rocketseat.courses_programming.modules.courses.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rocketseat.courses_programming.modules.courses.CourseRepository;
import com.rocketseat.courses_programming.modules.courses.dto.ResponseCourseDTO;
import com.rocketseat.courses_programming.modules.courses.dto.UpdateCourseDTO;

@Service
public class UpdateCourseUseCase {
    @Autowired
    private CourseRepository courseRepository;

    public ResponseCourseDTO execute(UpdateCourseDTO updateCourseDTO) {
        var courseForUpdate = this.courseRepository.findById(updateCourseDTO.getId())
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("Course not found!");
                });

        // fazer a alteração do course

        if (updateCourseDTO.getName() != null) {
            courseForUpdate.setName(updateCourseDTO.getName());
        }
        if (updateCourseDTO.getCategory() != null) {
            updateCourseDTO.convertCategory();
            courseForUpdate.setCategory(updateCourseDTO.getCategoryEnum());
        }

        // salvar o course
        this.courseRepository.save(courseForUpdate);

        // retornar ele como DTO
        var responseDTO = ResponseCourseDTO.builder()
                .id(courseForUpdate.getId())
                .name(courseForUpdate.getName())
                .active(courseForUpdate.isActive())
                .created_at(courseForUpdate.getCreated_at())
                .update_at(courseForUpdate.getUpdate_at())
                .status(courseForUpdate.getStatus())
                .category(courseForUpdate.getCategory())
                .build();

        return responseDTO;
    }
}
