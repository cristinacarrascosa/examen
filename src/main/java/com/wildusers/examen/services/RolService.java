package com.wildusers.examen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.wildusers.examen.entities.RolEntity;
import com.wildusers.examen.exception.ResourceNotModifiedException;
import com.wildusers.examen.repositories.RolRepository;

@Service
public class RolService {

    @Autowired
    RolRepository oRolRepository;

    public RolEntity get(Long id) {
        return oRolRepository.findById(id).get();
    }

    public Long create(RolEntity oRolEntity) {
        return oRolRepository.save(oRolEntity).getId();
    }


    public Long update(RolEntity oRolUpdateEntity) {
        RolEntity oRolEntity = oRolRepository.findById(oRolUpdateEntity.getId()).get();
        oRolEntity.setRol(oRolUpdateEntity.getRol());
        return oRolRepository.save(oRolEntity).getId();
    }

    public Long delete(Long id) {
        if (oRolRepository.existsById(id)) {
            oRolRepository.deleteById(id);
            return id;
        } else {
            throw new ResourceNotModifiedException("id " + id + " not exist");
        }

    }

    public Long count() {
        return oRolRepository.count();
    }

   /*  public Page<RolEntity> getPage(Pageable oPageable, String strFilter) {
        if (strFilter != null) {
            return oRolRepository.findByRolContainingIgnoreCase(strFilter, oPageable);
        } else {
            return oRolRepository.findAll(oPageable);
        }
    }*/

    public Page<RolEntity> getPage(int page, int size, String strFilter) {
        Pageable oPageable = PageRequest.of(page, size);
        if (strFilter != null) {
            return oRolRepository.findByRolContainingIgnoreCase(strFilter, oPageable);
        } else {
            return oRolRepository.findAll(oPageable);
        }
    }

}
