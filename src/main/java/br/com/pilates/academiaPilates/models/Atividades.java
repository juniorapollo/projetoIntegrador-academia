

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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author daniel
 */
@Entity
public class Atividades implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nivel;

    private String tipo;
    
    private String nome;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="atividades_relatorio",
            joinColumns = {@JoinColumn(name="atividades_id")},
            inverseJoinColumns = {@JoinColumn(name="relatorio_id")}) 
    private List<RelatorioDiario> relatorioDiario = new ArrayList<RelatorioDiario>();  
  
  
    public Atividades() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<RelatorioDiario> getRelatorioDiario() {
        return relatorioDiario;
    }

    public void setRelatorioDiario(List<RelatorioDiario> relatorioDiario) {
        this.relatorioDiario = relatorioDiario;
    }

    @Override
    public String toString() {
        return "Atividades{" + "id=" + id + ", nivel=" + nivel + ", tipo=" + tipo + ", nome=" + nome + ", relatorioDiario=" + relatorioDiario + '}';
    }



   
}
