package com.redpanda.courseregs.repository;

import com.redpanda.courseregs.domain.Course;
import com.redpanda.courseregs.domain.User;
import com.redpanda.courseregs.domain.UserCourse;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourse, Long> {
    Optional<UserCourse> findOneByUserAndCourse(User user, Course course);

    @Transactional // modifying behavior must be transactional
    void deleteByUserAndCourse(User user, Course course);

    List<UserCourse> findAllByUser(User user);
}
