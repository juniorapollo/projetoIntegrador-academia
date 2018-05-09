package br.com.pilates.academiaPilates.service;

import br.com.pilates.academiaPilates.models.Profissional;
import br.com.pilates.academiaPilates.security.UserSS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.pilates.academiaPilates.repository.ProfissionalRepository;

/**
 *
 * @author junior
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    ProfissionalRepository ur;
       

    //@Autowired
    //EmpresaRepository er;
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Profissional profissional = ur.findByLogin(login);

        if (!(profissional == null)) {
            System.out.println("***Logou:{Profissional: "+profissional.getNome()+" , nivel: "+ profissional.getNivelProfissional()+" , login: "+profissional.getLogin()+"  }***");
            if (profissional.isAtivo()) {
               return new UserSS(profissional.getId(), profissional.getNome(), profissional.getLogin(), profissional.getSenha(), profissional.getNivelProfissional(), profissional.getPerfis());
            } else {
                throw new UsernameNotFoundException("Usuário não encontrado");
            }

        } else {
            System.out.println("NAO EXISTE Profissional---------------------------------------------------------");
        }

        throw new UsernameNotFoundException("Usuário ou senha Inválidos");
    }

}
