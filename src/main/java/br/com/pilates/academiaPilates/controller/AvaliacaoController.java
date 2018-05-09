/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pilates.academiaPilates.controller;

import br.com.pilates.academiaPilates.models.Cliente;
import br.com.pilates.academiaPilates.models.Avaliacao;
import br.com.pilates.academiaPilates.models.Data;
import br.com.pilates.academiaPilates.models.GaleriaFoto;
import br.com.pilates.academiaPilates.repository.ClienteRepository;
import br.com.pilates.academiaPilates.repository.AvaliacaoRepository;
import br.com.pilates.academiaPilates.repository.GaleriaFotoRepository;
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
import org.springframework.web.bind.annotation.RequestBody;
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
    Avaliacao avaliacao;

    @Autowired
    ClienteRepository ar;
    Cliente cliente;

    @Autowired
    GaleriaFotoRepository gr;
    
  ClienteController ac;
  
   

    /* Json de todas Avaliações */
    @GetMapping(path = "${baseUrl}/sistema/api/avaliacao")
    public Iterable<Avaliacao> listarAvaliacao() {
        Iterable<Avaliacao> listaAvaliacao = avaliacaoRepository.findAll();
        return listaAvaliacao;
    }

    /* Json de todas Avaliações por cliente */
    @GetMapping(path = "${baseUrl}/sistema/api/avaliacao/cliente/{idCliente}")
    public Iterable<Avaliacao> listarAvaliacaoPorIdCliente(@PathVariable("idCliente") Long idCliente) {
        cliente = ar.findOne(idCliente);
        return avaliacaoRepository.findByCliente(cliente);
    }

    

    

    @PostMapping(path = "${baseUrl}/sistema/api/avaliacao/{idCliente}", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE}, headers = "content-type=application/x-www-form-urlencoded , application/json", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView salvarAvaliacao(Avaliacao avaliacao, @PathVariable("idCliente") Long idCliente, GaleriaFoto galeriaFoto, BindingResult result, RedirectAttributes atributes) {
    Data dataHora = new Data();
        try {
            if (result.hasErrors()) {
                System.out.println("Erro salvarAvaliacao() " + result.toString());
                return ac.consultarClientePage();
            }
            
            
            System.out.println("ENTROU ADICIONAR AVALIACAO");
            //UserSS user = UserService.authenticated(); //Carrega Usuário Logado no Sistema
            //   cliente.setIdCliente(null); // Setando ID Nulo para criar um novo usuário (Garantir)
            System.out.println("DATA . "+dataHora.getDataAtual());
            avaliacao.setDataAvaliacao(dataHora.getDataAtual());
            avaliacao.setHora(dataHora.getHoraAtual());

            avaliacao.setCliente(ar.getOne(idCliente));
            avaliacaoRepository.save(avaliacao);//Salva Avaliacao criada 
            
          
            galeriaFoto.setAvaliacao(avaliacao);
            
            gr.save(galeriaFoto);
            
            System.out.println(avaliacao.toString());
            

            ModelAndView mv = new ModelAndView("redirect:" + baseUrl + "/sistema/cliente/cadastro/" + avaliacao.getCliente().getId());
            //   atributes.addFlashAttribute("mensagem", "Cliente - " + cliente.getIdCliente() + " " + cliente.getNome() + " editado com sucesso.");
            return mv;
        } catch (Exception e) {
            System.out.println("Catch  Avaliacao POST:" + e);

            return ac.consultarClientePage();
        }

    }

    @PutMapping(path = "${baseUrl}/sistema/api/avaliacao/{idAvaliacao}")
    public ModelAndView editarAvaliacao(Avaliacao avaliacao, @PathVariable("idAvaliacao") Long idAvaliacao, GaleriaFoto galeriaFoto, BindingResult result, RedirectAttributes atributes) {
        
        try {
            if (result.hasErrors()) {
                System.out.println("Erro EditarAvaliacao() " + result.toString());
                return ac.consultarClientePage();
            }
            System.out.println("ENTROU Editar AVALIACAO");
            
            avaliacao.setCliente(this.avaliacaoRepository.findOne(idAvaliacao).getCliente());
            avaliacao.setFotos(this.avaliacaoRepository.findOne(idAvaliacao).getFotos());
            
            System.out.println(avaliacao.toString());
            
            
            
            //UserSS user = UserService.authenticated(); //Carrega Usuário Logado no Sistema
            
            avaliacaoRepository.save(avaliacao); //Salva Avaliacao
            
          
            ModelAndView mv = new ModelAndView("redirect:" + baseUrl + "/sistema/cliente/cadastro/" + avaliacao.getCliente().getId());
            //   atributes.addFlashAttribute("mensagem", "Cliente - " + cliente.getIdCliente() + " " + cliente.getNome() + " editado com sucesso.");
            return mv;
        } catch (Exception e) {
            System.out.println("Catch  Avaliacao PUT:" + e);

            return ac.consultarClientePage();
        }

    }

    @DeleteMapping(path = "${baseUrl}/sistema/api/avaliacao", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE}, headers = "content-type=application/x-www-form-urlencoded , application/json", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView testeSalvarAvalicao(@RequestBody @Valid GaleriaFoto galeria) {

        try {
            ModelAndView mv = new ModelAndView("redirect:" + baseUrl + "/sistema/fragments/uploadImage/");
            //   atributes.addFlashAttribute("mensagem", "Cliente - " + cliente.getIdCliente() + " " + cliente.getNome() + " editado com sucesso.");
            return mv;
        } catch (Exception e) {
            System.out.println("Catch  TESTE  IMAGEM :" + e);

            return ac.consultarClientePage();
        }

    }

}
