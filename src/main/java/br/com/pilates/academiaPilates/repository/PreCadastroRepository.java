/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pilates.academiaPilates.repository;

import br.com.pilates.academiaPilates.models.PreCadastro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author daniel
 */
public interface PreCadastroRepository extends JpaRepository<PreCadastro, Long> {    

    public List<PreCadastro> findByAtivoTrue();

    public List<PreCadastro> findByAtivoFalse();
    
}
