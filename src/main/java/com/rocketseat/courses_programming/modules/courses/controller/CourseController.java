package com.rocketseat.courses_programming.modules.courses.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rocketseat.courses_programming.modules.courses.CourseEntity;
import com.rocketseat.courses_programming.modules.courses.dto.FilterCourseDTO;
import com.rocketseat.courses_programming.modules.courses.useCase.CreateCourseUseCase;
import com.rocketseat.courses_programming.modules.courses.useCase.DeleteCourseUseCase;
import com.rocketseat.courses_programming.modules.courses.useCase.FilterCourseUseCase;
import com.rocketseat.courses_programming.modules.courses.useCase.ToggleActiveCourseUseCase;

import jakarta.validation.Valid;
import lombok.var;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CreateCourseUseCase createCourseUseCase;

    @Autowired
    private FilterCourseUseCase filterCourseUseCase;

    @Autowired
    private DeleteCourseUseCase deleteCourseUseCase;

    @Autowired
    private ToggleActiveCourseUseCase toggleActiveCourseUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CourseEntity courseEntity) {
        try {
            var result = this.createCourseUseCase.execute(courseEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<Object> filterCourses(String name, String category) {
        try {
            var courseForFilter = FilterCourseDTO.builder()
                    .name(name)
                    .category(category)
                    .build();

            System.out.println("TESTE");
            var listCourses = this.filterCourseUseCase.execute(courseForFilter);
            return ResponseEntity.ok().body(listCourses);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCourse(@PathVariable UUID id) {
        try {
            var response = this.deleteCourseUseCase.execute(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<Object> toggleAtive(@PathVariable UUID id) {
        try {
            var response = this.toggleActiveCourseUseCase.execute(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }
}
