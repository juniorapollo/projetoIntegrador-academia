/* JS para a Pagina de Agenda*/

var chklista = [0] ; 

$('#spinner1').spinner();
//function formatadarData(data) {
//    var dia = data.toString().substr(0, 2);
//    var mes = data.toString().substr(3, 2);
//    var ano = data.toString().substr(6, 4);
//    var hora = data.toString().substr(11, 2);
//    var min = data.toString().substr(14, 2);
//    var dataFormatada = ano + "-" + mes + "-" + dia + " " + hora + ":" + min;
//    return dataFormatada;
//}

//function pegarDataInput() {
//    var data = $('#horario').val();
//    var dataFormatada = formatadarData(data);
//    dataFormatada = dataFormatada.toString().substr(0, 10);
//    console.log(dataFormatada);
//    return dataFormatada;
//}

//function pegarHoraDoInput() {
//    var data = $('#horario').val();
//    var hora = data.toString().substr(11, 2);
//    var min = data.toString().substr(14, 2);
//    return hora + ":" + min;
//}

//function pegarHoraDoServico() {
//    var selObj = document.getElementById('servico');
//    var selecTedIndex = selObj.selectedIndex;
//    var textoSelecionado = selObj.options[selecTedIndex].text;
//    var saltoHora = textoSelecionado.toString().length - 8;
//    var saltoMinuto = textoSelecionado.toString().length - 5;
//    var horaServico = textoSelecionado.toString().substr(saltoHora, 5);
//    var minutoServico = textoSelecionado.toString().substr(saltoMinuto, 2);
//    return horaServico;
//}
//function somarTempos(tempo1, tempo2) {
//    var array1 = tempo1.toString().split(':');
//    var tempo_seg1 = (parseInt(array1[0]) * 3600) + (parseInt(array1[1]) * 60) + parseInt(array1[2]);
//    var array2 = tempo2.toString().split(':');
//    var tempo_seg2 = (parseInt(array2[0]) * 3600) + (parseInt(array2[1]) * 60) + parseInt(array2[2]);
//    var tempofinal = parseInt(tempo_seg1) + parseInt(tempo_seg2);
//    var hours = Math.floor(tempofinal / (60 * 60));
//    var divisorMinutos = tempofinal % (60 * 60);
//    var minutes = Math.floor(divisorMinutos / 60);
//    var divisorSeconds = divisorMinutos % 60;
//    var seconds = Math.ceil(divisorSeconds);
//    var contador = "";
//    if (!(hours > 10)) {
//        contador = "0" + hours + ":";
//    } else {
//        contador = hours + ":";
//    }
//    if (!(minutes > 10)) {
//        contador += "0" + minutes;
//    } else {
//        contador += minutes;
//    }
//    // if (!(seconds > 10)) { contador += "0" + seconds; } else { contador += seconds; }
//    return contador;
//}


function submeterForm() {
    var form = $('#formAgenda');
   
    if(chklista.length == 0){
        chklista=[0];
       
    }
    $('#listaDosDiasDaSemana').val(chklista);
    form.submit();
}

//function desabilitar(cliente, servico, horario, btnAgendar, maisSemanas) {
//    $("#cliente").attr("disabled", cliente);
//    $("#servico").attr("disabled", servico);
//    $("#horario").attr("disabled", horario);
//    $("#btnAgendar").attr("disabled", btnAgendar);
//    $("#maisSemanas").attr("disabled", maisSemanas);
//}
//var id = $('#idAgenda').val();
//if (!(id == '')) {
//    desabilitar(false, false, false, false, false);
//} else {
//    desabilitar(true, true, true, true, true);
//}
//var profissional;
//var cliente;
//var servico;
//var horario;
//$('#profissional').change(function () {
//    var selObj = document.getElementById('profissional');
//    var selecTedIndex = selObj.selectedIndex;
//    var textoSelecionado = selObj.options[selecTedIndex].text;
//    profissional = textoSelecionado;
//    if (!(profissional == '')) {
//        desabilitar(false, true, true, true, true);
//    } else {
//        desabilitar(true, true, true, true, true);
//    }
//});
//$('#cliente').change(function () {
//    var selObj = document.getElementById('cliente');
//    var selecTedIndex = selObj.selectedIndex;
//    var textoSelecionado = selObj.options[selecTedIndex].text;
//    cliente = textoSelecionado;
//    if (!(cliente == '')) {
//        desabilitar(false, false, true, true, true);
//    } else {
//        desabilitar(false, true, true, true, true);
//    }
//});
//$('#servico').change(function () {
//    var selObj = document.getElementById('servico');
//    var selecTedIndex = selObj.selectedIndex;
//    var textoSelecionado = selObj.options[selecTedIndex].text;
//    servico = textoSelecionado;
//    if (!(servico == '')) {
//        desabilitar(false, false, false, true, true);
//    } else {
//        desabilitar(false, false, true, true, true);
//    }
//});
//$('#horario').change(function () {
//    var horario = $('#horario').val();
//    if (!(horario == '')) {
//        desabilitar(false, false, false, false, false);
//    }
//});

