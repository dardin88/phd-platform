/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function myFunction()
{
    var x = document.getElementById("sel1").value;
    document.getElementById("demo").innerHTML = "You selected: " + x;
    if(x !== "default")  $("#panelDiv").show();
    else $("#panelDiv").hide();
    
    console.log(x);
    //$.get("/GetTutorServlet",{fkAccount:x}, function(data){
    //   $("#TutorNameField").html(data); 
    //});
}
