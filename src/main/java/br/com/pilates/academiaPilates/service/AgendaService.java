/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pilates.academiaPilates.service;

import br.com.pilates.academiaPilates.models.Agenda;
import br.com.pilates.academiaPilates.models.Cliente;
import br.com.pilates.academiaPilates.models.Profissional;
import br.com.pilates.academiaPilates.repository.AgendaRepository;
import br.com.pilates.academiaPilates.security.UserSS;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author daniel
 */
@Service
public class AgendaService {

    @Autowired
    public AgendaRepository repo;
    Agenda agenda;

    @Autowired
    ClienteService clienteService;

    public UserSS user() {
        return UserService.authenticated();
    }

    LocalDate HOJE = LocalDate.now(); // 2018-05-05
    
    private static final DayOfWeek DOMINGO = DayOfWeek.SUNDAY;
    private static final DayOfWeek SEGUNDA = DayOfWeek.MONDAY;
    private static final DayOfWeek TERCA = DayOfWeek.TUESDAY;
    private static final DayOfWeek QUARTA = DayOfWeek.WEDNESDAY;
    private static final DayOfWeek QUINTA = DayOfWeek.THURSDAY;
    private static final DayOfWeek SEXTA = DayOfWeek.FRIDAY;
    private static final DayOfWeek SABADO = DayOfWeek.SATURDAY;

    public Agenda agendaPorId(Long idAgenda) {

        agenda = repo.getOne(idAgenda);

        return agenda;
    }

    public Agenda salvarAgenda(Agenda agenda, RedirectAttributes atributes) {
        /*Seta Nome do Usuario que efetuou agendamento*/
        UserSS usuario = user();
        agenda.setNomeRealizouCadastro(usuario.getNome());

        /* Seta Data e Hora quando efetuou agendamento */
        agenda = setarDataHoraEfetivacaoAgendamento(agenda);

        /*Seta o tempo final do agendamento baseado no tempo de servico*/
        String[] horarioServico = agenda.getServico().getTempo().split(":");
        agenda.setEnd(agenda.getStart().plusHours(Long.parseLong(horarioServico[0])));//Hora
        agenda.setEnd(agenda.getEnd().plusMinutes(Long.parseLong(horarioServico[1])));//Minutos

        /*Seta Data/Hora Inicio e Final para realizar busca no banco */
        setarDataHoraInicioFinal(agenda);

        agenda.setTitle("Cliente: " + agenda.getCliente().getNome() + " ,Tel: " + agenda.getCliente().getCelular());

        if (!isReagendamento(agenda)) {
            if (clientePodeAgendar(agenda)) {
                repo.save(agenda);
                setAtributes(atributes, true);
            } else {
                setAtributes(atributes, false);
                return agenda;
            }

        }
        return agenda;

    }

    private Agenda setarDataHoraEfetivacaoAgendamento(Agenda agenda) {
        DateTimeFormatter formatadorData
                = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatadorHora
                = DateTimeFormatter.ofPattern("HH:mm");
        agenda.setDataRealizouCadastro(HOJE.format(formatadorData));
        agenda.setHoraRealizouCadastro(LocalTime.now().format(formatadorHora));
        return agenda;
    }

    public void setAtributes(RedirectAttributes atributes, boolean agendamentoRealizado) {
        if (agendamentoRealizado) {
            atributes.addFlashAttribute("mensagem", "Agendado com sucesso.");
        } else {
            System.out.println("Set Atrubutes Mensagem de Erro pois Cliente ja tem Agendamento  "  );
            atributes.addFlashAttribute("mensagemErro", "Cliente " + agenda.getCliente().getNome() + " já têm um horário marcado às " + agenda.getHoraInicio() + "hr do dia " + agenda.getDataInicio());
        }
    }

    public Agenda setarDataHoraInicioFinal(Agenda agenda) {
        /*Divide a DateTime em Data - Hora em um arraty de 2 Indices*/
        String[] start = agenda.getStart().toString().split("T");
        String[] end = agenda.getEnd().toString().split("T");

        agenda.setDataInicio(start[0]);
        agenda.setHoraInicio(start[1]);
        agenda.setDataFinal(end[0]);
        agenda.setHoraFinal(end[1]);

        return agenda;

    }

