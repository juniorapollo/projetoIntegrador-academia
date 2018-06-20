/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pilates.academiaPilates.service;

import br.com.pilates.academiaPilates.models.RelatorioDiario;
import br.com.pilates.academiaPilates.repository.RelatorioDiarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author daniel
 */
@Service
public class RelatorioDiarioService {
    @Autowired
    RelatorioDiarioRepository repo;
    
    public void salvarRelatorioDiario(RelatorioDiario rel){
    
        this.repo.save(rel);
    }
    
    
}
