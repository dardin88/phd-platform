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
             alert(value.name);
            curriculum = "<option class='optionItem' value='" + value.name + "'> " + value.name + "  </option> ";
            $("#CurriculumList").append(curriculum);
        });
    });
}


function selectedItem()
{
    
}

function addCurriculumButton()
{
    
}