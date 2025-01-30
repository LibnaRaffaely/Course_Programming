package com.rocketseat.courses_programming.modules.teachers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.rocketseat.courses_programming.modules.courses.CourseEntity;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "Teacher_App")
public class TeacherEntity {
    private UUID id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime update;
    private List<CourseEntity> coursesManaged;

}
