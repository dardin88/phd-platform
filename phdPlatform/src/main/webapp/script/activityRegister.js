/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
const MAX_HOURS = 1500;

/**
 * modifica l'interfaccia utente per la modifica di un'attività. I campi sono precompilati.
 * @returns {undefined}
 */
function updateFunction(){
    $("#name").val(sessionStorage.getItem('name'));
    $("#description").val(sessionStorage.getItem('description'));
    $("#dateActivity").val((sessionStorage.getItem('startDateTime')).substring(0,10));
    $("#startTimeActivity").val(convertToAmPm(sessionStorage.getItem('startDateTime').substring(11,20)));
    $("#endTimeActivity").val(convertToAmPm(sessionStorage.getItem('endDateTime').substring(11,20)));
    $("#typology").val(sessionStorage.getItem('typology'));

    $("#Intestazione").html('<h1>Modifica Attività nel Registro</h1>');
    $("#bottoneInsUpdate").html("<input  type='submit' class='btn btn-blue' value='Modifica' onclick=\"insertEditActivity('UpdateActivity')\">"); 
}

/**
 * modifica l'interfaccia utente adattandola all'inserimento
 * @returns {undefined}
 */
function insertFunction(){
   $("#Intestazione").html('<h1> Inserisci Attività nel Registro </h1>');
   $("#bottoneInsUpdate").html("<input  type='submit' class='btn btn-blue' value='Inserisci' onclick=\"insertEditActivity( 'InsertActivity')\">");
}

/**
 * metodo che permette l'inserimento o la modifica di un'attività
 * @param {type} servlet nome della servlet da invocare
 * @returns {undefined}
 */
