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
    //appendiamo il primo valore che è di defalut
    def = "<option class='optionItem' value='" + 'default' + "'> " + '- seleziona -' + "  </option> ";
    $("#CycleList").append(def);
    
    //servlet per richiamare la lista dei cicli
    $.getJSON("GetCyclesListNumers", function (data) {
            $.each(data.cyclesIds, function (index, value) {
            cycles = "<option class='optionItem' value='" + value + "'> " + value + "  </option> ";
            $("#CycleList").append(cycles);
        });
    });
}

function selectedItem()
{
    $("#bodyCollegio tr").remove();
    $("#tutorTableList tr").remove();
    
    selectedCycle = $("#CycleList option:selected").val(); // la chiave primaria di ciclo
    if (selectedCycle !== "default")
        {
         //servlet per richiamare le informazioni sul ciclo selezionato
            $.getJSON("GetCyclebyNumber", {number: selectedCycle} ,function (data) {
            $("#cycleName").html(" <b> Ciclo n. " + data.Cycle + "  </b> ");
            $("#cycleYear").html("Anno del ciclo:    " + data.Year + "   ");
       });
       
       
       //servlet per riempire la tabella con tutti i professori del collegio
        $.getJSON("ViewCollegeCycleServlet",{number: selectedCycle}, function (data) {
           
            $.each(data.prof, function (index, value) {
                
                professorColl = "<tr> <td> " + value.name + "</td> <td> " + value.surname + "</td> </tr> "; // la secondaryEmail è la chiave primaria del professore che dovrà essere settato come nuovo tutor
                $("#bodyCollegio").append(professorColl);
                professorTutor = "<tr> <td> " + value.name + "</td> <td> " + value.surname + "</td>   <td> <button class='btn btn-orange' id=" + value.secondaryEmail + " onclick='addTutorButton(" + 'id' + ")' > <span class='glyphicon glyphicon-sort' aria-hidden='true' ></span> Aggiorna </button>  </td>  </tr> "; // la secondaryEmail è la chiave primaria del professore che dovrà essere settato come nuovo tutor
                $("#tutorTableList").append(professorTutor);
           
            });
        });
        
        
        //servlet per richiamare il tutor
        $.getJSON("ViewCycleCoordinator", {number: selectedCycle}, function (data) {
            $("#removeTutorButton").show();
            $("#TutorNameField").html(" <b> " + data.name + " " + data.surname + " </b> ");
            tutorKey = data.fkAccount; //salviamo la mail del professore */
            
        });

        
         $("#CycleSelectedDiv").show();
         
         $("#coordinatoreDiv").show();
         $("#curriculumsDiv").show();
        }
    else 
        {
         $("#CycleSelectedDiv").hide();
         $("#collegioDiv").hide();
         $("#coordinatoreDiv").hide();
         $("#curriculumsDiv").hide();
        }
    
}

function addCycleButton()
{
    
}

function viewCollegio()
{
    $("#curriculumsDiv").hide();
    $("#collegioDiv").show();
}

function closeCollegioDiv()
{
    $("#collegioDiv").hide();
    $("#curriculumsDiv").show();
}