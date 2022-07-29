package com.example.share.Repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.share.Entities.Groups;
@Repository
public interface GroupRepo extends JpaRepository<Groups, Long> {
	@Query(value = "select * from groups g inner join grp_participantss p on p.group_id=g.group_id where p.st_id=?1",nativeQuery = true)
  Collection<Groups> getMyGroupsByStId(long ownid);
	@Query(value = "select distinct g.group_name,g.group_id,g.group_capacity,g.group_desc,g.group_icon,g.group_name from groups g inner join grp_messages",nativeQuery = true)
  Collection<Groups> getAllGrpArchievedById();
}

