/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pilates.academiaPilates.controller;

import br.com.pilates.academiaPilates.models.Aluno;
import br.com.pilates.academiaPilates.models.Pessoa;
import br.com.pilates.academiaPilates.repository.AlunoRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
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
public class AlunoController {

    Aluno aluno;
    
    @Autowired
    AlunoRepository ar;

    @Value("${baseUrl}")
    private String baseUrl;

    /**
     *
     * @return ModelAndView (html) , que vai listar os alunos cadastrados
     */
    // @PreAuthorize("hasAnyRole('OPERADOR')")
    @RequestMapping(path = "${baseUrl}/sistema/cadastro/aluno", method = RequestMethod.GET)
    public ModelAndView listarAlunoPage() {
        ModelAndView mv = new ModelAndView("cadastro/listaAluno");
        Iterable<Aluno> alunos = ar.findAll();
        mv.addObject("aluno", alunos);
        return mv;
    }
    
    // @PreAuthorize("hasAnyRole('OPERADOR')")
    @RequestMapping(path = "${baseUrl}/sistema/cadastro/aluno2", method = RequestMethod.GET)
    public ModelAndView listarAlunoPage2() {
        ModelAndView mv = new ModelAndView("cadastro/listaAluno2");
        Iterable<Aluno> aluno = ar.findAll();
        mv.addObject("aluno", aluno);
        return mv;
    }

    //@PreAuthorize("hasAnyRole('OPERADOR')")
    @RequestMapping(path = "${baseUrl}/sistema/api/aluno", method = RequestMethod.GET)
    public Iterable<Aluno> listarAlunos() {
        Iterable<Aluno> listaAlunos = ar.findAll();
        return listaAlunos;
    }

    //@PreAuthorize("hasAnyRole('OPERADOR')")
    @RequestMapping(path = "${baseUrl}/sistema/api/aluno", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE}, headers = "content-type=application/x-www-form-urlencoded , application/json", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView salvarAluno(@Valid Aluno aluno, BindingResult result, RedirectAttributes atributes) {
        try {
            if (result.hasErrors()) {
                System.out.println("Erro SalvarAluno() " + result.toString());
                return listarAlunoPage();
            }
            System.out.println("ENTROU SALVAR ALUNO");
            //UserSS user = UserService.authenticated(); //Carrega Usuário Logado no Sistema
           // aluno.setIdAluno(null); // Setando ID Nulo para criar um novo usuário (Garantir)
            System.out.println("Aluno: " + aluno.toString());
            
            ar.save(aluno);//Salva Aluno criado 

            ModelAndView mv = new ModelAndView("redirect:" + baseUrl + "/sistema/cadastro/aluno");
           // atributes.addFlashAttribute("mensagem", "Aluno - " + aluno.getIdAluno() + " " + aluno.getNome() + " salvo com sucesso.");
            return mv;
        } catch (Exception e) {
            System.out.println("Catch  AlunoController POST:" + e);

            return listarAlunoPage();
        }

    }
    
    
    //@PreAuthorize("hasAnyRole('OPERADOR')")
    @RequestMapping(path = "${baseUrl}/sistema/api/aluno", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE}, headers = "content-type=application/x-www-form-urlencoded , application/json", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView editarAluno(@Valid Aluno aluno, BindingResult result, RedirectAttributes atributes) {
        try {
            if (result.hasErrors()) {
                System.out.println("Erro EditarAluno() " + result.toString());
                return listarAlunoPage();
            }
            System.out.println("ENTROU EDITAR ALUNO");
            //UserSS user = UserService.authenticated(); //Carrega Usuário Logado no Sistema
         //   aluno.setIdAluno(null); // Setando ID Nulo para criar um novo usuário (Garantir)
            System.out.println("Aluno: " + aluno.toString());
            ar.save(aluno);//Salva Aluno criado 

            ModelAndView mv = new ModelAndView("redirect:" + baseUrl + "/sistema/cadastro/aluno");
         //   atributes.addFlashAttribute("mensagem", "Aluno - " + aluno.getIdAluno() + " " + aluno.getNome() + " editado com sucesso.");
            return mv;
        } catch (Exception e) {
            System.out.println("Catch  AlunoController PUT:" + e);

            return listarAlunoPage();
        }

    }
    
    
   // @RequestMapping(path = "${baseUrl}/sistema/api/aluno/{idAluno}", method = RequestMethod.DELETE)
   // public ModelAndView deletarAluno(@PathVariable("idAluno") Long idAluno, Aluno aluno, RedirectAttributes atributes) {

      //  aluno = ar.findByIdAluno(idAluno);
   //     aluno.setAtivo(false);//desativa o aluno
  //      ar.save(aluno);

    //    ModelAndView mv = new ModelAndView();
   //     mv.setViewName("redirect:" + baseUrl + "/sistema/cadastro/aluno");
       // atributes.addFlashAttribute("mensagem", "Aluno  - " + aluno.getIdAluno() + " - " + aluno.getNome() + " removido com sucesso.");

  //      return mv;
  //  }
    

}
