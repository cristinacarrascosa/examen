package com.wildusers.examen.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wildusers.examen.Response.Response;
import com.wildusers.examen.Response.ResponseUser;
import com.wildusers.examen.entities.UsuarioEntity;
import com.wildusers.examen.services.UsuarioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService oUsuarioService;
    
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntity> get(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<UsuarioEntity>(oUsuarioService.get(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Long> create(@RequestBody UsuarioEntity oUsuarioEntity) {
        return new ResponseEntity<Long>(oUsuarioService.create(oUsuarioEntity), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oUsuarioService.count(), HttpStatus.OK);
    }
    
    @PutMapping("")
    public ResponseEntity<Long> update(@RequestBody UsuarioEntity oUsuarioUpdateEntity) {
        return new ResponseEntity<Long>(oUsuarioService.update(oUsuarioUpdateEntity), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<Long>(oUsuarioService.delete(id), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Page<UsuarioEntity>> getPage(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "1") int size,
            @RequestParam(value = "nombre", required = false) String nombre) {
        return new ResponseEntity<Page<UsuarioEntity>>(oUsuarioService.getPage(page, size, nombre), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Response> prelogin() {
        return new ResponseEntity<Response>(oUsuarioService.prelogin(), HttpStatus.OK);
    }

  /*  @PostMapping("/loginc")
    public ResponseEntity<UsuarioEntity> loginc(@org.springframework.web.bind.annotation.RequestBody ResponseUser oResponseUser) {
        return new ResponseEntity<UsuarioEntity>(UsuarioService.loginc(oResponseUser), HttpStatus.OK);
    
      /*  @PostMapping("/loginc")
        public ResponseEntity<UsuarioEntity> loginc(@org.springframework.web.bind.annotation.RequestBody CaptchaBean oCaptchaBean) {
            return new ResponseEntity<UsuarioEntity>(oAuthService.loginc(oCaptchaBean), HttpStatus.OK);
        } */

    
}
