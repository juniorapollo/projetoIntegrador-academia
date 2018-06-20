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
    public ArrayList<Agenda> findByClienteAndDataInicio(Cliente cliente , String dataInicial);
    public ArrayList<Agenda> findByStartBetweenAndCanceladoFalse(String start , String end);
    public ArrayList<Agenda> findByCanceladoTrue();
    
    public Long  countByDataInicioBetween(String dataInicio, String dataFinal);
    
    public Long countByDataInicioBetweenAndCanceladoFalse(String dataInicio, String dataFinal);
    public Long countByDataInicioBetweenAndCanceladoTrue(String dataInicio, String dataFinal);
    
   
    
    public ArrayList<Agenda> findByStart(String start );
    //public ArrayList<Agenda> findByProfissionalAndHoraInicioBetweenAndCanceladoFalseAndDataInicio(Profissional profissional,String horaInicio , String horaFinal , String dataInicio);
    
//   public ArrayList<Agenda> findByClienteAndDataInicioCanceladoFalseAndHoraInicio(Cliente cliente,String dataInicio , String dataFinal);
    
}
