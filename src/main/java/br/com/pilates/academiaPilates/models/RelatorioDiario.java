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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author daniel
 */

@Entity
@Table(name = "relatorioDiario")
public class RelatorioDiario implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotBlank(message="Escolhe data que foi realizado o serviço")
    private String dataAula;
            
    private String horaInicial;
    
    private String horaFinal;
    
    @ManyToOne()
    @NotNull(message = "Selecione Um Cliente")
    @JoinColumn(name = "idCliente")
    private Cliente cliente;
    
  
    @ManyToMany(mappedBy = "relatorioDiario", cascade = CascadeType.ALL)
    private List<Atividades> atividades = new ArrayList<Atividades>();

    public RelatorioDiario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataAula() {
        return dataAula;
    }

    public void setDataAula(String dataAula) {
        this.dataAula = dataAula;
    }

    public String getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(String horaInicial) {
        this.horaInicial = horaInicial;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Atividades> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<Atividades> atividades) {
        this.atividades = atividades;
    }

    @Override
    public String toString() {
        return "RelatorioDiario{" + "id=" + id + ", dataAula=" + dataAula + ", horaInicial=" + horaInicial + ", horaFinal=" + horaFinal + ", cliente=" + cliente + ", atividades=" + atividades + '}';
    }
    
    
    
            
            
    
    
}
