/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {

    getCorsoList();
  getLesson();
});

function getCorsoList()
{
 //servlet per richiamare la lista dei nomi lezioni
   $.getJSON("GetAllLessonOfProfessor", function (data) {
    $.each(data.lessons, function (index, value) {
        
           corso = "<option value="+value.idLesson +" > " + value.name + "  </option> ";
        alert(value.idLesson);   
        $("#Corsoprofessore").append(corso);
        });
    });
    
}

function getLesson()
{
    
}