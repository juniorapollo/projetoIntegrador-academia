package br.com.pilates.academiaPilates.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;

import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author junior
 */
@Entity
@Table(name = "cliente")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cliente extends Pessoa implements Serializable {

    private boolean ativo;

    @NotBlank(message = "Escolha o nível do Cliente")
    private String nivelCliente;

    private String praticaAtividade;

    private String peso;

    private String altura;

    private String objetivo;

    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "cliente_atividades",
            joinColumns = {
                @JoinColumn(name = "cliente_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "atividades_id")})
    private List<Atividades> atividades = new ArrayList<Atividades>();
    
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "cliente_agenda", joinColumns = @JoinColumn(name = "cliente_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "agenda_id", referencedColumnName = "idAgenda"))
    private Set<Agenda> agendas = new HashSet<>();
    
    //private String login;
    //private String senha;
    //@Lob
    // @NotBlank()
    private String fotoPerfil;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
    private List<RelatorioDiario> relatorioDiario;


    public Cliente() {
    }

    
    
    //GETTERS E SETTERS
    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getNivelCliente() {
        return nivelCliente;
    }

    public void setNivelCliente(String nivelCliente) {
        this.nivelCliente = nivelCliente;
    }

    public String getPraticaAtividade() {
        return praticaAtividade;
    }

    public void setPraticaAtividade(String praticaAtividade) {
        this.praticaAtividade = praticaAtividade;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;

    }

    public List<RelatorioDiario> getRelatorioDiario() {
        return relatorioDiario;
    }

    public void setRelatorioDiario(List<RelatorioDiario> relatorioDiario) {
        this.relatorioDiario = relatorioDiario;
    }

    public List<Atividades> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<Atividades> atividades) {
        this.atividades = atividades;
    }

    public Set<Agenda> getAgendas() {
        return agendas; 
    }

    public void setAgendas(Set<Agenda> agendas) {
        this.agendas = agendas;
    }
    
    

   

   

}
