/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function selectedTypeEvent(){
     var selected = $("#typeEvent option:selected").val();
     if (selected==="lezione") 
     {
         $("#courselist1").html("");
         selectedCourse1();
         $("#descriptionPanelSeminar").hide();
         $("#descriptionPanelLesson").show();
     }
     if (selected==="seminario") 
     {
       $("#courselist2").html("");
       selectedCourse2();  
       $("#descriptionPanelLesson").hide();
       $("#descriptionPanelSeminar").show();
     }
 }
 
 function selectedCourse1(){
     def = "<option class='optionItem' value='" + 'default' + "'> " + '- seleziona -' + "  </option> ";
     $("#courselist1").append(def);    
     $.getJSON("GetAllCourse", function (data) {
            $.each(data.course, function (index, value) {
            courses = "<option class='optionItem' value='" + value.idCourse + "'> " + value.name + "  </option> ";
            $("#courselist1").append(courses);
        });
    });
 }
 function selectedCourse2(){
     def = "<option class='optionItem' value='" + 'default' + "'> " + '- seleziona -' + "  </option> ";
     $("#courselist2").append(def);    
     $.getJSON("GetAllCourse", function (data) {
            $.each(data.course, function (index, value) {
            courses = "<option class='optionItem' value='" + value.idCourse + "'> " + value.name + "  </option> ";
            $("#courselist2").append(courses);
        });
    });
 }
 
 function insertLesson(){
     idLezione = $("#idlezione").val();
     nomeLezione = $("#nomelezione").val();
     classe = $("#class").val();
     course = $("#courselist1 option:selected").val();
     descrizione = $("#description").val();
     dataLezione = $("#date").val();
     orainizio = $("#timepicker1").val();
     orafine = $("#timepicker2").val();
     if(idLezione !== "" && nomeLezione !== "" && course!=="default" && descrizione !== ""){
         $.getJSON("AddLessonServlet",{id: idLezione, name: nomeLezione, classroom: classe, course: course, description: descrizione, data: dataLezione, starttime: orainizio, endtime: orafine },function(data){
                if(data.result){
                        confirm("lezione inserita!");
                    }
         });
     }
 }
 
 function insertSeminar(){
     idSeminario = $("#idseminar").val();
     nomeSeminario = $("#nameseminar").val();
     nomespeacker = $("#nomespeacker").val();
     course = $("#courselist2 option:selected").val();
     place = $("#place").val();
     descrizione = $("#description").val();
     dataSeminario = $("#dateseminar").val();
     orainizio = $("#timepicker3").val();
     orafine = $("#timepicker4").val();
     if(idSeminario !== "" && nomeSeminario !== "" && nomespeacker !== "" && course!=="default" && descrizione !== "" ){
         $.getJSON("AddSeminarServlet",{id: idSeminario, name: nomeSeminario, place: place, course: course, description: descrizione, data: dataSeminario, starttime: orainizio, endtime: orafine, namespeacker: nomespeacker},function(data){
                if(data.result){
                        confirm("seminario inserito!");
                    }
         });
     }
     
 }

