/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pilates.academiaPilates.controller;

import br.com.pilates.academiaPilates.models.Agenda;
import br.com.pilates.academiaPilates.models.Cliente;
import br.com.pilates.academiaPilates.models.Profissional;
import br.com.pilates.academiaPilates.models.Servico;
import br.com.pilates.academiaPilates.repository.AgendaRepository;
import br.com.pilates.academiaPilates.security.UserSS;
import br.com.pilates.academiaPilates.service.AgendaService;
import br.com.pilates.academiaPilates.service.ClienteService;
import br.com.pilates.academiaPilates.service.GraficoService;
import br.com.pilates.academiaPilates.service.ProfissionalService;
import br.com.pilates.academiaPilates.service.ServicoService;
import br.com.pilates.academiaPilates.service.UserService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
@RequestMapping(path = "${baseUrl}/sistema/agenda")
public class AgendaController {

    LocalDate HOJE = LocalDate.now(); // 2018-05-05
    private static final DayOfWeek DOMINGO = DayOfWeek.SUNDAY;
    private static final DayOfWeek SEGUNDA = DayOfWeek.MONDAY;
    private static final DayOfWeek TERCA = DayOfWeek.TUESDAY;
    private static final DayOfWeek QUARTA = DayOfWeek.WEDNESDAY;
    private static final DayOfWeek QUINTA = DayOfWeek.THURSDAY;
    private static final DayOfWeek SEXTA = DayOfWeek.FRIDAY;
    private static final DayOfWeek SABADO = DayOfWeek.SATURDAY;

    @Value("${baseUrl}")
    private String baseUrl;

    @Autowired
    AgendaService agendaService;

    @Autowired
    AgendaRepository ar;

    @Autowired
    ProfissionalService profissionalService;
    Profissional profissional;

    @Autowired
    ClienteService clienteService;
    Cliente cliente;

    @Autowired
    ServicoService servicoService;
    Servico servico;
    
    @Autowired
    GraficoService graficoService;

    public UserSS user() {
        return UserService.authenticated();
    }

    @GetMapping(value = "/all")
    public List<Agenda> jsonAgenda() {
        try {
            List<Agenda> agendas = agendaService.listaAgendasIniciandoDoMesAtual();

            return agendas;

        } catch (Exception ioex) {
            System.out.println(ioex.getMessage());
        }
        return null;
    }

    @GetMapping(value = "/all/cancelado")
    public List<Agenda> jsonAgendaCancelada() {
        try {
            List<Agenda> agendas = agendaService.listarAgendasCanceladas();

            return agendas;

        } catch (Exception ioex) {
            System.out.println(ioex.getMessage());
        }
        return null;
    }

    @GetMapping(value = "/json-montar-agenda-por-cliente/{idCliente}")
    public ArrayList<Agenda> jsonMontarAgendaPorCliente(@PathVariable("idCliente") Long idCliente) {

        try {
            ArrayList<Agenda> agendas = new ArrayList<Agenda>();
            agendas = agendaService.listaAgendaPorCliente(clienteService.clientePorId(idCliente));

            return agendas;

        } catch (Exception ioex) {
            System.out.println(ioex.getMessage());
        }
        return null;
    }

    @GetMapping(value = "/json-montar-agenda-por-profissional/{idProfissional}")
    public ArrayList<Agenda> jsonMontarAgendaPorProfissional(@PathVariable("idProfissional") Long idProfissional) {

        try {
            ArrayList<Agenda> agendas = new ArrayList<Agenda>();
            agendas = agendaService.listaAgendaPorProfissional(profissionalService.profissionalPorId(idProfissional));

            return agendas;

        } catch (Exception ioex) {
            System.out.println(ioex.getMessage());
        }
        return null;
    }

    @GetMapping(value = "")
    public ModelAndView montarAgenda() {
        ModelAndView mv = new ModelAndView("agenda/agenda");
        Agenda agenda = new Agenda();
        mv.addObject("agenda", agenda);
        List<Agenda> agendas = agendaService.listaAgendasIniciandoDoMesAtual();
        mv.addObject("listaProfissional", profissionalService.listaProfissionalAtivo());
        mv.addObject("listaCliente", clienteService.listaClientesAtivos());
        mv.addObject("listaServico", servicoService.listaServicosAtivos());

        mv.addObject("linkMontarAgenda", "./agenda/all");
        mv.addObject("linkEditarAgenda", "./agenda/editar/");
        mv.addObject("todasAgendas", agendas);
        mv.addObject("abrirModal", false);

        return mv;
    }