$(document).ready(function () {
    var cb = [];
    $('#diasDaSemana').hide();
    $('#repetir').change(function () {
        var isSelected = $('#repetir').is(':checked');
        if (isSelected) {
            $('#qtdSemanas').show();
        } else {
            $('#inputQtdSemanas').val('0');
            $('#qtdSemanas').hide();
        }

    });


    $('#repeticoes').change(function () {
        var valor = $('#repeticoes option:selected').val();
        if (valor == 1) {
            $('#diasDaSemana').show();
        } else if (valor == 2) {

        }

    });
   

    $('input[name="chklista"]').click(function () {
        chklista= $('input[name="chklista"]:checked').toArray().map(function (check) {
            return $(check).val();
        });
    });
});

$(document).ready(function () {
  
    var dayOfWeek = {};
    var horarios = ['05:00', '05:15', '05:30', '06:00', '06:15', '06:30', '07:00', '07:15', '07:30', '08:00', '08:15', '08:30', '09:00', '09:15', '09:30', '10:00', '10:15', '10:30', '11:00', '11:15', '11:30', '12:00', '12:15', '12:30', '13:00', '13:15', '13:30', '14:00', '14:15', '14:30', '15:00', '15:15', '15:30', '16:00', '16:15', '16:30', '17:00', '17:15', '17:30', '18:00', '18:15', '18:30', '19:00', '19:15', '19:30', '20:00', '20:15', '20:30'];
    dayOfWeek[0] = { desc: "Dom", allowTimes: horarios };
    dayOfWeek[1] = { desc: "Seg", allowTimes: horarios };
    dayOfWeek[2] = { desc: "Ter", allowTimes: horarios };
    dayOfWeek[3] = { desc: "Qua", allowTimes: horarios };
    dayOfWeek[4] = { desc: "Qui", allowTimes: horarios };
    dayOfWeek[5] = { desc: "Sex", allowTimes: horarios };
    dayOfWeek[6] = { desc: "Sab", allowTimes: horarios };
    $.datetimepicker.setLocale('pt');
    var data = new Date();
    $('#horario').datetimepicker({
        allowTimes: dayOfWeek[data.getDay()].allowTimes,
        onChangeDateTime: function (date, input) {
            data = date;
            this.setOptions({
                allowTimes: dayOfWeek[data.getDay()].allowTimes
            });
        },
        onGenerate: function (ct) {
            // $(this).find('.xdsoft_date.xdsoft_weekend').addClass('xdsoft_disabled');
        },
        format: 'd/m/Y H:i',
        minDate: 0
    });
});


var linkMontarAgenda = $("#linkMontarAgenda").html();
var linkEditarAgenda = $("#linkEditarAgenda").html();
var abrirModal = $("#abrirModal").html();
console.log(linkMontarAgenda);
if (abrirModal == "true") {
    $("#novoEvento").modal({ backdrop: "static" });
}

var url = linkMontarAgenda;

