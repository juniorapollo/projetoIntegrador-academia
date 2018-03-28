package br.com.pilates.academiaPilates.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author junior
 */

@Entity
@Table(name = "aluno")
public class Aluno extends Pessoa {

    public Aluno() {
    }   

    public Aluno(boolean ativo, String nivelAluno, String praticaAtividade, String peso, String altura, String objetivo, String fotoPerfil, List<Avaliacao> avaliacao) {
        this.ativo = ativo;
        this.nivelAluno = nivelAluno;
        this.praticaAtividade = praticaAtividade;
        this.peso = peso;
        this.altura = altura;
        this.objetivo = objetivo;
        this.fotoPerfil = fotoPerfil;
        this.avaliacao = avaliacao;
    }
    
    
    private boolean ativo;
    
    private String nivelAluno;
            
    private String praticaAtividade;
    
    private String peso;
    
    private String altura;
    
    private String objetivo;  
    
    //private String login;
    
    //private String senha;
  
    //@Lob
   // @NotBlank()
    private String fotoPerfil;    
    
    
    @OneToMany()
    private List<Avaliacao> avaliacao; 
      
    
    //GETTERS E SETTERS
    public boolean isAtivo() {
        return ativo;
    }    

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getNivelAluno() {
        return nivelAluno;
    }

    public void setNivelAluno(String nivelAluno) {
        this.nivelAluno = nivelAluno;
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

    public List<Avaliacao> getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(List<Avaliacao> avaliacao) {
        this.avaliacao = avaliacao;
    }

    
}