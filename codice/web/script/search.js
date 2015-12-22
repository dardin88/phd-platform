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


        
        
           /* alert(result.item1+" "+result.item2+" "+result.item3);
            var a= result.item1 ;
	 $('#gattini').append('<p> nome: ' + a. + '</p>');*/
       
function searchForType(){  
var pro="Docente";
var dot="Dottorando";
    $('#sel1').on('change', function() {
        if (this.value==dot){ 
            
            alert("dio porco");
            $("#gattini tr").remove();
              $.getJSON("GetPhdStudentList", function (data) {
        alert("i sandali di cristo");
        $.each(data.account, function (index, value) {
           phdstudent = "<tr>  <td> "  + value.typeAccount+ "</td>  <td> " + value.name + "</td> <td> " + value.surname + "</td>    <td> " + value.email + "</td> ";
                $("#gattini").append(phdstudent);
        });
    });
        
        
           /* alert(result.item1+" "+result.item2+" "+result.item3);
            var a= result.item1 ;
	 $('#gattini').append('<p> nome: ' + a. + '</p>');*/
       
     
}
  else if(this.value==pro){ 
      alert(" Mannaggia la madonna");
 $("#gattini tr").remove();
            $.getJSON("GetProfessorsList", function (data) {
        alert("dio bastardo");
        $.each(data.account, function (index, value) {
           professor = "<tr>  <td> "  + value.typeAccount+ "</td>  <td> " + value.name + "</td> <td> " + value.surname + "</td>    <td> " + value.email + "</td> ";
                $("#gattini").append(professor);
        });
          });
        }
        else {
            alert("Quel gran bastardo di ponziopilates");
            $("#gattini tr").remove();
             $.getJSON("getAccountList", function (data) {
            $.each(data.account, function (index, value) {
                account = "<tr>  <td> " + value.typeAccount + "</td>  <td> " + value.name + "</td> <td> " + value.surname + "</td>    <td> " + value.email + "</td> ";
                $("#gattini").append(account);
            });
        });
        }
  
    });
            
        
     // alert( this.value +dot); // or $(this).val()
   }


function searchForName()
{ 
   
    
   $('#search').click(function() {
     $("#gattini tr").remove();
                    var   name = $("#word").val();

 
 
     $.getJSON("SearchUser",{name: name}, function (data) {
          
       
       alert(name+"mannagia  i 7 sandali di cristo");
            $.each(data.account, function (index, value) {
                
    var   account = "<tr>  <td> " + value.typeAccount + "</td>  <td> " + value.name + "</td> <td> " + value.surname + "</td>  <td> " + value.email + "</td> </tr>";
           alert(account);
                $("#gattini tbody").append(account);
           
               
                
         
          });
        });
   });
}

