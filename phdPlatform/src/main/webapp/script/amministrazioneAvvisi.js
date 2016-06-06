/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    getNewsList();
    Crealista();
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
//funzione per controllare se l'utente vuole inserire un avviso o una news
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

//viene creata una lista che contiene tutti la checkbox di tutti gli studenti 
function Crealista()
{
    lista_student = [];    
    $.getJSON("GetPhdStudentList", function (data) {
        $.each(data.account, function (index, value) {
            lista_student[index]="<div id='ris"+index+"'><input type='checkbox' value='"+value.secondaryEmail+"'> "+value.surname +" "+value.name+"</div>";
            $("#resulthead").append(lista_student[index]);
            $("#ris"+index).hide();            
        });
    });
}

function svuotalista()
{
    var i=0;
    while(i<lista_student.length){           
        $("#ris"+i).hide();
        i++;
    }
}      

//Metodo per caricare i cicli/curriculum/ciclo-curriculum selezionati dall utente
function selectioned()
{
    selected = $("#Cicli-curriculum option:selected");
    $("#Select-CC option").remove();
    all= "<option class='optionItem' value='TuttiAll' onclick='StudentCheck()'>  Tutti </option> ";
    $("#Select-CC").append(all);
    switch (selected.val()){
        case "Cicli":
            $.getJSON("GetCyclesListNumers", function (data) {
                $.each(data.cyclesIds, function (index, value) {
                    cycles = "<option class='optionItem' value='" + value + "' onclick='StudentCheck()'> " + value + "  </option> ";
                    $("#Select-CC").append(cycles);
                });
            });
            
        break;
        case "Curriculum":
            $.getJSON("GetCurriculumsNames", function (data) {
                $.each(data.curriculumNames, function (index, value) {
                    curriculum = "<option class='optionItem' value='" + value.name + "' onclick='StudentCheck()'> " + value.name + "  </option> ";
                    $("#Select-CC").append(curriculum);
                });
            });
        break;
        case "Cicli-curriculum":
            $.getJSON("GetCyclesListNumers", function (data) {
                $.each(data.cyclesIds, function (index, value) {
                    $.getJSON("GetCurriculumcicList", {number: value}, function (data) {
                        $.each(data.curriculumcicList, function (index, value2) {
                            curriculum = "<option class='optionItem' value='" + value + value2.name + "' onclick='StudentCheck()'> " + value + " - " + value2.name + " </option> ";
                            $("#Select-CC").append(curriculum);
                        });
                    });
                });
            });
        break;
    }
}

//Metodo che visualizza a schermo le checkbox relative alla scelta dei curriculum/cicli/ciclo-curriculum
function StudentCheck()
{
    svuotalista();
    selected = $("#Select-CC option:selected"); 
    $.getJSON("GetPhdStudentList", function (data) {
        $.each(data.account, function (index, value) {
            $.getJSON("GetAccountbyEmail", {index: value.email}, function (data) {
                if(data.fkCycle==selected.val()) 
                        $("#ris"+index).show();
                if(data.fkCurriculum==selected.val())
                        $("#ris"+index).show();
                if((data.fkCycle+data.fkCurriculum)==selected.val())
                        $("#ris"+index).show();
                    if("TuttiAll"==selected.val()) 
                        $("#ris"+index).show();
                    
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
        
        // Invio dati alla servlet per l'inserimento della news
        $("#divPanelAddORModify").hide();
            $("#accountListTable tr").remove();
            email=$("#resulthead input:checked");
            if($( "#curriculum_form input:checked" ).val()=="avviso"){
               var n = $("#resulthead input:checked").length;
               if (n > 0){
                    $("#resulthead input:checked").each(function(){         
                        $.getJSON("EmailForwarded",
                                {email:$(this).val() ,newsTitle:newsTitle,newsDescription:newsDescription}, function (data) {
                            if(data.result){
                                //deve fa tutti i getjson
                                $( "#curriculum_form input:checked" ).removeAttr('checked');
                            }                 
                        });
                    });
                }
            } 
        $.getJSON("InsertNews",
                {title: newsTitle, description: newsDescription}, function (data) {
            $("#divPanelAddORModify").hide();
            $("#accountListTable tr").remove();
            location.reload();
        });
    });

}

function modifyNewsButton(id)
{
    $("#saveNewsAdd").hide();
    $("#saveNewsModify").show();

    $("#DivSenderContact").hide();
    $("#descriptionPanel").hide();
    $("#tableDiv").hide();
    $("#divPanelAddORModify").show();

    //servlet per richiamare le informazioni sulla news selezionata
    $.getJSON("GetNewsbyId", {idNews: id}, function (data) {
        $("#newsTitle").val(data.title);
        $("#newsDescription").val(data.description);
    });

    $("#saveNewsModify").click(function () {
        // Invio dati alla servlet per la modifica della news
        $.getJSON("ModifyNews",
                {idNews: id, title: $("#newsTitle").val(), description: $("#newsDescription").val()}, function (data) {
            location.reload();
        });


    });

}


function removeNewsButton(id)
{
    $("#descriptionPanel").hide();

    // Servlet per la rimozione della news
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