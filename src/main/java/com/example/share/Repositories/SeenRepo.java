package com.example.share.Repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.share.Entities.Seen;

public interface SeenRepo extends JpaRepository<Seen, Long> {
	@Query(value = "select * from seen a where a.seen_id=?1",nativeQuery = true)
	Collection<Seen> getSeen(long id);
	@Query(value = "select * from seen s where std_id=?1 and msg_id=?2",nativeQuery = true)
	Seen getIfSeenById(long ownid,long msgid);
}
