
package br.com.pilates.academiaPilates.repository;


import br.com.pilates.academiaPilates.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author junior
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario findByLogin(String login);

   // public Iterable<Usuario> findByEmpresa(Empresa empresa);
   

}