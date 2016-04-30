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

{  $("#panelDiv").hide();
    $("#resulthead th").remove();
    $("#resultbody tr").remove();
    selected = $("#Corsoprofessore option:selected").val();
    if (selected !== "default") //se il valore della select Ã¨ default non mostriamo il div contenente le informazioni delle date delle lezioni
    { $("#resulthead th").remove();
       $("#resultbody tr").remove();
        selected = $("#Corsoprofessore option:selected").val();
        $("#panelDiv").show();
        //metodo per stampare le date
        
         $.getJSON("GetAllLessonServlet", {fkCourse: selected}, function (data1) {
             dot="<th> Dottorandi </th>"
               $("#resulthead ").append(dot);
            $.each(data1.lessons, function (index, value5) {
              
              data1=value5.data;
              
               dottorando11 = " <th> " +data1 + " </th>  ";
              
                         
                $("#resulthead ").append(dottorando11)
                
                
                
                ;});});
        $.getJSON("GetPresenceDottorandi", {idCourse: selected}, function (data) {
            $.each(data.presence, function (index, value) {
                dottorando = "<tr id=" + index + "> <td> " + value.name + " " + value.surname + " </td>  </tr>";
                id = value.secondaryEmail;
                         
                $("#resultbody ").append(dottorando);
  
$.getJSON("GetPresenceToLesson", {idCourse: selected, fkPhdstudent: id}, function (data) {
                    $.each(data.presence, function (index2, value2) {
                        lezione = value2.fkLesson;
                         td= value2.fkPhdstudent;
                         dottorandopre = "<td> <input type='checkbox' value=" + true + "   id=" + td + " onclick='changePresenza(" + 'id' + "," + lezione + ")' class='checkboxclass'  ";

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
    $("#panelDiv").hide();
  $("#resulthead th").remove();
    $("#resultbody tr").remove();
    selected = $("#Corsoprofessore option:selected").val();
    if (selected !== "default") //se il valore della select Ã¨ default non mostriamo il div contenente le informazioni delle date delle lezioni
    { $("#resulthead th").remove();
    $("#resultbody tr").remove();
        selected = $("#Corsoprofessore option:selected").val();
        $("#panelDiv").show();
        //metodo per stampare le date
        $.getJSON("GetAllLessonServlet", {fkCourse: selected}, function (data1) {
             dot="<th> Dottorandi </th>"
               $("#resulthead ").append(dot);
            $.each(data1.lessons, function (index, value5) {
              
              data1=value5.data;
              
               dottorando11 = " <th> " +data1 + " </th>  ";
              
                         
                $("#resulthead").append(dottorando11)
                
                
                
                ;});});
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

//Metodo utilizzato per chiamare tutte le lezioni create da un determinato Professore per il corso selezionato
function selectedItem2()

{  $("#panelDiv").hide();
    $("#resulthead th").remove();
    $("#resultbody tr").remove();
    selected = $("#Corsoprofessore option:selected").val();
    if (selected !== "default") //se il valore della select Ã¨ default non mostriamo il div contenente le informazioni delle date delle lezioni
    { $("#resulthead th").remove();
       $("#resultbody tr").remove();
        selected = $("#Corsoprofessore option:selected").val();
        $("#panelDiv").show();
        //metodo per stampare le date
        var isLessons;
         $.getJSON("GetLessonsProfessor", {fkCourse : selected}, function (data1) {
             dot="<th> Dottorandi </th>"
               $("#resulthead ").append(dot);
               isLessons = data1;
            $.each(data1.lessons, function (index, value5) {
                                        
                    data1=value5.data;
                    dottorando11 = " <th> " +data1 + " </th>  ";
                    $("#resulthead ").append(dottorando11);
                              
            });
        });
      
        $.getJSON("GetPresenceDottorandi", {idCourse: selected}, function (data) {
            $.each(data.presence, function (index, value) {
                dottorando = "<tr id=" + index + "> <td> " + value.name + " " + value.surname + " </td>  </tr>";
                id = value.secondaryEmail;
                         
                $("#resultbody ").append(dottorando);
  
$.getJSON("GetPresenceToLesson", {idCourse: selected, fkPhdstudent: id}, function (data) {
    
                    $.each(data.presence, function (index2, value2) {
                        flag = false;
                        lezione = value2.fkLesson;
                        for(var p=0;p<isLessons.lessons.length;p++)
                        {
                            if(isLessons.lessons[p].idLesson==lezione)
                            {
                               flag = true;
                               break;
                            }
                        }
                        if(flag){
                            td = value2.fkPhdstudent;
                            dottorandopre = "<td> <input type='checkbox' value=" + true + "   id=" + td + " onclick='changePresenza(" + 'id' + "," + lezione + ")' class='checkboxclass'  ";

                           if (value2.isPresent === true) {
                               dottorandopre += "checked";
                           }

                           dottorandopre += "></td>";
                           $("#" + index).append(dottorandopre);
 
                        }
                         
                    });
 
                });
 
            });
        });
 
 
    }
 
}