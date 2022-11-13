package com.wildusers.examen.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wildusers.examen.entities.RolEntity;

public interface RolRepository extends JpaRepository<RolEntity, Long> {

    Page<RolEntity> findByRolContainingIgnoreCase(String strFilter, Pageable oPageable);
    
}
