/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {

    getCorsoList();

});

function getCorsoList()
{
 //servlet per richiamare la lista dei nomi lezioni
   $.getJSON("GetAllLessonOfProfessor", function (data) {
    $.each(data.lessons, function (index, value) {
        
           corso = "<option value="+value.idLesson+" > " + value.name + "  </option> ";
        
        $("#Corsoprofessore").append(corso);
        });
    });
    
}

function selectedItem()
{ 
  
    $("#results").hide();
    
    
   selected = $("#Corsoprofessore option:selected").val(); // la chiave primaria di lesson
       
    if (selected !== "default") //se il valore della select è default non mostriamo il div contenente le informazioni
    {alert(selected);
        
        $("#results").show();
           

       $.getJSON("GetPresence",{fkLesson: selected}, function (data) {
            $.each(data.presence, function (index, value) {
               
                presenza = "<tr> <td> " +value.fkPhdstudent + "</td> <td> " + + "</td>     </tr> "; 
                $("#resultbody").append(presenza);
                });
        });
    }
    else
        $("#results").hide();
    
}