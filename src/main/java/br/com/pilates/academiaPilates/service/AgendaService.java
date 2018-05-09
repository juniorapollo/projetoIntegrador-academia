/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pilates.academiaPilates.service;

import br.com.pilates.academiaPilates.models.Agenda;
import br.com.pilates.academiaPilates.models.Cliente;
import br.com.pilates.academiaPilates.models.Profissional;
import br.com.pilates.academiaPilates.models.Servico;
import br.com.pilates.academiaPilates.repository.AgendaRepository;
import java.sql.Time;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author daniel
 */
@Service
public class AgendaService {

    @Autowired
    AgendaRepository repo;
    Agenda agenda;

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

    public String diaSemana(String data) {
        String dayWeek = "---";
        GregorianCalendar gc = new GregorianCalendar();
        try {
            gc.setTime(new SimpleDateFormat("yyyy-MM-dd hh:mm", new Locale("pt", "BR")).parse(data));
            String dia = new SimpleDateFormat("EEE", new Locale("pt", "BR")).format(gc.getTime()).toUpperCase();
            System.out.println(dia);
            return dia;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dayWeek;
    }

    public LocalDate proximoDataParaDia(String dataRealizouPrimeiroAgendamento ,String diaSemana) throws ParseException {
        DayOfWeek dia = null;
        String d = dataRealizouPrimeiroAgendamento.substring(0, 10);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate data = LocalDate.parse(d,formatter);

        TemporalAdjuster ajustadorDias;
        LocalDate proximoDiaEscolhido;
        switch (diaSemana) {
            case "DOM":
                dia = DOMINGO;
                ajustadorDias = TemporalAdjusters.next(dia);
                proximoDiaEscolhido = data.with(ajustadorDias);
                return proximoDiaEscolhido;

            case "SEG":
                dia = SEGUNDA;
                ajustadorDias = TemporalAdjusters.next(dia);
                proximoDiaEscolhido = data.with(ajustadorDias);
                return proximoDiaEscolhido;

            case "TER":
                dia = TERCA;
                ajustadorDias = TemporalAdjusters.next(dia);
                proximoDiaEscolhido = data.with(ajustadorDias);
                return proximoDiaEscolhido;
            case "QUA":
                dia = QUARTA;
                ajustadorDias = TemporalAdjusters.next(dia);
                proximoDiaEscolhido = data.with(ajustadorDias);
                return proximoDiaEscolhido;
            case "QUI":
                dia = QUINTA;
                ajustadorDias = TemporalAdjusters.next(dia);
                proximoDiaEscolhido = data.with(ajustadorDias);
                return proximoDiaEscolhido;
            case "SEX":
                dia = SEXTA;
                ajustadorDias = TemporalAdjusters.next(dia);
                proximoDiaEscolhido = data.with(ajustadorDias);
                return proximoDiaEscolhido;
            case "SÁB":
                dia = SABADO;
                ajustadorDias = TemporalAdjusters.next(dia);
                proximoDiaEscolhido = data.with(ajustadorDias);
                return proximoDiaEscolhido;

            default:
                return null;
        }

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
    public String adicionaDataEndComHorarioDoServico(String dataStart, Servico servico) {
        String data = dataStart.substring(0, 11);
        System.out.println(data);
        String horaStart = dataStart.substring(11, 16);
        String horaServico = servico.getTempo();

        GregorianCalendar gc = new GregorianCalendar();

        int hora = Integer.parseInt(horaServico.substring(0, 2));
        // System.out.println(hora);
        int min = Integer.parseInt(horaServico.substring(3, 5));
        // System.out.println(min);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
        Time time = new Time(hora, min, 00);
        gc.setTimeInMillis(time.getTime());
        hora = Integer.parseInt(horaStart.substring(0, 2));
        //System.out.println(hora);
        min = Integer.parseInt(horaStart.substring(3, 5));
        //System.out.println(min);

        gc.add(Calendar.HOUR, hora);
        gc.add(Calendar.MINUTE, min);
        gc.add(Calendar.SECOND, 00);
        //System.out.println("HORA SOMADA: " + sdf2.format(gc.getTime()));
        return data + sdf2.format(gc.getTime());

    }

}
