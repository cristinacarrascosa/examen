package com.wildusers.examen.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class RolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rol;

    @OneToMany(mappedBy = "rol", fetch = FetchType.LAZY)
    private List<UsuarioEntity> usuarios;

    
    public RolEntity() {
        this.usuarios = new ArrayList<>();
    }

    public Integer getUsuarios() {
        return usuarios.size();
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    
   }    
   
    

    

    

