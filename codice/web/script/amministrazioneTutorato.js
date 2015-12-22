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
        $.each(data.account, function (index, value) {
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
    selectedAccount = $("#phdStudentsList option:selected").val(); // la chiave primaria di account

    if (selectedAccount !== "default") //se il valore della select è default non mostriamo il div contenente le informazioni
    {
        $("#panelDiv").show();

        //servlet per richiamare il tutor
        $.getJSON("GetTutorServlet", {fkAccount: selectedAccount}, function (data) {
            $("#removeTutorButton").show();
            $("#TutorNameField").html("  " + data.name + " " + data.surname + " ");
            tutorKey = data.fkAccount; //salviamo la mail del professore 
        });

        //servlet per riempire la tabella con tutti i professori
        $.getJSON("GetProfessorsList", function (data) {
            $.each(data.account, function (index, value) {
                professor = "<tr> <td> " + value.name + "</td> <td> " + value.surname + "</td>   <td> <button class='btn btn-orange' id=" + value.secondaryEmail + " onclick='addTutorButton(" + 'id' + ")' > Aggiorna </button>  </td>  </tr> "; // la secondaryEmail è la chiave primaria del professore che dovrà essere settato come nuovo tutor
                $("#professorListTable").append(professor);
            });
        });
    }
    else
        $("#panelDiv").hide();
}


//funzione chiamata dall'onclick dei bottoni generali nella tabella contenente la lista dei professori
function addTutorButton(newProfessorkey)
{
    //in key abbiamo la mail del nuovo tutor
    this.newProfessorkey = newProfessorkey;
    alert("hai clickato su " + newProfessorkey);
    alert("il tutor che c'è ora è " + tutorKey);

    var studentMail = $("#phdStudentsList option:selected").val();
    //in selectedAccount abbiamo la mail dello studente selezionato nella select a cui bisogna assegnare il tutor 
    //in tutorKey abbiamo la mail del professore gia selezionato come tutor , se c'è (serve nel caso dobbiamo rimuoverlo)

    tutorName = $("#TutorNameField").html();
    if (tutorName === 'nessun tutor') { //non c'è un tutor assegnato, dobbiamo soltanto aggiungercelo
        /*
         //servlet per assegnare il tutor  
         $.getJSON("InsertStudentTutor", {idStudent: studentMail, idProfessor: newProfessorkey}, function (data) {
         
         });
         */
    }
    else { //c'è gia un tutor assegnato: dobbiamo  aggiornarlo
        // se selezioniamo lo stesso che c'è in tutorName non facciamo niente
        if (tutorKey === newProfessorkey)
        {
            //donothing
            messageDialog("Hai selezionato il tutor attuale"); //come funziona?
        }
        else {
            /*
           //servlet per fare l'update del tutor
            $.getJSON("UpdateTutorServlet", {idProfessor: newProfessorkey, idStudent: studentMail}, function (data) {
                
            });
           */ 
           
        }
    }

}

function removeTutorButton()
{
    var studentMail = $("#phdStudentsList option:selected").val();
    //servlet per rimuovere il tutor assegnato 
    $.getJSON("DeleteTutorServlet", {idStudent: studentMail}, function (data) {
        $("#removeTutorButton").hide();
        $("#TutorNameField").html("nessun tutor");
    });
}
