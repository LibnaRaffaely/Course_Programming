package com.rocketseat.courses_programming.modules.courses.dto;

import java.util.UUID;

import com.rocketseat.courses_programming.modules.courses.enumFiles.CourseCategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCourseDTO {

    private UUID id;
    private String name;
    private String category;
    private CourseCategory categoryEnum;

    public void convertCategory() {
        this.categoryEnum = CourseCategory.valueOf(this.category.toUpperCase());
    }

}
