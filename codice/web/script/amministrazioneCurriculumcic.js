/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $("#sezioneCiclo").hide();
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
    $("#sezioneCurriculum").hide();
    $("#removeTutorButton").hide();
    $("#addCurriculumButton").hide();
    $("#bodyCollegio tr").remove();
    $("#tutorTableList tr").remove();
    $("#curriculumList tr").remove();
    $("#descriptionPanel").hide();
    $("#coordinatoreCurriculumDiv").hide();
    $("#DocentiCurriculumDiv").hide();
    $("#CurriculumDocentiTableList tr").remove();
    $("#CurriculumStudentiTableList tr").remove();
        

    selectedCycle = $("#CycleList option:selected").val(); // la chiave primaria di ciclo
    if (selectedCycle !== "default")
    {
        //servlet per richiamare le informazioni sul ciclo selezionato
        $.getJSON("GetCyclebyNumber", {number: selectedCycle}, function (data) {
            $("#cycleName").html(" <b> Ciclo n. " + data.Cycle + "  </b> ");
            $("#cycleYear").html("Anno del ciclo:    " + data.Year + "   ");
        });


        //servlet per riempire la tabella con tutti i professori del collegio
        $.getJSON("ViewCollegeCycleServlet", {number: selectedCycle}, function (data) {
            $.each(data.prof, function (index, value) {
                professorColl = "<tr> <td> " + value.name + "</td> <td> " + value.surname + "</td> </tr> "; // la secondaryEmail è la chiave primaria del professore che dovrà essere settato come nuovo tutor
                $("#bodyCollegio").append(professorColl);
                professor = "<tr> <td> " + value.name + "</td> <td> " + value.surname + "</td>   <td> <button class='btn btn-orange' id=" + value.secondaryEmail + " onclick='addTutorButton(" + 'id' + ")' > <span class='glyphicon glyphicon-sort' aria-hidden='true' ></span> Aggiorna </button>  </td>  </tr> "; // la secondaryEmail è la chiave primaria del professore che dovrà essere settato come nuovo tutor
                $("#tutorTableList").append(professor);
            });
        });


        //servlet per richiamare il tutor
        $.getJSON("ViewCycleCoordinator", {number: selectedCycle}, function (data) {
            $("#removeTutorButton").show();
            $("#TutorNameField").html(" <b> " + data.name + " " + data.surname + " </b> ");
            tutorKey = data.fkAccount; //salviamo la mail del professore 
        });


        //servlet per richiamare i curriculum attivi nel ciclo selezionato
        $.getJSON("GetCurriculumcicList", {number: selectedCycle}, function (data) {
            $.each(data.curriculumcicList, function (index, value) {
                curriculum = "<tr> <td> " + value.name + "</td>   <td> <button class='btn btn-blue' id='" + value.name + "' onclick='viewCurriculumButton(" + 'id' + ")' >  Visualizza </button>  </td>  <td> <button class='btn btn-red' id='" + value.name + "' onclick='removeCurriculumButton(" + 'id' + ")' >  Elimina </button>  </td>  </tr> "; // la secondaryEmail è la chiave primaria del professore che dovrà essere settato come nuovo tutor
                $("#curriculumList").append(curriculum);
            });
        });


        //servlet per vedere se il ciclo selezionato è l'ultimo 
        $.getJSON("IsLast", {number: selectedCycle}, function (data) {
            if (data.controllo === true)
                $("#addCurriculumButton").show();
        });



        $("#CycleSelectedDiv").show();
        $("#coordinatoreDiv").show();
        $("#curriculumsDiv").show();
        $("#sezioneCiclo").show();
        $("#divPanelAddORModify").hide();
        $("#selectCurriculum").hide();
        $("#addCurriculumtoCicButton").hide();

    }
    else
    {
        $("#CycleSelectedDiv").hide();
        $("#collegioDiv").hide();
        $("#coordinatoreDiv").hide();
        $("#curriculumsDiv").hide();
        $("#divPanelAddORModify").hide();
        $("#sezioneCiclo").hide();
    }

}

