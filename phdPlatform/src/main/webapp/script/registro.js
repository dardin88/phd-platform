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
                var checklesson=true;
                var head="<tr><th style='color:#0066cc'> Lezioni Aperte: </th><th style='color:#0066cc'>Data</th><th style='color:#0066cc'>Aula</th><th style='color:#0066cc'>Presenza</th></tr>";
                closed_lesson="<tr><th style='color:#0066cc'> Lezioni Chiuse: </th></tr>";
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
                    result_line="<tr><td>"+lesson_name+"</td><td>"+date_format(lesson_date)+" "+lesson_start+" - "+lesson_end+"</td><td>"+lesson_class+"</td>";
                    checkbox="<td> <input type='checkbox' value='"+id_Phd+ "'   id='" + lesson_id+ "' class='checkboxclass' onchange='changePresenza("+'value'+","+lesson_id+")' checked ";//></td></tr>";
                    result_line=result_line+checkbox;    
                    if(lesson_status==='aperta' && !isAfterNow(lesson_start_date) && isAfterNow(lesson_end_date))
                    {
                        opened_lesson=opened_lesson+result_line+"></td></tr>";
                        checklesson=false;
                       
                    }
                    else closed_lesson=closed_lesson+result_line+"disabled></td></tr>";
                    
                
                });
                if(checklesson)
                {
                    opened_lesson="<tr><td colspan='4' style='text-align: center; font-weight: bold;'> ***** Al momento non sono presenti sessioni aperte *****</td></tr>";
                }
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

/* Metodo utilizzato per ricercare le presenze dei dottorandi per una 
 * determinata lezione.
 * Tale metedo inoltre si occupa della costruzione della tabella.
 */

function selectedItem3(idLesson,nameLesson){  
    
    changeLesson(1);
    $("#resultbody3 tr").remove();
    $("#resulthead3 tr").remove();
    $("#resultfoot3 tr").remove();
        
    var student;        
    head = "<tr id = thPresence><th colspan='2'>"+ nameLesson +"</th></tr>"; 
    head += "<tr><th> Dottorando </th><th> Presenza </th></tr>";
    $("#resulthead3").append(head);
        
    $.getJSON("GetPresencesLesson", {idLesson: idLesson}, function (data) {
    
        $.each(data.presence, function (index, value) {
            
            student = " <tr><td class='students'> " + value.name + " " + value.surname + " </td>";
            student += "<td style = 'text-indent: 37px;'><input type='checkbox'  id=" + value.fkPhdstudent + " onclick='changePresenza(" + 'id' + "," + idLesson + ")' ";
            if (value.isPresent === true) 
            {
                student += " checked";
            }                           
            student += "></td></tr>";
            $("#resultbody3").append(student);
        });        
    });
        
    $("#resultfoot3").append("<tr><td colspan = '2' style = 'text-align:center'></br><input type = 'button' onclick = 'changeLesson(0)' value = 'Torna alla lista delle Lezioni' class = 'btn btn-blue'></td></tr>");
}

/* Mmetodo per nascondere/visualizzare la sezione presenze per una lezione.
 * La variabile isEntry indica se si sta procedendo con fase di
 * visualizzazzione delle presenze per una lezione(1) o si sta 
 * ritornando alla lista delle lezioni(0). 
 * 
 * @ingresso
 */

function changeLesson(isEntry) {
    
    if(isEntry)
    {
        $("input[name='radioLesson']").attr('disabled','disabled');
        $("#Corsoprofessore").attr('disabled','disabled');
        
        $("#resultst3").css("display","");
        
        if($("#lez_aperte").is(':checked'))
        {
            $("#resultst").css("display","none");           
        }
        else if($("#lez_chiuse").is(':checked'))
        {
            $("#resultst2").css("display","none");          
        } 
    }
    else
    {
        $("#resultst3").css("display","none");
    
        if($("#lez_aperte").is(':checked'))
        {
            $("#resultst").css("display","");             
        }
        else if($("#lez_chiuse").is(':checked'))
        {
            $("#resultst2").css("display","");             
        }
        
        $("input[name='radioLesson']").removeAttr('disabled');
        $("#Corsoprofessore").removeAttr('disabled');
    }        
}

