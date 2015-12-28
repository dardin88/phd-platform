/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {

    getCorsoList();
    getFirma();
});

function getCorsoList()
{
 //servlet per richiamare la lista dei nomi lezioni
   $.getJSON("GetAllCourse", function (data) {
    $.each(data.course, function (index, value) {
        
           corso = "<option value="+value.idCourse+" > " + value.name + "  </option> ";
        
        $("#Corsoprofessore").append(corso);
        });
    });
    
}

function selectedItem()
{ 
  
    $("#results").hide();
    
    
   selected = $("#Corsoprofessore option:selected").val(); // la chiave primaria di course
       
    if (selected !== "default") //se il valore della select è default non mostriamo il div contenente le informazioni
    {
         $("#resulthead th").remove();
         $("#resultbody tr").remove();
        $("#results").show();
            selected = $("#Corsoprofessore option:selected").val();
$.getJSON("GetAllLesson",{fkCourse: selected}, function (data) { 
   
     $.getJSON("GetPresenceCourse", function (data) {   
          presente="<th>Dottorando</th> " ;
    $("#resulthead").append(presente);
                    $.each(data.corso, function (index, value) {                   
                    dottorando="<tr> <td> "+ value.fkPhdstudent +"</td>  <td> <input type='checkbox' value="+value.isPresent+"  data-reverse onclick="+setPresenza()+" > </td></tr>";
                      $("#resultbody").append(dottorando);
                 });
    
}); 

       });/*
        * per ora non va bene 
        * $.getJSON("GetPresence",{fkLesson: selected}, function (data) {
            $.each(data.presence, function (index, value) {
                  $.getJSON("GetAccountbyEmail",{secondaryEmail: value.fkPhdstudent}, function (dati) {
                    $.each(dati.account, function (index1,balue ) {   
                presenza = "<tr> <td> " +balue.name + "</td> <td> " +balue.surname + "</td>     </tr> "; 
                $("#resultbody").append(presenza);
                 });
                   });  
             
            });
        });*/
    }
    else
        $("#results").hide();
    $("#resulthead th").remove();
         $("#resultbody tr").remove();
}

function setPresenza() {
      $.getJSON("ModifyPresence", function (data) {   
          presente="<th>Dottorando</th> " ;
    $("#resulthead").append(presente);
                    $.each(data.corso, function (index, value) {                   
                    dottorando="<tr> <td> "+ value.fkPhdstudent +"</td>  <td> <input type='checkbox' value="+value.isPresent+"  data-reverse onclick="+setPresenza()+" > </td></tr>";
                      $("#resultbody").append(dottorando);
                 });
    
}); 
    
    
    
    
}
