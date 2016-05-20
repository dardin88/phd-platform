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
    
   $.getJSON("ModifyPresence",{fkPhdstudent:id,fkLesson:lezione}, function (data) { 
        
        if (data.result) {
                $("#titleInfo").html("");
                $("#descriptionInfo").html("");
                $("#infoDialog").modal();
                $("#titleInfo").html("Operazione eseguita con successo!");
                $("#descriptionInfo").html("Presenza modificata.");
            } else {
                $("#titleInfo").html("");
                $("#descriptionInfo").html("");
                $("#infoDialog").modal();
                $("#titleInfo").html("Errore esecuzione operazione!");
                $("#descriptionInfo").html("Errore nella modifica della presenza.");
            }
        
    });
    
}
  
function selectedItemDot()
{
    $("#panelDiv").hide();
    $("#resulthead th").remove();
    $("#resultbody tr").remove();
        selected = $("#Corsoprofessore option:selected").val();
        if (selected !== "default") //se il valore della select è default non mostriamo il div contenente le informazioni delle date delle lezioni
        { 
            $("#resulthead th").remove();
            $("#resultbody tr").remove();   
            selected = $("#Corsoprofessore option:selected").val();
            $("#panelDiv").show();
            //metodo per stampare le date
             var opened_lesson;
             var closed_lesson;
             var id_Phd;
             /*
              * con la chiamata alla servlet GetAllLessonServlet recupero tutte le lezioni del corso selezionato. In più ottengo anche l'id dell'attore che effettua la chiamata
              * nel ciclo effetto la costruzione della tabella filtrando le lezioni in base al loro stato(in corso/terminata)
              * Inizialmente l'attore è presente a tutte le lezioni. Infatti con la chiamata alla Servlet GetPresenceToLesson effettuo l'operazione di filtraggio
              * 
              */ 
            $.getJSON("GetAllLessonServlet", {fkCourse: selected}, function (data1) 
            {
                var head="<tr><th> Lezioni Aperte: </th><th>Data</th><th>Aula</th><th>Presenza</th></tr>";
                closed_lesson="<tr><th> Lezioni Chiuse: </th></tr>";
                id_Phd=data1.dottorando;
                $("#resulthead ").append(head);
                today=new Date();
                $.each(data1.lessons, function (index, value5) 
                {
                    lesson_id=value5.idLesson; 
                    lesson_name=value5.name;
                    lesson_class=value5.classroom;
                    lesson_date=value5.data;
                    lesson_start=value5.startTime;
                    lesson_end=value5.endTime;
                    lesson_status=value5.status;
                    lesson_start_date=get_Date(lesson_date,lesson_start);
                    lesson_end_date=get_Date(lesson_date,lesson_end);
                    result_line="<tr><td>"+lesson_name+"</td><td>"+date_format(lesson_date)+" "+lesson_start+"-"+lesson_end+"</td><td>"+lesson_class+"</td>";
                    checkbox="<td> <input type='checkbox' value='"+id_Phd+ "'   id='" + lesson_id+ "' class='checkboxclass' onchange='changePresenza("+'value'+","+lesson_id+")' checked ";//></td></tr>";
                    result_line=result_line+checkbox;    
                    if(lesson_status==='aperta' && !isAfterNow(lesson_start_date) && isAfterNow(lesson_end_date))
                    {
                        opened_lesson=opened_lesson+result_line+"></td></tr>";
                       
                    }
                    else closed_lesson=closed_lesson+result_line+"disabled></td></tr>";
                    
                
                });
                body=opened_lesson+closed_lesson;
                $("#resultbody ").append(body);
                $.getJSON("GetPresenceToLesson", {idCourse: selected, fkPhdstudent: id_Phd}, function (data)
                {
                    $.each(data.presence, function (index2, value2) 
                    {
                        lez_id = value2.fkLesson;
                        if (!value2.isPresent === true) 
                        {
                             $("#"+lez_id).removeAttr("checked");
                        } 
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
                        dottorando11 = " <th class='archiviata'> " + date_format(data1) + "<p>"+value5.name+"</p></th>  ";
                    }
                    else
                    {
                        dottorando11 = " <th class='"+value5.idLesson+"'> " + date_format(data1) + "<p>"+value5.name+"</p></th>  ";
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
                            if(isLessons.lessons[p].idLesson === lezione)
                            {
                               flag = true;
                               isClosed = isLessons.lessons[p].closed;
                               endDate = get_Date(isLessons.lessons[p].data,isLessons.lessons[p].endTime);
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
                                    app = "<td class='" + lezione + " celle'> <input type = 'button' id = " + lezione + " onclick = 'archiviaPresenze(" + 'id' + ")' value = 'Chiudi Sessione' class = 'btn btn-blue' ";                                
                                    if(isAfterNow(endDate))
                                    {
                                        app += "disabled > </td>";
                                    }
                                    else
                                    {
                                        app += "> </td>";
                                    }                                    
                                    $("#resultBotton ").append(app);
                                }                                                          
                            }
                            
                            td = value2.fkPhdstudent;
                            if (isClosed) {
                               dottorandopre = "<td class='celle archiviata'><input type='checkbox' value='true'   id=" + td + " onclick='changePresenza(" + 'id' + "," + lezione + ")' class='checkboxclass' disabled ";
                            }
                            else{
                                dottorandopre = "<td class='" + lezione + " celle'><input type='checkbox' value='true'   id=" + td + " onclick='changePresenza(" + 'id' + "," + lezione + ")' class='checkboxclass' ";
                                if(isAfterNow(endDate))
                                    {
                                        dottorandopre += "disabled ";
                                    }
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

/* Mmetodo per nascondere/visualizzare 
 * le sessioni chiuse(archiviate)
 */

function changeSessioni() {  
    
    if($("#sessioni").is(':checked')){       
       $(".archiviata").each(function(index,elem){
           $(elem).css("display","");
       });
   }
   else
   {
       $(".archiviata").each(function(index,elem){
           $(elem).css("display","none");
       });       
   }
}

/* Metodo per archiviare le presenze a seguito
 * della chiusura della sessione di una lezione
 * @idLesson 
 */

function archiviaPresenze(idLesson) { 
 //servlet per effettuare l'archiviazione delle presenze
   
   $.getJSON("SetClosedLesson", {idLesson: idLesson}, function (data) { 
    
        if (data.result) {
                $("#titleInfo").html("");
                $("#descriptionInfo").html("");
                $("#infoDialog").modal();
                $("#titleInfo").html("Operazione eseguita con successo!");
                $("#descriptionInfo").html("Sessione chiusa - presenze archiviate.");
                
                $("."+idLesson).each(function(index,elem){
                    
                    oldClass = $(elem).attr("class");
                    newClass = oldClass+" archiviata";
                    $(elem).attr("class",newClass);
                    
                    if($(elem).prop("tagName") === "TD"){
                        
                        if($(elem).find("INPUT").attr("id") === idLesson)
                        {
                            $(elem).find("INPUT").css("display","none");
                        }
                        else
                        {
                           $(elem).find("INPUT").attr("disabled",true); 
                        }
                        
                    } 
                                        
                });
                
                changeSessioni();
                
            } else {
                $("#titleInfo").html("");
                $("#descriptionInfo").html("");
                $("#infoDialog").modal();
                $("#titleInfo").html("Errore chiusura sessione!");
                $("#descriptionInfo").html("Errore nella chiusura della sessione.");
            }
     
      });
    
}

/* metodo per la gestione delle date.
 * crea una data partendo da due stringhe
 * @giorno 
 * @ora 
 */
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

/*
 * Confronta la data con quella attuale
 * @data
 * return true se la data attuale è minore di quella passata 
 * false altrimenti
 */
function isAfterNow(data)
{
    var now=new Date().getTime();
    if(data.getTime() < now)
        return false;
    else return true; 
}
/*
 * metodo che permette di visualizzare la data nel formato italiano
 */
function date_format(date)
{
    var month= date.substring(5,7);
    var day=date.substring(8);
    return day+"-"+month+"-"+date.substring(0,4);
}