/* Metodo per cambiare lo stato della lezione
 * @idLesson 
 * @status
 * @flagMex
 */

function changeStates(idLesson, status ,flagMex) { 
    
   $.getJSON("SetStatusLesson", {idLesson: idLesson, status: status}, function (data) { 
    
        if (data.result) {
            if(flagMex)
            {
                $("#titleInfo").html("");
                $("#descriptionInfo").html("");
                $("#infoDialog").modal();
                $("#titleInfo").html("Operazione eseguita con successo!");
                $("#descriptionInfo").html("Cambia stato effettuato.");
            }    
                
            if(status === "aperta")
            {
                $("#"+idLesson+"_open").css("display","none");
                $("#"+idLesson+"_closed").css("display","");          
            }
            else
            {                    
               $("#"+idLesson+"_cell").remove();
               
               if($("#resultbody2 tr").attr('name') === 'mex')
               {
                   $("#resultbody2 tr").remove();
               }               
              
               $("#resultbody2").append("<tr>" + $("#"+idLesson+"_row").html() + "</tr>"); 
               
               $("#"+idLesson+"_row").remove();
               
               if($("#resultbody tr").size() === 0)
               {
                   $("#resultbody").append("<tr><td colspan='4' style='text-align: center; font-weight: bold; font-size: 15px;'> ***** Al momento non sono presenti sessioni aperte *****</td></tr>");
               }               
               
            }                            
        }else 
        {
            $("#titleInfo").html("");
            $("#descriptionInfo").html("");
            $("#infoDialog").modal();
            $("#titleInfo").html("Errore cambia stato!");
            $("#descriptionInfo").html("Errore nel cambiamento di stato della lezione.");
        }     
      });
    
}

/* metodo per la gestione delle date.
 * crea una data partendo da due stringhe
 * @giorno 
 * @ora 
 */
function get_Date(giorno, ora)
{
    if(ora.length ===7) 
    {
        ora="0"+ora;
        
    }
    var ore=parseInt(ora.substring(0,2));
    var minuti=parseInt(ora.substring(3));
    
    if((ora.substring(6)==="PM")&&(ore !== 12))
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
    return day+"/"+month+"/"+date.substring(0,4);
}

/* Metodo utilizzato per ricercare le lezioni create da un determinato professore,
 * per il corso selezionato.
 * Inoltre tale metedo si occupa della costruzione della tabella con le lezioni
 * in stato aperto o chiuse per la funzionalità "Gestione Presenze".
 */

