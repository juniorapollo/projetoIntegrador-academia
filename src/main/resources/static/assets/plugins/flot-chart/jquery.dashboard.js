/**
 * Theme: Moltran Admin Template
 * Author: Coderthemes
 * Module/App: Dashboard
 */


!function ($) {
    "use strict";

    var Dashboard = function () {
        this.$body = $("body");
        this.$realData = [];
    };

    //creates plot graph
    Dashboard.prototype.createPlotGraph = function (selector, data1, data2, labels, colors, borderColor, bgColor) {
        //shows tooltip
        function showTooltip(x, y, contents) {
            $('<div id="tooltip" class="tooltipflot">' + contents + '</div>').css({
                position: 'absolute',
                top: y + 5,
                left: x + 5
            }).appendTo("body").fadeIn(200);
        }

        $.plot($(selector),
                [{data: data1,
                        label: labels[0],
                        color: colors[0]
                    },
                    {data: data2,
                        label: labels[1],
                        color: colors[1]
                    }
                ],
                {
                    series: {
                        lines: {
                            show: true,
                            fill: true,
                            lineWidth: 1,
                            fillColor: {
                                colors: [{opacity: 0.2},
                                    {opacity: 0.9}
                                ]
                            }
                        },
                        points: {
                            show: true
                        },
                        shadowSize: 0
                    },
                    legend: {
                        position: 'nw'
                    },
                    grid: {
                        hoverable: true,
                        clickable: true,
                        borderColor: borderColor,
                        borderWidth: 0,
                        labelMargin: 10,
                        backgroundColor: bgColor
                    },
                    yaxis: {
                        min: 0,
                        max: 15,
                        color: 'rgba(0,0,0,0)'
                    },
                    xaxis: {
                        color: 'rgba(0,0,0,0)'
                    },
                    tooltip: true,
                    tooltipOpts: {
                        content: '%s: Dia %x (  %y )',
                        shifts: {
                            x: -60,
                            y: 25
                        },
                        defaultTheme: false
                    }
                });
    },
            //end plot graph

            //creates Pie Chart
            Dashboard.prototype.createPieGraph = function (selector, labels, datas, colors) {
                var data = [{
                        label: labels[0],
                        data: datas[0]
                    }, {
                        label: labels[1],
                        data: datas[1]
                    }, {
                        label: labels[2],
                        data: datas[2]
                    }];
                var options = {
                    series: {
                        pie: {
                            show: true
                        }
                    },
                    legend: {
                        show: false
                    },
                    grid: {
                        hoverable: true,
                        clickable: true
                    },
                    colors: colors,
                    tooltip: true,
                    tooltipOpts: {
                        content: "%s, %p.0%"
                    }
                };

                $.plot($(selector), data, options);
            },
            //initializing various charts and components
            Dashboard.prototype.init = function () {
                /*<![CDATA[*/

                var baseUrl = /*[[@{${@environment.getProperty('baseUrl')}}]]*/ '';

                /*]]>*/

                //document.getElementById('audio').play();
                var grafico;

                $.ajax({
                    type: 'GET',
                    url: baseUrl + "/sistema/agenda/qtdAgenda",
                    async: false,
                    success: function (retorno) {
                        grafico = retorno;

                    },
                    error: function () {
                        console.log("Erro Verificar Notificações " + error);
                    }

                });
                console.log(grafico);



                //plot graph data
//          var uploads = [[0, 9], [1, 8], [2, 5], [3, 8], [4, 5], [5, 14], [6, 10],[7, 9], [8, 8], [9, 5], [10, 8], [11, 5], [12, 14], [13, 10],[14, 10]];
                //[[0, 9], [1, 8], [2, 5], [3, 8], [4, 5], [5, 14], [6, 10],[7, 9], [8, 8], [9, 5], [10, 8], [11, 5], [12, 14], [13, 10],[14, 10]];
//          var downloads = [[0, 5], [1, 12], [2,4], [3, 3], [4, 12], [5, 11], [6, 14],[7, 9], [8, 8], [9, 5], [10, 8], [11, 5], [12, 14], [14, 10],[15, 5], [16, 12], [17,4], [18, 3], [19, 12], [20, 11], [21, 14],[22, 9], [23, 8], [24, 5], [25, 8], [26, 5], [27, 14], [28, 10],[29, 5], [30, 14], [31, 10]];
//                var downloads = valor;
                var plabels = ['Cancelados', "Agendamentos"];
                var pcolors = ['#ff0000 ', ' #29b6f6  '];
                var borderColor = '#fff';
                var bgColor = '#fff';
                this.createPlotGraph("#grafico1", grafico.cancelado, grafico.agendado, plabels, pcolors, borderColor, bgColor);


                //Pie graph data
                var pielabels = ["Iniciantes", "  Avançados", "Intermediários "];
                var datas = [grafico.iniciante, grafico.intermediario, grafico.avancado];
                var colors = ["rgba(30, 136, 229, 0.85)", "rgba(41, 182, 246, 0.85)", "rgba(126, 87, 194, 0.85)"];
                this.createPieGraph("#pie-chart #pie-chart-container", pielabels, datas, colors);

            },
            //init Dashboard
            $.Dashboard = new Dashboard, $.Dashboard.Constructor = Dashboard;

}(window.jQuery),
//initializing Dashboard
        function ($) {
            "use strict";
            $.Dashboard.init();
        }(window.jQuery);


