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
var tut="Tutti";


 $.getJSON("getAccountList", function (data) {
            $.each(data.account, function (index, value) {
                 var nuovoType = value.typeAccount;
            var realType;
            
            switch(nuovoType) {
                case "phdstudent":
                    realType = "Dottorando";
                    break;
                case "professor":
                  realType = "Docente";
                    break;
                case "basic":
                   realType = "Base";
                    break;
            }
                account = "<tr>  <td> " + realType + "</td>  <td> " + value.name + "</td> <td> " + value.surname + "</td>    <td> " + value.email + "</td>  <td> <button class='btn btn-blu' id=" + value.email + " onclick='viewProfile(" + 'id' + ")' > <span class='glyphicon glyphicon-user' aria-hidden='true' ></span> Profilo</button>  </td> </tr>";
                $("#resultbody").append(account);
            });
        });
        
    $('#sel1').on('change', function() {
        $("#word").val("");
        if (this.value==dot){ 
            
          
            $("#resultbody tr").remove();
              $.getJSON("GetPhdStudentList", function (data) {
    console.log(data);
        $.each(data.account, function (index, value) {
            var nuovoType = value.typeAccount;
            var realType;
            
            switch(nuovoType) {
                case "phdstudent":
                    realType = "Dottorando";
                    break;
                case "professor":
                  realType = "Docente";
                    break;
                case "basic":
                   realType = "Base";
                    break;
            }
            
            
            
           phdstudent = "<tr  id="+  value.secondaryEmail+">  <td> "  + realType + "</td>  <td> " + value.name + "</td> <td> " + value.surname + "</td>    <td> " + value.email + "</td>  <td> <button class='btn btn-blu' id=" + value.email + " onclick='viewProfile(" + 'id' + ")' > <span class='glyphicon glyphicon-user' aria-hidden='true' ></span> Profilo</button>  </td> ";
            
            $("#resultbody").append(phdstudent);
        });
    });
        
        
      
       
     
}
  else if(this.value==pro){ 
     
 $("#resultbody tr").remove();
            $.getJSON("GetProfessorsList", function (data) {
       $.each(data.account, function (index, value) {
            var nuovoType = value.typeAccount;
            var realType;
            
            switch(nuovoType) {
                case "phdstudent":
                    realType = "Dottorando";
                    break;
                case "professor":
                  realType = "Docente";
                    break;
                case "basic":
                   realType = "Base";
                    break;
            }
           professor = "<tr>  <td> "  + realType+ "</td>  <td> " + value.name + "</td> <td> " + value.surname + "</td>    <td> " + value.email + "</td>  <td> <button class='btn btn-blu' id=" + value.email + " onclick='viewProfile(" + 'id' + ")' > <span class='glyphicon glyphicon-user' aria-hidden='true' ></span> Profilo</button>  </td> </tr> ";
                $("#resultbody").append(professor);
        });
          });
        }
        else if(this.value==tut) {
      
            $("#resultbody tr").remove();
             $.getJSON("getAccountList", function (data) {
            $.each(data.account, function (index, value) {
                 var nuovoType = value.typeAccount;
            var realType;
            
            switch(nuovoType) {
                case "phdstudent":
                    realType = "Dottorando";
                    break;
                case "professor":
                  realType = "Docente";
                    break;
                case "basic":
                   realType = "Base";
                    break;
            }
                account = "<tr>  <td> " + realType + "</td>  <td> " + value.name + "</td> <td> " + value.surname + "</td>    <td> " + value.email + "</td>  <td> <button class='btn btn-blu' id=" + value.email + " onclick='viewProfile(" + 'id' + ")' > <span class='glyphicon glyphicon-user' aria-hidden='true' ></span> Profilo</button>  </td> </tr>";
                $("#resultbody").append(account);
            });
        });
        }
  
    });
            
        
    
   }


function searchForName()
{   
   $('#word').keyup(function() {
     
     $("#resultbody tr").remove();
                    var   name = $("#word").val();
                    
     


 
     $.getJSON("SearchUser",{name: name}, function (data) {
         if(data.account === null){
             alert("ssdd");
         }
            $.each(data.account, function (index, value) {
                 var nuovoType = value.typeAccount;
            var realType;
            
            switch(nuovoType) {
                case "phdstudent":
                    realType = "Dottorando";
                    break;
                case "professor":
                  realType = "Docente";
                    break;
                case "basic":
                   realType = "Base";
                    break;
            }
                
    var   account = "<tr>  <td> " + realType + "</td>  <td> " + value.name + "</td> <td> " + value.surname + "</td>  <td> " + value.email + "</td>   <td> <button class='btn btn-blu' id=" + value.email + " onclick='viewProfile(" + 'id' + ")' > <span class='glyphicon glyphicon-user' aria-hidden='true' ></span> Profilo</button>  </td> </tr>";
           
                $("#resultbody").append(account);
           
        
                
         
          });
        });
   });
     
}


function viewProfile(id) {
    window.location.href = "viewProfile.jsp?mail=" + id;
}