function selectedItem2()
{
    var result_line;
    
    $("#panelDiv").hide();
    $("#resulthead th").remove();
    $("#resultbody tr").remove();
    $("#resulthead2 th").remove();
    $("#resultbody2 tr").remove();
        
    selected = $("#Corsoprofessore option:selected").val();
    
    if (selected !== "default") //se il valore della select è default non mostriamo il div contenente le informazioni delle date delle lezioni
    { 
        $("#resulthead th").remove();
        $("#resultbody tr").remove(); 
        $("#resulthead2 th").remove();
        $("#resultbody2 tr").remove();
        
                    
        selected = $("#Corsoprofessore option:selected").val();
        $("#panelDiv").show();
        
        var opened_lesson = "";
        var closed_lesson = "";
             
        /*
        * Con la chiamata alla servlet GetAllLessonProfessor recupero tutte le lezioni del corso selezionato
        * in base al professore loggato.        * 
        */  
        
        $.getJSON("GetLessonsProfessor", {fkCourse : selected}, function (data1)
        {
            var head="<tr><th> Lezioni Aperte: </th><th>Data</th><th>Aula</th><th></th></tr>";
            var head2="<tr><th> Lezioni Chiuse: </th><th>Data</th><th>Aula</th></tr>";
            $("#resulthead").append(head);
            $("#resulthead2").append(head2);
            
            if(data1.result)
            {
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
                    
                    if(isAfterNow(lesson_start_date))
                    {
                        result_line = "<tr id = '" + lesson_id + "_row'><td class = 'stLesson'>";
                    }
                    else
                    {
                        result_line = "<tr id = '" + lesson_id + "_row'><td onclick = \"selectedItem3(" + lesson_id + ",'" + lesson_name + "')\" class = 'stLesson stLesson_hover'>";                        
                    }
                        
                    result_line += lesson_name+"</td><td>"+date_format(lesson_date)+" &nbsp;&nbsp; "+lesson_start+" - "+lesson_end+"</td><td>"+lesson_class+"</td>";

                    if((!isAfterNow(lesson_end_date))&&(lesson_status !== "chiusa"))
                    {
                        result_line += "<td></td></tr>";
                        changeStates(lesson_id , "chiusa", 0);
                        lesson_status = "chiusa";
                    }    
                    else if(lesson_status === "in_programma")
                    {
                        result_line += "<td id = '" + lesson_id + "_cell' style = 'text-align:center'><button type = 'button' id = '" + lesson_id + "_open' onclick = 'changeStates(" + lesson_id + ", \"aperta\", 1)' class = 'btn btn-default btn-secondary' ";                                
                        if(isAfterNow(lesson_start_date))
                        {
                            result_line += "disabled > ";
                        }
                        else
                        {
                            result_line += "> ";
                        }
                        result_line += "<span class='glyphicon glyphicon-pencil'></span> Apri Sessione</button> "; 
                        result_line += "<button type = 'button' id = '" + lesson_id + "_closed' onclick = 'changeStates(" + lesson_id + ", \"chiusa\", 1)' class = 'btn btn-red' style=' display: none'>";                                
                        result_line += "<span class='glyphicon glyphicon-remove'></span> Chiudi Sessione</button></td></tr>";
                    }
                    else if(lesson_status === "aperta")
                    {
                        result_line += "<td id = '" + lesson_id + "_cell' style = 'text-align:center'><button type = 'button' id = '" + lesson_id + "_closed' onclick = 'changeStates(" + lesson_id + ", \"chiusa\", 1)' class = 'btn btn-red' >";                                
                        result_line += "<span class='glyphicon glyphicon-remove'></span> Chiudi Sessione</button></td></tr>";
                    }
                    else if(lesson_status === "chiusa")
                    {
                        result_line += "</tr>";
                    }        

                    if(lesson_status !== 'chiusa')
                    {
                        opened_lesson += result_line;
                    }
                    else
                    {
                        closed_lesson += result_line;
                    }

                });
            }
            
            if(opened_lesson === "")
            {
                opened_lesson="<tr><td colspan='4' style='text-align: center; font-weight: bold; font-size: 15px;'> ***** Al momento non sono presenti sessioni aperte *****</td></tr>";
            }
            if(closed_lesson === "")
            {
                closed_lesson="<tr name='mex'><td colspan='4' style='text-align: center; font-weight: bold; font-size: 15px;'> ***** Al momento non sono presenti sessioni chiuse *****</td></tr>";
            }
                      
            $("#resultbody ").append(opened_lesson);
            $("#resultbody2").append(closed_lesson);
        });             
    }
}

function mostraSessioni()
{  
    
    if($("#lez_aperte").is(':checked'))
    {       
       $("#resultst2").css("display","none");
       $("#resultst").css("display","");
    }
    else if($("#lez_chiuse").is(':checked'))
    {
       $("#resultst").css("display","none");
       $("#resultst2").css("display","");
    }       
   
}