function insertEditActivity(servlet) {

    //recupera i campi della form
    var idActivity = sessionStorage.getItem('idActivity'),
        name= "",
        description = $("#description").val().substring(0,30), 
        typology = $("#typology").val(),
        name = typology !=="Seminario" ?$("#name").val(): $("#seminarSelect :selected").text() ,
        date = $("#dateActivity").val(),
        startDateTime = date + " " + convertTo24Hours($("#startTimeActivity").val()),
        endDateTime = date + " " + convertTo24Hours($("#endTimeActivity").val()),
        d1 = new Date(startDateTime), 
        d2 = new Date(endDateTime),
        idSeminar = "", 
        idSeminar = typology !=="Seminario" ? "N/A": $("#seminarSelect :selected").val() ;

    //Controllo sui campi. Non devono essere vuoti
    if (name !== "" && typology !== "" && description !== "" && date !=="" && name !=="seleziona seminario") {
        //controllo se l'ora di fine viene dopo l'ora di inizio
        if(checkTime(d1,d2)){
            //controllo se l'aggiunta delle ore delle nuove attività non è maggiore di 1500
            if(checkTotHours(d1,d2)){
                 $.getJSON(servlet, {
                    idActivity:idActivity,
                    name:name,
                    description:description,
                    startDateTime:startDateTime,
                    endDateTime:endDateTime,
                    typology:typology,
                    idSeminar: idSeminar}, 
                    function (data) {
                        if (data.result) {
                            location.href = "activityRegister.jsp"; 
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
 * confronto tra l'ora di inizio e l'ora di fine dell'attività
 * l'attività di inizio deve essere minore dell'attività di fine
 * @param {type} startTime
 * @param {type} endTime
 * @returns {Boolean} true se l'ora di fine è maggiore di quella di inizio
 */
function checkTime(startTime,endTime){
     if(startTime < endTime )
         return true;
     else
         return false;
}

/**
 * il totale dei minuti passati come parametro viene sommato alle ore delle attività attuali 
 * e restituisce true se è minore di 1500, false altrimenti
 * @param {type} timeStart 
 * @param {type} timeEnd
 * @returns {Boolean} true se il nuemro di ore è minore di 1500, false altrimenti
 */
function checkTotHours(timeStart,timeEnd) {
    var diff = timeEnd.getTime() - timeStart.getTime();
    var hh = Math.floor(diff / 1000 / 60 / 60);
    
    var tot = hh + parseInt(sessionStorage.getItem('totalHours'));
    //console.log(tot +" "+sessionStorage.getItem('totalHours'));
    
    if(tot <= MAX_HOURS)
       return true;
    else
       return false;
}

/**
 * converte l'ora dal formato AM-PM in formato 24 ore
 * @param {type} ora in formato AM-PM
 * @returns {String} ora in 24 ore
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
        if(ore === 12)
            ore = 12;
        else
            ore=ore+12;
    }
    return ore+":"+minuti;
}

/**
 * converte l'ora dal formato 24 ore nel formato AM o PM
 * @param {type} ora in 24 ore
 * @returns {Number|String} ora in AM-PM
 */
function convertToAmPm(ora)
{
    var ore=parseInt(ora.substring(0,2));
    var minuti=ora.substring(3,5);

    if(ore < 12)
    {
        ore = ore +":"+minuti + " AM";
    } else if(ore === 12)
        ore = ore +":"+minuti + " PM";
    else{
        ore=ore-12;
        ore = ore +":"+minuti + " PM";
    }
    return ore;
}


/**
 * popola la select delle tipologie presenti nel database
 * @returns {undefined}
 */
 function getTypology(){
     $.getJSON("GetTypology", {},  
        function (data) {
            
            var options = $("#typologySelect");
            //options.append($("<option />").val("").text("seleziona tipologia"));
            $.each(data.typologyList, function(index,typology) {
                    options.append($("<option />").val(typology.name).text(typology.name));
            });
        }    
    );
}

/**
 * invocata nell'onready e recupera tutti i seminari e li inserisce nella select riguardanti i seminari
 * @returns {undefined}
 */
function getSeminar(){
     $.getJSON("GetAllSeminarServlet", {},  
        function (data) {
            var options = $("#seminarSelect");
            options.append($("<option />").val("").text("seleziona seminario"));
            window.allSeminars=data.seminar;

            $.each(data.seminar, function(index,seminar) {
                    options.append($("<option />").val(seminar.idSeminar).text(seminar.name));
            });
        }    
    );
}

/**
 * Una volta selezionato un seminario, i campi vengono riempiti con le informazioni relative al seminario presente nel DB
 * e i camoi non sono modificabili
 * @returns {undefined}
 */
function onChangeSeminar(){
    //controllo che se non viene selezionato un seminario ripulisce i campi
    if($("#seminarSelect :selected").text() !== "seleziona seminario"){
        var seminarId = $("#seminarSelect :selected").val();

        for(var i=0; i<window.allSeminars.length; i++ ){
            if(window.allSeminars[i].idSeminar == seminarId){
                var seminar = window.allSeminars[i];
                $("#description").val(seminar.description);
                $("#description").attr("disabled","disabled");
                
                $("#dateActivity").val(seminar.data);
                $("#dateActivity").attr("disabled","disabled");

                $("#startTimeActivity").val(seminar.startTime);
                $("#startTimeActivity").attr("disabled","disabled");

                $("#endTimeActivity").val(seminar.endTime);
                $("#endTimeActivity").attr("disabled","disabled");
                break;
           }
       }  
    }else
        clearAll();
 }
/**
 * Se la tipologia selezionata dall'utente è seminario, mostrerà nella select tutti i seminari
 * altrimenti da la possibilità all'utente di inserire il nome dell'attività.
 * @param {type} typology
 * @returns {undefined}
 */
function onSelectChange(typology){
    clearAll();

    if(typology ==="Seminario"){
        $("#seminarSelect").css( "visibility","visible" );
        $("#name").css("visibility","hidden");
    }else{
        $("#seminarSelect").css( "visibility","hidden" );
        $("#name").css("visibility","visible");
    }
}

/**
 * ripulisce i campi se erano riempiti
 * @returns {undefined}
 */
function clearAll(){
    $("#description").val("");
    $("#description").attr("disabled",false);
    
    $("#dateActivity").val("");
    $("#dateActivity").attr("disabled",false);
    
    $("#startTimeActivity").attr("disabled",false);
    $("#endTimeActivity").attr("disabled",false);
}

/**
 * controlla se la tipologia inserita dall'utente è "Seminario" 
 * viene sostituita con un'altra stinga
 * @returns {undefined}
 */
function checkSeminarTypology(){
    if($("#typology").val() === "Seminario"){
        $("#typology").val("Altro Seminario");
    }
}