/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pilates.academiaPilates.controller;

import br.com.pilates.academiaPilates.models.Servico;
import br.com.pilates.academiaPilates.repository.ServicoRepository;
import br.com.pilates.academiaPilates.service.ServicoService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author daniel
 */
@RestController
@RequestMapping(path = "${baseUrl}/sistema/servico")
public class ServicoController {

    @Value("${baseUrl}")
    private String baseUrl;

    @Autowired
    ServicoService servicoService;
    Servico servico;

    @Autowired
    ServicoRepository sr;

    @GetMapping(path = "/")
    public List<Servico> servicosJson(Servico servico) {

        List<Servico> listaServicos = servicoService.listaServicosAtivos();

        return listaServicos;
    }

    @GetMapping(path = "/cadastro")
    public ModelAndView pageCadastroServico(Servico servico) {
        ModelAndView mv = new ModelAndView("formularios/formServico");
        Iterable<Servico> listaServicos = servicoService.listaServicosAtivos();

        mv.addObject("listaServicos", listaServicos);
        mv.addObject("servico", servico);

        return mv;
    }

    @PostMapping(path = "/cadastro", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE}, headers = "content-type=application/x-www-form-urlencoded , application/json", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView salvarServico(Servico servico, BindingResult result, RedirectAttributes atributes) {
        System.out.println("ENTROU SALVAR Servico");
        try {
            if (result.hasErrors()) {
                System.out.println("Erro Salvar Servicos() " + result.toString());
                return pageCadastroServico(servico);
            }

            System.out.println("Servico: " + servico.toString());
            servico.setAtivo(true);
            sr.save(servico);//Salva Cliente criado 
            System.out.println("Servico: " + servico.toString());

            ModelAndView mv = new ModelAndView("redirect:" + baseUrl + "/sistema/servico/cadastro");
            atributes.addFlashAttribute("mensagem", "Servico - " + servico.getId() + " " + servico.getDescricao() + " salvo com sucesso.");
            return mv;
        } catch (Exception e) {
            System.out.println("Catch  Servico Controller POST:" + e);

            return pageCadastroServico(servico);
        }

    }

    @DeleteMapping(path = "/excluir/{idServico}")
    public ModelAndView excluirServico(@PathVariable("idServico") Long idServico, Servico servico, RedirectAttributes atributes) {

        servico = sr.findOne(idServico);

        servico.setAtivo(false);
        sr.save(servico);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:" + baseUrl + "/sistema/servico/cadastro");
        atributes.addFlashAttribute("mensagem", servico.getDescricao() + " excluído com sucesso.");
        return mv;
    }

    @GetMapping(path = "/editar/{idServico}")
    public ModelAndView editarServico(@PathVariable("idServico") Long idServico, Servico servico, RedirectAttributes atributes) {

        servico = sr.findOne(idServico);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:" + baseUrl + "/sistema/servico/cadastro");
        mv.addObject("servico", servico);
        return mv;
    }
}
