/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pilates.academiaPilates.controller;

import br.com.pilates.academiaPilates.models.Aluno;
import br.com.pilates.academiaPilates.models.Avaliacao;
import br.com.pilates.academiaPilates.repository.AlunoRepository;
import br.com.pilates.academiaPilates.repository.AvaliacaoRepository;
import java.time.LocalDateTime;
import java.time.ZoneId;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class AvaliacaoController {

    @Value("${baseUrl}")
    private String baseUrl;

    @Autowired
    AvaliacaoRepository avaliacaoRepository;

    @Autowired
    AlunoRepository ar;
    Aluno aluno;

    @RequestMapping(path = "${baseUrl}/sistema/api/avaliacao", method = RequestMethod.GET)
    public Iterable<Avaliacao> listarAvaliacao() {
        Iterable<Avaliacao> listaAvaliacao = avaliacaoRepository.findAll();
        return listaAvaliacao;
    }

    // @PreAuthorize("hasAnyRole('OPERADOR')")
    @RequestMapping(path = "${baseUrl}/sistema/cadastro/aluno2", method = RequestMethod.GET)
    public ModelAndView listarAlunoPage2() {
        ModelAndView mv = new ModelAndView("formularios/mostrarCadastroPage");
        Iterable<Aluno> aluno = ar.findAll();
        mv.addObject("aluno", aluno);
        return mv;
    }

    @RequestMapping(path = "${baseUrl}/sistema/api/avaliacao/{idAluno}", method = RequestMethod.POST)
    public ModelAndView salvarAvaliacao(@Valid Avaliacao avaliacao, @PathVariable("idAluno") Long idAluno, BindingResult result, RedirectAttributes atributes) {
        {
            try {
                if (result.hasErrors()) {
                    System.out.println("Erro EditarAluno() " + result.toString());
                    return listarAlunoPage2();
                }
                System.out.println("ENTROU ADICIONAR AVALIACAO");
                //UserSS user = UserService.authenticated(); //Carrega Usuário Logado no Sistema
                //   aluno.setIdAluno(null); // Setando ID Nulo para criar um novo usuário (Garantir)
                System.out.println("Avaliação: " + avaliacao.toString());
                 
                avaliacao.setData(LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))); 
                avaliacao.setAluno(ar.getOne(idAluno));
                System.out.println("ALUNO = " + avaliacao.getAluno().getNome());
                avaliacaoRepository.save(avaliacao);//Salva Aluno criado 

                ModelAndView mv = new ModelAndView("redirect:" + baseUrl + "/sistema/cadastro/aluno2");
                //   atributes.addFlashAttribute("mensagem", "Aluno - " + aluno.getIdAluno() + " " + aluno.getNome() + " editado com sucesso.");
                return mv;
            } catch (Exception e) {
                System.out.println("Catch  Avaliacao POST:" + e);

                return listarAlunoPage2();
            }
        }
    }

}
