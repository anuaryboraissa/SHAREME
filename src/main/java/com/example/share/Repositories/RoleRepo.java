package com.example.share.Repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.share.Entities.Roles;

public interface RoleRepo extends JpaRepository<Roles, Long> {
	@Query(value = "select * from roles r where r.role_name=?1",nativeQuery = true)
public Collection<Roles> getRoleByName(String role);
}
