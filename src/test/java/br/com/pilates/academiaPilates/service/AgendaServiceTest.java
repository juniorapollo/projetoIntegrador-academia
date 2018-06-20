/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pilates.academiaPilates.service;

import br.com.pilates.academiaPilates.models.Agenda;
import br.com.pilates.academiaPilates.models.Cliente;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author daniel
 */
public class AgendaServiceTest {
    
    
    Cliente c1 = new Cliente();
    AgendaService service = new AgendaService();
    Agenda agenda = new Agenda();
    
    @Before
    public void setUp() {
//        ****Cliente ***** 
        c1.setId(1l);
         c1.setNome("Daniel");
         c1.setAltura("");
         c1.setAtivo(true);
         c1.setCpf("1");
         c1.setEmail("");
         
//       **** Agenda ****
    agenda.setCancelado(true);
    agenda.setCliente(c1);
    agenda.setIdAgenda(1l);
    }
    
    @Test
    public void testeAgendaPorId() { 
        Agenda a = new Agenda();
       service.agendaPorId(270l);
        System.out.println(a);
    }
    
}
