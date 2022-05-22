package com.example.share.Repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.share.Entities.Course;

public interface CourseRepostry extends JpaRepository<Course, Long> {
	@Query(value = "select * from course s where s.course_semister=?1",nativeQuery = true)
 public Collection<Course> findCourseSemistById(int semist);
}
