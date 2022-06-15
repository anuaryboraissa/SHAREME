package com.example.share.Repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.share.Entities.Delete;

public interface DeleteRepo extends JpaRepository<Delete, Long> {
	@Query(value = "select * from deletee a where a.delete_id=?1",nativeQuery = true)
	Collection<Delete> getdeleted(long id);
}
