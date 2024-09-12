-- Test data used
CREATE TABLE course (
                      id bigint(20) NOT NULL AUTO_INCREMENT,
                      course_name varchar(100) NOT NULL,
                      course_location varchar(30) NOT NULL,
                      course_description varchar(200) NOT NULL,
                      instructor_id bigint(10) NOT NULL,
                      PRIMARY KEY (id),
                      UNIQUE KEY `UK_course_name` (course_name)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO `course` (`course_name`, `course_location`, `course_description`, `instructor_id`) VALUES ('Compiler',
                                                                                                       'LE540', 'Compiler design for beginer', '1');
INSERT INTO `course` (`course_name`, `course_location`, `course_description`, `instructor_id`) VALUES ('OOD',
                                                                                                       'CE2310', 'Object Oriendted Programming', '2');
INSERT INTO `course` (`course_name`, `course_location`, `course_description`, `instructor_id`) VALUES ('Operating
System', 'AC-31', 'Junior level OS', '1');

CREATE TABLE user_course (
                           id bigint(20) NOT NULL AUTO_INCREMENT,
                           user_id bigint(20) NOT NULL,
                           course_id bigint(20) NOT NULL,
                           PRIMARY KEY (id),
                           CONSTRAINT FK_user_id_user_id FOREIGN KEY (user_id) REFERENCES jhi_user (id),
                           CONSTRAINT FK_course_id_course_id FOREIGN KEY (course_id) REFERENCES course (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