    public List<Agenda> listaAgendasIniciandoDoMesAtual() {
        Calendar hoje = Calendar.getInstance();

        int mes = hoje.get(Calendar.MONTH) + 1;// O primeiro mês é 0 no Calendar
        int anoInicial = hoje.get(Calendar.YEAR);
        int anoFinal = hoje.get(Calendar.YEAR) + 1;

        if (mes < 10) {
            String m = Integer.toString(mes);
            m = "0" + m;
            List<Agenda> listaAgenda = repo.findByStartBetweenAndCanceladoFalse(anoInicial + "-" + m + "-01", anoFinal + "-" + m + "01");

            return listaAgenda;

        } else {
            List<Agenda> listaAgenda = repo.findByStartBetweenAndCanceladoFalse(anoInicial + "-" + mes + "-01", anoFinal + "-" + mes + "-01");
            return listaAgenda;
        }
    }

    public ArrayList<Agenda> listaAgendaPorProfissional(Profissional profissional) {
        ArrayList<Agenda> listaAgendaProfissional = repo.findByProfissional(profissional);
        return listaAgendaProfissional;
    }

    public ArrayList<Agenda> listaAgendaPorCliente(Cliente cliente) {
        ArrayList<Agenda> listaAgendaCliente = repo.findByCliente(cliente);
        return listaAgendaCliente;
    }

    public List<Agenda> listarAgendasCanceladas() {
        List<Agenda> listaAgenda = repo.findByCanceladoTrue();
        return listaAgenda;
    }

    public LocalDate hoje() {
        return HOJE;
    }


    /*public List<Agenda> listarAgendaPorProfissionalPelaDataEHora(Profissional profissional, String horaInicio, String horaFinal, String dataInicio) {

        int minutoFinal = Integer.parseInt(horaFinal.substring(4, 5));
        minutoFinal = minutoFinal;
        horaFinal = horaFinal.substring(0, 4) + minutoFinal;
        return repo.findByProfissionalAndHoraInicioBetweenAndCanceladoFalseAndDataInicio(profissional, horaInicio, horaFinal, dataInicio);

    }

    public boolean verificaListaAgendaVazia(Profissional profissional, String horaInicio, String horaFinal, String dataInicio, Long idAgenda) {
        System.out.println("Id Agenda Service  " + idAgenda);
        // Se é nulo , é para cadastrar agenda , entao é necessario verificar disponibilidade profissional
        List<Agenda> listaAgenda = listarAgendaPorProfissionalPelaDataEHora(profissional, horaInicio, horaFinal, dataInicio);
        System.out.println("AGenda do PRofissional disponivel? " + listaAgenda.isEmpty());
        if (listaAgenda.isEmpty()) {

            return listaAgenda.isEmpty();
        } else {
            return false;
        }

    }
    
     */
    public boolean clientePodeAgendar(Agenda agenda) {
        try {
            ArrayList<Agenda> agendasDoCliente = repo.findByClienteAndDataInicio(agenda.getCliente(), agenda.getDataInicio());
            SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
            Date horaAgendaCliente = formatador.parse(agendasDoCliente.get(0).getHoraFinal());
            Date horaNovaAgenda = formatador.parse(agenda.getHoraInicio());

            return horaNovaAgenda.getTime() >= horaAgendaCliente.getTime();
        } catch (Exception e) {
//            System.out.println("Agenda Service ClientePodeAgendar() 272 - Nao achou nenhuma Agendamento do cliente");
            return true;
        }
    }

    /*
    Verica se é agendamento ou reagendamento , verificando a existencia de id 
     */
    public boolean isReagendamento(Agenda agenda) {
        return agenda.getIdAgenda() != null;
    }

    public Long qtdAgendaMesAtual() {
        String mes = HOJE.toString().substring(5, 7);
        String dataInicioMes = HOJE.getYear() + "-" + mes + "-01";
        String dataFinalMes = HOJE.getYear() + "-" + mes + "-" + HOJE.getMonth().maxLength();
        Long qtd = repo.countByDataInicioBetween(dataInicioMes, dataFinalMes);
        HOJE.getMonth();
        DateTimeFormatter formatador
                = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return qtd;
    }

    public List<DayOfWeek> getProximoDia(String[] diasDaSemana) {
        List<DayOfWeek> proximoDia = new ArrayList<>();
       
        for (String diasDaSemana1 : diasDaSemana) {
           
            switch (diasDaSemana1) {
                case "01":
                    proximoDia.add(DOMINGO);
                    break;
                case "02":
                    proximoDia.add(SEGUNDA);
                    break;
                case "03":
                    proximoDia.add(TERCA);
                    break;
                case "04":
                    proximoDia.add(QUARTA);
                    break;
                case "05":
                    proximoDia.add(QUINTA);
                    break;
                case "06":
                    proximoDia.add(SEXTA);
                    break;
                case "07":
                    proximoDia.add(SABADO);
                    break;
                default:
                    break;
            }
        }
  
        return proximoDia;
    }
}


