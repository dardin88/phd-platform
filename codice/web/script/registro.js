/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
 
    getCorsoList();
   // getFirma();
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
    $.getJSON("GetAllLesson",{fkCourse: selected}, function (data) { 
    $.each(data.lessons, function (index, value) {  

    var curriculumDiv = "<option class='optionItem' value='" + value.idLesson + "'> " + value.idLesson+ "  </option>";
                    $("#Lezioneprofessore").append(curriculumDiv);
              
    
    });
    });
    
    
}

}
// metodo per mostrare i partecipanti a lezione

function mostraPresenze(){
    selected1 = $("#Lezioneprofessore option:selected").val();
    if (selected1 !== "default") //se il valore della select è default non mostriamo il div contenente le informazioni delle date delle lezioni
    {   
        $("#resultbody tr").remove();
        $("#results").show();
        selected1 = $("#Lezioneprofessore option:selected").val();
        //metodo per vedere i partecipanti
    $.getJSON("GetPresence",{fkLesson: selected1}, function (data) { 
           
        
        $.each(data.presence, function (index, value) { 
          
             dottorando="<tr > <td > "+ value.fkPhdstudent+" </td>   <td id=" +  value.isPresent + " class='checkboxclass' > <input type='checkbox'    id=" +  value.fkPhdstudent + " onclick='changePresenza(" + 'id' + ")'  class='checkboxclass1' ></td></tr>";
            
            // dottorando="<tr > <td> "+ value.name+" </td>  <td>"+value.surname+"</td> <td> <input type='checkbox' value="+true+"   id=" +  value.fkPhdstudent + " onclick='changePresenza(" + 'id' + ")' ></td></tr>";
      
            
            $("#resultbody").append(dottorando);
         
   
        });
       
 $('.checkboxclass').each(function()
{
   //metodo per settare la check boc in base alla presenza
    id=$(this).attr('id');
    alert("id vale "+id);
  if (id==true){ 
      $(":checkbox")[0].checked = true;
    }
 //$.each(data.corso, function (index, value) { 
        // alert(value.isPresent);
         
    /*if ($(this).val(data.isPresent)==true){
            
          $(this)[0].checked = true;
        }    
         */
        //});

   });  
    });
  
    }
}
// metodo per cambiare la presenza 

function changePresenza(id) {
    
     alert("firma inserita");
    $.getJSON("ModifyPresence",{fkPhdstudent:id,fkLesson:selected1}, function (data) { 
    
    });
    
}
  
function mostraPresenzeDot(){
    selected1 = $("#Lezioneprofessore option:selected").val();
    if (selected1 !== "default") //se il valore della select è default non mostriamo il div contenente le informazioni delle date delle lezioni
    {   
        $("#resultbody tr").remove();
        $("#results").show();
        selected1 = $("#Lezioneprofessore option:selected").val();
        //metodo per vedere i partecipanti
    $.getJSON("GetPresence",{fkLesson: selected1}, function (data) { 
           
        
        $.each(data.presence, function (index, value) { 
          
             dottorando="<tr > <td id=" +  value.fkPhdstudent + "> "+ value.fkPhdstudent+" </td>   <td> <input type='checkbox'    id=" +  value.fkPhdstudent + "   class='checkboxclass' disabled></td></tr>";
            
            // dottorando="<tr > <td> "+ value.name+" </td>  <td>"+value.surname+"</td> <td> <input type='checkbox' value="+true+"   id=" +  value.fkPhdstudent + "  class='checkboxclass' disabled></td></tr>";
      
            
            $("#resultbody").append(dottorando);
         
   
        });
       
 $('.checkboxclass').each(function()
{
   //metodo per settare la check boc in base alla presenza
    id=$(this).attr('id');
    
      $.getJSON("GetPresenceCourse",{fkPhdstudent:id, fkLesson : selected1},function(data){
   
   
            
          $('.checkboxclass')[0].checked = true;
       
         
});
   });  
    });
  
    }
}