$(document).ready(function () {
    var defaultEvents = {
        url: url,
    };


    $('#calendar').fullCalendar({
        slotDuration: '00:15:00',
        minTime: '07:00:00',
        maxTime: '22:00:00',
        defaultView: 'agendaWeek',
        handleWindowResize: true,
        height: $(window).height() - 200,
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek'
        },
        defaultDate: Date(),
        editable: true,
        eventLimit: true, selectable: true,
        lang: 'pt-br',
        events: defaultEvents,
        drop: function (date) {
            $this.onDrop($(this), date);
        },
        buttonText: {
            today: 'Hoje',
            month: 'Mês',
            week: 'Semana',
            day: 'Dia'
        },
        eventClick: function (calEvent) {
            window.location.href = linkEditarAgenda + calEvent.idAgenda;
            $("#novoEvent").modal({ backdrop: "static" });
            // change the border color just for fun
        },
        select: function (start, end, allDay) {
            var strData = start.toString();
            var partesData = strData.split("-");
            console.log(strData);
            var dia = parseInt(partesData[2]);
            var data = new Date(partesData[0], partesData[1] - 1, dia , 07 ,00);
            var dataParaComparar = new Date(partesData[0], partesData[1] - 1, dia + 1); // Para aceitar agendamento no dia atual
            if (!(dataParaComparar > new Date())) {
                $('#sa-basic').click(function () {
                    swal({
                        title: "Ops, você não pode agendar nessa data!",
                        text: "Escolha uma data a partir de hoje.",
                        type: "warning",
                        showCancelButton: false,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "OK",
                        closeOnConfirm: true

                    });
                });
                $('#sa-basic').click();
            } else {
                var inputHorario = $("#horario");
                data = data.toLocaleString().substring(0, 16);
                console.log(data);
                inputHorario.val(data);
                $("#novoEvento").modal({ backdrop: "static" });
            }
        }

    });
});


$(document).ready(function () {
    var defaultEvents = {
        url: url,
    };


    $('#calendarDia').fullCalendar({
        allDay: false,
        slotDuration: '00:15:00',
        minTime: '07:00:00',
        maxTime: '22:00:00',
        defaultView: 'agendaDay',
        handleWindowResize: true,
        height: $(window).height() - 200,
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'agendaDay'
        },
        defaultDate: Date(),
        editable: true,
        eventLimit: true, selectable: true,
        lang: 'pt-br',
        events: defaultEvents,

        drop: function (date) {
            $this.onDrop($(this), date);
        },
        buttonText: {
            today: 'Hoje',
            month: 'Mês',
            week: 'Semana',
            day: 'Dia'
        },
        eventClick: function (calEvent) {
            window.location.href = linkEditarAgenda + calEvent.idAgenda;
            $("#novoEvent").modal({ backdrop: "static" });
            // change the border color just for fun
        },
        select: function (start, end, allDay) {
            var strData = start.toString();
            var partesData = strData.split("-");
            var dia = parseInt(partesData[2]);
            var data = new Date(partesData[0], partesData[1] - 1, dia , 07 ,00);
            var dataParaComparar = new Date(partesData[0], partesData[1] - 1, dia + 1); // Para aceitar agendamento no dia atual
            if (!(dataParaComparar > new Date())) {
                $('#sa-basic').click(function () {
                    swal({
                        title: "Ops, você não pode agendar nessa data!",
                        text: "Escolha uma data a partir de hoje.",
                        type: "warning",
                        showCancelButton: false,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "OK",
                        closeOnConfirm: true

                    });
                });
                $('#sa-basic').click();
            } else {
                var a = $("#horario");
                data = data.toLocaleString().substring(0, 16);

                a.val(data);
                $("#novoEvento").modal({ backdrop: "static" });
            }
        }

    });
});







jQuery(document).ready(function () {
    //Funcoes de Mensagens de Aviso


    var hoje = new Date();
    var dd = hoje.getDate();
    var mm = hoje.getMonth() + 1; //January is 0!
    var yyyy = hoje.getFullYear();
    if (!(dd > 10)) {
        dd = '0' + dd;
    }
    if (!(mm > 10)) {
        mm = '0' + mm;
    }
    var hoje = yyyy + '-' + mm + '-' + dd;
    $('#dataInicio').attr({
        'min': hoje
    });
    // Time Picker

    jQuery('#horaInicio').timepicker({
        showMeridian: false,
        minuteStep: 15,
        defaultTime: '08:00 AM'
    });
    jQuery('#horaFinal').timepicker({
        showMeridian: false,
        minuteStep: 15,
        defaultTime: '08:00 AM'
    });
    $('#link').on('change', function () {
        var url = $(this).val();
        if (url) {
            window.location.href = url;
        }
        return false;
    });
});







