// Funzione per la gestione del menù
$(document).ready(function () {

    getCycleList();
   

});

function getCycleList()
{
    //servlet per richiamare la lista dei cicli
    $.getJSON("GetCyclesListNumers", function (data) {
            $.each(data.cyclesIds, function (index, value) {
            cycle = "<option class='optionItem' value='" + value.number + "'> " + value.number + "  </option> ";
            $("#cycleList").append(cycle);
        });
    });
}


function selectedItem()
{
    $("#removeButtonSpace button").remove();
    $("#modifyButtonSpace button").remove();
    $("#divPanelAddORModify").hide();
    
    
    selectedCycle = $("#cycleList option:selected").val(); // la chiave primaria di account
    if (selectedCycle !== "default") //se il valore della select è default non mostriamo il div contenente le informazioni
    {
        $("#descriptionPanel").show();
        
        //servlet per richiamare le informazioni sul ciclo selezionato
        $.getJSON("GetCyclebyNumber", {CycleNumber: selectedCycle}, function (data) {
            $("#CycleNumberField").html(" <b> " + data.CycleIds + "  </b> ");
            $("#CycleDescriptionField").html(data.CycleDescription);
            
            cycleNumber = data.CycleIds;
        });
    }
    else
        $("#descriptionPanel").hide();
}

function initCycle() {
    // servlet per avere le informazioni riguardanti un determinato ciclo
    $.getJSON("GetPhdCycle", {phdCycleId: $("#personCycle").val()}, function (data) {

        $("#phdCycle").html(data.phdCycle);
        $("#phdYear").html(data.phdYear);
        $("#phdDescription").html(data.phdDescription);
        $.getJSON("GetPerson", {pSSN: data.FK_Professor}, function (dataProfessor) {
            $("#phdProfessor").html("Coordinatore: " + dataProfessor.name + " " + dataProfessor.surname);
        });
    });
}
;

function initCycleList() {
    // servlet per avere la lista dei cicli esistenti
    $.getJSON("GetPhdCyclesIds", function (data) {

        $.each(data.cyclesIds, function (index, value) {
            var cycleDiv = "<p class='phdCycle_submenu' id='" + value + "'> " + value + "° ciclo </p>";
            $("#cycleList").append(cycleDiv);
        });
        $(".phdCycle_submenu").click(function () {

            cSelected = $(this).attr('id');
            $.getJSON("GetPhdCycle", {phdCycleId: cSelected}, function (data) {

                // questi campi necessitano di essere cancellati prima della visualizzazione di un nuovo ciclo
                $("#phdDescription").html("");
                $("#phdProfessor").html("");
                $("#phdCycle").html(data.phdCycle);
                $("#phdYear").html(data.phdYear);
                $("#phdDescription").html(data.phdDescription);
                $.getJSON("GetPerson", {pSSN: data.FK_Professor}, function (dataProfessor) {
                    $("#phdProfessor").html("Coordinatore: " + dataProfessor.name + " " + dataProfessor.surname);
                });
            });
            $(".panel-curriculum").remove();
            // servlet per avere le informazioni riguardanti un determinato curriculum
            $.getJSON("GetPhdCurriculumsNamesByPhdCycle", {phdCycleId: cSelected}, function (data) {
                $.each(data.curriculumNames, function (index, value) {
                    var curriculumDiv = "<div class='panel-curriculum col-sm-4 text-center pointer'> <a href='curriculum.jsp'> <h4> " + value + " </h4>  </a> </div>";
                    $("#curriculumList").append(curriculumDiv);
                });
            });
        });
    });
}
;

function initCurriculumList() {
    // servlet per avere le informazioni riguardanti un determinato curriculum
    $.getJSON("GetPhdCurriculumsNamesByPhdCycle", {phdCycleId: $("#personCycle").val()}, function (data) {

        $.each(data.curriculumNames, function (index, value) {
            var curriculumDiv = "<div class='panel-curriculum col-sm-4 text-center pointer'> <a href='curriculum.jsp?curriculumName=" + value + "'> <h4> " + value + " </h4>  </a> </div>";
            $("#curriculumList").append(curriculumDiv);
        });
    });
}
;




