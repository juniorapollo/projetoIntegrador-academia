
package br.com.pilates.academiaPilates.controller;

import br.com.pilates.academiaPilates.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 *
 * @author junior
 */

@Controller
public class PaginaController {

    
    AlunoRepository ar;
    
    @RequestMapping(value="${baseUrl}/sistema", method = RequestMethod.GET)
    public String index() {
        return "index/home";
    }
    
    @RequestMapping(value="${baseUrl}/login", method = RequestMethod.GET)
    public String login() {
        return "login/loginPage";
    }
    
  
    @RequestMapping(path = "${baseUrl}/sistema/cadastro/aluno-adicionar", method = RequestMethod.GET)
    public ModelAndView cadastrarAlunoPage() {
        ModelAndView mv = new ModelAndView("cadastro/adicionar/adicionarAluno");        
        
        return mv;
    }
    
    
    
}
