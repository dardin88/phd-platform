// Funzione per la gestione del menù
$(document).ready(function () {

    getCycleList();
    getCurriculumCicList();
    viewInfoCurriculumCic();
  
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


function getCurriculumCicList()
{
    
    numeroCycle = $("#CycleList option:selected").val(); // la chiave primaria di account
    
    if (numeroCycle !== "default") //se il valore della select è default non mostriamo il div contenente le informazioni
    {
       
        $("#CurriculumCicList").html("");
        def = "<option class='optionItem' value='" + 'default' + "'> " + '- seleziona -' + "  </option> ";
        $("#CurriculumCicList").append(def);
        //servlet per richiamare le informazioni sul ciclo selezionato
        $.getJSON("GetCurriculumcicList", {number: numeroCycle}, function (data) {
            $.each(data.curriculumcicList, function (index, value) {
                 currCic = "<option class='optionItem' value='" + value.name+ "'> " + value.name + "  </option> ";
                 $("#CurriculumCicList").append(currCic);
            });
        });
    }
}


function viewInfoCurriculumCic(){
    numeroCycle = $("#CycleList option:selected").val();
    nomeCurriculum = $("#CurriculumCicList option:selected").val();
    if(numeroCycle!=="default"){
        if(nomeCurriculum!=="default"){
            $("#descriptionPanel").show();
            $("#CycleNumber").append(numeroCycle);
            $("#CurriculumName").append(nomeCurriculum);
            //servlet per richiamare le informazioni sul coordinatore del ciclo
            $.getJSON("ViewCycleCoordinator", {number: numeroCycle}, function (data) {
                
                    $("#CoordinatorName").html(" <b> " + data.fkAccount +"  </b> ");
                
            });
            //servlet per richiamare i docenti del curriculumcic
            $.getJSON("ViewCycleCoordinator", {number: numeroCycle}, function (data) {
                
                nomeCoordinatore = data.fkAccount;
            
                $.getJSON("ViewProfessorListServlet",{fkCycle: numeroCycle, fkCurriculum: nomeCurriculum, fkProfessor: nomeCoordinatore},function(dataProf){
                    
                    $.each(dataProf.prof, function (index, value) {
                          professore = " <b> " + value.name + " "+ value.surname +"  </b><br> ";
                          
                            $("#ProfessorOfCVCic").append(professore);
                      });
                });
            });
            //servlet per richiamare i dottorandi del curriculumcic
            $.getJSON("ViewCycleCoordinator", {number: numeroCycle}, function (data) {
                nomeCoordinatore = data.fkAccount;
                $.getJSON("ViewPhdstudentCurriculumcicServlet",{fkCycle: numeroCycle, fkCurriculum: nomeCurriculum, fkProfessor: nomeCoordinatore},function(dataStud){
                      $.each(dataStud.phdstudent, function (index, value) {  
                            $("#StudentOfCVCic").html(" <b> " + value.name + " "+ value.surname +"  </b><br> ");

                      });
                });
            });
        }
    }
}



