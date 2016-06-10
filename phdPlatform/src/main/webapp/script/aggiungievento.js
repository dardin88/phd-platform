/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    selectedCourse1();
    selectedCourse2();
    selectedSpeakerName();
  
});

function selectedTypeEvent() {
    var selected = $("#typeEvent option:selected").val();
    if (selected === "lezione")
    {
        $("#courselist1").html("");
        selectedCourse1();
        $("#descriptionPanelSeminar").hide();
        $("#descriptionPanelLesson").show();
    }
    if (selected === "seminario")
    {
        $("#courselist2").html("");
        selectedCourse2();
        $("#descriptionPanelLesson").hide();
        $("#descriptionPanelSeminar").show();
    }
}

function selectedCourse1() {
    def = "<option class='optionItem' value='" + 'default' + "'> " + '- seleziona -' + "  </option> ";
    $("#courselist1").append(def);
    $.getJSON("GetAllCourse", function (data) {
        $.each(data.course, function (index, value) {
            courses = "<option class='optionItem' value='" + value.idCourse + "'> " + value.name + "  </option> ";
            $("#courselist1").append(courses);
        });
    });
}
function selectedCourse2() {
    def = "<option class='optionItem' value='" + 'default' + "'> " + '- seleziona -' + "  </option> ";
    $("#courselist2").append(def);
    $.getJSON("GetAllCourse", function (data) {
        $.each(data.course, function (index, value) {
            courses = "<option class='optionItem' value='" + value.idCourse + "'> " + value.name + "  </option> ";
            $("#courselist2").append(courses);
        });
    });
}

function insertLesson() {

    nomeLezione = $("#nomelezione").val();
    classe = $("#class").val();
    course = $("#courselist1 option:selected").val();
    descrizione = $("#description").val();
    dataLezione = $("#date").val();
    orainizio = $("#timepicker1").val();
    orafine = $("#timepicker2").val();
    if (nomeLezione !== "" && course !== "default" && descrizione !== "") {
        $.getJSON("AddLessonServlet", {name: nomeLezione, classroom: classe, course: course, description: descrizione, data: dataLezione, starttime: orainizio, endtime: orafine}, function (data) {
            if (data.result) {
                $("#titleInfo").html("");
                $("#descriptionInfo").html("");
                $("#infoDialog").modal();
                $("#titleInfo").html("Operazione eseguita con successo!");
                $("#descriptionInfo").html("L'evento è stato aggiunto.");
            } else {
                $("#titleInfo").html("");
                $("#descriptionInfo").html("");
                $("#infoDialog").modal();
                $("#titleInfo").html("Errore inserimento evento!");
                $("#descriptionInfo").html("L'evento non è stato aggiunto, riprova.");
            }
            
        });
    }


}

function insertSeminar() {

    nomeSeminario = $("#nameseminar").val();
    nomespeacker = $("#speakerName option:selected").text();
    course = $("#courselist2 option:selected").val();
    place = $("#place").val();
    descrizione = $("#descriptionseminar").val();
    dataSeminario = $("#dateseminar").val();
    orainizio = $("#timepicker3").val();
    orafine = $("#timepicker4").val();
    if (nomeSeminario !== "" && nomespeacker !== "" && course !== "default" && descrizione !== "") {
        $.getJSON("AddSeminarServlet", {name: nomeSeminario, place: place, course: course, description: descrizione, data: dataSeminario, starttime: orainizio, endtime: orafine, namespeacker: nomespeacker}, function (data) {
            if (data.result) {
                $("#titleInfo").html("");
                $("#descriptionInfo").html("");
                $("#infoDialog").modal();
                $("#titleInfo").html("Operazione eseguita con successo!");
                $("#descriptionInfo").html("L'evento è stato aggiunto.");
            } else {
                $("#titleInfo").html("");
                $("#descriptionInfo").html("");
                $("#infoDialog").modal();
                $("#titleInfo").html("Errore inserimento evento!");
                $("#descriptionInfo").html("L'evento non è stato aggiunto, riprova.");
            }
        });
    }

}
function selectedSpeakerName() {
  
     $.getJSON("GetProfessorsList", function (data) {
         
           $.each(data.account, function (index, value) {
            professor = "<option class='optionItem' value='"+value.name+ "'> " + value.name +" "+ value.surname+"  </option> ";
            $("#speakerName").append(professor);
        });
     });
}

