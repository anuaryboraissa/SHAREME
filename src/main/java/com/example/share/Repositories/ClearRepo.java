package com.example.share.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.share.Entities.ClearMsgs;

public interface ClearRepo extends JpaRepository<ClearMsgs, Long> {

}
