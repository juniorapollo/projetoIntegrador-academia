package br.com.pilates.academiaPilates.models;
import java.util.Set;
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
    
    private boolean ativo;
    //Perguntar oque é
    
    private String praticaAtividade;
    
    private String peso;
    
    private String altura;
    
    private String objetivo;  
    
    //private String login;
    
    //private String senha;
  
    //@Lob
   // @NotBlank()
    private String fotoPerfil; 
    
    @OneToMany(mappedBy="aluno")
    private Set<Avaliacao> diagnosticos;
      
    // @OneToMany
    //private List<ConvenioEstagio> convenioestagio = new ArrayList<>();
    
    //GETTERS E SETTERS
    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
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

    public Set<Avaliacao> getDiagnosticos() {
        return diagnosticos;
    }

    public void setDiagnosticos(Set<Avaliacao> diagnosticos) {
        this.diagnosticos = diagnosticos;
    }


    

   

    
   
    

}