package com.rocketseat.courses_programming.modules.courses;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rocketseat.courses_programming.modules.courses.enumFiles.CourseCategory;

public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {
    // esse metodo já é gerado sozinho : Optional<CourseEntity> findById(UUID id);

    List<CourseEntity> findByName(String name);

    List<CourseEntity> findByCategory(CourseCategory category);

    List<CourseEntity> findByNameAndCategory(String name, CourseCategory category);

}
