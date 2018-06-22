
package br.com.pilates.academiaPilates.service;

import br.com.pilates.academiaPilates.models.Profissional;
import br.com.pilates.academiaPilates.repository.ProfissionalRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author daniel
 */
@Service
public class ProfissionalService {
    
    @Autowired
    ProfissionalRepository repo;
    
     public Profissional profissionalPorId(Long id){
        
        return repo.findOne(id);
    }
    
    
     public List<Profissional> listaProfissionalAtivo(){
        
        List<Profissional> listaProfissional = (List<Profissional>) repo.findByAtivoTrueOrderByNomeAsc();
        
        return listaProfissional;
    }
}


