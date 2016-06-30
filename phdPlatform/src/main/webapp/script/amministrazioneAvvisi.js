/*   
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    getNewsList();
    createsList();
    componentsLoad();
});

function getNewsList()
{       
    $("#divPanelAddORModify").hide();
    $("#accountListTable tr").remove();
    $("#tableDiv").show();

    //servlet per riempire la tabella con tutti gli avvisi
    $.getJSON("GetAllNews", function (data) {
        $.each(data.news, function (index, value) {
            news = "<tr> <td> " + value.title + "</td> <td> <button class='btn btn-blue' id=" + value.id + " onclick='viewNewsButton(" + 'id' + ")' > <span class='glyphicon glyphicon-eye-open' aria-hidden='true' ></span>Visualizza news</button>  </td>   <td>  <button class='btn btn-orange' id=" + value.id + " onclick='modifyNewsButton(" + 'id' + ")' > <span class='glyphicon glyphicon-pencil' aria-hidden='true' ></span>Modifica news</button>  </td>  <td> <button class='btn btn-red' id=" + value.id + " onclick='removeNewsButton(" + 'id' + ")' > <span class='glyphicon glyphicon-remove' aria-hidden='true' ></span>Elimina news</button>  </td>    </tr> ";
            $("#accountListTable").append(news);
        });


    });
}
/** funzione per controllare se l'utente vuole inserire un avviso o una news **/
function optionContact(controllo)
{
    if(controllo==="avviso")
    {
        $("#DivSenderContact").show();
    }
    else
    {
        $("#DivSenderContact").hide();       
    }
}

/**viene creata una lista che contiene tutti la checkbox di tutti gli studenti **/
function createsList()
{
    listStudent = [];    
    $.getJSON("GetPhdStudentList", function (data) {
        $.each(data.account, function (index, value) {
            listStudent[index]="<div id='ris"+index+"'><input type='checkbox' value='"+value.secondaryEmail+"'> "+value.surname +" "+value.name+"</div>";
            $("#resulthead").append(listStudent[index]);
            $("#ris"+index).hide();            
        });
    });
}
function emptyList()
{  
    $.getJSON("GetPhdStudentList", function (data) {
        $.each(data.account, function (index) {
            $("#ris"+index).hide();            
        });
    });
}
/** Metodo per caricare i cicli e i curriculum **/
function componentsLoad()
{
    $.getJSON("GetCyclesListNumers", function (data) {
        $.each(data.cyclesIds, function (index, value) {
            cycles = "<input type='checkbox' name='cbox[]' class='s0' value='" + value + "' onclick='studentCheck()'> " + value +" " ;
            $("#CicliCheckbox").append(cycles);
        });
    });
    $.getJSON("GetCurriculumsNames", function (data) {
        $.each(data.curriculumNames, function (index, value) {
            curriculum = "<input type='checkbox' name='cbox[]' class='s1' value='" + value.name + "'onclick='studentCheck()'> " + value.name +" " ;
            $("#CurriculumCheckbox").append(curriculum);
        });
    });
    $.getJSON("GetCyclesListNumers", function (data) {
        $.each(data.cyclesIds, function (index, value) {
            $.getJSON("GetCurriculumcicList", {number: value}, function (data) {
                $.each(data.curriculumcicList, function (index, value2) {
                    curriculum = "<input type='checkbox' name='cbox[]' class='s2' value='" + value + value2.name + "' onclick='studentCheck()'> " + value + " - " + value2.name + " ";
                    $("#CicloCurriculumCheckbox").append(curriculum);
                });
            });
        });
    });
    var all = "<input type='checkbox' name='SelezionaTutti' class='SelezionaTutti' value='TuttiAll' onclick='allCheck(";
    all2 = "),studentCheck()'> Tutti " ;
    $("#CicliCheckbox").append(all + "0" + all2);
    $("#CurriculumCheckbox").append(all+"1"+all2);
    $("#CicloCurriculumCheckbox").append(all+"2"+all2);
}

/** Metodo per attivare/disattivare tutte le scelte dei curriculum/cicli/ciclo-curriculum **/
function allCheck(valore){
	$('.s'+valore).each(function(j){
            if($('.SelezionaTutti')[valore].checked) {
                $('.s'+valore)[j].checked=true;
            }else{
                $('.s'+valore)[j].checked=false; 
            }
        });  
}
/** Metodo che visualizza a schermo le checkbox relative alla scelta dei curriculum/cicli/ciclo-curriculum **/
function studentCheck()
{  
    emptyList();
    [].slice.call(document.querySelectorAll("[name='cbox[]']")).filter(function(e) { 
        $.getJSON("GetPhdStudentList", function (data) {
            $.each(data.account, function (index, value) {
                $.getJSON("GetAccountbyEmail", {index: value.email}, function (data) {
                    if((data.fkCycle==e.value || data.fkCurriculum==e.value || "TuttiAll"==e.value || (data.fkCycle+data.fkCurriculum)==e.value)&& e.checked){     
                           $ ("#ris"+index).show();        
                    }
                });
            });
        });       
    });
            
}




