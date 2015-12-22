/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    getStudentsList();

});

//servlet per avere la lista dei phd student, per riempire la select
function getStudentsList()
{
    $.getJSON("GetPhdStudentList", function (data) {
        // $("#phdStudetsList option").remove();
        alert("siamo in getphdstudentlist servlet");
        $.each(data.account, function (index, value) {
            // alert("siamo nell'each ");
            phdstudent = "<option class='optionItem' value='" + value.secondaryEmail + "'> " + value.name + " " + value.surname + " </option> ";
            $("#phdStudentsList").append(phdstudent);
        });
    });
}

//funzione chiamata dall'onclick della select
function selectedItem()
{
    $("#TutorNameField").html("nessun tutor"); 
    $("#removeTutorButton").hide(); 
    $("#professorListTable tr").remove();

    var selectedAccount = $("#phdStudentsList option:selected").val(); // la chiave primaria di account
    
    if (selectedAccount !== "default") //se il valore della select è default non mostriamo il div contenente le informazioni
        {
            $("#panelDiv").show();

            //servlet per richiamare il tutor
            $.getJSON("GetTutorServlet", {fkAccount: selectedAccount}, function (data) {
                //alert("siamo in get tutor servlet");
                // alert(data);
                $("#removeTutorButton").show();
                $("#TutorNameField").html(data.name + " " + data.surname);
            });


            //servlet per riempire la tabella con tutti i professori
            $.getJSON("GetProfessorsList", function (data) {
                //alert("siamo in get professor list servlet");
                $.each(data.account, function (index, value) {
                    professor = "<tr> <td> " + value.name + "</td> <td> " + value.surname + "</td>   <td> <button class='btn btn-orange' onclick='addTutorButton("+value.secondaryEmail+")' > Aggiorna </button>  </td>  </tr> "; // la secondaryEmail è la chiave primaria del professore che dovrà essere settato come nuovo tutor
                    $("#professorListTable").append(professor);
                });
            });
        }
    else
        $("#panelDiv").hide();
}


//funzione chiamata dall'onclick dei bottoni generali nella tabella contenente la lista dei professori
function addTutorButton()
{
    //alert(secondaryEmail);
    var selectedAccount = $("#phdStudentsList option:selected").val();
    alert(selectedAccount);
    //servlet per rimuovere il tutor assegnato 
    /*
     * è da finire perche nel caso il TUTORNAMEFIELD è gia "nessun tutor" non c'è bisogno di richiamare
     * la servlet di rimozione, ma soltanto quella di aggiunta
     */
    
    /*
            $.getJSON("DeleteTutorServlet", {idStudent: secondaryEmail}, function (data) {
                //alert("siamo in get tutor servlet");
                // alert(data);
                $("#removeTutorButton").show();
                $("#TutorNameField").html(data.name + " " + data.surname);
            });
    */
}


/*
funciton removeTutorButton()
{
    
}
*/