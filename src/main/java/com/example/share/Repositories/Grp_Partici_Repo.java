package com.example.share.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.share.Entities.Grp_participantss;
import com.example.share.Entities.Composites.ParticiComposite;


public interface Grp_Partici_Repo extends JpaRepository<Grp_participantss, ParticiComposite> {

}
