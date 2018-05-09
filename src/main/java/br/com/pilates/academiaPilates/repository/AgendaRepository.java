/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pilates.academiaPilates.repository;

import br.com.pilates.academiaPilates.models.Agenda;
import br.com.pilates.academiaPilates.models.Cliente;
import br.com.pilates.academiaPilates.models.Profissional;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author daniel
 */
public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    
    public ArrayList<Agenda> findByProfissional(Profissional profissional);
    public ArrayList<Agenda> findByCliente(Cliente cliente);
    
    public ArrayList<Agenda> findByStartBetweenAndCanceladoFalse(String start , String end);
    public ArrayList<Agenda> findByCanceladoTrue();
    
    //public ArrayList<Agenda> findByProfissionalAndHoraInicioBetweenAndCanceladoFalseAndDataInicio(Profissional profissional,String horaInicio , String horaFinal , String dataInicio);
    
   
}
