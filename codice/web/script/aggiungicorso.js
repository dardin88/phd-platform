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
        $("#curriculum").html("");
        def = "<option class='optionItem' value='" + 'default' + "'> " + '- seleziona -' + "  </option> ";
        $("#curriculum").append(def);
     $.getJSON("GetCurriculumcicList", {number:selected}, function (data) {
                $.each(data.curriculumcicList, function (index, value) {
                    var curriculumDiv = "<option class='optionItem' value='" + value.name + "'> " + value.name + "  </option>";
                    $("#curriculum").append(curriculumDiv);
                });
            });
        }
 }
 
 function insertCourse(){
     
     ciclo = $("#CycleList option:selected").val();
     cv = $("#curriculum option:selected").val();
     nome = $("#nameCourse").val();
     descrizione = $("#description").val();
     datainizio = $("#startdate").val();
     datafine = $("#enddate").val();
     if(ciclo!=="default" && cv!=="default" && nome!=="" && descrizione!==""){
             $.getJSON("AddCourseServlet", {cycle: ciclo, curriculum: cv, name: nome, description: descrizione, starttime: datainizio, endtime: datafine}, function (data) {
                   
                    if(data.result){
                        $("#titleInfo").html("");
                        $("#descriptionInfo").html("");
                        $("#infoDialog").modal();
                        $("#titleInfo").html("Operazione eseguita con successo!");
                        $("#descriptionInfo").html("Il corso è stato aggiunto.");
                    
                    }
                    else
                    {
                        
                        $("#titleInfo").html("");
                        $("#descriptionInfo").html("");
                        $("#infoDialog").modal();
                        $("#titleInfo").html("Errore inserimento corso!");
                        $("#descriptionInfo").html("Il corso non è stato aggiunto, riprova.");
                    
                    }
             });
     }
 }