function addNewsButton()
{
    $("#saveNewsAdd").show();
    $("#saveNewsModify").hide();

    $("#DivSenderContact").hide();
    $("#descriptionPanel").hide();
    $("#tableDiv").hide();
    $("#divPanelAddORModify").show();
    $("#newsTitle").val("");
    $("#newsDescription").val("");

    $("#saveNewsAdd").click(function () {

        newsTitle = $("#newsTitle").val();
        newsDescription = $("#newsDescription").val();
        /** Invio dati alla servlet per l'inserimento della news e l'invio dell'email con relativi controlli sui parametri immessi**/
        if(controlError(newsTitle) && controlError(newsDescription)){ 
            email=$("#resulthead input:checked");
            if($( "#curriculum_form input:checked" ).val()=="avviso"){
                var n = $("#resulthead input:checked").length;
                    if (n > 0){
                        $("#resulthead input:checked").each(function(){         
                            $.getJSON("EmailForwarded",
                             {email:$(this).val() ,newsTitle:newsTitle,newsDescription:newsDescription}, function (data) {
                                if(data.result){
                                    $( "#curriculum_form input:checked" ).removeAttr('checked');
                                }                 
                            });
                            alert("email inviata");
                        });
                    }
            }       
            $("#divPanelAddORModify").hide();
            $("#accountListTable tr").remove();
            $.getJSON("InsertNews",
             {title: newsTitle, description: newsDescription}, function (data) {
                $("#divPanelAddORModify").hide();
                $("#accountListTable tr").remove();
                location.reload();
            });        
        }
    });
}

function controlError(element){
    var iChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?";
    for (var i = 0; i < $("#newsTitle").val().length; i++) {
        if (iChars.indexOf($("#newsTitle").val().substring(i, i+1)) != -1) {
            alert("Il titolo contiene caratteri speciali. \nRimuovili e riprova")
            return false;                      
        }
    }    
    switch(element){
        case "":
            alert("Inserire i dati richiesti nella form");
            return false; 
            break;
        case $("#newsTitle").val():
            if(element.length >=5 && element.length <=50 )
                return true;
            alert("il titolo deve contenere un numero di carattari compresi nell'intervallo che va da 5 a 50 ");
            return false; 
            break;
        case $("#newsDescription").val():
            if(element.length >=1 && element.length <=65535 )
                return true;
            alert("la descrizione deve contenere un numero di carattari compresi nell'intervallo che va da 1 a 65535 ");
            return false; 
            break;       
    }
}
function modifyNewsButton(id)
{
    $("#saveNewsAdd").hide();
    $("#saveNewsModify").show();

    $("#DivSenderContact").hide();
    $("#descriptionPanel").hide();
    $("#tableDiv").hide();
    $("#divPanelAddORModify").show();

    /** Servlet per richiamare le informazioni sulla news selezionata **/
    $.getJSON("GetNewsbyId", {idNews: id}, function (data) {
        $("#newsTitle").val(data.title);
        $("#newsDescription").val(data.description);
    });

    $("#saveNewsModify").click(function () {
                if(controlError($("#newsTitle").val()) && controlError($("#newsDescription").val())){ 
        /** Invio dati alla servlet per la modifica della news **/
        $.getJSON("ModifyNews",
                {idNews: id, title: $("#newsTitle").val(), description: $("#newsDescription").val()}, function (data) {
            location.reload();
        });
    }
    });

}


function removeNewsButton(id)
{
    $("#descriptionPanel").hide();

    /** Servlet per la rimozione della news **/
    $.getJSON("DeleteNews", {idNews: id}, function (data) {
        $("#accountListTable tr").remove();
        $("#tableDiv").hide();
        getNewsList();
    });


}

function closeModifyORaddDiv()
{
    $("#divPanelAddORModify").hide();
    $("#accountListTable tr").remove();
    $("#curriculum_form input:checked" ).removeAttr('checked');
    getNewsList();
}

function viewNewsButton(id)
{
    $("#descriptionPanel").hide();
    $("#descriptionPanel").show();

    //servlet per richiamare le informazioni sulla news selezionato
    $.getJSON("GetNewsbyId", {idNews: id}, function (data) {
        $("#NewsNameField").html(" <b> " + data.title + "  </b> ");
        $("#newsDescriptionField").html(data.description);
    });
}