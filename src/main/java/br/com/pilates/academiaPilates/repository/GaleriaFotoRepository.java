/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pilates.academiaPilates.repository;

import br.com.pilates.academiaPilates.models.Avaliacao;
import br.com.pilates.academiaPilates.models.GaleriaFoto;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author daniel
 */
public interface GaleriaFotoRepository extends JpaRepository<GaleriaFoto, Long>{

    public GaleriaFoto findByAvaliacao(Avaliacao avaliacao);


    
    
}
