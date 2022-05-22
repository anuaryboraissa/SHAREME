package com.example.share.Services;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.share.Controller.DTOS.StudentDTO;
import com.example.share.Entities.Student;

public interface UniversityService extends UserDetailsService{
 Student saveUser(
		 StudentDTO studentDTO
		 );
 Student saveUserWithoutDTO(
		 Student student
		 );
}
