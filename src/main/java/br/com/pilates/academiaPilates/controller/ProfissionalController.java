/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pilates.academiaPilates.controller;

import br.com.pilates.academiaPilates.models.Profissional;
import br.com.pilates.academiaPilates.models.Servico;
import br.com.pilates.academiaPilates.models.enums.Perfil;

import br.com.pilates.academiaPilates.repository.ProfissionalRepository;
import br.com.pilates.academiaPilates.service.ServicoService;
import java.util.Objects;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
 * @author junior
 */
@RestController
@RequestMapping(path="${baseUrl}/sistema/profissional")
public class ProfissionalController {

    @Autowired
    BCryptPasswordEncoder senha;

    @Autowired
    ProfissionalRepository profissionalRepository;
    Profissional profissional;

    @Value("${baseUrl}")
    private String baseUrl;

    PaginaController pagina;
    
    @Autowired
    ServicoService servicoService;
    Servico servico;
    

    //@PreAuthorize("hasAnyRole('OPERADOR')")
    @GetMapping()
    public Iterable<Profissional> profissionais() {
        Iterable<Profissional> profissionais = profissionalRepository.findAll();
        return profissionais;
    }

    /* Pagina Cadastro Profissional / Usuario  */

    @GetMapping(path = "/cadastro")
    public ModelAndView cadastrarProfissionalPage(Profissional profissional) {
        ModelAndView mv = new ModelAndView("formularios/formCadastroProfissional");
       mv.addObject("servicos" , servicoService.listaServicosAtivos());
        mv.addObject("profissional", profissional);
        
        return mv;
    }
    
    
    /* Pagina Editar Usuário  */
    @GetMapping(path = "/editar/{idProfissional}")
    public ModelAndView editarProfissionalPage(@PathVariable("idProfissional") Long idProfissional) {
        this.profissional = profissionalRepository.findOne(idProfissional);
        
        return cadastrarProfissionalPage(profissional);
    }
    

    //@PreAuthorize("hasAnyRole('SUPERVISOR')")
    @PostMapping( consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE}, headers = "content-type=application/x-www-form-urlencoded , application/json", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView salvarProfissional(@Valid Profissional profissional,BindingResult result, RedirectAttributes atributes) {
        System.out.println("ProfissionalController: POST");
        System.out.println("Servicos " +profissional.getServicos());
        try {
            
            if (result.hasErrors()) {
                System.out.println("Erro SalvarProfissional() " + result.toString());
                return cadastrarProfissionalPage(profissional);
            }

            switch (profissional.getNivelProfissional()) {
                case "OPERADOR":
                    profissional.addPerfil(Perfil.OPERADOR);
                    break;
                case "PROFESSOR":
                    profissional.addPerfil(Perfil.PROFESSOR);
                    break;
                case "ADMINISTRADOR":
                    profissional.addPerfil(Perfil.OPERADOR);
                    profissional.addPerfil(Perfil.PROFESSOR);
                    profissional.addPerfil(Perfil.ADMINISTRADOR);
                    break;
                default:
                    break;
            }

            profissional.setId(null); // Setando ID Nulo para criar um novo usuário (Garantir)
            profissional.setSenha(senha.encode(profissional.getSenha()));// Criptografando a senha para salvar no banco   
            profissional.setAtivo(true); // Usuário Criado ja Ativo para Usar o Sistema 

            System.out.println(profissional.toString());
           
            profissionalRepository.save(profissional);//Salva Usuário criado 

            ModelAndView mv = new ModelAndView("redirect:" + baseUrl + "/sistema/profissional/relatorio");
            atributes.addFlashAttribute("mensagem", "Profissional - " + profissional.getId() + " " + profissional.getNome() + " salvo com sucesso.");
            
            return mv;
        } catch (DataIntegrityViolationException e) {
            System.out.println("Catch  Violação de Integridade ProfissionalController POST:" + e);
            result.addError(new ObjectError("Login Duplicado!", "Login já cadastrado, tente novamente"));
            return cadastrarProfissionalPage(profissional);
        }

    }

    //@PreAuthorize("hasAnyRole('SUPERVISOR')")
    @PutMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE}, headers = "content-type=application/x-www-form-urlencoded , application/json", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView editarProfissional(@Valid Profissional profissional, BindingResult result, RedirectAttributes atributes) {
        System.out.println("ProfissionalController: PUT");
        
        try {
            if (result.hasErrors()) {
                return cadastrarProfissionalPage(profissional);
            }

            /* verifica se a senha foi modificaca */
            this.profissional = profissionalRepository.getOne(profissional.getId());
            if (!(Objects.equals(this.profissional.getSenha(), profissional.getSenha()))) {
                /* Criptografando a senha para salvar no banco  */
                profissional.setSenha(senha.encode(profissional.getSenha())); 
            }

            switch (profissional.getNivelProfissional()) {
                case "OPERADOR":
                    profissional.addPerfil(Perfil.OPERADOR);
                    break;
                case "PROFESSOR":
                    profissional.addPerfil(Perfil.PROFESSOR);
                    break;
                case "ADMINISTRADOR":
                    profissional.addPerfil(Perfil.OPERADOR);
                    profissional.addPerfil(Perfil.PROFESSOR);
                    profissional.addPerfil(Perfil.ADMINISTRADOR);
                    break;
                default:
                    break;
            }

            //UserSS user = UserService.authenticated(); //Carrega Usuário Logado no Sistema*/
            //profissional.setEmpresa(empresaResource.buscarEmpresaId(user.getEmpresa().getIdEmpresa())); //Setando empresa do Profissional Logado
            System.out.println("Profissional Editado: " + profissional.toString());
            profissionalRepository.save(profissional);//Salva Usuário criado 

            ModelAndView mv = new ModelAndView("redirect:" + baseUrl + "/sistema/profissional/relatorio");
            atributes.addFlashAttribute("mensagem", "Usuário - " + profissional.getId() + " " + profissional.getNome() + " Editado com sucesso.");
            return mv;
        } catch (DataIntegrityViolationException e) {
            System.out.println("Catch  Violação de Integridade ProfissionalController PUT:" + e);
            result.addError(new ObjectError("Login Duplicado!", "Login já cadastrado, tente novamente"));
            return cadastrarProfissionalPage(profissional);
        }

    }

    
    @GetMapping(path = "/relatorio")
    public ModelAndView relatorioProfissionalPage() {
        ModelAndView mv = new ModelAndView("relatorio/relatorioProfissionalPage");
        
        
        mv.addObject("profissionais", profissionais());
        
        return mv;
    }
    
    
    @DeleteMapping(path = "/{idProfissional}")
    public ModelAndView desativarProfissional(@PathVariable("idProfissional") Long idProfissional, Profissional profissional, RedirectAttributes atributes) {
        String mensagem;
        
        profissional = profissionalRepository.findOne(idProfissional);
        if (profissional.isAtivo()) {
            mensagem="INATIVADO";
            profissional.setAtivo(false);
        } else {
            mensagem="ATIVADO";
            profissional.setAtivo(true);
        }

        profissionalRepository.save(profissional);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:" + baseUrl + "/sistema/profissional/relatorio");
        atributes.addFlashAttribute("mensagem",profissional.getNome() +" foi "+ mensagem + " com sucesso.");
        return mv;
    }
}
