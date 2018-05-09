/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pilates.academiaPilates.controller;

import br.com.pilates.academiaPilates.models.Atividades;

import br.com.pilates.academiaPilates.repository.AtividadesRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
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
@RequestMapping(path="${baseUrl}/sistema/atividades")
public class AtividadesController {
    
    
    @Value("${baseUrl}")
    private String baseUrl;
    
    @Autowired
    AtividadesRepository repo;
    Atividades atividades;
    
     @GetMapping(path="/cadastro")
    public ModelAndView pageCadastroAtividades(Atividades atividade) {
        ModelAndView mv = new ModelAndView("formularios/formAtividades");
        Iterable<Atividades> listaAtividades = repo.findAll();

            mv.addObject("listaAtividades", listaAtividades);
            
        return mv;
    }
    
    
    
    
    
    @PostMapping(path="/cadastro" , consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE}, headers = "content-type=application/x-www-form-urlencoded , application/json", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView salvarServico(@Valid Atividades atividades, BindingResult result, RedirectAttributes atributes) {
        try {
            if (result.hasErrors()) {
                System.out.println("Erro Salvar Atividades() " + result.toString());
                return pageCadastroAtividades(atividades);
            }
            System.out.println("ENTROU SALVAR atividade");
            System.out.println("Atividades : " + atividades.toString());
          
            repo.save(atividades);//Salva Aluno criado 
            System.out.println("Atividades : " + atividades.toString());

            ModelAndView mv = new ModelAndView("redirect:" + baseUrl + "/sistema/atividades/cadastro");
            atributes.addFlashAttribute("mensagem", "atividade - " + atividades.getId() + " " + atividades.getNome()+ " salvo com sucesso.");
            return mv;
        } catch (Exception e) {
            System.out.println("Catch  atividade Controller POST:" + e);

            return pageCadastroAtividades(atividades);
        }

    }
    
    @GetMapping(path="/editar/{idAtividades}")
    public ModelAndView editarServico(@PathVariable("idAtividades") Long idAtividades, Atividades atividades, RedirectAttributes atributes) {
            
        atividades = repo.findOne(idAtividades);       

        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:" + baseUrl + "/sistema/atividades/cadastro");
        mv.addObject("atividades", atividades);
        return mv;
    }
    
    @GetMapping(path="/json/{nivel}")
    public ModelAndView listarServicosPorNivel(@PathVariable("nivel") String nivel, RedirectAttributes atributes) {
            
        List<Atividades> atividades = repo.findByNivel(nivel);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:" + baseUrl + "/sistema/atividades/cadastro");
        mv.addObject("atividades", atividades);
        return mv;
    }
}
