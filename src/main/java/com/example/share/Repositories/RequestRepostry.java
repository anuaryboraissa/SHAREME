package com.example.share.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.share.Entities.Requests;

public interface RequestRepostry extends JpaRepository<Requests, Long> {
@Query(value = "select * from requests r where r.stdto_id=?1 and r.stdfrom_id=?2 and r.request_status=?3",nativeQuery = true)
  Requests selectRequestById(long to,long from,int status);
}
