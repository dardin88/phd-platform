/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
  //getAccountList();
  
   searchForType();
  
    
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
  else if(this.value==pro){ alert(" Mannaggia la madonna");
   $.getJSON("GetProfessorsList", function (data) {
        alert("dio bastardo");
        $.each(data.account, function (index, value) {
           professor = "<tr>  <td> "  + value.typeAccount+ "</td>  <td> " + value.name + "</td> <td> " + value.surname + "</td>    <td> " + value.email + "</td> ";
                $("#gattini").append(professor);
        });
          });
        }
        else {
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


/*function getAccountList()
{
    
    
        
}
*/
