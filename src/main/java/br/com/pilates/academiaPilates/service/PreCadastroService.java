
package br.com.pilates.academiaPilates.service;

import br.com.pilates.academiaPilates.models.PreCadastro;
import br.com.pilates.academiaPilates.repository.PreCadastroRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author daniel
 */
@Service
public class PreCadastroService {

    
    @Autowired 
    PreCadastroRepository repo;

    
    public void salvar(PreCadastro pc , boolean ativo){
        try {
            pc.setAtivo(ativo);
            repo.save(pc);
            
            
        } catch (Exception e) {
            System.out.println("Pre Cadastro 29:" + e);
        }
        
    }
    
    public void apagar(PreCadastro pc , boolean ativo){
        try {
            pc.setAtivo(false);
            repo.save(pc);
            
            
        } catch (Exception e) {
            System.out.println("Pre Cadastro 41:" + e);
        }
        
    }
    
    public List<PreCadastro> preCadastros(boolean ativo){
        if(ativo){
            return repo.findByAtivoTrue();
        }else{
            return repo.findByAtivoFalse();
        }   
            
       
        
    }

    public PreCadastro finById(Long id) {
        try {
            return repo.findOne(id);
        } catch (Exception e) {
            System.out.println("PreCadastroService 62: FindById(): " + e);
            return null;
        }
        
    }
}