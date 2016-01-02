/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    getAccountList();
    searchForName();
});



function getAccountList() {
    $.getJSON("getAccountList", function(data) {
        $.each(data.account, function (index,value) {
            account = "<tr><td> " + value.name + " " + value.surname
                    + "</td><td> " + value.secondaryEmail + "</td>"
                    + "<td> " + value.typeAccount + "</td></tr>";
            
            $("#accountListTable").append(account);
        });
    });
}


function searchForName()
{ 
   $('#word').on("keyup",function() {    
     $("#bodyTable tr").hide();
                    var   name = $("#word").val();
     if(name == "")
         getAccountList();
     else  {
     $.getJSON("SearchUser",{name: name}, function (data) {       
            $.each(data.account, function (index, value) {               
    var   account = "<tr>  <td> " + value.name + " " + value.surname  + "</td>  <td> " + value.email + "</td> <td> " + value.typeAccount + "</td>/tr>";
           
                $("#accountListTable").append(account);
          });
        });
      
     }
   });
}




