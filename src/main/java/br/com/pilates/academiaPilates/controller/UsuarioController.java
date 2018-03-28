/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pilates.academiaPilates.controller;

import br.com.pilates.academiaPilates.models.Usuario;
import br.com.pilates.academiaPilates.models.enums.Perfil;

import br.com.pilates.academiaPilates.repository.UsuarioRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author junior
 */
@RestController
public class UsuarioController {

    @Autowired
    BCryptPasswordEncoder senha;

    @Autowired
    UsuarioRepository ur;
    Usuario usuario;

    @Value("${baseUrl}")
    private String baseUrl;

    PaginaController pagina;

    //@PreAuthorize("hasAnyRole('OPERADOR')")
    @GetMapping(path = "${baseUrl}/sistema/api/usuario")
    public Iterable<Usuario> usuariosJson() {
        Iterable<Usuario> usuarios = ur.findAll();
        return usuarios;
    }

    @RequestMapping(path = "${baseUrl}/sistema/cadastro/usuario-adicionar")
    public ModelAndView cadastrarUsuarioPage(Usuario usuario) {
        ModelAndView mv = new ModelAndView("cadastro/adicionar/formCadastroUsuario");
        mv.addObject("usuario", usuario);        
        return mv;
    }

    //@PreAuthorize("hasAnyRole('SUPERVISOR')")
    @PostMapping(path = "${baseUrl}/sistema/api/usuario", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE}, headers = "content-type=application/x-www-form-urlencoded , application/json", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView salvarUsuario(@Valid Usuario usuario, BindingResult result, RedirectAttributes atributes) {
        System.out.println("UsuarioController: POST" );
        try {

            if (result.hasErrors()) {
                return cadastrarUsuarioPage(usuario);
            }

            switch (usuario.getNivelUsuario()) {
                case "OPERADOR":
                    usuario.addPerfil(Perfil.OPERADOR);
                    break;
                case "PROFESSOR":
                    usuario.addPerfil(Perfil.PROFESSOR);
                    break;
                case "ADMINISTRADOR":
                    usuario.addPerfil(Perfil.OPERADOR);
                    usuario.addPerfil(Perfil.PROFESSOR);
                    usuario.addPerfil(Perfil.ADMINISTRADOR);
                    break;
                default:
                    break;
            }

            //UserSS user = UserService.authenticated(); //Carrega Usuário Logado no Sistema*/
            usuario.setId(null); // Setando ID Nulo para criar um novo usuário (Garantir)
            //usuario.setEmpresa(empresaResource.buscarEmpresaId(user.getEmpresa().getIdEmpresa())); //Setando empresa do Usuario Logado
            usuario.setSenha(senha.encode(usuario.getSenha()));// Criptografando a senha para salvar no banco   
            usuario.setAtivo(true); // Usuário Criado ja Ativo para Usar o Sistema 

            System.out.println(usuario.toString());
            ur.save(usuario);//Salva Usuário criado 

            ModelAndView mv = new ModelAndView("redirect:" + baseUrl + "/sistema/cadastro/usuario-adicionar");
            atributes.addAttribute("mensagem", "Usuário - " + usuario.getId() + " " + usuario.getNome() + " salvo com sucesso.");
            return mv;
        } catch (DataIntegrityViolationException e) {
            System.out.println("Catch  Violação de Integridade UsuarioController POST:" + e);
            result.addError(new ObjectError("Login Duplicado!", "Login já cadastrado, tente novamente"));
            return cadastrarUsuarioPage(usuario);
        }

    }

}
