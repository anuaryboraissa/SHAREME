package com.example.share.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.share.Entities.GroupAdmin;
import com.example.share.Entities.Composites.AdminComposite;

public interface GrpAdminRepo extends JpaRepository<GroupAdmin, AdminComposite> {

}
