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
/*
function updateActivity(){
   
   
}

function fillField(){
     $("#name").val();
    $("#description").val("afasfasfsfasffassfafsfas");
    $("#dateActivity").val("2016-05-10 09:00 AM".substring(0,10));
    $("#startTimeActivity").val("2016-05-10 18:00:00".substring(12,20))
}

function convertToAmPm(ora)
{
    var ore=parseInt(ora.substring(0,2));
    var minuti=ora.substring(4,6);
    
    if(ore > 12)
    {
        ore=ore-12;
    }
    
    return ore+":"+minuti;
}*/