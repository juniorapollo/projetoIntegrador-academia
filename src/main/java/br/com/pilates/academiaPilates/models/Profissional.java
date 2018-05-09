/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pilates.academiaPilates.models;

import br.com.pilates.academiaPilates.models.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author junior
 */
@Entity
@Table(name = "profissional")
public class Profissional extends Pessoa implements Serializable {
 
    
    @NotBlank(message = "Seleciona o nível do Usuário")
    private String nivelProfissional;

    private boolean ativo = true;


    @Column(unique = true)
    private String login;

    private String senha;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name="profissional_servico",
            joinColumns = {@JoinColumn(name="profissional_id")},
            inverseJoinColumns = {@JoinColumn(name="servico_id")}) 
    private List<Servico> servicos = new ArrayList<Servico>();  
    
    @OneToMany(fetch=FetchType.LAZY)
    private List<Agenda> agenda = new ArrayList<Agenda>();//Tentar Iterable
    
    

    @ElementCollection(fetch = FetchType.EAGER) // EAGER, par reftornar o perfil ENUM com o Profissional no Json
    @CollectionTable(name = "perfis")
    private final Set<Integer> perfis = new HashSet<>();

    public Profissional() {
    }

    public Profissional(String nivelProfissional, String login, String senha) {
        this.nivelProfissional = nivelProfissional;
        this.login = login;
        this.senha = senha;
    }
    
    

    /* Getters e Setters */
    public String getNivelProfissional() {
        return nivelProfissional;
    }

    public void setNivelProfissional(String nivelProfissional) {
        this.nivelProfissional = nivelProfissional;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil) {
        perfis.add(perfil.getCod());

    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }

    public List<Agenda> getAgenda() {
        return agenda;
    }

    public void setAgenda(List<Agenda> agenda) {
        this.agenda = agenda;
    }

    

    
    
    
    
    

    
    

}
