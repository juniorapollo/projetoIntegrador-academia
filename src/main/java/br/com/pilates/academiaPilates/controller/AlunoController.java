/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

package br.com.pilates.academiaPilates.controller;

import br.com.pilates.academiaPilates.models.Aluno;
import br.com.pilates.academiaPilates.models.Avaliacao;
import br.com.pilates.academiaPilates.repository.AlunoRepository;
import br.com.pilates.academiaPilates.repository.AvaliacaoRepository;
import java.util.ArrayList;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping(path = "${baseUrl}/sistema/aluno")
public class AlunoController {

    Aluno aluno;

    @Autowired
    AlunoRepository ar;
    
    @Autowired
    AvaliacaoRepository avaliacaoRepository;
    Avaliacao avaliacao;

    @Value("${baseUrl}")
    private String baseUrl;

  
    // @PreAuthorize("hasAnyRole('OPERADOR')") 
    @GetMapping(path="/relatorio")
    public ModelAndView listarAlunoPage(Aluno aluno) {
        ModelAndView mv = new ModelAndView("cadastro/listaAluno");
        Iterable<Aluno> listaAlunos = ar.findAll();

        mv.addObject("listaAlunos", listaAlunos);
        mv.addObject("aluno", aluno);

        return mv;
    }
    
   
    // @PreAuthorize("hasAnyRole('OPERADOR')")
    @GetMapping(path = "/consultar")
    public ModelAndView consultarAlunoPage() {
        ModelAndView mv = new ModelAndView("relatorio/mostrarCadastroPage");
        Iterable<Aluno> listaAlunos = ar.findAll();
        mv.addObject("listaAlunos", listaAlunos);
        return mv;
    }
    
    // @PreAuthorize("hasAnyRole('OPERADOR')")
    @GetMapping(path = "/cadastro/{idAluno}")
    public ModelAndView consultarAlunoPageRelatorio(@PathVariable("idAluno") Long idAluno) {
        ModelAndView mv = new ModelAndView("relatorio/mostrarCadastroPage");
        
        aluno = ar.findOne(idAluno);
        Iterable<Aluno> listaAlunos = ar.findAll();
       // ArrayList<Avaliacao> listaAvaliacao = avaliacaoRepository.findByAluno(aluno);
        
        mv.addObject("avaliacao", avaliacao);
        
        mv.addObject("listaAlunos", listaAlunos);
        mv.addObject("aluno", aluno);
//        mv.addObject("listaAvaliacao", listaAvaliacao);

        return mv;
    }
    
    
    @GetMapping(path = "/cadastro")
    public ModelAndView cadastrarAlunoPage(Aluno aluno) {
        ModelAndView mv = new ModelAndView("formularios/formAluno");
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
    @PostMapping( consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE}, headers = "content-type=application/x-www-form-urlencoded , application/json", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView salvarAluno(@Valid Aluno aluno, BindingResult result, RedirectAttributes atributes) {
        try {
            if (result.hasErrors()) {
                System.out.println("Erro SalvarAluno() " + result.toString());
                return cadastrarAlunoPage(aluno);
            }
            System.out.println("ENTROU SALVAR ALUNO");
            //UserSS user = UserService.authenticated(); //Carrega Usuário Logado no Sistema
            // aluno.setIdAluno(null); // Setando ID Nulo para criar um novo usuário (Garantir)
            System.out.println("Aluno: " + aluno.toString());
            aluno.setAtivo(true);
            ar.save(aluno);//Salva Aluno criado 

            ModelAndView mv = new ModelAndView("redirect:" + baseUrl + "/sistema/aluno/cadastro");
            atributes.addFlashAttribute("mensagem", "Aluno - " + aluno.getId() + " " + aluno.getNome() + " salvo com sucesso.");
            return mv;
        } catch (Exception e) {
            System.out.println("Catch  AlunoController POST:" + e);

            return cadastrarAlunoPage(aluno);
        }

    }

 
    
    
    //@PreAuthorize("hasAnyRole('OPERADOR')")
    @PutMapping( consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE}, headers = "content-type=application/x-www-form-urlencoded , application/json", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView editarAluno(@Valid Aluno aluno, BindingResult result, RedirectAttributes atributes) {
        try {
            if (result.hasErrors()) {
                System.out.println("Erro EditarAluno() " + result.toString());
                return consultarAlunoPageRelatorio(aluno.getId());
            }
            System.out.println("ENTROU EDITAR ALUNO");
            //UserSS user = UserService.authenticated(); //Carrega Usuário Logado no Sistema
            //   aluno.setIdAluno(null); // Setando ID Nulo para criar um novo usuário (Garantir)
            System.out.println("Aluno: " + aluno.toString());
            ar.save(aluno);//Salva Aluno criado 

            ModelAndView mv = new ModelAndView("redirect:" + baseUrl + "/sistema/aluno/cadastro/"+aluno.getId());
            atributes.addFlashAttribute("mensagem", "Aluno   " + aluno.getNome() + " editado com sucesso.");
            return mv;
        } catch (Exception e) {
            System.out.println("Catch  AlunoController PUT:" + e);

            return consultarAlunoPageRelatorio(aluno.getId());
        }

    }

    @DeleteMapping(path = "/{idAluno}")
    public ModelAndView desativarAluno(@PathVariable("idAluno") Long idAluno, Aluno aluno, RedirectAttributes atributes) {
        String mensagem;
        
        aluno = ar.findOne(idAluno);
        if (aluno.isAtivo()) {
            mensagem="INATIVADO";
            aluno.setAtivo(false);//desativa o aluno
        } else {
            mensagem="ATIVADO";
            aluno.setAtivo(true);//desativa o aluno
        }

        ar.save(aluno);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:" + baseUrl + "/sistema/aluno/relatorio");
        atributes.addFlashAttribute("mensagem",aluno.getNome() +" foi "+ mensagem + " com sucesso.");
        return mv;
    }
    
    @GetMapping(path="/ativos")
    public ModelAndView listarAlunoAtivoPage(Aluno aluno) {
        ModelAndView mv = new ModelAndView("cadastro/listaAluno");
        Iterable<Aluno> listaAlunos = ar.findByAtivoTrue();

        mv.addObject("listaAlunos", listaAlunos);
        mv.addObject("aluno", aluno);

        return mv;
    }
    
    @GetMapping(path="/inativos")
    public ModelAndView listarAlunoInativoPage(Aluno aluno) {
        ModelAndView mv = new ModelAndView("cadastro/listaAluno");
        Iterable<Aluno> listaAlunos = ar.findByAtivoFalse();

        mv.addObject("listaAlunos", listaAlunos);
        mv.addObject("aluno", aluno);

        return mv;
    }
}
*/