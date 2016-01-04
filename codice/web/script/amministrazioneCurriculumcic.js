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
    $("#TutorNameField").html("nessun tutor");
    $("#removeTutorButton").hide();
    $("#bodyCollegio tr").remove();
    $("#tutorTableList tr").remove();
    $("#curriculumList tr").remove();
    
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
           });
        });
        
       
        //servlet per richiamare il tutor
        $.getJSON("ViewCycleCoordinator", {number: selectedCycle}, function (data) {
            
            $("#removeTutorButton").show();
            $("#TutorNameField").html(" <b> " + data.name + " " + data.surname + " </b> ");
            tutorKey = data.fkAccount; //salviamo la mail del professore 
        });
       
       

        //servlet per riempire la tabella con tutti i professori
        $.getJSON("GetProfessorsList", function (data) {
            $.each(data.account, function (index, value) {
                professor = "<tr> <td> " + value.name + "</td> <td> " + value.surname + "</td>   <td> <button class='btn btn-orange' id=" + value.secondaryEmail + " onclick='addTutorButton(" + 'id' + ")' > <span class='glyphicon glyphicon-sort' aria-hidden='true' ></span> Aggiorna </button>  </td>  </tr> "; // la secondaryEmail è la chiave primaria del professore che dovrà essere settato come nuovo tutor
                $("#tutorTableList").append(professor);
            });
        });
        
        
        //servlet per richiamare i curriculum attivi nel ciclo selezionato
        $.getJSON("GetCurriculumcicList", {number: selectedCycle}, function (data) {
            $.each(data.curriculumcicList, function (index, value) {
                curriculum = "<tr> <td> " + value.name + "</td>   <td> <button class='btn btn-blue' id=" + value.name + " onclick='viewCurriculumButton(" + 'id' + ")' >  Visualizza </button>  </td>  <td> <button class='btn btn-red' id=" + value.name + " onclick='removeCurriculumButton(" + 'id' + ")' >  Elimina </button>  </td>  </tr> "; // la secondaryEmail è la chiave primaria del professore che dovrà essere settato come nuovo tutor
                $("#curriculumList").append(curriculum);
            });
        });
        
        
        
        
         $("#CycleSelectedDiv").show();
         $("#coordinatoreDiv").show();
         $("#curriculumsDiv").show();
         $("#divPanelAddORModify").hide();
        }
    else 
        {
         $("#CycleSelectedDiv").hide();
         $("#collegioDiv").hide();
         $("#coordinatoreDiv").hide();
         $("#curriculumsDiv").hide();
         $("#divPanelAddORModify").hide();
        }
    
}

function addCycleButton()
{
    $("#coordinatoreDiv").hide();
    $("#curriculumsDiv").hide();
    $("#divPanelAddORModify").show();
    
     $("#cycleTitle").html("Aggiunta di un nuovo ciclo");
     //$("#cycleYear").html("");
     $("#cycleDescription").val("");
     
     $("#saveCycle").click(function () {
         alert("hai clickato salva");
         alert($("#cycleDescription").val());
         alert($("#cycleYear").val());
                    // Invio dati alla servlet per l'inserimento del ciclo
                    $.getJSON("InsertCycle",
                            {description: $("#cycleDescription").val(),year: $("#cycleYear").val()}, function(data) {
                                alert("siamo nella servlet");
                                
                                /*$("#descriptionPanel").hide();
                                 $("#CurriculumList option").remove();
                               
                                getCycleList();
                                selectedItem();*/
                           });
                });
   
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


function addTutorButton(newProfessorkey)
{
   
    //in newProfessorkey abbiamo la mail del nuovo tutor
   
    //in tutorKey abbiamo la mail del professore gia selezionato come tutor , se c'è (serve nel caso dobbiamo rimuoverlo)

    tutorName = $("#TutorNameField").html();
    if (tutorName === 'nessun tutor') { //non c'è un tutor assegnato, dobbiamo soltanto aggiungercelo

        //servlet per fare inserire il nuovo coordinatore
            $.getJSON("InsertCycleCoordinator", {number: selectedCycle, fkProfessor: newProfessorkey}, function (data) {
                selectedItem();
            });

    }
    else { //c'è gia un tutor assegnato: dobbiamo  aggiornarlo
        if (tutorKey === newProfessorkey)
        {
            alert("Hai selezionato il tutor attuale");
        }
        else {
            
            //servlet per rimuovere il vecchio coordinatore assegnato 
            $.getJSON("DeleteCycleCoordinator", {number: selectedCycle}, function (data) {
                
            });
            
            //servlet per fare inserire il nuovo coordinatore
            $.getJSON("InsertCycleCoordinator", {number: selectedCycle, fkProfessor: newProfessorkey}, function (data) {
                selectedItem();
            });
        }
    }

}


function removeTutorButton()
{
    //servlet per rimuovere il coordinatore assegnato 
    $.getJSON("DeleteCycleCoordinator", {number: selectedCycle}, function (data) {
        $("#removeTutorButton").hide();
        $("#TutorNameField").html("nessun tutor");
        selectedItem();
    });
}



function viewCurriculumButton()
{
    
}

function removeCurriculumButton()
{
    
}


function addCurriculuminCicButton()
{
    
}

function closeModifyORaddDiv()
{
    selectedItem();
}