package com.redpanda.courseregs.web.rest;

import com.redpanda.courseregs.security.SecurityUtils;
import com.redpanda.courseregs.service.CourseService;
import com.redpanda.courseregs.service.dto.CourseDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor // for auto gen-ing the constructor
@RequestMapping("/api") // for auth interception. see SecurityConfiguration.java
public class CourseController {

    private CourseService courseService;

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

    @PostMapping(path = "/student/course/{courseName}")
    @ResponseStatus(HttpStatus.CREATED)
    public void enrollStudent(@PathVariable String courseName) {
        String username = getUserName();
        courseService.enrollCourse(username, courseName);
    }

    @DeleteMapping(path = "/student/course/{courseName}")
    @ResponseStatus(HttpStatus.OK)
    public void dropStudent(@PathVariable String courseName) {
        String username = getUserName();
        courseService.dropCourse(username, courseName);
    }

    private String getUserName() {
        return SecurityUtils.getCurrentUserLogin()
            .orElseThrow(() -> {
                throw new UsernameNotFoundException("Username not found");
            });
    }
}
