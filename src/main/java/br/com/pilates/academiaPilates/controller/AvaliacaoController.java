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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
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

//    /* Json de todas Avaliações por cliente */
//    @GetMapping(path = "${baseUrl}/sistema/api/avaliacao/cliente/{idCliente}")
//    public Iterable<Avaliacao> listarAvaliacaoPorIdCliente(@PathVariable("idCliente") Long idCliente) {
//        cliente = ar.findOne(idCliente);
//        return avaliacaoRepository.findByCliente(cliente);
//    }
//,headers = "content-type=multipart/*"
    @GetMapping(path = "${baseUrl}/sistema/api/avaliacao/{idCliente}")
    public ModelAndView retornoPagina(@PathVariable("idCliente") Long idCliente) {
        ModelAndView mv = new ModelAndView("redirect:" + baseUrl + "/sistema/cliente/cadastro/" + idCliente);
        return mv;
    }

    @PostMapping(path = "${baseUrl}/sistema/api/avaliacao/{idCliente}", headers = "content-type=application/x-www-form-urlencoded , application/json, multipart/*", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView salvarAvaliacao(Avaliacao avaliacao, BindingResult result, @PathVariable("idCliente") Long idCliente, RedirectAttributes atributes) {
        Data dataHora = new Data();
        ArrayList<Avaliacao> listaAvaliacao = avaliacaoRepository.findByCliente(avaliacao.getCliente());
        cliente = ar.getOne(idCliente);
        boolean a = true;
        try {
            if (result.hasErrors()) {
                System.out.println("Erro SALVAR AVALIACAO() " + result.toString());
                return ac.consultarClientePage();
            }
//            System.out.println("ENTROU ADICIONAR AVALIACAO " + avaliacao.getAnamnese());
            //UserSS user = UserService.authenticated(); //Carrega Usuário Logado no Sistema
            //cliente.setIdCliente(null); // Setando ID Nulo para criar um novo usuário (Garantir)

            avaliacao.setDataAvaliacao(dataHora.getDataAtual());
            avaliacao.setHora(dataHora.getHoraAtual());
            avaliacao.setCliente(ar.getOne(idCliente));
            avaliacaoRepository.save(avaliacao);//Salva Avaliacao criada 

            //galeriaFoto.setAvaliacao(avaliacao);
            //gr.save(galeriaFoto);
            //System.out.println(avaliacao.toString());
            ModelAndView mv = new ModelAndView("relatorio/mostrarCadastroPage");

            Iterable<Cliente> listaClientes = ar.findAll();
            mv.addObject("listaClientes", listaClientes);
            mv.addObject("avaliacao", avaliacao);
            mv.addObject("cliente", cliente);
            mv.addObject("abrirModalAposSalvarAvaliacao", true);

            //atributes.addFlashAttribute("mensagem", "Cliente - " + cliente.getIdCliente() + " " + cliente.getNome() + " editado com sucesso.");
            return mv;
        } catch (Exception e) {
            System.out.println("Catch  Avaliacao POST:" + e);

            return ac.consultarClientePage();
        }

    }

    @PutMapping(path = "${baseUrl}/sistema/api/avaliacao/{idAvaliacao}")
    public ModelAndView editarAvaliacao(Avaliacao avaliacao, @PathVariable("idAvaliacao") Long idAvaliacao, GaleriaFoto galeriaFoto, BindingResult result, RedirectAttributes atributes) {

        ArrayList<Avaliacao> listaAvaliacao = avaliacaoRepository.findByCliente(avaliacao.getCliente());
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
//            avaliacaoRepository.save(avaliacao); //Salva Avaliacao
//            ModelAndView mv = new ModelAndView("redirect:" + baseUrl + "/sistema/cliente/cadastro/" + avaliacao.getCliente().getId());
            ModelAndView mv = new ModelAndView("relatorio/mostrarCadastroPage");
            Iterable<Cliente> listaClientes = ar.findAll();
            mv.addObject("listaClientes", listaClientes);
//            mv.addObject("avaliacao", avaliacao);
            mv.addObject("cliente", avaliacao.getCliente());
            mv.addObject("listaAvaliacao", listaAvaliacao);
            mv.addObject("listaAvaliacao", listaAvaliacao);
            mv.addObject("abrirModalAposSalvarAvaliacao", true);
//            atributes.addFlashAttribute("mensagem", "Avaliaçãoo - " + avaliacao.getCliente().getNome() + " editado com sucesso!");
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

    @PostMapping(path = "${baseUrl}/sistema/api/avaliacao/adicionarfoto/{idAvaliacao}")
    public ModelAndView salvarFotosAvaliacao(MultipartHttpServletRequest request, @PathVariable("idAvaliacao") Long idAvaliacao) throws IOException, ServletException {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao = avaliacaoRepository.findOne(idAvaliacao);
        System.out.println("ID AVALIACAO = " + avaliacao.getId());
        int qtdFotos = request.getParts().size() - 1;
        List<String> fotos = new ArrayList<>();
        ArrayList<Avaliacao> listaAvaliacao = avaliacaoRepository.findByCliente(avaliacao.getCliente());

        try {

            System.out.println("ENTROU ADICIONAR FOTOS ");
            try {

                Iterator<String> itr = request.getFileNames();
                int cont = 0;
                while (itr.hasNext()) {

                    String uploadedFile = itr.next();
                    MultipartFile file = request.getFile(uploadedFile);
                    String mimeType = file.getContentType();
                    String filename = file.getOriginalFilename();

                    byte[] bytes = file.getBytes();

                    StringBuilder sb = new StringBuilder();
                    sb.append("data:image/png;base64,");
                    String base64 = "data:image/png;base64," + Base64.getEncoder().encodeToString(bytes);
                    System.out.println("sb.toString()");

                    System.out.println("uploadedFile " + uploadedFile);
                    System.out.println("file " + file);
                    System.out.println("mimeType " + mimeType);
                    System.out.println("filename " + filename);
                    System.out.println("Base64 " + base64);
                    fotos.add(cont, base64);
                    avaliacao.setFotos(fotos);
                    avaliacaoRepository.save(avaliacao);
                    cont++;

//  GaleriaFoto newFile = new GaleriaFoto(null, mimeType, bytes, null);
                    //gr.save(newFile);
                }
            } catch (Exception e) {
                System.out.println("Catch Salvar Foto : " + e);
            }

            System.out.println("AVALIACAO ANTES _ " + avaliacao.getId() + avaliacao.getFotos().get(0));

            System.out.println("TAMANHO = " + avaliacao.getFotos().size());
            System.out.println("AVALIACAO DEPOIS _ " + avaliacao.getId() + avaliacao.getFotos().get(0));

            ModelAndView mv = new ModelAndView("relatorio/mostrarCadastroPage");
            Iterable<Cliente> listaClientes = ar.findAll();
            mv.addObject("listaClientes", listaClientes);
            mv.addObject("avaliacao", avaliacao);
            mv.addObject("cliente", cliente);
            mv.addObject("listaAvaliacao", listaAvaliacao);
            mv.addObject("abrirModalAposSalvarAvaliacao", false);
            //atributes.addFlashAttribute("mensagem", "Cliente - " + cliente.getIdCliente() + " " + cliente.getNome() + " editado com sucesso.");
            return mv;
        } catch (Exception e) {
            System.out.println("Catch  Avaliacao POST:" + e);
            ModelAndView mv = new ModelAndView("relatorio/mostrarCadastroPage");
            Iterable<Cliente> listaClientes = ar.findAll();
            mv.addObject("listaClientes", listaClientes);
            return mv;
        }

    }

}
