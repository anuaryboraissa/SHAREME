package com.example.share.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.share.Entities.Roles;

public interface RoleRepo extends JpaRepository<Roles, Long> {

}
