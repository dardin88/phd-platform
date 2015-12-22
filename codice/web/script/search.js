/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
  //getAccountList();
  
   searchForType();
  searchForName();
    
   });


        
 
       
function searchForType(){  
var pro="Docente";
var dot="Dottorando";
    $('#sel1').on('change', function() {
        if (this.value==dot){ 
            
          
            $("#resultbody tr").remove();
              $.getJSON("GetPhdStudentList", function (data) {
    
        $.each(data.account, function (index, value) {
           phdstudent = "<tr  id="+  value.secondaryEmail+">  <td> "  + value.typeAccount+ "</td>  <td> " + value.name + "</td> <td> " + value.surname + "</td>    <td> " + value.email + "</td> ";
            
            $("#resultbody").append(phdstudent);
        });
    });
        
        
      
       
     
}
  else if(this.value==pro){ 
     
 $("#resultbody tr").remove();
            $.getJSON("GetProfessorsList", function (data) {
       $.each(data.account, function (index, value) {
           professor = "<tr>  <td> "  + value.typeAccount+ "</td>  <td> " + value.name + "</td> <td> " + value.surname + "</td>    <td> " + value.email + "</td> ";
                $("#resultbody").append(professor);
        });
          });
        }
        else {
      
            $("#resultbody tr").remove();
             $.getJSON("getAccountList", function (data) {
            $.each(data.account, function (index, value) {
                account = "<tr>  <td> " + value.typeAccount + "</td>  <td> " + value.name + "</td> <td> " + value.surname + "</td>    <td> " + value.email + "</td> ";
                $("#resultbody").append(account);
            });
        });
        }
  
    });
            
        
    
   }


function searchForName()
{ 
   
    
   $('#search').click(function() {
     
     $("#resultbody tr").remove();
                    var   name = $("#word").val();


 
     $.getJSON("SearchUser",{name: name}, function (data) {
        
            $.each(data.account, function (index, value) {
                
    var   account = "<tr>  <td> " + value.typeAccount + "</td>  <td> " + value.name + "</td> <td> " + value.surname + "</td>  <td> " + value.email + "</td> </tr>";
           
                $("#resultbody").append(account);
           
        
                
         
          });
        });
   });
     
}

