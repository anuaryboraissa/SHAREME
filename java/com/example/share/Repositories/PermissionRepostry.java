package com.example.share.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.share.Entities.Permission;

public interface PermissionRepostry extends JpaRepository<Permission, Long>{

}
