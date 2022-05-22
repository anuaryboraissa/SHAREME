package com.example.share.Repositories;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.share.Entities.Student;

@Repository
public interface StudentRepostry extends JpaRepository<Student, Long>{
	Student findByEmail(String email);
}