function addCycleButton()
{
    $("#sezioneCurriculum").hide();
    $("#sezioneCiclo").hide();
    $("#CycleSelectedDiv").hide();
    $("#collegioDiv").hide();
    $("#coordinatoreDiv").hide();
    $("#curriculumsDiv").hide();
    $("#divPanelAddORModify").show();
    $("#descriptionPanel").hide();
    $("#coordinatoreCurriculumDiv").hide();
    $("#DocentiCurriculumDiv").hide();
    $("#StudentiCurriculumDiv").hide();


    $("#cycleTitle").html("Aggiunta di un nuovo ciclo");
    $("#cycleYearField").html("");
    $("#cycleDescription").val("");

    $("#saveCycle").click(function () {
        // Invio dati alla servlet per l'inserimento del ciclo
        $.getJSON("InsertCycle",
                {description: $("#cycleDescription").val(), year: $("#cycleYearField").val()}, function (data) {
            // alert("ciclo aggiunto correttamente");
            $("#descriptionPanel").hide();
            //$("#CurriculumList option").remove();
            $("#CycleList option").remove();
            getCycleList();
            selectedItem();
        });
    });
}

function removeCycleButton() {
    $("#CycleSelectedDiv").hide();
    $("#coordinatoreDiv").hide();
    $("#curriculumsDiv").hide();

    $.getJSON("DeleteCycle", {number: selectedCycle}, function (data) {
        $("#CycleList option").remove();
        getCycleList();
        $("#CycleList").val('default');
        selectedItem();
    });
}

