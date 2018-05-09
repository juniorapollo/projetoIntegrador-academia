/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pilates.academiaPilates.controller;

import br.com.pilates.academiaPilates.models.Cliente;
import br.com.pilates.academiaPilates.models.Atividades;
import br.com.pilates.academiaPilates.models.RelatorioDiario;
import br.com.pilates.academiaPilates.repository.ClienteRepository;
import br.com.pilates.academiaPilates.repository.AtividadesRepository;
import br.com.pilates.academiaPilates.repository.ServicoRepository;
import br.com.pilates.academiaPilates.service.ClienteService;
import br.com.pilates.academiaPilates.service.AtividadesService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
@RequestMapping(path = "${baseUrl}/sistema/relatorioDiario")
public class RelatorioDiarioController {

    @Value("${baseUrl}")
    private String baseUrl;

    
    @Autowired
    AtividadesService atividadesService;
    Atividades atividades;

    @Autowired
    ClienteService clienteService;
    Cliente cliente;

    
    RelatorioDiario relatorioDiario;

    @GetMapping(path = "/cadastro/clientes/")
    public ModelAndView pageRelatorioDiarioEscolherCliente(Cliente cliente) {
        ModelAndView mv = new ModelAndView("relatorioDiario/relatorioDiario");
        List<Cliente> listaClientes = clienteService.listaClientesAtivos();
        List<Atividades> listaAtividades = atividadesService.listaAtividades();

        if (cliente.getId() == null) {
            mv.addObject("listaClientes", listaClientes);

        } else if (listaAtividades != null) {

            mv.addObject("listaAtividades", listaAtividades);
            mv.addObject("cliente", cliente);

        }
        return mv;
    }

    @GetMapping(path = "/cadastro/clientes/{idCliente}")
    public ModelAndView pageRelatorioDiarioEscolherAtividades(@PathVariable("idCliente") Long idCliente) {
        ModelAndView mv = new ModelAndView("relatorioDiario/relatorioDiario");

        cliente = clienteService.clientePorId(idCliente);

        mv.addObject("cliente", cliente);

        List<Atividades> listaAtividades = atividadesService.listaAtividadesPorNivelCliente(cliente.getNivelCliente());
        List<Cliente> listaClientes = clienteService.listaClientesAtivos();

        mv.addObject("atividades", listaAtividades);
        mv.addObject("listaClientes", listaClientes);
        relatorioDiario = new RelatorioDiario();
        mv.addObject("relatorioDiario", relatorioDiario);
       
        
        return mv;
    }
    
    
    @PostMapping( path = "/cadastro" , consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE}, headers = "content-type=application/x-www-form-urlencoded , application/json", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView salvarRelatorioDiario(@Valid RelatorioDiario relatorioDiario,BindingResult result, RedirectAttributes atributes) {
        System.out.println("RelatorioDiarioController : POST");
        System.out.println("Atividades " +relatorioDiario.getAtividades());
        try {
            
            if (result.hasErrors()) {
                System.out.println("Erro SalvarProfissional() " + result.toString());
                return null;
            }

            

            relatorioDiario.setId(null); // Setando ID Nulo para criar um novo usuário (Garantir)
             System.out.println(relatorioDiario.toString());
           
           
            ModelAndView mv = new ModelAndView("redirect:" + baseUrl + "/sistema/relatorioDiario/cadastro/clientes/");
            atributes.addFlashAttribute("mensagem", "Relatorio do Cliente " + relatorioDiario.getCliente().getNome() + " salvo com sucesso.");
            
            return mv;
        } catch (DataIntegrityViolationException e) {
            System.out.println("Catch  Violação de Integridade ProfissionalController POST:" + e);
            result.addError(new ObjectError("Login Duplicado!", "Login já cadastrado, tente novamente"));
            return null;
        }

    }

}
