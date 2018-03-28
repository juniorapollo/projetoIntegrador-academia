/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pilates.academiaPilates.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author junior
 */
@Entity
@Table(name = "galeriaFoto")
public class GaleriaFoto implements  Serializable{
 
    private static final long serialVersionUID = 1L;    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String descrição;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataGaleria;
    
    @ManyToOne()
    @JoinColumn(name = "idAvaliacao")
    private Avaliacao avaliacao;    
    
    
    
}
