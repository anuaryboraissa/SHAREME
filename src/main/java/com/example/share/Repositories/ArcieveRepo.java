package com.example.share.Repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.share.Entities.Archieve;
import com.example.share.Entities.Seen;

public interface ArcieveRepo extends JpaRepository<Archieve, Long> {
@Query(value = "select * from achieve a where a.achieve_id=?1",nativeQuery = true)
Collection<Archieve> getArchieved(long id);
@Query(value = "select * from achieve a where a.std_id=?1 and a.status=?3 and a.msg_id=?2",nativeQuery = true)
Archieve getIfArchieveById(long ownid,long msgid,int status);
}
