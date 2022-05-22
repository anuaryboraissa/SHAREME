package com.example.share.Repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.share.Entities.Permission;

public interface PermissionRepostry extends JpaRepository<Permission, Long>{
	@Query(value = "select * from permissions p where p.permit_id=?1",nativeQuery = true)
 public Permission findPerById(long id);
	@Query(value = "select * from permissions p where p.permit_id=?1",nativeQuery = true)
 public Collection<Permission>  findPermById(long id);
}
