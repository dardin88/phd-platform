/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    alert("ci siamo");
    $.getJSON("GetPhdStudentList", function (data) {
                 alert("ci siamo in getJson");
                // $("#phdStudetsList option").remove();
                $.each(data.phdStudentsList, function (index, value) {
                   
                    phdstudent = "<option class='optionItem' id='" + value.fkAccount + "'> " + value.fkAccount +  " </option> ";
                    $("#phdStudentsList").append(phdstudent);
                });
            });
});


/*
function myFunction()
{
    var x = document.getElementById("sel1").value;
    // document.getElementById("demo").innerHTML = "You selected: " + x;
    if(x !== "default")  $("#panelDiv").show();
    else $("#panelDiv").hide();
    
    console.log(x);
    //$.get("/GetTutorServlet",{fkAccount:x}, function(data){
    //   $("#TutorNameField").html(data); 
    //});
}
*/