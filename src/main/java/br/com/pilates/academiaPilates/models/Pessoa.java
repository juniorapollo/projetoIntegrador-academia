/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pilates.academiaPilates.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author junior
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Pessoa implements Serializable{
 
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    
    @NotBlank(message = "Informe Nome") 
    private String nome;
   
    @NotBlank(message = "Informe Email")
    @Email(message="Email inválido")
    private String email;
        
    @NotBlank(message ="Informe Cpf")
    @CPF(message = "CPF inválido")
    private String cpf;
    
    @NotBlank(message = "Informe Sexo")
    private String sexo;

   
    @NotBlank(message = "Informe data nascimento")
    private String dataNasc;
   
    private String idade;   
   
    private String telefone;

    @NotBlank(message = "Informe Celular")
    private String celular;
    
    @NotBlank(message = "Informe Cep")
    private String cep;
    
    @NotBlank(message = "Informe Cidade")
    private String cidade;
     
    @NotBlank(message = "Informe Estado")
    private String uf;     

    @NotBlank(message = "Informe Bairro")
    private String bairro;

    @NotBlank(message = "Informe Rua")
    private String logradouro;
    
    private String numeroEnd;

    private String complemento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumeroEnd() {
        return numeroEnd;
    }

    public void setNumeroEnd(String numeroEnd) {
        this.numeroEnd = numeroEnd;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "id=" + id + ", nome=" + nome + ", email=" + email + ", cpf=" + cpf + ", sexo=" + sexo + ", dataNasc=" + dataNasc + ", idade=" + idade + ", telefone=" + telefone + ", celular=" + celular + ", cep=" + cep + ", cidade=" + cidade + ", uf=" + uf + ", bairro=" + bairro + ", logradouro=" + logradouro + ", numeroEnd=" + numeroEnd + ", complemento=" + complemento + '}';
    }

    
    
}
