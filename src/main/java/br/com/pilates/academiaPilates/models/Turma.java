/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pilates.academiaPilates.models;

import com.fasterxml.jackson.annotation.JsonView;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "turma")
public class Turma implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Define um nome de Turma")
    private String nome;

    @NotBlank(message = "Seleciona o nível da Turma")
    private String nivelTurma;

    private boolean ativo = true;
    

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE,
            })
    @JoinTable(name = "cliente_turmas",
            joinColumns = { @JoinColumn(name = "cliente_id") },
            inverseJoinColumns = { @JoinColumn(name = "turma_id") })
    private List<Cliente> clientes = new ArrayList<>(); 


    public Turma() {
    }

    public Turma(Long id, String nome, String nivelTurma, List<Cliente> clientes) {
        this.id = id;
        this.nome = nome;
        this.nivelTurma = nivelTurma;
        // this.clientes = clientes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNivelTurma() {
        return nivelTurma;
    }

    public void setNivelTurma(String nivelTurma) {
        this.nivelTurma = nivelTurma;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.nome);
        hash = 53 * hash + Objects.hashCode(this.nivelTurma);
        hash = 53 * hash + (this.ativo ? 1 : 0);
        hash = 53 * hash + Objects.hashCode(this.clientes);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Turma other = (Turma) obj;
        if (this.ativo != other.ativo) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.nivelTurma, other.nivelTurma)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.clientes, other.clientes)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Turma{" + "id=" + id + ", nome=" + nome + ", nivelTurma=" + nivelTurma + ", ativo=" + ativo + ", clientes=" + clientes + '}';
    }

    
    
}
