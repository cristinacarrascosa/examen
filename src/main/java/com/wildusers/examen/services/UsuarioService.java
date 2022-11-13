package com.wildusers.examen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.wildusers.examen.exception.ResourceNotFoundException;
import com.wildusers.examen.exception.ResourceNotModifiedException;
import com.wildusers.examen.entities.UsuarioEntity;
import com.wildusers.examen.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository oUsuarioRepository;

    public UsuarioEntity get(Long id) {

        try {
            return oUsuarioRepository.findById(id).get();
        } catch (Exception ex) {
            throw new ResourceNotFoundException("id " + id + " not exist");
        }
    }

    public  Long create(UsuarioEntity oUsuarioEntity) {
       oUsuarioEntity.setId(0L);
       return oUsuarioRepository.save(oUsuarioEntity).getId();
    }

    public Long count() {
        return oUsuarioRepository.count();
    }

    public Long update(UsuarioEntity oUsuarioUpdateEntity) {
        UsuarioEntity oUsuarioEntity = oUsuarioRepository.findById(oUsuarioUpdateEntity.getId()).get();
        oUsuarioEntity.setNombre(oUsuarioUpdateEntity.getNombre());
        oUsuarioEntity.setApellidos(oUsuarioUpdateEntity.getApellidos());
        oUsuarioEntity.setRol(oUsuarioUpdateEntity.getRol());
        return oUsuarioRepository.save(oUsuarioEntity).getId();
    }

    public Long delete(Long id) {
        if (oUsuarioRepository.existsById(id)) {
            oUsuarioRepository.deleteById(id);
            return id;
        } else {
            throw new ResourceNotModifiedException("id " + id + " not exist");
        }

    }

    public Page<UsuarioEntity> getPage(int page, int size, String nombre) {
        Pageable oPageable = PageRequest.of(page, size);
        if (nombre != null) {
            return oUsuarioRepository.findByNombreIgnoreCaseContainingOrApellidosIgnoreCaseContaining(nombre, nombre, oPageable);
        } else {
            return oUsuarioRepository.findAll(oPageable);
        }
        
    }

}
