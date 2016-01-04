/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
 
    getCorsoList();
    //getLesson();
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
//metodo per chiamare tutt ele lezioni
function selectedItem()
{
    $("#resultbody tr").remove();
    selected = $("#Corsoprofessore option:selected").val();
    if (selected !== "default") //se il valore della select Ã¨ default non mostriamo il div contenente le informazioni delle date delle lezioni
    {
        selected = $("#Corsoprofessore option:selected").val();
        $("#panelDiv").show();
        //metodo per stampare le date
        $.getJSON("GetPresenceDottorandi", {idCourse: selected}, function (data) {
            $.each(data.presence, function (index, value) {
                dottorando = "<tr id=" + index + "> <td> " + value.name + " " + value.surname + " </td>  </tr>";
                $("#resultbody ").append(dottorando);
                id = value.secondaryEmail;

                $.getJSON("GetPresenceToLesson", {idCourse: selected, fkPhdstudent: id}, function (data) {
                    $.each(data.presence, function (index2, value2) {
                        lezione = value2.fkLesson;
                        dottorandopre = "<td> <input type='checkbox' value=" + true + "   id=" + id + " onclick='changePresenza(" + 'id' + "," + lezione + ")' class='checkboxclass'  ";
 
                        if (value2.isPresent === true) {
                            dottorandopre += "checked";
                        }
                       
                        dottorandopre += "></td>";
                        $("#" + index).append(dottorandopre);
 
                    });
 
                });
 
            });
        });
 
 
    }
 
}
// metodo per mostrare i partecipanti a lezione

// metodo per cambiare la presenza 

function changePresenza(id,lezione) {
    
     alert("firma inserita");
    $.getJSON("ModifyPresence",{fkPhdstudent:id,fkLesson:lezione}, function (data) { 
    
    });
    
}
  
function selectedItemDot(){
  
    $("#resultbody tr").remove();
    selected = $("#Corsoprofessore option:selected").val();
    if (selected !== "default") //se il valore della select Ã¨ default non mostriamo il div contenente le informazioni delle date delle lezioni
    {
        selected = $("#Corsoprofessore option:selected").val();
        $("#panelDiv").show();
        //metodo per stampare le date
        $.getJSON("GetPresenceDottorandi", {idCourse: selected}, function (data) {
            $.each(data.presence, function (index, value) {
                dottorando = "<tr id=" + index + "> <td> " + value.name + " " + value.surname + " </td>  </tr>";
                $("#resultbody ").append(dottorando);
                id = value.secondaryEmail;
 
                $.getJSON("GetPresenceToLesson", {idCourse: selected, fkPhdstudent: id}, function (data) {
                    $.each(data.presence, function (index2, value2) {
                        lezione = value2.fkLesson;
                        dottorandopre = "<td> <input type='checkbox' value=" + true + "   id=" + id + "  class='checkboxclass'  ";
 
                        if (value2.isPresent === true) {
                            dottorandopre += "checked";
                        }
                       
                        dottorandopre += " disabled></td>";
                        $("#" + index).append(dottorandopre);
 
                    });
 
                });
 
            });
        });
 
 
    }

}