    @GetMapping(value = "/cancelado")
    public ModelAndView montarAgendaCancelada() {
        ModelAndView mv = new ModelAndView("agenda/agenda");
        Agenda agenda = new Agenda();
        mv.addObject("agenda", agenda);
        List<Agenda> agendas = agendaService.listarAgendasCanceladas();
        mv.addObject("listaProfissional", profissionalService.listaProfissionalAtivo());
        mv.addObject("listaCliente", clienteService.listaClientesAtivos());
        mv.addObject("listaServico", servicoService.listaServicosAtivos());

        mv.addObject("linkMontarAgenda", ".././agenda/all/cancelado");
        mv.addObject("linkEditarAgenda", ".././agenda/editar/");
        mv.addObject("todasAgendas", agendas);
        mv.addObject("abrirModal", false);

        return mv;
    }

    @GetMapping(value = "/editar/{idAgenda}")
    public ModelAndView montarAgendaParaEditar(@PathVariable("idAgenda") Long idAgenda) {
        ModelAndView mv = new ModelAndView("agenda/agenda");

        Agenda agenda = new Agenda();
        agenda = agendaService.agendaPorId(idAgenda);
        mv.addObject("agenda", agenda);

        mv.addObject("listaCliente", clienteService.listaClientesAtivos());
        mv.addObject("listaProfissional", profissionalService.listaProfissionalAtivo());
        mv.addObject("cliente", agenda.getCliente());
        mv.addObject("listaServico", servicoService.listaServicosAtivos());

        List<Agenda> agendas = agendaService.listaAgendasIniciandoDoMesAtual();
        mv.addObject("todasAgendas", agendas);
        mv.addObject("linkMontarAgenda", "./all");
        mv.addObject("abrirModal", true);
        mv.addObject("nomeRealizouCadastro", agenda.getNomeRealizouCadastro());
        mv.addObject("dataRealizouCadastro", agenda.getDataRealizouCadastro());
        mv.addObject("horaRealizouCadastro", agenda.getHoraRealizouCadastro());

        return mv;
    }

    @GetMapping(value = "/profissional/{idProfissional}")
    public ModelAndView montarAgendaPorProfissional(@PathVariable("idProfissional") Long idProfissional) {
        ModelAndView mv = new ModelAndView("agenda/agenda");

        Agenda agenda = new Agenda();
        mv.addObject("agenda", agenda);
        profissional = profissionalService.profissionalPorId(idProfissional);
        mv.addObject("listaCliente", clienteService.listaClientesAtivos());
        mv.addObject("listaProfissional", profissionalService.listaProfissionalAtivo());
        mv.addObject("profissional", profissional);
        mv.addObject("agendaProfissional", agendaService.listaAgendaPorProfissional(profissional));
        System.out.println("AGenda por profissional : " + profissional);

        mv.addObject("listaServico", servicoService.listaServicosAtivos());

        mv.addObject("linkMontarAgenda", ".././json-montar-agenda-por-profissional/" + idProfissional);
        mv.addObject("linkEditarAgenda", ".././editar/");
        mv.addObject("abrirModal", false);
        return mv;
    }

