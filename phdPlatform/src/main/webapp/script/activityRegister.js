/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function insertActivity() {
    name= $("#name").val();
    description = $("#description").val();
    startDateTime = $("#dateActivity").val() + " " +convertTo24Hours($("#startTimeActivity").val());
    endDateTime = $("#dateActivity").val() + " " +convertTo24Hours($("#endTimeActivity").val());
    typology = $("#typology option:selected").val();
    if (name !== "" && typology !== "default" && description !== "") {
        $.getJSON("InsertActivity", {name:name,description:description,startDateTime:startDateTime,endDateTime:endDateTime,typology:typology}, 
        function (data) {
            console.log(data)
           if (data.result) {
                $("#titleInfo").html("");
                $("#descriptionInfo").html("");
                $("#infoDialog").modal();
                $("#titleInfo").html("Operazione eseguita con successo!");
                $("#descriptionInfo").html("L'attività è stata aggiunta.");
            } else {
                $("#titleInfo").html("");
                $("#descriptionInfo").html("");
                $("#infoDialog").modal();
                $("#titleInfo").html("Errore inserimento evento!");
                $("#descriptionInfo").html("L'attività NON è stata aggiunta, riprova.");
            }
        });
    }else {
        alert("Compilare tutti i campi!");
    }
}



function convertTo24Hours(ora)
{
    if(ora.length ===7) 
    {
        ora="0"+ora;
        
    }
    var ore=parseInt(ora.substring(0,2));
    var minuti=ora.substring(3,5);
    
    if(ora.substring(6)==="PM")
    {
        ore=ore+12;
    }
    
    return ore+":"+minuti;
}

function updateActivity(){
    idActivity = sessionStorage.getItem('idActivity');
    name= $("#name").val();
    description = $("#description").val();
    startDateTime = $("#dateActivity").val() + " " +convertTo24Hours($("#startTimeActivity").val());
    endDateTime = $("#dateActivity").val() + " " +convertTo24Hours($("#endTimeActivity").val());
    typology = $("#typology option:selected").val();
    if (name !== "" && typology !== "default" && description !== "") {
        $.getJSON("UpdateActivity", {idActivity:idActivity, name:name, description:description,startDateTime:startDateTime,endDateTime:endDateTime,typology:typology}, 
        function (data) {
            console.log(data)
           if (data.result) {
                $("#titleInfo").html("");
                $("#descriptionInfo").html("");
                $("#infoDialog").modal();
                $("#titleInfo").html("Operazione eseguita con successo!");
                $("#descriptionInfo").html("L'attività è stata aggiunta.");
            } else {
                $("#titleInfo").html("");
                $("#descriptionInfo").html("");
                $("#infoDialog").modal();
                $("#titleInfo").html("Errore inserimento evento!");
                $("#descriptionInfo").html("L'attività NON è stata aggiunta, riprova.");
            }
        });
    }else {
        alert("Compilare tutti i campi!");
    }
}

function fillField(){
    $("#name").val(sessionStorage.getItem('name'));
    $("#description").val(sessionStorage.getItem('description'));
    $("#dateActivity").val((sessionStorage.getItem('startDateTime')).substring(0,10));
    $("#startTimeActivity").val(convertToAmPm(sessionStorage.getItem('startDateTime').substring(11,20)));
    $("#endTimeActivity").val(convertToAmPm(sessionStorage.getItem('endDateTime').substring(11,20)));
    $("#typology option[value='"+sessionStorage.getItem('typology')+"']").attr('selected','selected');
}

function convertToAmPm(ora)
{
    var ore=parseInt(ora.substring(0,2));
        console.log(ore);

    var minuti=ora.substring(3,5);
        console.log(minuti);

    if(ore <= 12)
    {
        ore = ore +":"+minuti + " AM";
    } 
    else{
        ore=ore-12;
        ore = ore +":"+minuti + " PM";
    }
    return ore;
}

function updateFunction(){
    fillField();
    $("#Intestazione").html('<h1>Modifica Attività nel Registro</h1>');
   $("#bottoneInsUpdate").html("<input  type='submit' class='btn btn-blue' value='Modifica' onclick='updateActivity()'>");
    
}

function insertFunction(){
   $("#Intestazione").html('<h1> Inserisci Attività nel Registro </h1>');
    $("#bottoneInsUpdate").html("<input  type='submit' class='btn btn-blue' value='Inserisci' onclick='insertActivity()'>");

}