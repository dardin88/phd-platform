<%-- 
    Document   : activityRegister
    Created on : 9-mag-2016, 16.48.10
    Author     : Ernesto
--%>

<%@page import="it.unisa.dottorato.account.PhdStudent"%>
<%@page import="it.unisa.dottorato.account.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta name="description" content="Xenon Boostrap Admin Panel" />
        <meta name="author" content="" />

        <title>DISTRA-MIT Dottorato</title>

        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Arimo:400,700,400italic">
        <link rel="stylesheet" href="assets/css/fonts/linecons/css/linecons.css">
        <link rel="stylesheet" href="assets/css/fonts/fontawesome/css/font-awesome.css">
        <link rel="stylesheet" href="assets/css/bootstrap.css">
        <link rel="stylesheet" href="assets/css/xenon-core.css">
        <link rel="stylesheet" href="assets/css/xenon-forms.css">
        <link rel="stylesheet" href="assets/css/xenon-components.css">
        <link rel="stylesheet" href="assets/css/xenon-skins.css">
        <link rel="stylesheet" href="assets/css/custom.css">  
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <link rel="stylesheet" href="style/dottorato.css">
        <script src="assets/js/jquery-1.11.1.min.js"></script>
        
        <script type="text/javascript">   
            
            function deleteActivity(idActivity) {
                $.getJSON("DeleteActivity", {idActivity: idActivity},
                        function (data) {
                                alert('Attività '+idActivity+' eliminata');
                        }
                    );                
                $("#mytable").find("tr:gt(0)").remove();
                getActivityRegister(email);    
            }
            
            function redirectToEdit(string){
                
                sessionStorage.setItem("name", string.split(',')[0]);
                sessionStorage.setItem("description", string.split(',')[1]);
                sessionStorage.setItem("startDateTime", string.split(',')[2]);
                sessionStorage.setItem("endDateTime", string.split(',')[3]);
                sessionStorage.setItem("typology", string.split(',')[4]);
                sessionStorage.setItem("idActivity", string.split(',')[5]);
                sessionStorage.setItem("fkPhdStudent", string.split(',')[6]);
                location.href = 'insertEditActivity.jsp';
            }
            
            function getActivityRegister(email){
                $.getJSON("GetActivityRegister", {fkPhdStudent: email},
                
                        function (data) { 
                            var table = $('#mytable');                           
                            
                            $.each(data.activities, function(rowIndex, r) {
                                var row = $("<tr/>");                             
                                                                
                                row.append($("<td/>").text(r.name));
                                row.append($("<td/>").text(r.description));
                                row.append($("<td/>").text(r.startDateTime));
                                row.append($("<td/>").text(r.endDateTime));
                                row.append($("<td/>").text(r.totalTime));
                                row.append($("<td/>").text(r.typology));
                                row.append('<td width="20px">'+
                                                '<button type="button" class="btn btn-white" title="modifica">'+
                                                    '<span class="glyphicon glyphicon-cog" aria-hidden="true" onclick="redirectToEdit(\''+ r.name+ ','  +r.description+ ',' +r.startDateTime+ ',' +r.endDateTime+ ',' +r.typology+ ',' +r.idActivity+ ',' +r.fkPhdStudent +'\')" ></span>' +
                                                '</button>' +
                                            '</td>');
                                row.append('<td width="20px">' +
                                                    '<button type="button" class="btn btn-white" title="delete">' +
                                                        '<span class="glyphicon glyphicon-remove" aria-hidden="true" onclick="deleteActivity('+ r.idActivity +')" ></span>' +
                                                    '</button>' +
                                                '</td>');
                                
                                table.append(row);
                                
                            }); 
                        }    
               );
            }
            
            $(document).ready(function(){                 
                <% Account loggedPerson = ((Account) session.getAttribute("account"));%> 
                email = '<%= loggedPerson.getSecondaryEmail()%>';
                getActivityRegister(email);
            });
        </script>
    </head>
    
    <body class="page-body">

        <!-- Inclusione della pagina contenente il menù superiore --> 
        <jsp:include page="barraMenu.jsp" flush="true"/> 
        <div class="page-container">
            <!-- Inclusione della pagina contenente il menù laterale --> 

            <!-- Contenuto della pagina --> 

            <div class="main-content" id="content">

                <div class="row">

                    <div class="col-sm-1"></div>

                    <div class="col-sm-10">                        
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h1> Registro attività di <%= loggedPerson.getName()%> <%= loggedPerson.getSurname()%> </h1>                                    
                            </div>
                            <div class="panel-body">
                                <table id="mytable" width="98%" align="center" > 
                                    <th width="15%">Nome</th>
                                    <th width="28%">Descrizione</th>
                                    <th width="15%">Inizio</th>
                                    <th width="15%">Fine</th>
                                    <th width="10%">Durata (min)</th>
                                    <th width="10%">Tipologia</th>
                                </table>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-1"></div>

                </div>
            </div>

        </div>                          
    </body>
</html>
