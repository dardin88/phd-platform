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


function mostraPresenze(){
    selected = $("#Lezioneprofessore option:selected").val();
    if (selected !== "default") //se il valore della select è default non mostriamo il div contenente le informazioni delle date delle lezioni
    {   
        $("#resultbody tr").remove();
        $("#results").show();
        selected = $("#Lezioneprofessore option:selected").val();
    $.getJSON("GetPresence",{fkLesson: selected}, function (data) { 
           
        
        $.each(data.presence, function (index, value) { 
          
             dottorando="<tr > <td> "+ value.fkPhdstudent+" </td>   <td> <input type='checkbox'    id=" +  value.fkPhdstudent + " onclick='changePresenza(" + 'id' + ")'  ></td></tr>";
            
            // dottorando="<tr > <td> "+ value.name+" </td>  <td>"+value.surname+"</td> <td> <input type='checkbox' value="+true+"   id=" +  value.fkPhdstudent + " onclick='changePresenza(" + 'id' + ")' ></td></tr>";
                               
            $("#resultbody").append(dottorando);
         
   
        });
        
    });
    
   
    }
}


function changePresenza(id) {
    $.getJSON("GetPresenceCourse",{fkPhdstudent:id},function(data){
    
        
        
        $("#id");
        
        
        
      
});
    $.getJSON("ModifyPresence",{fkPhdstudent:id}, function (data) { 
    alert(data);
    });
    
}
  