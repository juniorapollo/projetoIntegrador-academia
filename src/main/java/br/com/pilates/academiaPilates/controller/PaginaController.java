package br.com.pilates.academiaPilates.controller;

import br.com.pilates.academiaPilates.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author junior
 */
@Controller
public class PaginaController {
    
    @Autowired
    ClienteService clienteService;

    public PaginaController() {
    }
    
    @GetMapping(value = "${baseUrl}/sistema")
    public ModelAndView index() {
        Long qtdClientes = clienteService.countClientesAtivos();
        ModelAndView mv  = new ModelAndView("index/home");
        mv.addObject("qtdClientes",qtdClientes);
        return mv;
    }

    @GetMapping(value = "${baseUrl}/entrar")
    public String login() {
        return "login/login";
    }
    
    @GetMapping(path = "${baseUrl}/sistema/fragments/painelCadastro")
    public ModelAndView painelCadastroPage() {
        ModelAndView mv = new ModelAndView("/fragments/painelCadastro");
        return mv;
    }

    @RequestMapping("${baseUrl}/sistema/fragments/menu")
    public ModelAndView abrirMenu() {
        ModelAndView mv = new ModelAndView("/formularios/formServico");
        return mv;
    }
    
    @RequestMapping("${baseUrl}/sistema/fragments/uploadImage")
    public ModelAndView pageUploadImage() {
        ModelAndView mv = new ModelAndView("/uploadImage/uploadImage");
        return mv;
    }
    
    @RequestMapping("${baseUrl}/fotos")
    public ModelAndView fotos() {
        ModelAndView mv = new ModelAndView("/testeFotos/fotos");
        return mv;
    }
    
     @RequestMapping("${baseUrl}/sistema/profile")
    public ModelAndView profile() {
        ModelAndView mv = new ModelAndView("/profile/profile");
        return mv;
    }
    

}
