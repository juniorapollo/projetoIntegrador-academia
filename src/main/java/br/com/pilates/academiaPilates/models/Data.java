/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pilates.academiaPilates.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author junior
 */
public class Data {

    //Get current date time
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter dataAtual = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    DateTimeFormatter horaAtual = DateTimeFormatter.ofPattern("HH:mm:ss");

    public String getDataAtual(){
        String dataFormatada = now.format(dataAtual);
        return dataFormatada;
    }
    
    public String getHoraAtual(){
        String horaFormatada = now.format(horaAtual);
        return horaFormatada;
        
    }

    public Data() {
    }

    
}
