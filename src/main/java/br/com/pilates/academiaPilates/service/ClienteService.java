/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pilates.academiaPilates.service;

import br.com.pilates.academiaPilates.models.Cliente;
import br.com.pilates.academiaPilates.repository.ClienteRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author daniel
 */
@Service
public class ClienteService {
    
    @Autowired
    ClienteRepository repo;
    Cliente cliente;
    
    public List<Cliente> listaClientesAtivos(){
        
        List<Cliente> listaClientes = (List<Cliente>) repo.findByAtivoTrueOrderByNomeAsc();
        
        return listaClientes;
    }
    
    public Cliente clientePorId(Long idCliente){
     
        return repo.getOne(idCliente);
    }
    
    
    public Long countClientesAtivos(){
    
        return repo.countByAtivoTrue();
    }
    
    
    public Long countClientesAtivosPorNivel(String nivelCliente){
    
        return repo.countByNivelClienteAndAtivoTrue(nivelCliente);
    }
    
    
    
}
