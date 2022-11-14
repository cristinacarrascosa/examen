package com.wildusers.examen.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.wildusers.examen.exception.ResourceNotFoundException;
import com.wildusers.examen.exception.ResourceNotModifiedException;
import com.wildusers.examen.exception.UnauthorizedException;
import com.wildusers.examen.helper.RandomHelper;
import com.wildusers.examen.Response.Response;
import com.wildusers.examen.Response.ResponseUser;
import com.wildusers.examen.entities.PendentEntity;
import com.wildusers.examen.entities.QuestionEntity;
import com.wildusers.examen.entities.UsuarioEntity;
import com.wildusers.examen.repositories.PendentRepository;
import com.wildusers.examen.repositories.QuestionRepository;
import com.wildusers.examen.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository oUsuarioRepository;

    @Autowired
    QuestionRepository oQuestionRepository;

    @Autowired
    PendentRepository oPendentRepository;

    @Autowired
    PendentEntity oPendentEntity;



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

    @Transactional
    public Response prelogin() {
        // punto 1
        int N1 = RandomHelper.getRandomInt(1,(int) oQuestionRepository.count());

        Long id = Long.valueOf(N1);

        QuestionEntity oQuestionEntity = new QuestionEntity();

        oQuestionEntity = oQuestionRepository.findById(id).orElseThrow(() -> new RuntimeException("Question with id: " + id + " not found"));//Controlar el error si no existe
        // punto 2
        PendentEntity oPendentEntity = new PendentEntity();

        oPendentRepository.save(oPendentEntity);// si no haces esto se modifica la bd con lo que guardes en el objeto

        oPendentEntity.setQuestion(oQuestionEntity);

        // punto 3
        StringBuffer token = new StringBuffer(N1);

        token.append(RandomHelper.getRandomInt(1, 9999));

        String cadena = RandomHelper.getSHA256(token.toString());// he concatenado todo y lo he pasado a estring y encriptado con sha256    

        // punto 4
        Response oResponse = new Response();

        oResponse.setToken(oPendentEntity.getToken());
        oResponse.setPregunta(oPendentEntity.getQuestion().getStatement());// solo coge el campo statement
        

        return oResponse;


    }

    // fase 2
    public UsuarioEntity loginc(@RequestBody ResponseUser oResponseUser) {
        if (oResponseUser.getPassword() != null) {
            UsuarioEntity oUsuarioEntity = oUsuarioRepository.findByLoginAndPassword(oResponseUser.getLogin(), oResponseUser.getPassword());
            if (oUsuarioEntity != null) {
                //oHttpSession.setAttribute("usuario", oUsuarioEntity);
               // PendentEntity oPendentEntity = oPendentRepository.findByToken(oResponseUser.getToken());
                return oUsuarioEntity;
            } else {
                throw new UnauthorizedException("login or password incorrect");
            }
        } else {
            throw new UnauthorizedException("wrong password");
        }
    }


}
