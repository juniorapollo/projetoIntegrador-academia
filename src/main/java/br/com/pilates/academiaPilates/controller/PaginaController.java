
package br.com.pilates.academiaPilates.controller;

import br.com.pilates.academiaPilates.models.Usuario;
import br.com.pilates.academiaPilates.repository.AlunoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 *
 * @author junior
 */

@Controller
public class PaginaController {

    public PaginaController() {
    }

    
   
    AlunoRepository ar;
    
    @GetMapping(value="${baseUrl}/sistema")
    public String index() {
        return "index/home";
    }
    
    @GetMapping(value="${baseUrl}/entrar")
    public String login() {
        return "login/login";
    }
    
      
    @GetMapping(path = "${baseUrl}/sistema/cadastro/aluno-adicionar")
    public ModelAndView cadastrarAlunoPage() {
        ModelAndView mv = new ModelAndView("/cadastro/adicionar/adicionarAluno");
        return mv;
    }
    
    
    
    
    
}