function viewCollegio()
{
    $("#curriculumsDiv").hide();
    $("#collegioDiv").show();
    $("#descriptionPanel").hide();
    $("#coordinatoreCurriculumDiv").hide();
    $("#DocentiCurriculumDiv").hide();
    $("#sezioneCurriculum").hide();
    $("#buttonCloseSezioneCurriculum").hide();
    $("#StudentiCurriculumDiv").hide();

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



function viewCurriculumButton(id)
{
    selectedDescriptionCurriculum = id;
    $("#sezioneCurriculum").show();
    //servlet per richiamare le informazioni sul curriculum selezionato
    $.getJSON("GetCurriculumByName", {CurriculumName: id}, function (data) {
        $("#CurriculumNameField").html(" <b> " + data.CurriculumName + "  </b> ");
        $("#CurriculumDescriptionField").html(data.CurriculumDescription);
    });

    $("#descriptionPanel").show();
    $("#coordinatoreCurriculumDiv").show();
    $("#coordinatoreCurriculumDiv tr").remove();
    $("#ProfessorsSelectebleList option").remove();
    $("#StudentsSelectebleList option").remove();
    $("#DocentiCurriculumDiv").show();
    $("#CurriculumDocentiTableList tr").remove();
    $("#StudentiCurriculumDiv").show();
    $("#CurriculumStudentiTableList tr").remove();


    //servlet per riempire la tabella con tutti i professori del curriculum per la tabella del tutor
    $.getJSON("ViewProfessorListServlet", {fkCycle: selectedCycle, fkCurriculum: id}, function (data) {
        $.each(data.prof, function (index, value) {
            professorColleg = "<tr> <td> " + value.name + "</td> <td> " + value.surname + "</td>  <td> <button class='btn btn-orange' id=" + value.secondaryEmail + " onclick='addCurriculumTutorButton(" + 'id' + ")' > <span class='glyphicon glyphicon-sort' aria-hidden='true' ></span> Aggiorna </button>  </td>  </tr> "; // la secondaryEmail è la chiave primaria del professore che dovrà essere settato come nuovo tutor
            $("#CurriculumtutorTableList").append(professorColleg);
        });
    });


    //servlet per richiamare il coordinatore del curriculum all'interno del ciclo
    $.getJSON("ViewCurriculumcicCoordinatorServlet", {fkCycle: selectedCycle, fkCurriculum: id}, function (data) {
        if (data.result === false)
        {
            $("#CurriculumTutorNameField").html("nessun tutor");
            $("#removeCurriculumTutorButton").hide();
        }
        else {
            $("#removeCurriculumTutorButton").show();
            $("#CurriculumTutorNameField").html(" <b> " + data.name + " " + data.surname + " </b> ");
            CurriculumtutorKey = data.fkAccount; //salviamo la mail del professore 
        }
    });


    //servlet per riempire la tabella con tutti i professori del collegio
    $.getJSON("ViewProfessorListServlet", {fkCycle: selectedCycle, fkCurriculum: id}, function (data) {
        $.each(data.prof, function (index, value) {
            professore = "<tr> <td> " + value.name + " " + value.surname + "</td>   <td> <button class='btn btn-red' id=" + value.secondaryEmail + " onclick='removeProfessorFromCurriculum(" + 'id' + ")' > <span class='glyphicon glyphicon-remove' aria-hidden='true' ></span> Rimuovi </button>  </td>  </tr> "; // la secondaryEmail è la chiave primaria del professore che dovrà essere settato come nuovo tutor
            $("#CurriculumDocentiTableList").append(professore);
        });
    });

    //servlet per richiamare la lista dei professori all'interno della select per aggiungere un nuovo professore al curriculum
    $.getJSON("GetProfessorsList", function (data) {
        $.each(data.account, function (index, value) {
            professorToAppend = "<option class='optionItem' value='" + value.secondaryEmail + "'> " + value.name + " " + value.surname + " </option> ";
            $("#ProfessorsSelectebleList").append(professorToAppend);
        });
    });


    //servlet per riempire la tabella con tutti gli studenti del curriculum
    $.getJSON("ViewPhdstudentCurriculumcicServlet", {fkCycle: selectedCycle, fkCurriculum: id}, function (data) {
        $.each(data.phdstudent, function (index, value) {
            studente = "<tr> <td> " + value.name + " " + value.surname + "</td>   <td> <button class='btn btn-red' id=" + value.secondaryEmail + " onclick='removeStudentFromCurriculum(" + 'id' + ")' > <span class='glyphicon glyphicon-remove' aria-hidden='true' ></span> Rimuovi </button>  </td>  </tr> "; // la secondaryEmail è la chiave primaria del professore che dovrà essere settato come nuovo tutor
            $("#CurriculumStudentiTableList").append(studente);
        });
    });
    
    //servlet per richiamare la lista degli studenti all'interno della select per aggiungere un nuovo studente al curriculum
    $.getJSON("GetPhdStudentList", function (data) {
        $.each(data.account, function (index, value) {
            studentToAppend = "<option class='optionItem' value='" + value.secondaryEmail + "'> " + value.name + " " + value.surname + " </option> ";
            $("#StudentsSelectebleList").append(studentToAppend);
        });
    });
    
}

function removeCurriculumButton(id)
{
    //alert("vuoi eliminare il curriculum con id " + id);

    //servlet per rimuovere il curriculum selezionato
    $.getJSON("DeleteCurriculumcic", {number: selectedCycle, name: id}, function (data) {
        $("#selectCurriculum option").remove();
        selectedItem();
    });
}


function addCurriculuminCicButton()
{
    $("#addCurriculumButton").hide();

    $("#selectCurriculum").show();

    //servlet per richiamare la lista dei curriculum
    $.getJSON("GetCurriculumsNames", function (data) {
        $.each(data.curriculumNames, function (index, value) {
            curriculumToAppend = "<option class='optionItem' value='" + value.name + "'> " + value.name + "  </option> ";
            $("#CurriculumSelectebleList").append(curriculumToAppend);
        });
    });

    $("#addCurriculumtoCicButton").show();
    $("#addCurriculumtoCicButton").click(function () {
        selectedCurriculum = $("#CurriculumSelectebleList option:selected").val();

        //servlet per inserire il curriculum selezionato
        $.getJSON("InsertCurriculumcic", {number: selectedCycle, name: selectedCurriculum}, function (data) {
            $("#selectCurriculum option").remove();
            selectedItem();
        });
    });



}

function closeModifyORaddDiv()
{
    selectedItem();
}

function closeSezioneCurriculum()
{
    $("#StudentiCurriculumDiv").hide();
    selectedItem();
}


function addCurriculumTutorButton(id)
{
    //in id abbiamo la mail del nuovo coordinatore che deve essere assegnato
    //alert(id);

    CurriculumTutorName = $("#CurriculumTutorNameField").html();
    if (CurriculumTutorName === 'nessun tutor') { //non c'è un tutor assegnato, dobbiamo soltanto aggiungercelo
        //servlet per fare inserire il nuovo coordinatore
        $.getJSON("InsertCurriculumcicCoordinator", {fkCycle: selectedCycle, fkCurriculum: selectedDescriptionCurriculum, fkProfessor: id}, function (data) {
            //alert("siamo nella servlet");
            selectedItem();
            viewCurriculumButton(selectedDescriptionCurriculum);
        });

    }
    else { //c'è gia un tutor assegnato: dobbiamo  aggiornarlo
        if (CurriculumtutorKey === id)
        {
            alert("Hai selezionato il tutor attuale");
        }
        else {
            // da IMPLEMENTARE e provare ancora PERCHE SERVE UN ALTRO PROFESSORE
            //servlet per rimuovere il vecchio coordinatore assegnato 
            $.getJSON("DeleteCurriculumcicCoordinatorServlet", {fkCycle: selectedCycle, fkCurriculum: selectedDescriptionCurriculum}, function (data) {

            });

            //servlet per fare inserire il nuovo coordinatore
            $.getJSON("InsertCurriculumcicCoordinator", {fkCycle: selectedCycle, fkCurriculum: selectedDescriptionCurriculum, fkProfessor: id}, function (data) {
                //alert("siamo nella servlet");
                selectedItem();
                viewCurriculumButton(selectedDescriptionCurriculum);
            });
        }
    }

}

function removeCurriculumTutorButton()

{
    alert(CurriculumtutorKey);

    //servlet per eliminare il coordinatore del curriculum all'interno del ciclo
    $.getJSON("DeleteCurriculumcicCoordinatorServlet", {fkCycle: selectedCycle, fkCurriculum: selectedDescriptionCurriculum}, function (data) {
        // alert("siamo nella servlet");
        selectedItem();
        viewCurriculumButton(selectedDescriptionCurriculum);
    });
}

function removeProfessorFromCurriculum(id)
{
   // alert(id);
//servlet per eliminare un professore da un curriculum all'interno di un ciclo
    $.getJSON("DeleteProfessor", {fkCycle: selectedCycle, fkCurriculum: selectedDescriptionCurriculum, teach:id}, function (data) {
        // alert("siamo nella servlet");
        selectedItem();
        viewCurriculumButton(selectedDescriptionCurriculum);
    });
}

function removeStudentFromCurriculum (id)
{
    //alert(id);
    
    //servlet per eliminare uno studente da un curriculum all'interno di un ciclo
    $.getJSON("DeletePhdstudent", {fkPhdstudent :id}, function (data) {
        // alert("siamo nella servlet");
        selectedItem();
        viewCurriculumButton(selectedDescriptionCurriculum);
    });
    
}

function selectedProfessortoAdd()
{

    selectedProfessorToAdd = $("#ProfessorsSelectebleList option:selected").val();
    //alert(selectedProfessorToAdd);

    //servlet per inserire il professore selezionato
    $.getJSON("InsertProfessor", {fkCycle: selectedCycle, fkCurriculum: selectedDescriptionCurriculum, teach: selectedProfessorToAdd}, function (data) {
        $("#ProfessorsSelectebleList option").remove();
        $("#CurriculumDocentiTableList tr").remove();
        selectedItem();
        viewCurriculumButton(selectedDescriptionCurriculum);
    });

}

function selectedStudentToAdd()
{
    selectedStudentToAddVar = $("#StudentsSelectebleList option:selected").val();
    //alert(selectedStudentToAddVar);
    
    //servlet per inserire lo studente selezionato
    $.getJSON("InsertPhdstudent", {fkCycle: selectedCycle, fkCurriculum: selectedDescriptionCurriculum, fkPhdstudent: selectedStudentToAddVar}, function (data) {
        //alert("siamo nella servlet");
        $("#StudentsSelectebleList option").remove();
        $("#CurriculumStudentiTableList tr").remove();
        selectedItem();
        viewCurriculumButton(selectedDescriptionCurriculum);
    });
}