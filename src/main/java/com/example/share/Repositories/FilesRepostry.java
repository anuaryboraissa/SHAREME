package com.example.share.Repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.share.Entities.Files;

public interface FilesRepostry extends JpaRepository<Files, Long> {
    @Query(value = "select * from resources r inner join file_permissions inn on inn.file_id=r.rsc_id where inn.permit_id=?1",nativeQuery = true)
  public Collection<Files> findFilePermById(long permid);
	@Query(value = "select DISTINCT * from resources r  where r.file_type=?1",nativeQuery = true)
  public Collection<Files> findTotalFilesByType(String type);
	@Query(value = "select * from resources r inner join file_permissions inn on inn.file_id=r.rsc_id"
			+ " where inn.permit_id=?1 and r.course_id NOT IN (select c.course_id from "
			+ "course c inner join std_courses sc on sc.course_id=c.course_id where sc.st_id=?2)",nativeQuery = true)
	  public Collection<Files> findNotOwnerFileById(long permid,long stdid);
	@Query(value = " select DISTINCT * from resources EXCEPT "
			+ "select * from resources r  where r.file_type=?1 or r.file_type=?2",nativeQuery = true)
	  public Collection<Files> findTotalOthersByType(String type1,String type2);
}
