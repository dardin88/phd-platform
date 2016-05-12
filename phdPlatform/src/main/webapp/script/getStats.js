/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global d3, svg, options, ctx */

$(document).ready(function () {

    getCorsoList();

});



function getCorsoList()
{
    //metodo per richiamare la lista dei nomi dei corsi seguiti dall'utente autenticato
    $.getJSON("GetCoursebyStudent", function (data) {
        $.each(data.presence, function (index, value) {


            corso = "<option value=" + value.length + " > " + value.name + "  </option> ";

            $("#Corsoseguito").append(corso);
        });

    });


}

function getPresenze()
{
//metodo per visualizzare il report delle presenze ed i grafici sulle statistiche dello studente
    selected = $("#Corsoseguito option:selected").text();

    $.getJSON("GetTotalPresenceServlet", {Coursename: $.trim(selected)}, function (data) {
     var dataClone= data;

  
     
        if (selected !== "default") //se il valore della select è default non mostriamo il div contenente le informazioni delle presenze
        {
            $("#summary").hide();

            $("#summary").html("Lezioni: " + data.presenze.toString() + " Assenze: " + data.Assenze.toString() + " Presenze: " + data.presenzeEff.toString() + "</p>");
            $("#summary").show();


        } ;
        
        //disattiva la legenda del grafico a barre
        Chart.defaults.bar.legend=false; 
        
        //variabile dei dati utilizzata dal grafico a ciambella
       var dataDonut= {
    labels: [
        "Presenze",
        "Assenze"
    ],
    datasets: [
        {
            data: [data.presenzeEff, data.Assenze],
           
            backgroundColor: [
                
                "#36A2EB",
                "#FFCE56"
            ],
            hoverBackgroundColor: [
                
                "#36A2EB",
                "#FFCE56"
            ]
  
        }]
};

//variabile dei dati utilizzati dal grafico a barre
    var dataBar = {
    labels: [
        "Lezioni",
        "Presenze",
        "Assenze"
    ],
    datasets: [
        {
      
        
            data: [dataClone.presenze, dataClone.presenzeEff, dataClone.Assenze],
             backgroundColor: [
                "#FF6384",
                "#36A2EB",
                "#FFCE56"
            ],     
            borderWidth: 1,
            hoverBackgroundColor: [
                "#FF6384",
                "#36A2EB",
                "#FFCE56"
            ]
         
  
        }]
};

//selezione degli elemnti della pgina html dove verrano stampati i graific
     var ctx = document.getElementById("chart").getContext('2d');
     var ctx1 = document.getElementById("chart2").getContext('2d');

//costruzione grafico a barre
var myBarChart = new Chart(ctx1, {
    type: 'bar',
    data: dataBar,
    responsive: true,
  
  
   options: {

        scales: {
                
            yAxes: [{
                ticks: {
                    beginAtZero:true
                }
            }]
     }
    }
    });
    
    


//costruzione grafico a ciambella
var myDoughnutChart = new Chart(ctx, {
    type: 'doughnut',
    data: dataDonut,
    responsive: true

});

    });
}

