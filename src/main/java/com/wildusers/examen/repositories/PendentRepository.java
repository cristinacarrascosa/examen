package com.wildusers.examen.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wildusers.examen.entities.PendentEntity;
import com.wildusers.examen.entities.RolEntity;

public interface PendentRepository extends JpaRepository<PendentEntity, Long> {

   
    
}
