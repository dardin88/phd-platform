/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    getNewsList();

});

function getNewsList()
{
    $("#tableDiv").show();
    
    
    //servlet per riempire la tabella con tutti gli avvisi
        $.getJSON("GetAllNews", function (data) {
            $.each(data.news, function (index, value) {
                news = "<tr> <td> " + value.title + "</td> <td> <button class='btn btn-blue' id=" + value.id + " onclick='viewNewsButton(" + 'id' + ")' > <span class='glyphicon glyphicon-eye-open' aria-hidden='true' ></span>Visualizza news</button>  </td>   <td>  <button class='btn btn-orange' id=" + value.id + " onclick='modifyNewsButton(" + 'id' + ")' > <span class='glyphicon glyphicon-pencil' aria-hidden='true' ></span>Modifica news</button>  </td>  <td> <button class='btn btn-red' id=" + value.id + " onclick='removeNewsButton(" + 'id' + ")' > <span class='glyphicon glyphicon-remove' aria-hidden='true' ></span>Elimina news</button>  </td>    </tr> "; 
                $("#accountListTable").append(news);
            });
        });
}


function addNewsButton()
{
 alert("bottone INSERIMENTO premuto");
 $("#descriptionPanel").hide();
 $("#tableDiv").hide();
 $("#divPanelAddORModify").show();
 
 $("#saveNews").click(function () {
                    
        // Invio dati alla servlet per l'inserimento della news
                    $.getJSON("InsertNews",
                            {title: $("#newsTitle").val(),description: $("#newsDescription").val()}, function(data) {
                                alert("news aggiunta correttamente");
                                $("#divPanelAddORModify").hide();
                                 $("#accountListTable tr").remove();
                                getNewsList();
                           });
                });
}

function viewNewsButton(id)
{
    $("#descriptionPanel").hide();
    alert("bottone VISUALIZZAZIONE premuto " + id);
    $("#descriptionPanel").show();
    
    //servlet per richiamare le informazioni sulla news selezionato
        $.getJSON("GetNewsbyId", {idNews: id}, function (data) {
            $("#NewsNameField").html(" <b> " + data.title + "  </b> ");
            $("#newsDescriptionField").html(data.description);
            
        });
}

function modifyNewsButton(id)
{
     $("#descriptionPanel").hide();
     alert("bottone MODIFICA premuto " + id);
     $("#tableDiv").hide();
     $("#divPanelAddORModify").show();
}


function removeNewsButton(id)
{
     alert("bottone ELIMINAZIONE premuto " + id);
     $("#descriptionPanel").hide();
}

function closeModifyORaddDiv()
{
    $("#divPanelAddORModify").hide(); 
    $("#accountListTable tr").remove();
    getNewsList();
}