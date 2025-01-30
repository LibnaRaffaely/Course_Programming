package com.rocketseat.courses_programming.modules.Users.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rocketseat.courses_programming.modules.Users.UserRepository;
import com.rocketseat.courses_programming.modules.courses.CourseRepository;

@Service
public class ContractCourseUseCase {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    public boolean execute(UUID userId, UUID courseId) {
        var user = this.userRepository.findById(userId)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("User not found");
                });
        var course = this.courseRepository.findById(courseId)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("Course not found");
                });

        var added = user.getCoursesUser().add(course);
        if (added) {
            this.userRepository.save(user);
            return true;
        } else {
            return false;
        }

    }
}
