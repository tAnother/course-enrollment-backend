package com.redpanda.courseregs.repository;

import com.redpanda.courseregs.domain.Course;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findOneByCourseName(String courseName);
}
