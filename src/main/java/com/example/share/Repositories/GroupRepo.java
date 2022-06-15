package com.example.share.Repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.share.Entities.Groups;
@Repository
public interface GroupRepo extends JpaRepository<Groups, Long> {
	@Query(value = " select * from groups g inner join grp_participants p on "
			+ "p.group_id=g.group_id where p.st_id=?1",nativeQuery = true)
  Collection<Groups> getMyGroupsByStId(long ownid);
}
