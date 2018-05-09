
package br.com.pilates.academiaPilates.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author junior
 */

@Entity
@Table(name = "professor")
public class Professor extends Pessoa {    

    private static final long serialVersionUID = 1L;
      
    public String dataAdmissao;
    
    public Professor() {
    }

    public String getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(String dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }
    
    
    
}


