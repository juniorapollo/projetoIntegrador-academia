/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pilates.academiaPilates.service;

import br.com.pilates.academiaPilates.models.Atividades;
import br.com.pilates.academiaPilates.repository.AtividadesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author daniel
 */
@Service
public class AtividadesService {
    
    @Autowired
    AtividadesRepository repo;
    Atividades atividades;
    
    public List<Atividades> listaAtividades(){
        return repo.findAll();
    }   
    
    
    public List<Atividades> listaAtividadesPorNivelCliente(String nivelCliente){
        return repo.findByNivel(nivelCliente);
    }
    
    
    
    
}
