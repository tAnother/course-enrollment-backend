package com.redpanda.courseregs.service.mapper;

import com.redpanda.courseregs.domain.Course;
import com.redpanda.courseregs.service.dto.CourseDTO;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public CourseDTO courseToCourseDTO(Course course) {
        return CourseDTO.builder()
            .courseDescription(course.getCourseDescription())
            .courseName(course.getCourseName())
            .courseLocation(course.getCourseLocation())
            .instructorId(course.getInstructorId())
            .build();
    }
}
