/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    getStudentsList();

});


function getStudentsList()
{

    $.getJSON("GetPhdStudentList", function (data) {
        // $("#phdStudetsList option").remove();
        $.each(data.account, function (index, value) {
            phdstudent = "<option class='optionItem' value='" + value.secondaryEmail + "'> " + value.name + " " + value.surname + " </option> ";
            $("#phdStudentsList").append(phdstudent);
        });
    });
}

function selectedItem()
{
    $("#TutorNameField").html("nessun tutor");
    $("#removeTutorButton").hide();
    $("#professorListTable tr").remove();

    var selectedAccount = $("#phdStudentsList option:selected").val(); // la chiave primaria di account
    if (selectedAccount !== "default")
    {
        $("#panelDiv").show();
        //servlet per richiamare il tutor
        $.getJSON("GetTutorServlet", {fkAccount: selectedAccount}, function (data) {
            // alert("siamo in get tutor servlet");
            // alert(data);
            $("#removeTutorButton").show();
            $("#TutorNameField").html(data.name + " " + data.surname);
        });

        //servlet per riempire la tabella con tutti i professori
        $.getJSON("GetProfessorsList", function (data) {
            $.each(data.account, function (index, value) {
                professor = "<tr> <td> " + value.name + "</td> <td> " + value.surname + "</td>   <td> <button class='btn' onclick='addTutorButton("+value.secondaryEmail+")' >Aggiungi </button>  </td>  </tr> ";
                $("#professorListTable").append(professor);
            });
        });
        
        
        
    }
    else
        $("#panelDiv").hide();
}


/*
funciton removeTutorButton()
{
    
}

function addTutorButton()
{
    
}
*/