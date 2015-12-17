/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
    var dot="Dottorando";
    $('#sel1').on('change', function() {
        if (this.value==dot){ 
            
            alert("dio porco");
            
        
        
        
           /* alert(result.item1+" "+result.item2+" "+result.item3);
            var a= result.item1 ;
	 $('#gattini').append('<p> nome: ' + a. + '</p>');*/
       
     
}
  else alert(" Mannaggia la madonna");      
    });
            
        
     // alert( this.value +dot); // or $(this).val()
  });
  



/*

function initSearchUser() {
    // servlet per avere le informazioni riguardanti un determinato curriculum
    $.getJSON("SearchUser", {name: $("#word").val()}, function (data) {

        $.each(data.account, function (value) {
            var nome = "<div class='panel-curriculum col-sm-4 text-center pointer'> <a href='curriculum.jsp?curriculumName=" + value + "'> <h4> " + value + " </h4>  </a> </div>";
            $("#cycleList").append(nome);
        });
    });
}
;


*/




/* function initAccount()
{  // servlet per avere le informazioni riguardanti un determinato ciclo
    $.getJSON("getAccountList", {name: $("#word").val()}, function (data) {
    $("#name").html(data.word);
    });
}
;
function initAccountList(){
    
};
 * 
 * 
 * $.get("SearchUser", function (data) {
     parsedData = $.parseJSON(data);
     $.each(parsedData.AccountNames, function (value) {
                        
         var namediv = "<input id='word' class='form-control' type='text' value='" + value + "'></div>";
       alert(namediv);
    });
});



// servlet per avere le informazioni riguardanti un determinato curriculum
$.get("GetPhdCurriculumsNames", function (data) {
    parsedData = $.parseJSON(data);
    $.each(parsedData.curriculumNames, function (index, value) {
        var curriculumDiv = "<div class='panel-curriculum col-sm-4 text-center pointer' id='" + value + "'> <h4> " + value + " </h4> </div>";
        $("#curriculumList").append(curriculumDiv);
    });

    if ($("#curriculumParameter").val() !== "null") {

            $.getJSON("GetPhdCurriculum?phdCurriculumName=" + $("#curriculumParameter").val(), function (data) {

                // questi campi necessitano di essere cancellati prima della visualizzazione di un nuovo ciclo
                $("#phdCurriculumName").html("");
                $("#phdCurriculumProfessor").html("");
                $("#phdCurriculumDescription").html("");

                $("#phdCurriculumName").html(data.phdCurriculumName);

                $.getJSON("GetPerson?pSSN=" + data.FK_Professor, function (dataProfessor) {
                    $("#phdCurriculumProfessor").html("Coordinatore: " + dataProfessor.name + " " + dataProfessor.surname);
                });

                $("#phdCurriculumDescription").html(data.phdCurriculumDescription);
            });

            $("#curriculumPanel").slideDown();


    }



    $(".panel-curriculum").click(function () {
        $.getJSON("SearchUser" + $(this).attr('name'), function (data) {

            // questi campi necessitano di essere cancellati prima della visualizzazione di un nuovo ciclo
            $("#name").html("");
            $("#phdCurriculumProfessor").html("");
            $("#phdCurriculumDescription").html("");

            $("#phdCurriculumName").html(data.phdCurriculumName);

            $.getJSON("GetPerson?pSSN=" + data.FK_Professor, function (dataProfessor) {
                $("#phdCurriculumProfessor").html("Coordinatore: " + dataProfessor.name + " " + dataProfessor.surname);
            });

            $("#phdCurriculumDescription").html(data.phdCurriculumDescription);
        });

        $("#curriculumPanel").slideDown();

    });
});
*/


