/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var curriculumYear;
$(document).ready(function(){ 
    var year = new Date().getFullYear();
    curriculumYear = (year-1) + "/"+year;
    $("#year").html(curriculumYear);
    //Servlet che riempie il campo con i seminari dei corsi seguiti dallo studente di un determinato corso
    $.getJSON("GetSeminarOfCourseOfStudent", {}, 
        function (data) {
            console.log(data);
            createHeaderTable('#courseSeminarTable',"Nome Corso - Seminario - Data - Ora Inizio - Ora Fine - Descrizione - Luogo - Nome Speacker",
            "10%-10%-10%-10%-10%-30%-10%-10%");
            createTable('#courseSeminarTBody',data.seminarsOfCourse,"courseName name date startTime endTime description place nameSpeacker");
            });

    //Servlet che riempie la tabella delle pubblicazioni
    $.getJSON("GetAllPublicationsServlet", {}, 
        function (data) {
            console.log(data);
            createHeaderTable('#publicationTable',"Titolo - tipologia - Altri Autori - Abstract",
            "20%-10%-10%-60%");
            createTable('#publicationTBody',data.publications,"title type otherAuthors abstract");});
    
    //Servlet che riempie la tabella delle missioni
    $.getJSON("GetAllMissionsServlet", {}, 
        function (data) {
            createHeaderTable('#missionTable',"Data Inizio - Data Fine - Descrizione - Luogo",
            "25%-25%-25%-25%");
            createTable('#missionTBody',data.missions,"startDate endDate description place");});

    //Servlet che riempie la tabella delle collaborazioni
    $.getJSON("GetAllCollaborationsServlet", {}, 
        function (data) {
            createHeaderTable('#collaborationTable',"Data Inizio - Data Fine - Descrizione - Istituzione" ,
            "25%-25%-25%-25%");
            createTable('#collaborationTBody',data.collaborations,"startDate endDate description istitution");});
    
    //creazione tabella per la partecipazione ad altre scuole di dottorato
    createHeaderTable('#convegni',"Partecipazione a - Luogo - Data - Ruolo",
            "25%-25%-25%-25%");
   
   //creazione della tabella per inserire altre attività
    createHeaderTable('#altreAttivita',"Altre Attività","100%");

    //premendo il bottone aggiungi Riga o Aggiungi Altre Attività, aggiunge una riga alla tabella corrispondente
    $('.plusButton').click(function() {
        var table = $( this ).next().attr("id");//recupera l'id della tabella
        var tds = $('#'+table).children('tbody').children('tr').children('th').length;//recupera dinamicamente il numero di colonne della tabella
        tableEditable(tds,'#'+table);//crea una riga con testo editabile
    });
});

/**
 * Crea gli header della tabella, passata come parametro. i campi e le rispettive larghezze
 * sono passate come parametro
 * @param {type} nameTable : nome della tabella, id o class del tag <table> in cui va creata la tabella
 * @param {type} toPrint : header della tabella
 * @param {type} width : larghezza dei campi della tabella
 * @returns {toPrint@call;split.length}
 */
function createHeaderTable(nameTable,toPrint,width){
    var table = $(nameTable);
    var row= $("<tr/>");
    var toPrintArr = toPrint.split("-");
    var widthTh = width.split("-");
    for(var i=0; i < toPrintArr.length; i++){
        row.append($("<th/>").text(toPrintArr[i]).css('width',widthTh[i]));  
    }
    table.append(row);
    return toPrintArr.length;
}
/**
 * crea una tabella e aggiunge ad ogni riga una checkbox
 * @param {type} nameTbody : id o class del tag <tbody> in cui va creata la tabella
 * @param {type} arr :  array di oggetti recuperati dalla Servlet
 * @param {type} toPrint : stringa dei valori dei campi dell'oggetto da stampare nella tabella
 * @returns {undefined}
 */
function createTable(nameTbody,arr,toPrint){
    var table = $(nameTbody);
    var row= $("<tr/>");
    var toPrintArr = toPrint.split(" ");
    
    //itera l'array passato dalla Servlet
    arr.forEach(function(obj){
        row = $("<tr/>");//crea la riga
        
        for(var i=0; i < toPrintArr.length; i++){      
            row.append($("<td/>").text(obj[toPrintArr[i]]));
        }
        //aggiunge la checkbox
        row.append('<td>'+'<input type="checkbox" name="toPDF" checked/>' + '</td>');
        table.append(row);
    } );
} 

/**
 * Crea una tabella con i campi modificabili e con il bottone per poter eliminare la riga
 * @param {type} elementHeader : numero di elementi dell'header
 * @param {type} idTable : id o class della tabella
 * @returns {undefined}
 */
function tableEditable(elementHeader,idTable){   
    var table = $(idTable);
    //crea bottone delete e checkbox
    var buttonDelete ='<td width="10%">' +
            '<button type="button" class="btn btn-white minusButton" title="delete" onclick="$(this).parent().parent().remove();">' +
            '<span class="glyphicon glyphicon-remove" aria-hidden="true" ></span>' +
            '</button>' + '<input type="checkbox" name="toPDF" style="visibility:hidden;" checked/>' + '</td>';
    var row = $("<tr/></tbody>"); 
    //crea le colonne della tabella editabili
    for(var i=0; i<elementHeader ;i++)
        row.append("<td contenteditable='true' width='10%'></td>");
    row.append(buttonDelete);
    table.append(row);
}

/**
 * Crea un array degli oggetti che sono stati selezionati dall'utente
 * @param {type} name
 * @param {type} surname
 * @param {type} email
 * @returns {undefined}
 */
function createPDFJson(name,surname,email,curriculum){
    var jsonArray = [];//array di oggetti
    
    //itera su tutti gli elementi checked
    $("input:checkbox[name=toPDF]:checked").each(function(){
        var $id = $(this).closest("div").attr("id");
        var jsonObj={};
        
        //recupera gli header
        var $rowh = $(this).closest("table");// cerca la tabella
        var $tdh = $rowh.find("th");   //recupera gli header
        var header=[];
        $.each($tdh, function() {
            header.push($(this).text().trim());
        });
        
        //recupera i valori delle colonne
        var $row = $(this).closest("tr");// Find the row
        var $tds = $row.find("td");   
        var item=[];
        $.each($tds, function() {
            item.push($(this).text());
        });
        item.pop();//elimina la casella con la checkbox

        //crea l'oggetto 
        jsonObj.typology = $id;//aggunge un campo typology
        //le proprietà dell'oggetto sono gli header, i valori corrispondenti sono i valori delle colonne
        $.each(header,function(key,val){
            jsonObj[val]=item[key];
        });
        jsonArray.push(jsonObj);
    });
   // console.log(jsonArray); 
    
    
    var nameProf="";  
    var nameSurnameStudent = name + " " + surname;
    $.getJSON("GetTutorServlet", {fkAccount:email}, 
        function (data) {
            if(data.result)
                nameProf=data.name +" "+data.surname; 
      console.log(nameSurnameStudent+" "+curriculumYear+" "+nameProf+" "+curriculum);
      printPDF(nameSurnameStudent, curriculumYear, nameProf, curriculum, jsonArray);

    });
}