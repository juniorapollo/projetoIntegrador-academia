/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pilates.academiaPilates.models;

import br.com.pilates.academiaPilates.models.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author junior
 */
@Entity
@Table(name = "usuario")
public class Usuario extends Pessoa implements Serializable {
 
    
    @NotBlank(message = "Seleciona o nível do Usuário")
    private String nivelUsuario;

    private boolean ativo = true;


    @Column(unique = true)
    private String login;


    private String senha;


    @ElementCollection(fetch = FetchType.EAGER) // EAGER, par reftornar o perfil ENUM com o Usuario no Json
    @CollectionTable(name = "perfis")
    private final Set<Integer> perfis = new HashSet<>();

    public Usuario() {
    }

    public Usuario(String nivelUsuario, String login, String senha) {
        this.nivelUsuario = nivelUsuario;
        this.login = login;
        this.senha = senha;
    }
    
    

    /* Getters e Setters */
    public String getNivelUsuario() {
        return nivelUsuario;
    }

    public void setNivelUsuario(String nivelUsuario) {
        this.nivelUsuario = nivelUsuario;
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

    @Override
    public String toString() {
        return "Usuario{" + "nivelUsuario=" + nivelUsuario + ", ativo=" + ativo + ", login=" + login + ", senha=" + senha + ", perfis=" + perfis + '}';
    }
    
    

}
