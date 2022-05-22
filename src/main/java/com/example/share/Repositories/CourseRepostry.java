package com.example.share.Repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.share.Entities.Course;
import com.example.share.Entities.Permission;

public interface CourseRepostry extends JpaRepository<Course, Long> {
	@Query(value = "select * from course s where s.course_semister=?1",nativeQuery = true)
 public Collection<Course> findCourseSemistById(int semist);
	@Query(value = "select DISTINCT * from course s inner join std_courses inn on inn.course_Id=s.course_Id where inn.st_Id=?1",nativeQuery = true)
 public Collection<Course> findCoursetById(long logid);
	@Query(value = "select * from course p where p.course_id=?1",nativeQuery = true)
 public Course findCoursById(long id);
}
