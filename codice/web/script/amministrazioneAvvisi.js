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


function addNewsButton()
{
    $("#saveNewsAdd").show();
    $("#saveNewsModify").hide();

    $("#descriptionPanel").hide();
    $("#tableDiv").hide();
    $("#divPanelAddORModify").show();
    $("#newsTitle").val("");
    $("#newsDescription").val("");

    $("#saveNewsAdd").click(function () {

        newsTitle = $("#newsTitle").val();
        newsDescription = $("#newsDescription").val();

        // Invio dati alla servlet per l'inserimento della news
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