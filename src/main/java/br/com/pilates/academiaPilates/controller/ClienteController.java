/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pilates.academiaPilates.controller;

import br.com.pilates.academiaPilates.models.Cliente;
import br.com.pilates.academiaPilates.models.Avaliacao;
import br.com.pilates.academiaPilates.repository.ClienteRepository;
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

/**
 *
 * @author junior
 */
@RestController
@RequestMapping(path = "${baseUrl}/sistema/cliente")
public class ClienteController {

    Cliente cliente;

    @Autowired
    ClienteRepository ar;
    
    @Autowired
    AvaliacaoRepository avaliacaoRepository;
    Avaliacao avaliacao;

    @Value("${baseUrl}")
    private String baseUrl;

    /**
     *
     * @param cliente
     * @return ModelAndView (html) , que vai listar os clientes cadastrados
     */
    // @PreAuthorize("hasAnyRole('OPERADOR')") 
    @GetMapping(path="/relatorio")
    public ModelAndView listarClientePage(Cliente cliente) {
        ModelAndView mv = new ModelAndView("cadastro/listaCliente");
        Iterable<Cliente> listaClientes = ar.findAll();

        mv.addObject("listaClientes", listaClientes);
        mv.addObject("cliente", cliente);

        return mv;
    }
    
     /* Abre a página para consultar cliente cadastrados ativos , */
    // @PreAuthorize("hasAnyRole('OPERADOR')")
    @GetMapping(path = "/consultar")
    public ModelAndView consultarClientePage() {
        ModelAndView mv = new ModelAndView("relatorio/mostrarCadastroPage");
        Iterable<Cliente> listaClientes = ar.findAll();
        mv.addObject("listaClientes", listaClientes);
        return mv;
    }
    
    // @PreAuthorize("hasAnyRole('OPERADOR')")
    @GetMapping(path = "/cadastro/{idCliente}")
    public ModelAndView consultarClientePageRelatorio(@PathVariable("idCliente") Long idCliente) {
        ModelAndView mv = new ModelAndView("relatorio/mostrarCadastroPage");
        
        cliente = ar.findOne(idCliente);
        Iterable<Cliente> listaClientes = ar.findAll();
        ArrayList<Avaliacao> listaAvaliacao = avaliacaoRepository.findByCliente(cliente);
        
        mv.addObject("avaliacao", avaliacao);
        
        mv.addObject("listaClientes", listaClientes);
        mv.addObject("cliente", cliente);
        mv.addObject("listaAvaliacao", listaAvaliacao);

        return mv;
    }
    
    
    @GetMapping(path = "/cadastro")
    public ModelAndView cadastrarClientePage(Cliente cliente) {
        ModelAndView mv = new ModelAndView("formularios/formCliente");
        mv.addObject("cliente", cliente);

        return mv;
    }
    

    /**
     *
     * @return Lista de Clientes em Json
     */
    //@PreAuthorize("hasAnyRole('OPERADOR')")
    @RequestMapping(path = "${baseUrl}/sistema/api/cliente", method = RequestMethod.GET)
    public Iterable<Cliente> listarClientes() {
        Iterable<Cliente> listaClientes = ar.findAll();
        return listaClientes;
    }

    /**
     *
     * @param cliente
     * @param result
     * @param atributes
     * @return 
     */
    //@PreAuthorize("hasAnyRole('OPERADOR')")
    @PostMapping( consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE}, headers = "content-type=application/x-www-form-urlencoded , application/json", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView salvarCliente(@Valid Cliente cliente, BindingResult result, RedirectAttributes atributes) {
        try {
            if (result.hasErrors()) {
                System.out.println("Erro SalvarCliente() " + result.toString());
                return cadastrarClientePage(cliente);
            }
            System.out.println("ENTROU SALVAR ALUNO");
            //UserSS user = UserService.authenticated(); //Carrega Usuário Logado no Sistema
            // cliente.setIdCliente(null); // Setando ID Nulo para criar um novo usuário (Garantir)
            System.out.println("Cliente: " + cliente.toString());
            cliente.setAtivo(true);
            ar.save(cliente);//Salva Cliente criado 

            ModelAndView mv = new ModelAndView("redirect:" + baseUrl + "/sistema/cliente/cadastro");
            atributes.addFlashAttribute("mensagem", "Cliente - " + cliente.getId() + " " + cliente.getNome() + " salvo com sucesso.");
            return mv;
        } catch (Exception e) {
            System.out.println("Catch  ClienteController POST:" + e);

            return cadastrarClientePage(cliente);
        }

    }

    /**
     *
     * @param cliente
     * @param result
     * @param atributes
     * @return
     */
    
    
    //@PreAuthorize("hasAnyRole('OPERADOR')")
    @PutMapping( consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE}, headers = "content-type=application/x-www-form-urlencoded , application/json", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView editarCliente(@Valid Cliente cliente, BindingResult result, RedirectAttributes atributes) {
        try {
            if (result.hasErrors()) {
                System.out.println("Erro EditarCliente() " + result.toString());
                return consultarClientePageRelatorio(cliente.getId());
            }
            System.out.println("ENTROU EDITAR ALUNO");
            //UserSS user = UserService.authenticated(); //Carrega Usuário Logado no Sistema
            //   cliente.setIdCliente(null); // Setando ID Nulo para criar um novo usuário (Garantir)
            System.out.println("Cliente: " + cliente.toString());
            ar.save(cliente);//Salva Cliente criado 

            ModelAndView mv = new ModelAndView("redirect:" + baseUrl + "/sistema/cliente/cadastro/"+cliente.getId());
            atributes.addFlashAttribute("mensagem", "Cliente   " + cliente.getNome() + " editado com sucesso.");
            return mv;
        } catch (Exception e) {
            System.out.println("Catch  ClienteController PUT:" + e);

            return consultarClientePageRelatorio(cliente.getId());
        }

    }

    @DeleteMapping(path = "/{idCliente}")
    public ModelAndView desativarCliente(@PathVariable("idCliente") Long idCliente, Cliente cliente, RedirectAttributes atributes) {
        String mensagem;
        
        cliente = ar.findOne(idCliente);
        if (cliente.isAtivo()) {
            mensagem="INATIVADO";
            cliente.setAtivo(false);//desativa o cliente
        } else {
            mensagem="ATIVADO";
            cliente.setAtivo(true);//desativa o cliente
        }

        ar.save(cliente);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:" + baseUrl + "/sistema/cliente/relatorio");
        atributes.addFlashAttribute("mensagem",cliente.getNome() +" foi "+ mensagem + " com sucesso.");
        return mv;
    }
    
    @GetMapping(path="/ativos")
    public ModelAndView listarClienteAtivoPage(Cliente cliente) {
        ModelAndView mv = new ModelAndView("cadastro/listaCliente");
        Iterable<Cliente> listaClientes = ar.findByAtivoTrueOrderByNomeAsc();

        mv.addObject("listaClientes", listaClientes);
        mv.addObject("cliente", cliente);

        return mv;
    }
    
    @GetMapping(path="/inativos")
    public ModelAndView listarClienteInativoPage(Cliente cliente) {
        ModelAndView mv = new ModelAndView("cadastro/listaCliente");
        Iterable<Cliente> listaClientes = ar.findByAtivoFalse();

        mv.addObject("listaClientes", listaClientes);
        mv.addObject("cliente", cliente);

        return mv;
    }
}
