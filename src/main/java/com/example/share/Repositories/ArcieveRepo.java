package com.example.share.Repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.share.Entities.Archieve;

public interface ArcieveRepo extends JpaRepository<Archieve, Long> {
@Query(value = "select * from achieve a where a.achieve_id=?1",nativeQuery = true)
Collection<Archieve> getArchieved(long id);
}
