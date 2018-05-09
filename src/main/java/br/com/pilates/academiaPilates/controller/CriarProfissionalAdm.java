/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pilates.academiaPilates.controller;

import br.com.pilates.academiaPilates.models.Professor;
import br.com.pilates.academiaPilates.models.Profissional;
import br.com.pilates.academiaPilates.models.enums.Perfil;
import br.com.pilates.academiaPilates.repository.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author daniel
 */

@RestController
public class CriarProfissionalAdm {
     @Autowired
    BCryptPasswordEncoder senha;

    @Autowired
    ProfissionalRepository profissionalRepository;
    Profissional profissional;

    @Value("${baseUrl}")
    private String baseUrl;

    PaginaController pagina;
    
    
    @RequestMapping(path = "${baseUrl}/sistema/criarUsuario", method = RequestMethod.GET)
    public void criarUsuario() {
        profissional = new Profissional();
       profissional.setAtivo(true);
       profissional.setBairro("bairro");
       profissional.setCelular("celular");
       profissional.setCep("86020-380");
        profissional.setCidade("cidade");
        profissional.setComplemento("");
        profissional.setCpf("404.223.020-23");
        profissional.setDataNasc("data");
        profissional.setEmail("email");
 
        profissional.setIdade("idade");
        profissional.setLogin("admin");
        profissional.setLogradouro("logradouro");
        profissional.setNivelProfissional("ADMINISTRADOR");
        profissional.setNome("NOME USUARIO");
        profissional.setNumeroEnd("1");
        profissional.setSenha(senha.encode("123"));// Criptografando a senha para salvar no banco   
         profissional.setSexo("M");
         profissional.setTelefone("tel");
         profissional.setUf("UF");
         profissional.addPerfil(Perfil.ADMINISTRADOR);
         profissional.addPerfil(Perfil.ALUNO);
         profissional.addPerfil(Perfil.OPERADOR);
         profissional.addPerfil(Perfil.PROFESSOR);
         profissional.addPerfil(Perfil.SYSTEM_ADMIN);
         
         profissionalRepository.save(profissional);
         System.out.println("Profissional Usuario Criado \n"+ profissional.toString());
    }
    
}
