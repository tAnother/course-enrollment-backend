package com.redpanda.courseregs.service.mapper;

import com.redpanda.courseregs.domain.Course;
import com.redpanda.courseregs.service.dto.CourseDTO;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public CourseDTO courseToCourseDTO(Course course, boolean isEnrolled) {
        return CourseDTO.builder()
            .courseDescription(course.getCourseDescription())
            .courseName(course.getCourseName())
            .courseLocation(course.getCourseLocation())
            .instructorId(course.getInstructorId())
            .isEnrolled(isEnrolled)
            .build();
    }
}
