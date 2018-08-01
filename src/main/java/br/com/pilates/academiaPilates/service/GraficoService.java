package br.com.pilates.academiaPilates.service;

import br.com.pilates.academiaPilates.models.Agenda;
import br.com.pilates.academiaPilates.models.Cliente;
import br.com.pilates.academiaPilates.repository.AgendaRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author daniel
 */
@Service
public class GraficoService {

    @Autowired
    AgendaRepository repo;

    @Autowired
    ClienteService clienteService;

    Agenda agenda;
    Cliente cliente;

    LocalDate HOJE = LocalDate.now();

    public GraficoService() {

    }

    public Map montarGraficos() {
        String mes = HOJE.toString().substring(5, 7);
        String dataFinal = null;
        
        ArrayList<Integer[]> listaAgendados = new ArrayList<>();
        ArrayList<Integer[]> listaCancelados = new ArrayList<>();

        Map map = new HashMap();

        for (int i = 1; i <= HOJE.getMonth().maxLength(); i++) {
            Integer[] dataAgendados = new Integer[2];
            Integer[] dataCancelados = new Integer[2];

           if(i < 10){
                dataFinal = HOJE.getYear() + "-" + mes + "-0" + i;
            }else{
                dataFinal = HOJE.getYear() + "-" + mes + "-" + i;
            }
            Long agendado = repo.countByDataInicioBetweenAndCanceladoFalse(dataFinal, dataFinal);
            dataAgendados[0] = i;
            dataAgendados[1] = Integer.parseInt(Long.toString(agendado));
            listaAgendados.add(dataAgendados);

            Long cancelado = repo.countByDataInicioBetweenAndCanceladoTrue(dataFinal, dataFinal);
            dataCancelados[0] = i;
            dataCancelados[1] = Integer.parseInt(Long.toString(cancelado));
            listaCancelados.add(dataCancelados);
        }

        Long clienteIniciante = clienteService.countClientesAtivosPorNivel("INICIANTE");
        

        Long clienteIntermediario = clienteService.countClientesAtivosPorNivel("INTERMEDIARIO");
       
       
        Long clienteAvancado = clienteService.countClientesAtivosPorNivel("AVANCADO");
       
       
        map.put("agendado", listaAgendados);
        map.put("cancelado", listaCancelados);
        map.put("iniciante", clienteIniciante);
        map.put("intermediario", clienteIntermediario);
        map.put("avancado", clienteAvancado);

        return map;

    }

}