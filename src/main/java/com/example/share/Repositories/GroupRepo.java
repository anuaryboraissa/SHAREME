package com.example.share.Repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.share.Entities.Groups;
@Repository
public interface GroupRepo extends JpaRepository<Groups, Long> {
	@Query(value = " select * from groups g inner join grp_participants p on p.group_id=g.group_id where p.st_id=?1",nativeQuery = true)
  Collection<Groups> getMyGroupsByStId(long ownid);
	@Query(value = "select distinct g.group_name,g.group_id,g.group_capacity,g.group_desc,g.group_icon,g.group_name from groups g inner join grp_messages gm on gm.group_id=g.group_id where gm.msg_id in (select m.msg_id from message m inner join msg_archieved ma on ma.msg_id=m.msg_id inner join msg_deleted md on md.msg_id=m.msg_id inner\r\n" + 
			"join std_archieve sa on sa.msg_id=m.msg_id where sa.st_id=?1 and  ma.achieve_id=?2 and md.delete_id=?3)",nativeQuery = true)
  Collection<Groups> getAllGrpArchievedById(long ownid,long status1,long status2);
}

