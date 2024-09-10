package com.redpanda.courseregs.service;

import com.redpanda.courseregs.domain.Course;
import com.redpanda.courseregs.domain.User;
import com.redpanda.courseregs.domain.UserCourse;
import com.redpanda.courseregs.repository.CourseRepository;
import com.redpanda.courseregs.repository.UserCourseRepository;
import com.redpanda.courseregs.repository.UserRepository;
import com.redpanda.courseregs.service.dto.CourseDTO;
import com.redpanda.courseregs.service.mapper.CourseMapper;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserCourseRepository userCourseRepository;
    private final UserRepository userRepository;
    private final CourseMapper courseMapper;

    public void enrollCourse(String username, String courseName) {
        // sanity check + gen ref record
        UserCourse userCourse = getUserCourse(username, courseName);
        // check if existed
        Optional<UserCourse> recordOptional = userCourseRepository.findOneByUserAndCourse(userCourse.getUser(), userCourse.getCourse());
        recordOptional.ifPresent(record -> {
            throw new IllegalArgumentException("UserCourse already exists: " + record.toString());
        });
        // enroll
        userCourseRepository.save(userCourse);
    }

    public void dropCourse(String username, String courseName) {
        // sanity check + gen ref record
        UserCourse userCourse = getUserCourse(username, courseName);
        // try deleting
        userCourseRepository.deleteByUserAndCourse(userCourse.getUser(), userCourse.getCourse());
    }

    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(course -> courseMapper.courseToCourseDTO(course)).collect(Collectors.toList());
    }

    public List<CourseDTO> getEnrolledCourses(String username) {
        Optional<User> userOptional = userRepository.findOneByLogin(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("No such user: " + username));
        List<UserCourse> usercourses = userCourseRepository.findAllByUser(user);
        return usercourses.stream().map(usercourse -> courseMapper.courseToCourseDTO(usercourse.getCourse())).collect(Collectors.toList());
    }

    // validate username & coursename
    // return the user-course mapping
    private UserCourse getUserCourse(String username, String courseName) {
        Optional<User> userOptional = userRepository.findOneByLogin(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("No such user: " + username));

        Optional<Course> courseOptional = courseRepository.findOneByCourseName(courseName);
        Course course = courseOptional.orElseThrow(() -> new IllegalArgumentException("No such course: " + courseName));

        return new UserCourse(user, course);
    }
}