    @GetMapping(value = "/cliente/{idCliente}")
    public ModelAndView montarAgendaPorCliente(@PathVariable("idCliente") Long idCliente) {
        ModelAndView mv = new ModelAndView("agenda/agenda");

        Agenda agenda = new Agenda();
        cliente = clienteService.clientePorId(idCliente);
        agenda.setCliente(cliente);
        mv.addObject("agenda", agenda);

        mv.addObject("listaCliente", clienteService.listaClientesAtivos());
        mv.addObject("listaProfissional", profissionalService.listaProfissionalAtivo());
        mv.addObject("cliente", cliente);
        mv.addObject("agendaCliente", agendaService.listaAgendaPorCliente(cliente));
        mv.addObject("listaServico", servicoService.listaServicosAtivos());

        mv.addObject("linkMontarAgenda", ".././json-montar-agenda-por-cliente/" + idCliente);
        mv.addObject("linkEditarAgenda", ".././editar/");
        mv.addObject("abrirModal", false);

        return mv;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE}, headers = "content-type=application/x-www-form-urlencoded , application/json", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView salvarAgenda(@Valid Agenda agenda, BindingResult result, RedirectAttributes atributes, @ModelAttribute("qtdSemanas") String qtdSemanas) {
        try {
            ModelAndView mv = new ModelAndView("redirect:" + baseUrl + "/sistema/agenda");

            if (result.hasErrors()) {
                System.out.println("Erro Salvar Agenda() " + result.toString());
                return montarAgenda();
            }
            UserSS usuario = user();
            agendaService.setarDataHoraInicioFinal(agenda);

            if (!agendaService.isReagendamento(agenda)) {
                System.out.println("Não é Reagendamento");
                if (!agendaService.clientePodeAgendar(agenda)) {
                    atributes.addFlashAttribute("mensagemErro", "Cliente " + agenda.getCliente().getNome() + " já têm um horário marcado entre  " + agenda.getHoraInicio() + " às " + agenda.getHoraFinal() + " horas no dia " + agenda.getDataInicio());
                    return mv;
                }
            }

            if (agenda.getIdAgenda() == null) {
                agenda.setColor("#439247");
            } else {
                agenda.setColor("");
            }

            agenda.setCancelado(false);
            agenda.setTitle(agenda.getProfissional().getNome() + " , Cliente: " + agenda.getCliente().getNome() + " , Serviço: " + agenda.getServico() + " ,  Tel: " + agenda.getCliente().getCelular());
            agenda.setNomeRealizouCadastro(usuario.getNome());
//          System.out.println("Nome Quem Realizou Cadastro = " + agenda.getNomeRealizouCadastro());
            ar.save(agenda);//Salva agenda criado 

            int qtdSemana = Integer.parseInt(qtdSemanas);
            System.out.println("Qtd Semanas " + qtdSemanas);
            if (qtdSemana > 0) {
                
                String horaEnd = null;
                LocalDate proximoDiaEscolhido = agendaService.proximoDataParaDia(agenda.getStart(), agendaService.diaSemana(agenda.getStart()));
                String horaStart = agenda.getStart().substring(11, 16);
                try {
                    horaEnd = agenda.getEnd().substring(12, 17);
                } catch (Exception e) {
                    horaEnd = agenda.getEnd().substring(11, 16);
                }

                for (int i = 0; i < qtdSemana; i++) {
                    Agenda a = new Agenda();
                    
                    a.setIdAgenda(null);
                    a.setCancelado(false);
                    a.setCliente(agenda.getCliente());
                    a.setColor("#439247");

                    a.setNomeRealizouCadastro(usuario.getNome());
                    a.setProfissional(agenda.getProfissional());
                    a.setServico(agenda.getServico());
                    a.setTitle(agenda.getTitle());
                    a.setDataRealizouCadastro(agenda.getDataRealizouCadastro());
                    a.setHoraRealizouCadastro(agenda.getHoraRealizouCadastro());

                    a.setStart(proximoDiaEscolhido + " " + horaStart);
                    a.setEnd(proximoDiaEscolhido + " " + horaEnd);

                    if (!agendaService.isReagendamento(a)) {
                        System.out.println("Não é Reagendamento");
                        if (!agendaService.clientePodeAgendar(a)) {
                            atributes.addFlashAttribute("mensagemErro", "Cliente " + a.getCliente().getNome() + " já têm um horário marcado entre  " + a.getHoraInicio() + " às " + a.getHoraFinal() + " horas no dia " + a.getDataInicio());
                            return mv;
                        }
                    }
                    agendaService.setarDataHoraInicioFinal(a);
                    ar.save(a);
                    proximoDiaEscolhido = proximoDiaEscolhido.plusDays(7);
                     System.out.println(i + " Loop");   
//                      System.out.println("Proximas datas " + proximoDiaEscolhido);

                }

            }

            atributes.addFlashAttribute("mensagem", "Agendado com sucesso.");
            return mv;
        } catch (Exception e) {
            System.out.println("Catch  Agenda Controller POST:" + e);
            atributes.addFlashAttribute("mensagem", "Agendado com sucesso.");
            return montarAgenda();
        }

    }

