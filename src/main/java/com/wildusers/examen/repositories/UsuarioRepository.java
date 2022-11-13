package com.wildusers.examen.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wildusers.examen.entities.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    /*Page<UsuarioEntity> findByNombre(String nombre, Pageable oPageable);*/

    /*Page<UsuarioEntity> findByNombreIgnoreCaseContaining(String nombre, Pageable oPageable);*/
    Page<UsuarioEntity> findByNombreIgnoreCaseContainingOrApellidosIgnoreCaseContaining(String nombre, String apellidos, Pageable oPageable);

   

  

    /*Page<UsuarioEntity> findByNameorfindBySurnameContainingIgnoreCase(String nombre, Pageable oPageable);*/
    
}
