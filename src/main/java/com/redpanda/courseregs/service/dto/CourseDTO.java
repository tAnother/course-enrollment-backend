package com.redpanda.courseregs.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder // for passing in various args in random order. see usage in mapper
@Data // getters & setters
public class CourseDTO {

    private String courseName;
    private String courseDescription;
    private String courseLocation;
    private Long instructorId;

    @JsonProperty("isEnrolled")
    private boolean isEnrolled;
}
