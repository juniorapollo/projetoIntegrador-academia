/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pilates.academiaPilates.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author junior
 */
@Entity
@Table(name = "avaliacao")
public class Avaliacao implements Serializable{
    
    private static final long serialVersionUID = 1L;    
     
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
   
    private LocalDateTime data;
    
    private String anamnese;
    
    private String diagnostico;
    
    private String ap;   
    
    //Perguntar oque é
    private String qp;
    
    private String examesComplementares;       
    
    @ManyToOne()
    @JoinColumn(name = "idAluno")
    private Aluno aluno; 
     
   @OneToMany()
   private List<GaleriaFoto> fotos;
    
    //Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    

    @ManyToOne
    @JoinColumn(name="id_aluno", nullable=false)
    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
    
    public Avaliacao() {
    }

    @Override
    public String toString() {
        return "Avaliacao{" + "id=" + id + ", data=" + data + ", anamnese=" + anamnese + ", diagnostico=" + diagnostico + ", ap=" + ap + ", qp=" + qp + ", examesComplementares=" + examesComplementares + ", aluno=" + aluno + ", fotos=" + fotos + '}';
    }
    
    

  
}
