/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pilates.academiaPilates.Servico;

import br.com.pilates.academiaPilates.controller.ServicoController;
import br.com.pilates.academiaPilates.models.Servico;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author daniel
 */
public class ServicoControllerTest {

    @Autowired
    BindingResult bindinResult;

    @Autowired
    RedirectAttributes redirectAtributtes;

    ServicoController servicoController;
    int tamanho_listaClientes;

    List<Servico> servicos = new ArrayList<>();

    /**
     * Ação executada antes de qualquer teste.
     */
    @Before
    public void setUp() {
        /* ========== Preparação do cenário ========== */

        Servico servico01 = new Servico(1l, "Pilates", 200.00, "1:00", true);
        Servico servico02 = new Servico(2l, "Musculacao", 100.00, "2:00", true);
        Servico servico03 = new Servico(3l, "Natacao", 300.00, "00:50", true);
        Servico servico04 = new Servico(4l, "CrossFit", 80.00, "1:30", true);

        servicos.add(servico01);
        servicos.add(servico02);
        servicos.add(servico03);
        servicos.add(servico04);
        tamanho_listaClientes = servicos.size();

        System.out.println("Setup executado!");

    }

    @After
    public void tearDown() {
        servicos.clear();
        System.out.println("Limpo");
    }

    @Test
    public void testAdicionaServico() {
        /* ========== Execução ========== */
        
            Servico servico05 = new Servico(51l, "IOGA", 30.00, "1:00", false);
            System.out.println(servico05.toString());
            servicoController.salvarServico(servico05, bindinResult, redirectAtributtes);
            servicos.add(servico05);
            assertThat(servicos.size(), is(tamanho_listaClientes + 1));
            assertThat(servico05.getId(), is(51222l));
     
       
        /* ========== Verificações ========== */
    }

}
