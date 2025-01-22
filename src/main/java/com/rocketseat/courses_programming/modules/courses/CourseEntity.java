package com.rocketseat.courses_programming.modules.courses;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.rocketseat.courses_programming.modules.courses.enumFiles.CourseCategory;
import com.rocketseat.courses_programming.modules.courses.enumFiles.StatusCourse;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity(name = "Course")
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    private String name;

    private boolean active;

    @CreationTimestamp
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime update_at;

    @Enumerated(EnumType.STRING)
    private StatusCourse status;

    @Enumerated(EnumType.STRING)
    private CourseCategory category;

    public void alternActive() {
        this.active = !this.active;
    }
}
