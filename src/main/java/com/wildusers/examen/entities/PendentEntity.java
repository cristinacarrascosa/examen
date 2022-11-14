package com.wildusers.examen.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.FetchType;

@Entity
@Table(name = "pendent")
public class PendentEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;
    

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "id_question")
    private RolEntity question;

    
    public PendentEntity() {

    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getToken() {
        return token;
    }


    public void setToken(String token) {
        this.token = token;
    }


    public RolEntity getQuestion() {
        return question;
    }


    public void setQuestion(RolEntity question) {
        this.question = question;
    }
    
    
}
