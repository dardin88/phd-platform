/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
 
    getCorsoList();
    //getLesson();
});


function getCorsoList()
{
 //servlet per richiamare la lista dei nomi lezioni
   $.getJSON("GetAllCourse", function (data) {
    $.each(data.course, function (index, value) {
        
           corso = "<option value="+value.idCourse+" > " + value.name + "  </option> ";
        
        $("#Corsoprofessore").append(corso);
        });
    });
    
}
//metodo per chiamare tutt ele lezioni
function selectedItem()
{ 
    selected = $("#Corsoprofessore option:selected").val();
    if (selected !== "default") //se il valore della select è default non mostriamo il div contenente le informazioni delle date delle lezioni
    { selected = $("#Corsoprofessore option:selected").val();
        $("#panelDiv").show();
    //metodo per stampare le date
       $.getJSON("GetPresenceDottorandi",{idCourse:selected}, function (data) { 
       $.each(data.presence, function (index,value) { 
        dottorando="<tr id="+value.secondaryEmail+"> <td id="+value.secondaryEmail+" > "+ value.name +" "+value.surname+" </td>  </tr>";
         $("#resultbody ").append(dottorando);
            id= value.secondaryEmail;
            
    $.getJSON("GetPresenceToLesson",{idCourse:selected,fkPhdstudent:id}, function (data) { 
       $.each(data.presence, function (index,balue) {
        lezione= balue.fkLesson;
        dottorandopre="<td> <input type='checkbox' value="+true+"   id=" +  id + " onclick='changePresenza(" + 'id' + ","+lezione +")' class='checkboxclass' ></td> ";
           
          
 $("#resultbody ").find('td:last').after(dottorandopre);
    if(balue.isPresent==true){ $('#id').prop('checked', true);}
      
    });
    
});

        });
    });
  
      
}

}
// metodo per mostrare i partecipanti a lezione

// metodo per cambiare la presenza 

function changePresenza(id,lezione) {
    
     alert("firma inserita");
    $.getJSON("ModifyPresence",{fkPhdstudent:id,fkLesson:lezione}, function (data) { 
    
    });
    
}
  
function selectedItemdot(){
   
    selected = $("#Corsoprofessore option:selected").val();
    if (selected !== "default") //se il valore della select è default non mostriamo il div contenente le informazioni delle date delle lezioni
    { selected = $("#Corsoprofessore option:selected").val();
        $("#panelDiv").show();
    //metodo per stampare le date
       $.getJSON("GetPresenceDottorandi",{idCourse:selected}, function (data) { 
       $.each(data.presence, function (index,value) { 
        dottorando="<tr> <td id="+value.secondaryEmail+" > "+ value.name +" "+value.surname+" </td>  </tr>";
            id= value.secondaryEmail;
            
    $.getJSON("GetPresenceToLesson",{idCourse:selected,fkPhdstudent:id}, function (data) { 
       $.each(data.presence, function (index,value) {
         
        dottorandopre="<td> <input type='checkbox' value="+true+"   id=" +  value.fkPhdstudent + " onclick='changePresenza(" + 'id' + ")' class='checkboxclass' disabled></td> ";
            
           $("#resultbody ").append(dottorando).find('td:last').after(dottorandopre);
    if(value.isPresent==true){ $('.checkboxclass')[0].checked = true;}
      
    });
    
});
// dottorando="<tr > <td> "+ value.name+" </td>  <td>"+value.surname+"</td> <td> <input type='checkbox' value="+true+"   id=" +  value.fkPhdstudent + " onclick='changePresenza(" + 'id' + ")' ></td></tr>";
 //$("#resultbody").append(dottorando);
   
        });
    });
  
      
}

}