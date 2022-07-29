package com.example.share.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;



import com.example.share.Entities.RequestFrom;
import com.example.share.Entities.Composites.CompositeKey;

public interface ReqFromRepo extends JpaRepository<RequestFrom, CompositeKey> {

}
