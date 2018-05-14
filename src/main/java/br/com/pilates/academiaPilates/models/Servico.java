/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pilates.academiaPilates.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "servico")
public class Servico implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Descrição não pode ser nulo")
    private String descricao;
    
    private double valor;
    
    private String tempo;
    
    private boolean ativo;
    
    @ManyToMany(mappedBy = "servicos", cascade = CascadeType.ALL )
    private List<Profissional>profissional = new ArrayList<Profissional>();    

    

    public Servico() {
    }

    public Servico(Long id, String descricao, double valor, String tempo, boolean ativo) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.tempo = tempo;
        this.ativo = ativo;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
   
    
    
    public List<Profissional> getProfissional() {
        return profissional;
    }

    public void setProfissional(List<Profissional> profissional) {
        this.profissional = profissional;
    }

    @Override
    public String toString() {
        return "Servico{" + "id=" + id + ", descricao=" + descricao + ", valor=" + valor + ", tempo=" + tempo + ", ativo=" + ativo + ", profissional=" + profissional + '}';
    }
    
    

    
    
    
    
}
