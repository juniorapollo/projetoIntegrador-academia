/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pilates.academiaPilates.repository;

import br.com.pilates.academiaPilates.models.Avaliacao;
import br.com.pilates.academiaPilates.models.Cliente;
import java.util.ArrayList;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author junior
 */
public interface AvaliacaoRepository extends CrudRepository<Avaliacao, Long> {
    
    public ArrayList<Avaliacao> findByCliente(Cliente cliente);
    
}
