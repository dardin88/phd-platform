/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var global_nStudents = 0;

$(document).ready(function () {    
    start();
});

function start() {
    
    idLesson = sessionStorage.getItem('idLesson');
    nameLesson = sessionStorage.getItem('nameLesson');
    
    $("#titleCourse").append("<p>" + sessionStorage.getItem('nameCourse') + "</p>");
    
    presenceManagement(idLesson,nameLesson);
}

/* Metodo utilizzato per ricercare le presenze dei dottorandi per una 
 * determinata lezione.
 * Tale metedo inoltre si occupa della costruzione della tabella.
 */

function presenceManagement(idLesson,nameLesson){  
    
    $("#resultbody tr").remove();
    $("#resulthead tr").remove();
    $("#resultfoot tr").remove();
        
    var student;        
    head = "<tr id = thPresence><th colspan='2'>Lezione: "+ nameLesson +"</th></tr>"; 
    head += "<tr><th> Dottorando </th><th style='text-align: center'> Presenze </th></tr>";
    $("#resulthead").append(head);
        
    $.getJSON("GetPresencesLesson", {idLesson: idLesson}, function (data) {
        
        if(data.result)
        {
            $.each(data.presence, function (index, value) {

                student = " <tr><td class='students'> " + value.name + " " + value.surname + " </td>";
                student += "<td class='presences'><input type='checkbox'  id=" + value.fkPhdstudent + " onclick='changePresenza(" + 'id' + "," + idLesson + ")' ";
                if (value.isPresent === true) 
                {
                    student += " checked";
                }                           
                student += "></td></tr>";
                $("#resultbody").append(student);
                
                global_nStudents ++;
            });
        }
        else
        {
            $("#titleInfo").html("");
            $("#descriptionInfo").html("");
            $("#infoDialog").modal();
            $("#titleInfo").html("Errore esecuzione operazione!");
            $("#descriptionInfo").html("Errore nella ricerca delle presenze.");            
        }
    });
    
    if(sessionStorage.getItem('sessionLesson') === '#openLessons')
    {
        foot = "<tr><td></td><td style = 'text-align:center'><button type = 'button' onclick = 'changeAllPresences(1)' class = 'btn btn-default btn-secondary space' title=\"Inserisci tutte le presenze\">";
        foot += "<span class='glyphicon glyphicon-pencil'></span> Inserisci Tutte</button>"; 
        foot += "<button type = 'button' onclick = 'changeAllPresences(0)' class = 'btn btn-red space' title=\"Elimina tutte le presenze\">";                                
        foot += "<span class='glyphicon glyphicon-remove'></span> Elimina Tutte</button></td></tr>";
        
        $("#resultfoot").append(foot);
    }
        
    $("#resultfoot").append("<tr><td colspan = '2' style = 'text-align:center'></br><input type = 'button' onclick = \"location.href = 'registroPresenze.jsp'\" value = 'Torna alla lista delle Lezioni' class = 'btn btn-blue'></td></tr>");   
}


// metodo impostare la presenza 

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

function changeAllPresences(isPresent) {
    
    var inputCheck = 0;
    
    $("input:checkbox").each(function() {
        if($(this).is(':checked'))
            inputCheck ++;
    });
    
    if((inputCheck != 0 && !isPresent)||(!(inputCheck == global_nStudents) && isPresent))
    {
        $.getJSON("ChangeAllPresencesLesson",{fkLesson:sessionStorage.getItem('idLesson'), isPresent:isPresent}, function (data) { 
        
        if (data.result) {
                $("#titleInfo").html("");
                $("#descriptionInfo").html("");
                $("#infoDialog").modal();
                $("#titleInfo").html("Operazione eseguita con successo!");
                if(isPresent)
                {
                    $("#descriptionInfo").html("Presenze inserite.");
                    $("input:checkbox").prop("checked",true);
                }
                else
                {
                    $("#descriptionInfo").html("Presenze eliminate.");
                    $("input:checkbox").removeProp("checked");
                }                
            } else {
                $("#titleInfo").html("");
                $("#descriptionInfo").html("");
                $("#infoDialog").modal();
                $("#titleInfo").html("Errore esecuzione operazione!");
                $("#descriptionInfo").html("Errore nella modifica della presenze.");
            }
        
        });
    }
}
