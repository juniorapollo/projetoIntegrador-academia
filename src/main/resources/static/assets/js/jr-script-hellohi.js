

/*Consulta o cep e busca na API para carregar nos inputs o endereco
 $('#cep').on('blur', function () {
 if ($.trim($("#cep").val()) != "") {
 $("#mensagem").html('(Aguarde, consultando CEP ...)');
 $.getScript("http://cep.republicavirtual.com.br/web_cep.php?formato=javascript&cep=" + $("#cep").val(), function () {
 
 if (resultadoCEP["resultado"]) {
 $("#rua").val(unescape(resultadoCEP["tipo_logradouro"]) + " " + unescape(resultadoCEP["logradouro"]));
 $("#bairro").val(unescape(resultadoCEP["bairro"]));
 $("#cidade").val(unescape(resultadoCEP["cidade"]));
 $("#uf").val(unescape(resultadoCEP["uf"]));
 }
 $("#mensagem").html('');
 });
 }
 ;
 });*/


//Consulta o cep e busca na API para carregar nos inputs o endereco
function meu_callback(conteudo) {
    
    console.log(conteudo.logradouro);
    if (!("erro" in conteudo)) {
        //Atualiza os campos com os valores.
        document.getElementById('logradouro').value = (conteudo.logradouro);
        document.getElementById('bairro').value = (conteudo.bairro);
        document.getElementById('cidade').value = (conteudo.localidade);
        document.getElementById('uf').value = (conteudo.uf);

    } //end if.
    else {
        //CEP não Encontrado.
        limpa_formulário_cep();
        alert("CEP não encontrado.");
    }
}

function pesquisacep(valor) {

    //Nova variável "cep" somente com dígitos.
    var cep = valor.replace(/\D/g, '');

    //Verifica se campo cep possui valor informado.
    if (cep != "") {

        //Expressão regular para validar o CEP.
        var validacep = /^[0-9]{8}$/;

        //Valida o formato do CEP.
        if (validacep.test(cep)) {

            //Preenche os campos com "..." enquanto consulta webservice.
            document.getElementById('logradouro').value = "...";
            document.getElementById('bairro').value = "...";
            document.getElementById('cidade').value = "...";
            document.getElementById('uf').value = "...";


            //Cria um elemento javascript.
            var script = document.createElement('script');

            //Sincroniza com o callback.
            script.src = 'https://viacep.com.br/ws/' + cep + '/json/?callback=meu_callback';

            //Insere script no documento e carrega o conteúdo.
            document.body.appendChild(script);

        } //end if.
        else {
            //cep é inválido.
            limpa_formulário_cep();
            alert("Formato de CEP inválido.");
        }
    } //end if.
    else {
        //cep sem valor, limpa formulário.
        limpa_formulário_cep();
    }
}
;

//Funcao Para Mostrar Data e hora Atual
function mostrarDataHora() {
    var currentTime = new Date();
    var horas = currentTime.getHours();
    var minutos = currentTime.getMinutes();
    var segundos = currentTime.getSeconds();
    var dia = currentTime.getDate();
    var mes = currentTime.getMonth();
    var ano = currentTime.getFullYear();
    var Dia = currentTime.getDay();
    var Mes = currentTime.getUTCMonth();
    if (minutos < 10) {
        minutos = "0" + minutos;
    }
    if (segundos < 10) {
        segundos = "0" + segundos;
    }
    if (dia < 10) {
        dia = "0" + dia;
    }
    if (mes < 10) {
        mes = "0" + mes;
    }

    var arrayDia = new Array();//Array recebe os Dias 
    arrayDia[0] = "Domingo";
    arrayDia[1] = "Segunda-Feira";
    arrayDia[2] = "Terça-Feira";
    arrayDia[3] = "Quarta-Feira";
    arrayDia[4] = "Quinta-Feira";
    arrayDia[5] = "Sexta-Feira";
    arrayDia[6] = "Sábado";

    var arrayMes = new Array();//Array recebe os Meses 
    arrayMes[0] = "Janeiro";
    arrayMes[1] = "Fevereiro";
    arrayMes[2] = "Março";
    arrayMes[3] = "Abril";
    arrayMes[4] = "Maio";
    arrayMes[5] = "Junho";
    arrayMes[6] = "Julho";
    arrayMes[7] = "Agosto";
    arrayMes[8] = "Setembro";
    arrayMes[9] = "Outubro";
    arrayMes[10] = "Novembro";
    arrayMes[11] = "Dezembro";

    var diaAtual = arrayDia[Dia] + ", " + dia + " de " + arrayMes[Mes] + " de " + ano;
    var horaAtual = horas + ":" + minutos + ":" + segundos;

    var horaDisplayCelular = horas + ":" + minutos;
    var dataDisplayCelular = arrayDia[Dia] + ", " + arrayMes[Mes] + ", " + dia;
    $("#horaDisplay").text(horaDisplayCelular);
    $("#dataDisplay").text(dataDisplayCelular);

    $("#datahora").html("<div class='pull-right'><b>" + diaAtual + " &nbsp " + horaAtual + "</b></div>"); // MOstrar Data Hora no NavBar
    $("#timer").html("" + diaAtual + " &nbsp " + horaAtual + "");
}
$(document).ready(function () {
    // O metodo nativo setInterval executa uma determinada funcao em um determinado tempo 
    setInterval(mostrarDataHora, 1000);
});





/*
 function readFile() {
 if (this.files && this.files[0]) {
 
 var FR = new FileReader();
 
 
 FR.addEventListener("load", function (e) {
 alert( e.target.length);
 var base64 = e.target.result;
 
 $("#imagens").append('<img id="foto' + i + '" height="50px; width:50px;"/>');
 document.getElementById("foto" + i).src = base64;
 
 
 
 document.getElementById("b64").innerHTML = e.target.result;
 });
 
 
 FR.readAsDataURL(this.files[0]);
 }
 }
 
 document.getElementById("inp").addEventListener("change", readFile);
 */

$('#btnHabilitar').click(function(){
	$('input').prop('readonly', true);
});


