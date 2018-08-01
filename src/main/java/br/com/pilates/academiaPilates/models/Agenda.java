package br.com.pilates.academiaPilates.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "agenda")
@Transactional(readOnly = true)
public class Agenda implements Serializable {

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

    @JsonFormat
      (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime start;

    @JsonFormat
      (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime end;
 
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "idServico")
    private Servico servico;
    

    private String nomeRealizouCadastro;

    private String dataRealizouCadastro;

    private String horaRealizouCadastro;

    private boolean cancelado;

    private String dataCancelamento;

    private String horaCancelamento;

    private String nomeRealizouCancelamento;

    private String motivoCancelamento;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "idProfissional")
    private Profissional profissional;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "idCliente")
    private Cliente cliente;
    
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "idTurma")
    private Turma turma;

//    @ManyToOne()
//    @JoinColumn(name = "idTurma")
//    private Turma turma; 
    private String title;

    public Agenda() {
    }

    public Agenda(Long idAgenda, String color, String textColor, String dataInicio, String horaInicio, String dataFinal, String horaFinal, LocalDateTime start, LocalDateTime end, Servico servico, String nomeRealizouCadastro, String dataRealizouCadastro, String horaRealizouCadastro, boolean cancelado, String dataCancelamento, String horaCancelamento, String nomeRealizouCancelamento, String motivoCancelamento, Profissional profissional, Cliente cliente,Turma turma, String title) {
        this.idAgenda = idAgenda;
        this.color = color;
        this.textColor = textColor;
        this.dataInicio = dataInicio;
        this.horaInicio = horaInicio;
        this.dataFinal = dataFinal;
        this.horaFinal = horaFinal;
        this.start = start;
        this.end = end;
        this.servico = servico;
        this.nomeRealizouCadastro = nomeRealizouCadastro;
        this.dataRealizouCadastro = dataRealizouCadastro;
        this.horaRealizouCadastro = horaRealizouCadastro;
        this.cancelado = cancelado;
        this.dataCancelamento = dataCancelamento;
        this.horaCancelamento = horaCancelamento;
        this.nomeRealizouCancelamento = nomeRealizouCancelamento;
        this.motivoCancelamento = motivoCancelamento;
        this.profissional = profissional;
        this.cliente = cliente;
        this.turma = turma;
        this.title = title;       
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

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    } 

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
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

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
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

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.idAgenda);
        hash = 37 * hash + Objects.hashCode(this.color);
        hash = 37 * hash + Objects.hashCode(this.textColor);
        hash = 37 * hash + Objects.hashCode(this.dataInicio);
        hash = 37 * hash + Objects.hashCode(this.horaInicio);
        hash = 37 * hash + Objects.hashCode(this.dataFinal);
        hash = 37 * hash + Objects.hashCode(this.horaFinal);
        hash = 37 * hash + Objects.hashCode(this.start);
        hash = 37 * hash + Objects.hashCode(this.end);
        hash = 37 * hash + Objects.hashCode(this.servico);
        hash = 37 * hash + Objects.hashCode(this.nomeRealizouCadastro);
        hash = 37 * hash + Objects.hashCode(this.dataRealizouCadastro);
        hash = 37 * hash + Objects.hashCode(this.horaRealizouCadastro);
        hash = 37 * hash + (this.cancelado ? 1 : 0);
        hash = 37 * hash + Objects.hashCode(this.dataCancelamento);
        hash = 37 * hash + Objects.hashCode(this.horaCancelamento);
        hash = 37 * hash + Objects.hashCode(this.nomeRealizouCancelamento);
        hash = 37 * hash + Objects.hashCode(this.motivoCancelamento);
        hash = 37 * hash + Objects.hashCode(this.profissional);
        hash = 37 * hash + Objects.hashCode(this.cliente);
        hash = 37 * hash + Objects.hashCode(this.title);
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
        final Agenda other = (Agenda) obj;
        if (this.cancelado != other.cancelado) {
            return false;
        }
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        if (!Objects.equals(this.textColor, other.textColor)) {
            return false;
        }
        if (!Objects.equals(this.dataInicio, other.dataInicio)) {
            return false;
        }
        if (!Objects.equals(this.horaInicio, other.horaInicio)) {
            return false;
        }
        if (!Objects.equals(this.dataFinal, other.dataFinal)) {
            return false;
        }
        if (!Objects.equals(this.horaFinal, other.horaFinal)) {
            return false;
        }
        if (!Objects.equals(this.nomeRealizouCadastro, other.nomeRealizouCadastro)) {
            return false;
        }
        if (!Objects.equals(this.dataRealizouCadastro, other.dataRealizouCadastro)) {
            return false;
        }
        if (!Objects.equals(this.horaRealizouCadastro, other.horaRealizouCadastro)) {
            return false;
        }
        if (!Objects.equals(this.dataCancelamento, other.dataCancelamento)) {
            return false;
        }
        if (!Objects.equals(this.horaCancelamento, other.horaCancelamento)) {
            return false;
        }
        if (!Objects.equals(this.nomeRealizouCancelamento, other.nomeRealizouCancelamento)) {
            return false;
        }
        if (!Objects.equals(this.motivoCancelamento, other.motivoCancelamento)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.idAgenda, other.idAgenda)) {
            return false;
        }
        if (!Objects.equals(this.start, other.start)) {
            return false;
        }
        if (!Objects.equals(this.end, other.end)) {
            return false;
        }
        if (!Objects.equals(this.servico, other.servico)) {
            return false;
        }
        if (!Objects.equals(this.profissional, other.profissional)) {
            return false;
        }
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "Agenda{" + "idAgenda=" + idAgenda + ", color=" + color + ", textColor=" + textColor + ", horaInicio=" + horaInicio + ", dataFinal=" + dataFinal + ", horaFinal=" + horaFinal + ", start=" + start + ", end=" + end + " , nomeRealizouCadastro=" + nomeRealizouCadastro + ", dataRealizouCadastro=" + dataRealizouCadastro + ", horaRealizouCadastro=" + horaRealizouCadastro + ", cancelado=" + cancelado + ", dataCancelamento=" + dataCancelamento + ", horaCancelamento=" + horaCancelamento + ", nomeRealizouCancelamento=" + nomeRealizouCancelamento + ", motivoCancelamento=" + motivoCancelamento + ", title=" + title + '}';
    }

}