package com.example.share.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.share.Entities.Files;

public interface FilesRepostry extends JpaRepository<Files, Long> {

}
