
package br.com.pilates.academiaPilates.repository;


import br.com.pilates.academiaPilates.models.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author junior
 */
public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {

    public Profissional findByLogin(String login);

    public Iterable<Profissional> findByAtivoTrueOrderByNomeAsc();
}