package com.rocketseat.courses_programming.modules.courses.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.rocketseat.courses_programming.modules.courses.enumFiles.CourseCategory;
import com.rocketseat.courses_programming.modules.courses.enumFiles.StatusCourse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCourseDTO {

    private UUID id;
    private String name;
    private boolean active;
    private LocalDateTime created_at;
    private LocalDateTime update_at;
    private StatusCourse status;
    private CourseCategory category;

}
