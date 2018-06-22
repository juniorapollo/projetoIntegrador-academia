/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pilates.academiaPilates.controller;

import br.com.pilates.academiaPilates.models.PreCadastro;
import br.com.pilates.academiaPilates.repository.PreCadastroRepository;
import br.com.pilates.academiaPilates.service.PreCadastroService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author daniel
 */
@RestController
@RequestMapping(path = "${baseUrl}/sistema/preCadastro")
public class PreCadastroController {
    
    @Value("${baseUrl}")
    private String baseUrl;
    
    PreCadastro pc;

    @Autowired
    PreCadastroService service;
    
    @GetMapping()
    public ModelAndView listaPreCadastro() {
        ModelAndView mv = new ModelAndView("relatorio/relatorioPreCadastroPage");
        mv.addObject("preCadastros", service.preCadastros(true));
        return mv;
    }
    @GetMapping(path = "/inativos")
    public ModelAndView listaPreCadastroInativos() {
        ModelAndView mv = new ModelAndView("relatorio/relatorioPreCadastroPage");
        mv.addObject("preCadastros", service.preCadastros(false));
        return mv;
    }
    
    
    @GetMapping(path = "/cadastro")
    public ModelAndView formPreCadastro(PreCadastro preCadastro) {
        ModelAndView mv = new ModelAndView("formularios/formPreCadastro");
        mv.addObject("preCadastro", preCadastro);

        return mv;
    }
    
    @PostMapping( consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE}, headers = "content-type=application/x-www-form-urlencoded , application/json", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView salvarPreCadastro(@Valid PreCadastro preCadastro, BindingResult result, RedirectAttributes atributes) {
        try {
            if (result.hasErrors()) {
                System.out.println("Erro SalvarPreCadastro() " + result.toString());
                return formPreCadastro(preCadastro);
            }
            System.out.println("ENTROU SALVAR ALUNO");
            //UserSS user = UserService.authenticated(); //Carrega Usuário Logado no Sistema
            // preCadastro.setIdPreCadastro(null); // Setando ID Nulo para criar um novo usuário (Garantir)
            System.out.println("PreCadastro: " + preCadastro.toString());
            preCadastro.setAtivo(true);
            service.salvar(preCadastro,true);//Salva PreCadastro criado 

            ModelAndView mv = new ModelAndView("redirect:" + baseUrl + "/sistema/preCadastro/");
            atributes.addFlashAttribute("mensagem", " - " + preCadastro.getId() + " " + preCadastro.getNome() + " salvo com sucesso.");
            return mv;
        } catch (Exception e) {
            System.out.println("Catch  PreCadastroController POST:" + e);

            return formPreCadastro(preCadastro);
        }

    }
    
    @DeleteMapping(path = "/{id}")
    public ModelAndView desativarAluno(@PathVariable("id") Long id, PreCadastro preCadastro, RedirectAttributes atributes) {
        String mensagem = "desativado";
        
       this.pc = service.finById(id);
       service.apagar(pc, false);

        
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:" + baseUrl + "/sistema/preCadastro");
        atributes.addFlashAttribute("mensagem",preCadastro.getNome() +" foi "+ mensagem + " com sucesso.");
        return mv;
    }
    
    
}