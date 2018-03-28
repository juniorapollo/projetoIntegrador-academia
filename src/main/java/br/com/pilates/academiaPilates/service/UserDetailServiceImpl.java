package br.com.pilates.academiaPilates.service;

import br.com.pilates.academiaPilates.models.Usuario;
import br.com.pilates.academiaPilates.repository.UsuarioRepository;
import br.com.pilates.academiaPilates.security.UserSS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author junior
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioRepository ur;
       

    //@Autowired
    //EmpresaRepository er;
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = ur.findByLogin(login);

        if (!(usuario == null)) {
            System.out.println("***Logou:{usuaio: "+usuario.getNome()+" , nivel: "+ usuario.getNivelUsuario()+" , login: "+usuario.getLogin()+"  }***");
            if (usuario.isAtivo()) {
               return new UserSS(usuario.getId(), usuario.getNome(), usuario.getLogin(), usuario.getSenha(), usuario.getNivelUsuario(), usuario.getPerfis());
            } else {
                throw new UsernameNotFoundException("Usuário não encontrado");
            }

        } else {
            System.out.println("NAO EXISTE USUARIO---------------------------------------------------------");
        }

        throw new UsernameNotFoundException("Usuário ou senha Inválidos");
    }

}
