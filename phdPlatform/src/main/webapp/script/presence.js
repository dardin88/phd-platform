/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
    head += "<tr><th> Dottorando </th><th> Presenza </th></tr>";
    $("#resulthead").append(head);
        
    $.getJSON("GetPresencesLesson", {idLesson: idLesson}, function (data) {
        
        if(data.result)
        {
            $.each(data.presence, function (index, value) {

                student = " <tr><td class='students'> " + value.name + " " + value.surname + " </td>";
                student += "<td style = 'text-indent: 37px;'><input type='checkbox'  id=" + value.fkPhdstudent + " onclick='changePresenza(" + 'id' + "," + idLesson + ")' ";
                if (value.isPresent === true) 
                {
                    student += " checked";
                }                           
                student += "></td></tr>";
                $("#resultbody").append(student);
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