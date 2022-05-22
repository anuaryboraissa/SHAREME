package com.example.share.Services;

import com.example.share.Entities.Course;

import com.example.share.Entities.University;

public interface CollegeService {
	Course saveCourses(Course course);
	University saveUniversity(University uni);
}
