package br.com.pilates.academiaPilates.controller;

import br.com.pilates.academiaPilates.models.Turma;
import br.com.pilates.academiaPilates.service.ClienteService;
import br.com.pilates.academiaPilates.service.TurmaService;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author daniel
 */
@RestController
@RequestMapping(path = "${baseUrl}/sistema/turma")
public class TurmaController {

    @Autowired
    TurmaService service;

    @Autowired
    ClienteService clienteService;

    @Value("${baseUrl}")
    private String baseUrl;

    @GetMapping(path = "/api/json")
    public List<Turma> jsonTurmas() {
        return service.listaTurma(true, true);
    }

    @GetMapping(path = "/id/{idTurma}")
    public Turma findId(@PathVariable("idTurma") Long id) {
        return service.turmaPorId(id);
    }

    @GetMapping
    public ModelAndView listaTurmaPage() {
        return listaTurma("true", "false");
    }

    @GetMapping(path = "/editar/{idTurma}")
    public ModelAndView editarTurmaPage(@PathVariable("idTurma") Long id) {
        return cadastroTurma(service.turmaPorId(id));
    }

    @GetMapping(path = "/ativo={ativo}/inativo={inativo}")
    public ModelAndView listaTurma(@PathVariable("ativo") String ativo, @PathVariable("inativo") String inativo) {
        ModelAndView mv = new ModelAndView("relatorio/relatorioTurmaPage");
        mv.addObject("turmas", service.listaTurma(Boolean.parseBoolean(ativo), Boolean.parseBoolean(inativo)));
        return mv;
    }

    @GetMapping(path = ("/cadastro"))
    public ModelAndView cadastroTurma(Turma turma) {
        ModelAndView mv = new ModelAndView("formularios/formTurma");
        mv.addObject("clientes", clienteService.listaClientesAtivos());
        mv.addObject("turma", turma);
        return mv;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE}, headers = "content-type=application/x-www-form-urlencoded , application/json", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView salvarTurma(@Valid Turma turma, BindingResult result, RedirectAttributes atributes) {
        try {
            if (result.hasErrors()) {
                System.out.println("Erro Salvar turma POst() " + result.toString());
                return (cadastroTurma(turma));
            }
            service.ativarTurma(turma);
            service.salvarTurma(turma, turma.getClientes());

            ModelAndView mv = new ModelAndView("redirect:" + baseUrl + "/sistema/turma");
            atributes.addFlashAttribute("mensagem", " - " + turma.getId() + " " + turma.getNome() + " salvo com sucesso.");
            return mv;
        } catch (Exception e) {
            System.out.println("Catch TurmaController POST:" + e);
            return cadastroTurma(turma);
        }

    }

    @PutMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE}, headers = "content-type=application/x-www-form-urlencoded , application/json", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView editarTurma(@Valid Turma turma, BindingResult result, RedirectAttributes atributes) {
        try {
            if (result.hasErrors()) {
                System.out.println("Erro Salvar turma Put() " + result.toString());
                return (cadastroTurma(turma));
            }
            service.ativarTurma(turma);
            service.salvarTurma(turma, turma.getClientes());

            ModelAndView mv = new ModelAndView("redirect:" + baseUrl + "/sistema/turma");
            atributes.addFlashAttribute("mensagem", " - " + turma.getId() + " " + turma.getNome() + " salvo com sucesso.");
            return mv;
        } catch (Exception e) {
            System.out.println("Catch TurmaController PUT:" + e);
            return cadastroTurma(turma);
        }

    }

    @DeleteMapping(path = ("/id/{idTurma}"))
    public ModelAndView deleteTurma(@PathVariable("idTurma") Long idTurma) {
        service.inativarTurma(service.turmaPorId(idTurma));
        ModelAndView mv = new ModelAndView("redirect:" + baseUrl + "/sistema/turma");
        return mv;
    }

}
