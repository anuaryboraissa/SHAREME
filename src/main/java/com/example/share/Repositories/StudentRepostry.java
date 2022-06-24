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
	@Query(value = "select * from student s inner join left_group lg on lg.std_id=s.st_id where lg.grp_id=?1",nativeQuery = true)
	Collection<Student> findStudentLeftGroupById(long grp);
	@Query(value = "select * from student s where s.st_id=?1",nativeQuery = true)
	Student findStById(long id);
	@Query(value = "select * from student s inner join grp_lefts g on g.st_id=s.st_id inner join std_left sl on sl.st_id=s.st_id where g.group_id=?2 and sl.st_id=?1 and sl.left_id=?3",nativeQuery = true)
	Student findStudentToLeftGrpById(long ownid,long grp,long leftid);
	@Query(value = " select DISTINCT * from student s inner join requests r on r.stdfrom_id=s.st_id where r.request_status=?1 and r.stdto_id=?2",nativeQuery = true)
	 Collection<Student> findStudRequestByStatus(int status,long ownId);
	@Query(value = "select * from student except select DISTINCT * from student s where s.email=?1 or "
			+ " (s.st_id IN (select s.st_id from student s inner join requests r "
			+ "on (r.stdfrom_id=s.st_id or r.stdto_id=s.st_id) where(r.request_status=?2 or r.request_status=?3 or r.request_status=?4 or r.request_status=?5)"
			+ "and (r.stdto_id=?6 or r.stdfrom_id=?6)))",nativeQuery = true)
	 Collection<Student> findSuggestedStudRequestByStatus(String email,int status1,int status2,int status3,int status4,long ownId);
	@Query(value = "select * from student except select DISTINCT * from student s where s.email=?1 "
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
	@Query(value = "select distinct s.st_id,s.st_age,s.email,s.st_f_name,s.progr_id,s.st_l_name,s.st_password,s.perm_id,s.st_photo from student s inner join msgfrom_std f on f.std_id=s.st_id where f.msg_id in (select f.msg_id from message s inner join msgfrom_std f on f.msg_id=s.msg_id inner join msg_to_std t on t.msg_id=s.msg_id inner join msg_deleted d on d.msg_id=s.msg_id inner join msg_archieved a on a.msg_id=s.msg_id left join std_delete sd on sd.msg_id=s.msg_id where t.std_id=?1 and (d.delete_id=?2 or not sd.st_id=?1) and a.achieve_id=?3)",nativeQuery = true)
	Collection<Student> findmgsWhoSendToMeById(long ownId,int status,int status1);
	@Query(value = "select distinct s.st_id,s.st_age,s.email,s.st_f_name,s.progr_id,s.st_l_name,s.st_password,s.perm_id,s.st_photo from student s inner join msgfrom_std f on f.std_id=s.st_id where f.msg_id in (select f.msg_id from message s inner join msgfrom_std f on f.msg_id=s.msg_id inner join msg_to_std t on t.msg_id=s.msg_id inner join msg_deleted d on d.msg_id=s.msg_id inner join msg_archieved a on a.msg_id=s.msg_id where t.std_id=?1 and d.delete_id=?2 and a.achieve_id=?3) and s.st_id in (select r.st_id from student r where match(r.st_f_name,r.st_l_name,email) against (?4))",nativeQuery = true)
	Collection<Student> searchmgsWhoSendToMeByKey(long ownId,int status,int status1,String key);
	@Query(value = "select distinct s.st_id,s.st_age,s.email,s.st_f_name,s.progr_id,s.st_l_name,s.st_password,s.perm_id,s.st_photo from student s inner join msgfrom_std f on f.std_id=s.st_id where f.msg_id in (select f.msg_id from message s inner join msgfrom_std f on f.msg_id=s.msg_id inner join msg_to_std t on t.msg_id=s.msg_id inner join msg_deleted d on d.msg_id=s.msg_id inner join msg_archieved a on a.msg_id=s.msg_id where t.std_id=?1 and d.delete_id=?2 and a.achieve_id=?3)",nativeQuery = true)
	Collection<Student> findmgsArchievedById(long ownId,int status,int status1);
	@Query(value = "select * from student s inner join grp_admins g on g.st_id=s.st_id where g.group_id=?1",nativeQuery = true)
	Collection<Student> findGoupAdminsdById(long grpid);
	@Query(value = "select * from student s where not s.st_id=?1",nativeQuery = true)
	Collection<Student> findExceptsStdById(long ownid);
	@Query(value = "select * from student h where h.st_id in (select r.st_id from student r where match(r.st_f_name,r.st_l_name,email) against (?4)) except select DISTINCT * from student s where s.email=?1 or (s.st_id NOT IN (select s.st_id from student s inner join requests r on (r.stdfrom_id=s.st_id or r.stdto_id=s.st_id) where (r.request_status=?2 and (r.stdto_id=?3 or r.stdfrom_id=?3))))",nativeQuery = true)
	Collection<Student> searchByKey(String email,int status,long ownId,String keyword);
	@Query(value = "select * from student h where h.st_id in (select r.st_id from student r where match(r.st_f_name,r.st_l_name,email) against (?4)) except select DISTINCT * from student s where s.email=?1 or (s.st_id NOT IN (select s.st_id from student s inner join requests r on (r.stdfrom_id=s.st_id or r.stdto_id=s.st_id) where (r.request_status=?2 and r.stdto_id=?3)))",nativeQuery = true)
	Collection<Student> searchBlockeByKey(String email,int status,long ownId,String keyword);
	@Query(value = "select * from student h where h.st_id in (select r.st_id from student r where match(r.st_f_name,r.st_l_name,email) against (?1)) except select DISTINCT * from student s where s.email=?2 or (s.st_id IN (select s.st_id from student s inner join requests r on (r.stdfrom_id=s.st_id or r.stdto_id=s.st_id) where(r.request_status=?3 or r.request_status=?4 or r.request_status=?5 or r.request_status=?6) and (r.stdto_id=?7 or r.stdfrom_id=?7)))",nativeQuery = true)
	Collection<Student> searchSuggestedByKey(String key,String email,int status1,int status2,int status3,int status4,long ownId);
}
