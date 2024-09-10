package com.redpanda.courseregs.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "course")
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_location")
    private String courseLocation;

    @Column(name = "course_description")
    private String courseDescription;

    @Column(name = "instructor_id")
    private Long instructorId;
}
