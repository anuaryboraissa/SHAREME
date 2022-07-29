package com.example.share.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.example.share.Entities.RequestTo;
import com.example.share.Entities.Composites.CompositeKeyTo;
@Repository
public interface RequestToRepo extends JpaRepository<RequestTo,CompositeKeyTo> {
	@Query(value = "select * from request_to t inner join request_from f on t.req_id=f.req_id where f.st_id=?2 and t.st_id=?1",nativeQuery = true)
   public RequestTo findReqToById(long toid,long fromid);
}
