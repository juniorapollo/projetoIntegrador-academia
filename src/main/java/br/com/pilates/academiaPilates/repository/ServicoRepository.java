/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pilates.academiaPilates.repository;

import br.com.pilates.academiaPilates.models.Servico;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author daniel
 */
public interface ServicoRepository extends JpaRepository<Servico, Long> {
   
    public List<Servico> findByAtivoTrueOrderByDescricaoAsc();

    public Servico findByDescricaoAndAtivoTrue(String descricao);
    
}
