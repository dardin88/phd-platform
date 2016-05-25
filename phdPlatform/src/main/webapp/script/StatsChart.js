/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




/* global i, presenze */

$(document).ready(function () {
CycleList();
  //  getStatsPresenze();

});

function CycleList(){
    
    $.getJSON("GetCyclesListNumers", function (data){
  
        $.each(data.cyclesIds, function (index, value) {
             ciclo = "<option value=" + value + " > " + value+ "  </option> ";

            $("#Ciclo").append(ciclo);
        });
    });
}

function getStatsPresenze()
{
selected = $("#Ciclo option:selected").val();

    $.getJSON("GetTotalPresenceServlet",{Ciclo: selected}, function (data) {
        




        var dataClone = data;

        //disattiva la legenda del grafico a barre
        Chart.defaults.bar.legend = false;


//variabile dei dati utilizzati dal grafico a barre
        var dataBar = {
            labels:
                    data.map(function (data) {
                        return data.nome;
                    }),
            datasets: [
                {
                    data:
                            data.map(function (data) {
                                return data.presenze;
                            }),
                   backgroundColor: 
                        "#FF6384",
                        
                    
                    borderWidth: 1
               
                },
                {
                    data:
                            data.map(function (data) {
                                return data.Presenze;
                            }),
                    backgroundColor: 
                     
                        "#36A2EB",
                        
                   
                    borderWidth: 1
              
                }




            ]
        };

//selezione degli elementi della pagina html dove verrano stampati i grafici

        var ctx1 = document.getElementById("chart2").getContext('2d');

//costruzione grafico a barre
        var myBarChart = new Chart(ctx1, {
           type: 'horizontalBar',
            data: dataBar,
            responsive: true,
            options: {
                scales: {
                    yAxes: [{
                            	
                            ticks: {
                                beginAtZero: true
                            }
                        }],
           	xAxes: [{
                        ticks: {
                        beginAtZero: true
                        }
                                    }]
                }
            }
        });



    });




}
