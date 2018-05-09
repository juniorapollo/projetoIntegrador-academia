/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pilates.academiaPilates.repository;


import br.com.pilates.academiaPilates.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author daniel
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {    
    
    public Iterable<Cliente> findByAtivoTrueOrderByNomeAsc();
    public Iterable<Cliente> findByAtivoFalse();
  
}
