/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pilates.academiaPilates.service;

import br.com.pilates.academiaPilates.models.Servico;
import br.com.pilates.academiaPilates.repository.ServicoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author daniel
 */
@Service
public class ServicoService {
    @Autowired
    ServicoRepository repo;
    
    public List<Servico> listaServicosAtivos(){
       return repo.findByAtivoTrueOrderByDescricaoAsc();        
    }
    
    public Servico servicoPorDescricao(String descricao){
        return repo.findByDescricaoAndAtivoTrue(descricao);
    }
    
}
