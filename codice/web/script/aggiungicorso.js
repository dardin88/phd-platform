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
     
     $.getJSON("GetCurriculumcicList", {phdCycleId: selected}, function (data) {
                $.each(data.curriculumNames, function (index, value) {
                    var curriculumDiv = "<option class='optionItem' value='" + value + "'> " + value + "  </option>";
                    $("#curriculum").append(curriculumDiv);
                });
            });
 }