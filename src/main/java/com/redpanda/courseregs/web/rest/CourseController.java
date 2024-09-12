package com.redpanda.courseregs.web.rest;

import com.redpanda.courseregs.CourseEnrollmentBackendApp;
import com.redpanda.courseregs.security.SecurityUtils;
import com.redpanda.courseregs.service.CourseService;
import com.redpanda.courseregs.service.dto.CourseDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor // for auto gen-ing the constructor
@RequestMapping("/api") // for auth interception. see SecurityConfiguration.java
public class CourseController {

    private CourseService courseService;
    private static final Logger LOG = LoggerFactory.getLogger(CourseEnrollmentBackendApp.class);

    @GetMapping(path = "/courses")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<CourseDTO> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping(path = "/student/courses")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<CourseDTO> getEnrolledCourses() {
        String username = getUserName();
        return courseService.getEnrolledCourses(username);
    }

    @PostMapping(path = "/student/course")
    @ResponseStatus(HttpStatus.CREATED)
    public void enrollStudent(@RequestBody CourseDTO course) {
        String username = getUserName();
        courseService.enrollCourse(username, course.getCourseName());
    }

    @DeleteMapping(path = "/student/course")
    @ResponseStatus(HttpStatus.OK)
    public void dropStudent(@RequestBody CourseDTO course) {
        String username = getUserName();
        courseService.dropCourse(username, course.getCourseName());
    }

    private String getUserName() {
        return SecurityUtils.getCurrentUserLogin()
            .orElseThrow(() -> {
                throw new UsernameNotFoundException("Username not found");
            });
    }
}
