package com.rocketseat.courses_programming.modules.courses.useCase;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocketseat.courses_programming.modules.courses.CourseEntity;
import com.rocketseat.courses_programming.modules.courses.CourseRepository;
import com.rocketseat.courses_programming.modules.courses.dto.FilterCourseDTO;
import com.rocketseat.courses_programming.modules.courses.dto.ResponseCourseDTO;

@Service
public class FilterCourseUseCase {
    @Autowired
    private CourseRepository courseRepository;

    private List<ResponseCourseDTO> changeResponse(List<CourseEntity> courses) {
        List<ResponseCourseDTO> response = new ArrayList<>();
        for (CourseEntity course : courses) {
            var responseDTO = ResponseCourseDTO.builder()
                    .id(course.getId())
                    .name(course.getName())
                    .active(course.isActive())
                    .status(course.getStatus())
                    .category(course.getCategory())
                    .build();
            response.add(responseDTO);
        }

        return response;
    }

    public List<ResponseCourseDTO> execute(FilterCourseDTO filterCourseDTO) {
        // caso seja null os atributos terão valor true
        boolean nameNull = (filterCourseDTO.getName() == null);
        boolean categoryNull = (filterCourseDTO.getCategory() == null);
        System.out.println(nameNull + "    |    " + categoryNull);

        if (nameNull && categoryNull) {
            var listCourses = this.courseRepository.findAll();
            var listCoursesUpdate = changeResponse(listCourses);
            return listCoursesUpdate;
        }

        if (nameNull) {
            filterCourseDTO.convertCategory();
            var listCourses = this.courseRepository.findByCategory(filterCourseDTO.getCategoryEnum());
            var listCoursesUpdate = changeResponse(listCourses);
            return listCoursesUpdate;
        }

        if (categoryNull) {
            var listCourses = this.courseRepository.findByNameIgnoreCase(filterCourseDTO.getName());
            var listCoursesUpdate = changeResponse(listCourses);
            return listCoursesUpdate;
        }

        filterCourseDTO.convertCategory();
        var listCourses = this.courseRepository.findByNameIgnoreCaseAndCategory(filterCourseDTO.getName(),
                filterCourseDTO.getCategoryEnum());

        var listCoursesUpdate = changeResponse(listCourses);
        return listCoursesUpdate;

    }

}
