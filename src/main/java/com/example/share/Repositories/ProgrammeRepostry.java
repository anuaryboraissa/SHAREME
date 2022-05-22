package com.example.share.Repositories;

import java.util.Collection;


import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.share.Entities.Programme;
@Repository
public interface ProgrammeRepostry extends JpaRepository<Programme, Long> {
	@org.springframework.data.jpa.repository.Query(value="select * from programme p where p.college_id=?1",nativeQuery = true)
	Collection<Programme> findProgrammeById(long id);
}
