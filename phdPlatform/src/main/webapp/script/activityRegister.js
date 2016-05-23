/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
const MAX_HOURS = 1500;

/**
 * metodo che permette l'inserimento o la modifica di un'attività
 * effettua i seguenti controlli:
 * i campi non sono vuoti
 * l'ora iniziale è minore di quella finale
 * il numero di ore raggiunte è minore di 1500
 * @param {type} servlet nome della servlet da invocare
 * @returns {undefined}
 */
function insertEditActivity(servlet) {

    var idActivity = sessionStorage.getItem('idActivity'),
        name= $("#name").val(),
        description = $("#description").val(), 
        typology = $("#typology option:selected").val(),
        date = $("#dateActivity").val(),
        startDateTime = date + " " + convertTo24Hours($("#startTimeActivity").val()),
        endDateTime = date + " " + convertTo24Hours($("#endTimeActivity").val()),
        d1 = new Date(startDateTime), 
        d2 = new Date(endDateTime) ;
       
    if (name !== "" && typology !== "default" && description !== "" && date !=="" ) {
        if(checkTime(d1,d2)){
            if(checkTotHours(d1,d2)){
                 $.getJSON(servlet, {
                     idActivity:idActivity,
                     name:name,
                     description:description,
                     startDateTime:startDateTime,
                     endDateTime:endDateTime,
                     typology:typology}, 
                    function (data) {
                        if (data.result) {
                            //location.href = "activityRegister.jsp"; 

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
            }else{
                $("#infoDialog").modal();
                  $("#titleInfo").html("Ore maggiori di 1500");
                
            }
        }else{
           // $("#infoDialog").modal();
            //$("#titleInfo").html("L'ora iniziale è maggiore della finale");
             $("#startTimeActivity").css("border", "2px red solid");
             $("#endTimeActivity").css("border", "2px red solid");
             setTimeout(function(){
                  $("#startTimeActivity").css("border", "1px solid #e4e4e4");
                  $("#endTimeActivity").css("border", "1px solid #e4e4e4");
             },1500);
        }
    } else {
        $("#infoDialog").modal();
        $("#titleInfo").html("Compilare tutti i campi!");
    }
}
/**
 * confronto tra l'ora di inizio e di fine dell'attività
 * @param {type} startTime
 * @param {type} endTime
 * @returns {Boolean} true se l'ora di fine è maggiore di quella di inizio
 */
function checkTime(startTime,endTime){
    console.log(startTime+" "+endTime)
     if(startTime.getHours() < endTime.getHours() )
         return true;
     else
         return false;
}

/**
 * considerando due date calcola la differenza e delle ore passate 
 * e le somma al totale delle ore calcolate fino a quel momento.
 * e verifica se minore di 1500
 * @param {type} timeStart 
 * @param {type} timeEnd
 * @returns {Boolean} true se il nuemro di ore è minore di 1500, false altrimenti
 */
function checkTotHours(timeStart,timeEnd) {
    //get values
    var diff = timeEnd.getTime() - timeStart.getTime();
    var msec = diff;
    var hh = Math.floor(msec / 1000 / 60 / 60);
    msec -= hh * 1000 * 60 * 60;
    var mm = Math.floor(msec / 1000 / 60);
    msec -= mm * 1000 * 60;
    var ss = Math.floor(msec / 1000);
    msec -= ss * 1000;
  
    console.log("TotalH "+sessionStorage.getItem('totalHours'));

  
    var tot = hh + parseInt(sessionStorage.getItem('totalHours'));
    console.log(tot +" "+sessionStorage.getItem('totalHours'));
    if(tot < MAX_HOURS)
        return true;
    else
        return false;
  //;
}

/**
 * converte la stringa con l'ora rappresentata con AM e PM
 * in 24 ore
 * @param {type} ora
 * @returns {String}
 */
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

/**
 * converte l'ora in formato 24 ore in formato AM o PM
 * @param {type} ora
 * @returns {Number|String}
 */
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

/**
 * modifica l'interfaccia utente per la modifica di un'attività compilando i campi.
 * @returns {undefined}
 */
function updateFunction(){
    $("#name").val(sessionStorage.getItem('name'));
    $("#description").val(sessionStorage.getItem('description'));
    $("#dateActivity").val((sessionStorage.getItem('startDateTime')).substring(0,10));
    $("#startTimeActivity").val(convertToAmPm(sessionStorage.getItem('startDateTime').substring(11,20)));
    $("#endTimeActivity").val(convertToAmPm(sessionStorage.getItem('endDateTime').substring(11,20)));
    $("#typology option[value='"+sessionStorage.getItem('typology')+"']").attr('selected','selected');

    $("#Intestazione").html('<h1>Modifica Attività nel Registro</h1>');
    $("#bottoneInsUpdate").html("<input  type='submit' class='btn btn-blue' value='Modifica' onclick=\"insertEditActivity('UpdateActivity')\">");
    
}

/**
 * modifica l'interfaccia utente per l'inserimento
 * @returns {undefined}
 */
function insertFunction(){
   $("#Intestazione").html('<h1> Inserisci Attività nel Registro </h1>');
   $("#bottoneInsUpdate").html("<input  type='submit' class='btn btn-blue' value='Inserisci' onclick=\"insertEditActivity( 'InsertActivity')\">");
}