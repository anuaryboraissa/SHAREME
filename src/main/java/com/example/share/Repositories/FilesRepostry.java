package com.example.share.Repositories;

import java.util.Collection;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.share.Entities.Files;

public interface FilesRepostry extends JpaRepository<Files, Long> {
    @Query(value = "select * from resources r inner join file_permissions inn on inn.file_id=r.rsc_id where inn.permit_id=?1",nativeQuery = true)
  public Collection<Files> findFilePermById(long permid);
	@Query(value = "select DISTINCT * from resources r  where r.file_type=?1 and r.std_id=?2",nativeQuery = true)
  public Collection<Files> findTotalFilesByType(String type,long ownid);
	@Query(value = "select * from resources r inner join file_permissions inn on inn.file_id=r.rsc_id"
			+ " where inn.permit_id=?1 and not r.std_id=?2",nativeQuery = true)
	  public Collection<Files> findNotOwnerFileById(long permid,long stdid);
	@Query(value = "select * from resources r inner join file_permissions inn on inn.file_id=r.rsc_id"
			+ " where inn.permit_id=?1 and r.std_id=?2",nativeQuery = true)
	  public Collection<Files> findHistryFilesById(long permid,long stdid);
	@Query(value = " select DISTINCT * from resources EXCEPT "
			+ "select * from resources r  where (r.file_type=?1 or r.file_type=?2) or not r.std_id=?3",nativeQuery = true)
	  public Collection<Files> findTotalOthersByType(String type1,String type2,long ownid);
	@Query(value = "select *from resources r inner join file_tagged f on f.file_id=r.rsc_id where f.std_id=?1",nativeQuery = true)
	  public Collection<Files> findTaggedById(long ownid);
	@Query(value = "select * from resources r inner join file_tagged f on f.file_id=r.rsc_id where "
			+ "r.file_type=?1 and f.std_id=?2",nativeQuery = true)
	public Collection<Files> findByIdTagged(String type,long ownid);
	@Query(value = "select * from resources r inner join file_tagged f on f.file_id=r.rsc_id where "
			+ "not (r.file_type=?1 or r.file_type=?2) and f.std_id=?3",nativeQuery = true)
 public Collection<Files> findTotalOthersTaggedByType(String type1,String type2,long ownid);

}