    @DeleteMapping(value = "/{idAgenda}", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE}, headers = "content-type=application/x-www-form-urlencoded , application/json", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView apagarAgenda(RedirectAttributes atributes, @PathVariable("idAgenda") Long idAgenda, @ModelAttribute("motivoCancelamento") String motivoCancelamento) {

        Agenda agenda = ar.getOne(idAgenda);
        UserSS usuario = user();

        String data = "dd/MM/yyyy";
        String h = "h:mm - a";
        String hoje, hora;
        java.util.Date agora = new java.util.Date();;
        SimpleDateFormat formata = new SimpleDateFormat(data);
        hoje = formata.format(agora);
        formata = new SimpleDateFormat(h);
        hora = formata.format(agora);

        agenda.setNomeRealizouCancelamento(usuario.getNome());
        agenda.setDataCancelamento(hoje);
        agenda.setHoraCancelamento(hora);
        agenda.setColor("#fb6565");
        agenda.setCancelado(true);
        agenda.setMotivoCancelamento(motivoCancelamento);
        ar.save(agenda);
        ModelAndView mv = new ModelAndView("redirect:" + baseUrl + "/sistema/agenda");
        atributes.addFlashAttribute("mensagem", "Agenda cancelada com sucesso.");
        return mv;

    }

//    TEste
    @GetMapping(value = "/teste")
    public void testeHora() {

        String dayWeek = "---";
        try {
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(new SimpleDateFormat("yyyy-MM-dd hh:mm", new Locale("pt", "BR")).parse("2018/05/02 00:11"));
            String dia = new SimpleDateFormat("EEEEEEEEEEEE", new Locale("pt", "BR")).format(gc.getTime()).toUpperCase();
            System.out.println(dia);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        TemporalAdjuster ajustadorDias = TemporalAdjusters.next(SEXTA);

        LocalDate proximoDiaEscolhido = HOJE.with(ajustadorDias);
        System.out.println("Proxima datas  ê " + proximoDiaEscolhido.toString());

        int qtdSemanas = 20;

        for (int i = 0; i < qtdSemanas; i++) {

            System.out.println(i + 1 + " SEXTA por " + qtdSemanas + " semanas =" + proximoDiaEscolhido);
            proximoDiaEscolhido = proximoDiaEscolhido.plusDays(7);

        }

        System.out.println(HOJE.withDayOfMonth(10));
        for (int i = 1; i < 11; i++) {

            LocalDate amanha = HOJE.plusDays(7 * i);
            System.out.println("proximas datas  =    " + amanha);
            HOJE = HOJE.plusDays(7 * 1);

        }

        // LocalDate proximaSexta = LocalDate.now().with(ajustadorParaProximaSexta);
        //System.out.println("Proxima Sexta feira " + proximaSexta);
        /* String data = "dd/MM/yyyy";
            String h = "h:mm - a";
            //String hoje, hora;
            java.util.Date agora = new java.util.Date();;
            SimpleDateFormat formata = new SimpleDateFormat(data);
            //hoje = formata.format(agora);
            formata = new SimpleDateFormat(h);
            //hora = formata.format(agora);
        
        
        
        DateTimeFormatter formatador = 
        DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println(hoje.format(formatador)); //// 05/05/2018
            //System.out.println("Hoje = "+ hoje);
            LocalTime horarioDeEntrada = LocalTime.of(9, 0);//09:00
            //System.out.println(horarioDeEntrada); 
            MonthDay natal = MonthDay.of(Month.DECEMBER, 25);
            YearMonth copaDoMundo2014 = YearMonth.of(2014, Month.JUNE);
            System.out.println("Natal : " + natal);
            System.out.println("Copa Mundo 2014 : " + copaDoMundo2014 );*/
    }

    @GetMapping(value = "/qtdAgenda")
    public Map qtdAgendaMesAtual() {
        
        agendaService.qtdAgendaMesAtual();
        Map map = graficoService.montarGraficos();

        return map;
    }

}
