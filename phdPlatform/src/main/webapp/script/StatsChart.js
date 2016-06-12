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

function CycleList() {

    $.getJSON("GetCyclesListNumers", function (data) {

        $.each(data.cyclesIds, function (index, value) {
            ciclo = "<option value=" + value + " > " + value + "  </option> ";

            $("#Ciclo").append(ciclo);
        });
    });
}

function getStatsPresenze()
{
    selected = $("#Ciclo option:selected").val();
   $("#grafico").children().remove();
   var html=$('<canvas/>',{
       id: 'chart2'
   });
       $("#grafico").append(html);
 
    $.getJSON("GetTotalPresenceServlet", {Ciclo: selected}, function (data) {
        createTableStudent(data, 0);
 Chart.defaults.global.title.display=true;
     Chart.defaults.global.title.text="I miei corsi";
Chart.defaults.global.title.fontSize=30;
//variabile dei dati utilizzati dal grafico a barre
        var dataBar = {
            labels:
                    data.map(function (data) {
                        return data.nome;
                    }),
            datasets: [
                {
                     label: "Lezioni",
                    data:
                            data.map(function (data) {
                                return data.presenze;
                            }),
                    backgroundColor:
                            "#FF6384",
                    borderWidth: 1

                },
                {
                     label: "Presenze",
                    data:
                            data.map(function (data) {
                                return data.presenzeEff;
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
                beginAtZero: true,
                stepSize: 1
                            }
                        }],
                    xAxes: [{
                            ticks: {
                                beginAtZero: true,
                                stepSize: 1,
                                max: dataBar.Presenze
                            }
                        }]
                }
            }
        });



    });




}

function getStudentbyCycle(){
   selected = $("#Ciclo option:selected").val();
   $("#grafico").empty();
  
     
   $.getJSON("GetPhdStudentsCoursebyProfessor" ,{Ciclo: selected}, function (data){
        
        var i = 0;
 $.each(data, function(nomeCorso, studenti){
    
     Chart.defaults.global.title.display=true;
     Chart.defaults.global.title.text=nomeCorso;
     Chart.defaults.global.title.fontSize=30;
         var dataBar = {
            labels:
                    studenti.map(function(fullName){
                        return fullName.DottorandoName +" "+ fullName.DottorandoSurname;
                    }),
            datasets: [
                {
                    label: "Lezioni",
                    data:
                          studenti.map(function(student){
                              return student.presenze;
                          }),
                    backgroundColor:
                            "#FF6384",
                    borderWidth: 1

                },
                {
                      label: "Presenze",
                    data:
                       studenti.map(function(student){
                              return student.presenzeEff;
                          }),
                   backgroundColor:
                            "#36A2EB",
                    borderWidth: 1

                }




            ]
        };
       
         var html=$('<canvas/>',{
       id: 'chart'+i
   });
       $("#grafico").append(html);
       createTable(studenti, i);
        var ctx1 = document.getElementById("chart"+i).getContext('2d');
        
     var myBarChart = new Chart(ctx1, {
            type: 'horizontalBar',
            data: dataBar,
            responsive: true,
            options: {
      
                scales: {
                    yAxes: [{
      
                            ticks: {
                beginAtZero: true,
                stepSize: 1
                            }
                        }],
                       xAxes: [{
                            ticks: {
                                beginAtZero: true,
                                stepSize: 1
                               
                            }
                        }]
                }
                
            }
        });
       
        i++;
 });
    
  
   });
    
    
    
}

function createTable(studenti, i){
  oreTot = studenti.map(function(st){
      return st.OreTotali;
  });
  
  oreEff = studenti.map(function(st){
      return st.OreFrequentate;
  });
  var perc = [];

  nomeStudenti= studenti.map(function(fullName){
                        return fullName.DottorandoName +" "+ fullName.DottorandoSurname;
                    });
  $.each(oreTot, function(key, value){
      perc[key] = Math.round((oreEff[key] / value)*100);
  });
   $('#grafico').append('<hr><div id='+'tabella'+' class='+'tabe'+'><table id=table'+i+'><thead><tr><th>Dottorando</th><th>OreTotali</th><th>OreEffettive</th><th>% Presenze</th></tr></thead><tbody></tbody></table></div>');
  $.each(oreTot, function(tmp, value){
      $("#table"+i).find('tbody').append('<tr><td>'+nomeStudenti[tmp]+'</td><td>'+value+'</td><td>'+oreEff[tmp]+'</td><td>'+perc[tmp]+'%</td></tr>');
  
  });
  
  
 /* $.each(oreEff, function(tmp){
      $('#table'+i).filter("th")[1].append('<tr>'+tmp+'</tr>');
  });*/
  
 }
function createTableStudent(studenti, i){
  oreTot = studenti.map(function(st){
      return st.OreTotali;
  });
  
  oreEff = studenti.map(function(st){
      return st.OreFrequentate;
  });
  var perc = [];

  nomeCorsi= studenti.map(function (data) {
                        return data.nome;
                    });
  $.each(oreTot, function(key, value){
      perc[key] = Math.round((oreEff[key] / value)*100);
  });
   $('#grafico').append('<hr><div id='+'tabella'+' class='+'tabe'+'><table id=table'+i+'><thead><tr><th>Corso</th><th>OreTotali</th><th>OreEffettive</th><th>% Presenze</th></tr></thead><tbody></tbody></table></div>');
  $.each(oreTot, function(tmp, value){
      $("#table"+i).find('tbody').append('<tr><td>'+nomeCorsi[tmp]+'</td><td>'+value+'</td><td>'+oreEff[tmp]+'</td><td>'+perc[tmp]+'%</td></tr>');
  
  });
  
  
 /* $.each(oreEff, function(tmp){
      $('#table'+i).filter("th")[1].append('<tr>'+tmp+'</tr>');
  });*/
  
 }