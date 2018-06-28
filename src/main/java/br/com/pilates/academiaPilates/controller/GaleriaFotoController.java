/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pilates.academiaPilates.controller;

import java.util.Base64;
import java.util.Iterator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.pilates.academiaPilates.models.GaleriaFoto;
import br.com.pilates.academiaPilates.repository.GaleriaFotoRepository;

/**
 *
 * @author daniel
 */
@RestController
public class GaleriaFotoController {

    @Value("${baseUrl}")
    private String baseUrl;

    @Autowired
    GaleriaFotoRepository gr;

    // @PreAuthorize("hasAnyRole('OPERADOR')")
    @RequestMapping(path = "${baseUrl}/sistema/api/galeriaFoto", method = RequestMethod.POST, consumes = {
            MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            MediaType.APPLICATION_JSON_VALUE }, headers = "content-type=application/x-www-form-urlencoded , application/json", produces = {
                    MediaType.APPLICATION_JSON_VALUE })
    public ModelAndView salvarFotos(@Valid GaleriaFoto galeriaFoto, BindingResult result,
            RedirectAttributes atributes) {
        try {
            if (result.hasErrors()) {
                System.out.println("Erro SALVAR FOTO() " + result.toString());
                return null;
            }
            System.out.println("ENTROU SALVAR FOTO");
            // UserSS user = UserService.authenticated(); //Carrega Usuário Logado no
            // Sistema
            // professor.setIdProfessor(null); // Setando ID Nulo para criar um novo usuário
            // (Garantir)
            System.out.println("DESCRICAO " + galeriaFoto.getDescricao());
            gr.save(galeriaFoto);// Salva Professor criado

            ModelAndView mv = new ModelAndView("redirect:" + baseUrl + "/sistema/fragments/uploadImage");
            // atributes.addFlashAttribute("mensagem", "Professor - " +
            // professor.getIdProfessor() + " " + professor.getNome() + " salvo com
            // sucesso.");
            return mv;
        } catch (Exception e) {
            System.out.println("Catch  ProfessorController POST:" + e);

            return null;
        }

    }

    // @PreAuthorize("hasAnyRole('OPERADOR')")
    @PostMapping(path = "${baseUrl}/fotos/enviar")
    public ResponseEntity TestesalvarFotos(MultipartHttpServletRequest request,
            @ModelAttribute("nome") String qtdSemanas) {
        System.out.println("TESTETESTE " + qtdSemanas);
        try {
            Iterator<String> itr = request.getFileNames();

            while (itr.hasNext()) {
                String uploadedFile = itr.next();
                MultipartFile file = request.getFile(uploadedFile);
                String mimeType = file.getContentType();
                String filename = file.getOriginalFilename();

                byte[] bytes = file.getBytes();

                StringBuilder sb = new StringBuilder();
                sb.append("data:image/png;base64,");
                String a = "data:image/png;base64," + Base64.getEncoder().encodeToString(bytes);
                System.out.println("sb.toString()");

                System.out.println("uploadedFile " + uploadedFile);
                System.out.println("file " + file);
                System.out.println("mimeType " + mimeType);
                System.out.println("filename " + filename);
                System.out.println("Base64 " + a);

                // GaleriaFoto newFile = new GaleriaFoto(null, mimeType, bytes, null);

                // gr.save(newFile);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("{}", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("{}", HttpStatus.OK);

    }

}
