/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    getCurriculumsList();

});

function getCurriculumsList()
{

    //appendiamo il primo valore che è di defalut
    def = "<option class='optionItem' value='" + 'default' + "'> " + '- seleziona -' + "  </option> ";
    $("#CurriculumList").append(def);

    //servlet per richiamare la lista dei curriculum
    $.getJSON("GetCurriculumsNames", function (data) {
        nomi = [];
        $.each(data.curriculumNames, function (index, value) {
            nomi.push(value.name);
            curriculum = "<option class='optionItem' value='" + value.name + "'> " + value.name + "  </option> ";
            $("#CurriculumList").append(curriculum);
        });
    });
}


function selectedItem()
{
    $("#removeButtonSpace button").remove();
    $("#modifyButtonSpace button").remove();
    $("#divPanelAddORModify").hide();


    selectedCurriculum = $("#CurriculumList option:selected").val(); // la chiave primaria di account
    if (selectedCurriculum !== "default") //se il valore della select è default non mostriamo il div contenente le informazioni
    {
        $("#descriptionPanel").show();

        //servlet per richiamare le informazioni sul curriculum selezionato
        $.getJSON("GetCurriculumByName", {CurriculumName: selectedCurriculum}, function (data) {
            $("#CurriculumNameField").html(" <b> " + data.CurriculumName + "  </b> ");
            $("#CurriculumDescriptionField").html(data.CurriculumDescription);
            deleteButton = " <button  class='btn btn-red btn-block'  id='" + data.CurriculumName + "' onclick='removeButtonSelected(" + 'id' + ")'  > <span class='glyphicon glyphicon-remove-sign' aria-hidden='true' ></span> Rimuovi Curriculum </button> ";
            modifyButton = " <button  class='btn btn-orange btn-block' id='" + data.CurriculumName + "' onclick='modifyButtonSelected(" + 'id' + ")' > <span class='glyphicon glyphicon glyphicon-pencil' aria-hidden='true' ></span> Modifica Curriculum </button> ";
            $("#removeButtonSpace").append(deleteButton);
            $("#modifyButtonSpace").append(modifyButton);
            curriculumDescription = data.CurriculumDescription;
        });
    }
    else
        $("#descriptionPanel").hide();
}

function modifyButtonSelected(curriculumName)
{
    this.curriculumName = curriculumName;
    //in curriculumName abbiamo il nome del curriculum che vogliamo modificare
    //in curriculumDescription abbiamo la descrizione del curriculum che vogliamo modificare
    $("#savePhdCurriculumModify").show();
    $("#savePhdCurriculumAdd").hide();

    $("#descriptionPanel").hide();
    $("#divPanelAddORModify").show();
    $("#phdCurriculumTitle").html("Mofica del curriculum selezionato");
    $("#phdCurriculumName").val(curriculumName);
    $("#phdCurriculumDescription").val(curriculumDescription);

    $("#savePhdCurriculumModify").click(function () {

        nome = $("#phdCurriculumName").val();
        if (($("#phdCurriculumName").val() === "") || (($.inArray(nome, nomi) !== -1))) {
            $("#ErroreDialog").modal();
        }
        else {
            // Invio dati alla servlet per l'inserimento del curriculum
            $.getJSON("UpdateCurriculum",
                    {oldNameCurriculum: curriculumName, newNameCurriculum: $("#phdCurriculumName").val(), description: $("#phdCurriculumDescription").val()}, function (data) {
                $("#descriptionPanel").hide();
                $("#CurriculumList option").remove();
                $("#ModificaDialog").modal();
                getCurriculumsList();
                selectedItem();
             });
        }
    });

}



function addCurriculumButton()
{
    $("#savePhdCurriculumModify").hide();
    $("#savePhdCurriculumAdd").show();

    $("#phdCurriculumTitle").html("Aggiunta di un nuovo curriculum");
    $("#phdCurriculumName").val("");
    $("#phdCurriculumDescription").val("");
    $("#descriptionPanel").hide();
    $("#divPanelAddORModify").show();
    curriculumName = $("#phdCurriculumName").val();

    $("#savePhdCurriculumAdd").click(function () {
        
        nome = $("#phdCurriculumName").val();
        if (($("#phdCurriculumName").val() === "") || (($.inArray(nome, nomi) !== -1))) {
            $("#ErroreDialog").modal();
        }
        else{
        // Invio dati alla servlet per l'inserimento del curriculum
        $.getJSON("InsertCurriculum",
                {name: $("#phdCurriculumName").val(), description: $("#phdCurriculumDescription").val()}, function (data) {
            $("#InserimentoDialog").modal();
            $("#descriptionPanel").hide();
            $("#CurriculumList option").remove();
            location.reload();
         });
    }
    });
}

function removeButtonSelected(curriculumName)
{
    this.curriculumName = curriculumName;
    //in curriculumName abbiamo il nome del curriculum che vogliamo eliminare

    // Servlet per la rimozione del curriculum

    $.getJSON("DeleteCurriculum", {nameCurriculum: curriculumName}, function (data) {
        $("#descriptionPanel").hide();
        $("#CurriculumList option").remove();
        $("#CancellazioneDialog").modal();
        getCurriculumsList();
        selectedItem();
    });
}

/*
function removeButtonSelected(curriculumName)
{
    this.curriculumName = curriculumName;
    //in curriculumName abbiamo il nome del curriculum che vogliamo eliminare
    
                    // Servlet per la rimozione del curriculum
                    
                    $.getJSON("DeleteCurriculum",{nameCurriculum: curriculumName},function (data) {
                        alert("curriculum eliminato correttamente");
                        $("#descriptionPanel").hide();
                        $("#CurriculumList option").remove();
                        getCurriculumsList();
                        selectedItem();
                    });
}
*/

//per il bottone X 
function closeModifyORaddDiv()
{
    $("#divPanelAddORModify").hide();
    $("#phdCurriculumName").val("");
    $("#phdCurriculumDescription").val("");
    selectedItem();
}

