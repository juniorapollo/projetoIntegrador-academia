/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pilates.academiaPilates.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author junior
 */
@Entity
@Table(name = "avaliacao")
public class Avaliacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String dataAvaliacao;

    private String hora;

    @Lob
    @NotBlank(message = "ANAMNESE EM BRANCO")
    private String anamnese;

    private String diagnostico;

    private String ap;

    private String qp;

    private String examesComplementares; 
    
    private String proximaAvaliacao;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @JsonIgnore
    @OneToOne(mappedBy = "avaliacao", fetch = FetchType.LAZY, cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    private GaleriaFoto fotos;
 
    //Getters e Setters
    public Avaliacao() {
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(String dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    
   
    
    public String getProximaAvaliacao() {
        return proximaAvaliacao; 
    }

    public void setProximaAvaliacao(String proximaAvaliacao) {
        this.proximaAvaliacao = proximaAvaliacao;
    }


    public String getAnamnese() {
        return anamnese;
    }

    public void setAnamnese(String anamnese) {
        this.anamnese = anamnese;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getAp() {
        return ap;
    }

    public void setAp(String ap) {
        this.ap = ap;
    }

    public String getQp() {
        return qp;
    }

    public void setQp(String qp) {
        this.qp = qp;
    }

    public String getExamesComplementares() {
        return examesComplementares;
    }

    public void setExamesComplementares(String examesComplementares) {
        this.examesComplementares = examesComplementares;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public GaleriaFoto getFotos() {
        return fotos;
    } 

    public void setFotos(GaleriaFoto fotos) {
        this.fotos = fotos;
    }

    

    @Override
    public String toString() {
        return "Avaliacao{" + "id: " + id + ",\n dataAvaliacao=" + dataAvaliacao + ",\n hora=" + hora + ",\n anamnese=" + anamnese + ",\n diagnostico=" + diagnostico + ",\n ap=" + ap + ",\n qp=" + qp + ",\n examesComplementares=" + examesComplementares + ",\n cliente=" + cliente + ",\n fotos=" + fotos + '}';
    }

    
    
    
}