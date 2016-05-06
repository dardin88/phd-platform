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
function selectedItem2(){  
    var nButtons=0;
    $("#panelDiv").hide();
    $("#resulthead th").remove();
    $("#resultbody tr").remove();
    $("#sessione label").remove();
    selected = $("#Corsoprofessore option:selected").val();
    if (selected !== "default") //se il valore della select Ã¨ default non mostriamo il div contenente le informazioni delle date delle lezioni
    { $("#resulthead th").remove();
       $("#resultbody tr").remove();
       $("#resultBotton td").remove();
       $("#sessione").append("<label><input id='sessioni' type='checkbox' name='sessione' checked onchange='changeSessioni()'> Visualizza le sessioni chiuse</label>");
       $("#resultBotton").append("<td> </td>");
        selected = $("#Corsoprofessore option:selected").val();
        $("#panelDiv").show();
        
        //metodo per stampare le date
        var isLessons;           
        $.getJSON("GetLessonsProfessor", {fkCourse : selected}, function (data1) {
             dot="<th style='text-align:left; font-size: 20px;'> Dottorandi </th>";
               $("#resulthead ").append(dot);
               isLessons = data1;
               $.each(data1.lessons, function (index, value5) {
                            
                    data1=value5.data;
                    if(value5.closed){
                        dottorando11 = " <th class='archiviata'> " +data1 + "<p>"+value5.name+"</p></th>  ";
                    }
                    else
                    {
                        dottorando11 = " <th class='"+value5.idLesson+"'> " +data1 + "<p>"+value5.name+"</p></th>  ";
                    }
                    $("#resulthead ").append(dottorando11);
                    nButtons++;
                         
            });
        });
                
        $.getJSON("GetPresenceDottorandi", {idCourse: selected}, function (data) {
            $.each(data.presence, function (index, value) {
                dottorando = "<tr id=" + index + "> <td class='students'> " + value.name + " " + value.surname + " </td>  </tr>";
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
                               isClosed = isLessons.lessons[p].closed;
                               break;
                            }
                        }
                        if(flag){
                            if(nButtons>0)
                            {
                                nButtons--;
                                if(isClosed)
                                {
                                    $("#resultBotton ").append("<td class='archiviata'> </td>");
                                }
                                else
                                {
                                    app = "<td class='" + lezione + " celle'> <input type = 'button' id = " + lezione + " onclick = 'archiviaPresenze(" + 'id' + ")' value = 'Chiudi Sessione' class = 'btn btn-blue'> </td>";                                
                                    $("#resultBotton ").append(app);
                                }                                                          
                            }
                            
                            td = value2.fkPhdstudent;
                            if (isClosed) {
                               dottorandopre = "<td class='celle archiviata'><input type='checkbox' value='true'   id=" + td + " onclick='changePresenza(" + 'id' + "," + lezione + ")' class='checkboxclass' disabled ";
                            }
                            else{
                                dottorandopre = "<td class='" + lezione + " celle'><input type='checkbox' value='true'   id=" + td + " onclick='changePresenza(" + 'id' + "," + lezione + ")' class='checkboxclass' ";
                            }
                                                 
                            if (value2.isPresent === true) {
                               dottorandopre += " checked";
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


function get_Date( giorno, ora)
{
    if(ora.length ===7) 
    {
        ora="0"+ora;
        
    }
    var ore=parseInt(ora.substring(0,2));
    var minuti=parseInt(ora.substring(3));
    
    if(ora.substring(6)==="PM")
    {
        ore=ore+12;
    }
    
    var data=new Date(giorno);
    data.setHours(ore);
    data.setMinutes(minuti);
  
    return data;
}

function isAfterNow(data)
{
    var now=new Date().getTime();
    if(data.getTime() < now)
        return false;
    else return true; 
}
