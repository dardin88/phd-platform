/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {

    getCycleList();
   

});

function getCycleList()
{
   

 
    $.getJSON("GetCyclesListNumers", function (data) {
            $.each(data.cyclesIds, function (index, value) {
            cycles = "<option class='optionItem' value='" + value + "'> " + value + "  </option> ";
            $("#CycleList").append(cycles);
        });
    });
    
    
    
    
     
}

 function selectedItem(){
     var selected = $("#CycleList option:selected").val();
     if (selected!== "default") 
    {
     $.getJSON("GetCurriculumcicList", {number:selected}, function (data) {
                $.each(data.curriculumcicList, function (index, value) {
                    var curriculumDiv = "<option class='optionItem' value='" + value.name + "'> " + value.name + "  </option>";
                    $("#curriculum").append(curriculumDiv);
                });
            });
        }
 }