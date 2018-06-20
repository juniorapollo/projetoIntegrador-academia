/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pilates.academiaPilates.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author daniel
 */

@Entity
@Table(name = "agenda")
public class Agenda implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAgenda;
    
     private String color;

     private String textColor;
  
    private String dataInicio;

    private String horaInicio;
    
    private String dataFinal;
    
    private String horaFinal;
    
    private String start ;
    
    private String end;

    private String servico;
    
    private String nomeRealizouCadastro;
    
    private String dataRealizouCadastro;
    
    private String horaRealizouCadastro;
    
    private boolean cancelado;
    
    private String dataCancelamento;
    
    private String horaCancelamento;
    
    private String nomeRealizouCancelamento;
    
    private String motivoCancelamento;
    
    
    
    @ManyToOne()
    @JoinColumn(name = "idProfissional")
    private Profissional profissional;  
    
    @ManyToOne()
    @JoinColumn(name = "idCliente")
    private Cliente cliente; 
    
    private String title ;
    

    public Agenda() {
    }

    public Long getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(Long idAgenda) {
        this.idAgenda = idAgenda;
    }

    public String getTitle() {
        return title;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }
    
    

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }



    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public String getNomeRealizouCadastro() {
        return nomeRealizouCadastro;
    }

    public void setNomeRealizouCadastro(String nomeRealizouCadastro) {
        this.nomeRealizouCadastro = nomeRealizouCadastro;
    }

    public String getDataRealizouCadastro() {
        return dataRealizouCadastro;
    }

    public void setDataRealizouCadastro(String dataRealizouCadastro) {
        this.dataRealizouCadastro = dataRealizouCadastro;
    }

    public String getHoraRealizouCadastro() {
        return horaRealizouCadastro;
    }

    public void setHoraRealizouCadastro(String horaRealizouCadastro) {
        this.horaRealizouCadastro = horaRealizouCadastro;
    }

    public boolean isCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }

    public String getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(String dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public String getHoraCancelamento() {
        return horaCancelamento;
    }

    public void setHoraCancelamento(String horaCancelamento) {
        this.horaCancelamento = horaCancelamento;
    }

    public String getNomeRealizouCancelamento() {
        return nomeRealizouCancelamento;
    }

    public void setNomeRealizouCancelamento(String nomeRealizouCancelamento) {
        this.nomeRealizouCancelamento = nomeRealizouCancelamento;
    }

    public String getMotivoCancelamento() {
        return motivoCancelamento;
    }

    public void setMotivoCancelamento(String motivoCancelamento) {
        this.motivoCancelamento = motivoCancelamento;
    }

    @Override
    public String toString() {
        return "Agenda{" + "idAgenda=" + idAgenda + ", color=" + color + ", textColor=" + textColor + ", horaInicio=" + horaInicio + ", dataFinal=" + dataFinal + ", horaFinal=" + horaFinal + ", start=" + start + ", end=" + end + ", servico=" + servico + ", nomeRealizouCadastro=" + nomeRealizouCadastro + ", dataRealizouCadastro=" + dataRealizouCadastro + ", horaRealizouCadastro=" + horaRealizouCadastro + ", cancelado=" + cancelado + ", dataCancelamento=" + dataCancelamento + ", horaCancelamento=" + horaCancelamento + ", nomeRealizouCancelamento=" + nomeRealizouCancelamento + ", motivoCancelamento=" + motivoCancelamento + ", title=" + title + '}';
    }

    
    
    

    
    

    
    
    

  
    
    
    
    
    
    
    
    
    
}
