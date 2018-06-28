
package br.com.pilates.academiaPilates.repository;

import br.com.pilates.academiaPilates.models.Turma;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author daniel
 */
public interface TurmaRepository extends JpaRepository<Turma, Long>{

    public List<Turma> findByAtivoTrueOrderByNomeAsc();

    public List<Turma> findByAtivoFalseOrderByNomeAsc();
    
}
