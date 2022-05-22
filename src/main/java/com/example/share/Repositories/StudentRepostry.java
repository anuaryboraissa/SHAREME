package com.example.share.Repositories;




import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.share.Entities.Requests;
import com.example.share.Entities.Student;

@Repository
public interface StudentRepostry extends JpaRepository<Student, Long>{
	Student findByEmail(String email);
	@Query(value = "select * from student except select * from student s where s.email=?1",nativeQuery = true)
	Collection<Student> findStudentsByEmail(String email);
	@Query(value = "select * from student s where s.email=?1",nativeQuery = true)
	Collection<Student> findStudentByEmail(String email);
	@Query(value = "select * from student s where s.st_id=?1",nativeQuery = true)
	Student findStById(long id);
	@Query(value = " select DISTINCT * from student s inner join requests r on r.stdfrom_id=s.st_id where r.request_status=?1 and r.stdto_id=?2",nativeQuery = true)
	 Collection<Student> findStudRequestByStatus(int status,long ownId);
	@Query(value = "select * from student except select DISTINCT * from student s where s.email=?1 or "
			+ " (s.st_id IN (select s.st_id from student s inner join requests r "
			+ "on (r.stdfrom_id=s.st_id or r.stdto_id=s.st_id) where(r.request_status=?2 or r.request_status=?3 or r.request_status=?4 or r.request_status=?5)"
			+ "and (r.stdto_id=?6 or r.stdfrom_id=?6)))",nativeQuery = true)
	 Collection<Student> findSuggestedStudRequestByStatus(String email,int status1,int status2,int status3,int status4,long ownId);
	@Query(value = " select * from student except select DISTINCT * from student s where s.email=?1 "
			+ "or (s.st_id NOT IN (select s.st_id from student s inner join "
			+ "requests r on (r.stdfrom_id=s.st_id or r.stdto_id=s.st_id) where (r.request_status=?2 and (r.stdto_id=?3 or r.stdfrom_id=?3))))",nativeQuery = true)
	Collection<Student> findSuggestedStudNewFriedsByStatus(String email,int status,long ownId);
	@Query(value = " select * from student except select DISTINCT * from student s where s.email=?1 "
			+ "or (s.st_id NOT IN (select s.st_id from student s inner join "
			+ "requests r on (r.stdfrom_id=s.st_id or r.stdto_id=s.st_id) where (r.request_status=?2 and (r.stdto_id=?3))))",nativeQuery = true)
	Collection<Student> findBlockedstudentsByStatus(String email,int status,long ownId);
	@Query(value = " select * from student except select DISTINCT * from student s where s.email=?1"
			+ " or (s.st_id NOT IN (select s.st_id from student s inner join requests r "
			+ "on (r.stdfrom_id=s.st_id or r.stdto_id=s.st_id) where (r.request_status=?2 and r.stdfrom_id=?3)))",nativeQuery = true)
	Collection<Student> findwhoConfirmFriedsByStatus(String email,int status,long ownId);
	@Query(value = "select * from student s inner join requests r on r.stdfrom_id=s.st_id where r.stdto_id=?1 and r.stdfrom_id=?2",nativeQuery = true)
	Student editRequestStatus(long toId,long fromid);
}
