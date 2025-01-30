package com.rocketseat.courses_programming.modules.teachers;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<TeacherEntity, UUID> {

}
