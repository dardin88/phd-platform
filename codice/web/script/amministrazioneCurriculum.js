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
    //servlet per richiamare la lista dei curriculum
    $.getJSON("GetCurriculumsNames", function (data) {
             
            $.each(data.curriculumNames, function (index, value) {
            curriculum = "<option class='optionItem' value='" + value.name + "'> " + value.name + "  </option> ";
            $("#CurriculumList").append(curriculum);
        });
    });
}


function selectedItem()
{
    
    
    selectedCurriculum = $("#CurriculumList option:selected").val(); // la chiave primaria di account
    if (selectedCurriculum !== "default") //se il valore della select è default non mostriamo il div contenente le informazioni
    {
        $("#descriptionPanel").show();
        
        //servlet per richiamare le informazioni sul curriculum selezionato
        $.getJSON("GetCurriculumByName", {CurriculumName: selectedCurriculum}, function (data) {
            $("#CurriculumNameField").html(" <b> " + data.CurriculumName + "  </b> ");
            $("#CurriculumDescriptionField").html(data.CurriculumDescription);
            deleteButton = " <button type='button' class='btn btn-red' id=" +  + " onclick='removeButton(" + 'id' + ")' > <span class='glyphicon glyphicon-remove-sign' aria-hidden='true' ></span> Rimuovi Curriculum </button> ";
            $("#footerPanel").html(deleteButton);
            //tutorKey = data.fkAccount; //salviamo la mail del professore 
        });
    }
    else
        $("#descriptionPanel").hide();
}

function addCurriculumButton()
{
    
}