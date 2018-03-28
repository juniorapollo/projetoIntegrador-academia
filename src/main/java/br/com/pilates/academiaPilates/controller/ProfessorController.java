/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pilates.academiaPilates.controller;

import br.com.pilates.academiaPilates.models.Professor;
import br.com.pilates.academiaPilates.repository.ProfessorRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author junior
 */
@Controller
public class ProfessorController {

    ProfessorRepository pr;

    @Value("${baseUrl}")
    private String baseUrl;

    @RequestMapping(path = "${baseUrl}/sistema/cadastro/professor", method = RequestMethod.GET)
    public ModelAndView listarProfessorPage() {
        ModelAndView mv = new ModelAndView("cadastro/listaProfessor");
        Iterable<Professor> professor = pr.findAll();
        mv.addObject("professor", professor);
        return mv;
    }
    
    
    /**
     * 
     * @return Lista de Professores Cadastrados
     */
    
    //@PreAuthorize("hasAnyRole('OPERADOR')")
    @RequestMapping(path = "${baseUrl}/sistema/api/professor", method = RequestMethod.GET)
    public Iterable<Professor> listarProfessors() {
        Iterable<Professor> listaProfessors = pr.findAll();
        return listaProfessors;
    }

    
    
    //@PreAuthorize("hasAnyRole('OPERADOR')")
    @RequestMapping(path = "${baseUrl}/sistema/api/professor", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE}, headers = "content-type=application/x-www-form-urlencoded , application/json", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView salvarProfessor(@Valid Professor professor, BindingResult result, RedirectAttributes atributes) {
        try {
            if (result.hasErrors()) {
                System.out.println("Erro SalvarProfessor() " + result.toString());
                return listarProfessorPage();
            }
            System.out.println("ENTROU SALVAR ALUNO");
            //UserSS user = UserService.authenticated(); //Carrega Usuário Logado no Sistema
        //    professor.setIdProfessor(null); // Setando ID Nulo para criar um novo usuário (Garantir)
            System.out.println("Professor: " + professor.toString());
            pr.save(professor);//Salva Professor criado 

            ModelAndView mv = new ModelAndView("redirect:" + baseUrl + "/sistema/cadastro/professor");
            //atributes.addFlashAttribute("mensagem", "Professor - " + professor.getIdProfessor() + " " + professor.getNome() + " salvo com sucesso.");
            return mv;
        } catch (Exception e) {
            System.out.println("Catch  ProfessorController POST:" + e);

            return listarProfessorPage();
        }

    }
    
    
    
    
    //@PreAuthorize("hasAnyRole('OPERADOR')")
    @RequestMapping(path = "${baseUrl}/sistema/api/professor", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE}, headers = "content-type=application/x-www-form-urlencoded , application/json", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView editarProfessor(@Valid Professor professor, BindingResult result, RedirectAttributes atributes) {
        try {
            if (result.hasErrors()) {
                System.out.println("Erro SalvarProfessor() " + result.toString());
                return listarProfessorPage();
            }
            System.out.println("ENTROU SALVAR Professor");
            //UserSS user = UserService.authenticated(); //Carrega Usuário Logado no Sistema
          //  professor.setIdProfessor(null); // Setando ID Nulo para criar um novo usuário (Garantir)
            System.out.println("Professor: " + professor.toString());
            pr.save(professor);//Salva Professor criado 

            ModelAndView mv = new ModelAndView("redirect:" + baseUrl + "/sistema/cadastro/professor");
            //atributes.addFlashAttribute("mensagem", "Professor - " + professor.getIdProfessor() + " " + professor.getNome() + " salvo com sucesso.");
            return mv;
        } catch (Exception e) {
            System.out.println("Catch  ProfessorController PUT:" + e);

            return listarProfessorPage();
        }

    }
    
    
   // @RequestMapping(path = "${baseUrl}/sistema/api/professor/{idProfessor}", method = RequestMethod.DELETE)
   // public ModelAndView deletarProfessor(@PathVariable("idProfessor") Long idProfessor, Professor professor, RedirectAttributes atributes) {

    //    professor = pr.findByIdProfessor(idProfessor);
       // professor.setAtivo(false);//desativa o professor
    //    pr.save(professor);

    //    ModelAndView mv = new ModelAndView();
    //    mv.setViewName("redirect:" + baseUrl + "/sistema/cadastro/professor");
        //atributes.addFlashAttribute("mensagem", "Professor  - " + professor.getIdProfessor() + " - " + professor.getNome() + " removido com sucesso.");

     //   return mv;
   // }
    
    
}
