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
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/TweenMax.min.js"></script>
        <script src="assets/js/resizeable.js"></script>
        <script src="assets/js/joinable.js"></script>
        <script src="assets/js/xenon-api.js"></script>
        <script src="assets/js/xenon-toggles.js"></script>
        <script src="assets/js/jquery-ui/jquery-ui.min.js"></script>

        <!-- Imported scripts on this page -->
        <script src="assets/js/tocify/jquery.tocify.min.js"></script>

        <!-- Imported scripts on this page -->
        <script src="assets/js/jquery-validate/jquery.validate.min.js"></script>

        <!-- JavaScripts initializations and stuff -->
        <script src="assets/js/xenon-custom.js"></script>
        
        <script src="assets/js/jsPDF-1.2.60/dist/jspdf.min.js"></script>        
        <script type="text/javascript">   
            
            function printPDF(){
                
                $.getJSON("GetActivityRegister", {fkPhdStudent: email},
                
                        function (data) { 
                            
                            var doc = new jsPDF(),
                                top = 0,
                                page = 1;
                            data.activities.forEach(function(activity, i){
                                var splitTitle = doc.splitTextToSize("Descrizione: "+ activity.description, 180);
                                
                                switch(true){  
                                    case i === 0:
                                        doc.setFont('times','italic');
                                        doc.setFontSize(9);
                                        doc.text(5,8,'Page '+page);
                                        doc.setFont('times','italic');
                                        doc.setFontSize(22);
                                        doc.text(20,20,"Registro attività di " + firstLastName);
                                        doc.setFontSize(16);                                        
                                        doc.text(20,40, (i+1) +'. ' + activity.name);
                                        doc.setFont('times','regular');
                                        doc.setFontSize(14);
                                        doc.text(20,50, splitTitle);
                                        doc.text(20,(activity.description.length > 120 ? 70 : 60),"Tipologia: " + activity.typology + '\n\n'+
                                            "Inizio: " + activity.startDateTime + '\n\n'+
                                            "Fine: " + activity.endDateTime + '\n\n'+
                                            "Tempo impiegato: " + activity.totalTime + '\n\n'+
                                            "Firma: _____________________"
                                        );
                                        top = 120;
                                        break;
                                    case (i % 3) === 0 && i > 0:
                                        page++;                                        
                                        doc.addPage(); 
                                        doc.setFont('times','italic');
                                        doc.setFontSize(9);
                                        doc.text(5,8,'Page '+page);
                                        doc.setFontSize(16);
                                        doc.text(20,30, (i+1) +'. ' + activity.name);
                                        doc.setFont('times','regular');
                                        doc.setFontSize(14);
                                        doc.text(20,40, splitTitle);
                                        doc.text(20,(activity.description.length > 120 ? 60 : 50),"Tipologia: " + activity.typology + '\n\n'+
                                            "Inizio: " + activity.startDateTime + '\n\n'+
                                            "Fine: " + activity.endDateTime + '\n\n'+
                                            "Tempo impiegato: " + activity.totalTime + '\n\n'+
                                            "Firma: _____________________"
                                        );
                                        top = 120;
                                        break;
                                    default:
                                        doc.setFont('times','italic');
                                        doc.setFontSize(16);
                                        doc.text(20,top, (i+1) +'. ' + activity.name);
                                        doc.setFont('times','regular');
                                        doc.setFontSize(14);
                                        doc.text(20,(top+10), splitTitle);
                                        doc.text(20,(top+(activity.description.length > 120 ? 30 : 20)),"Tipologia: " + activity.typology + '\n\n'+
                                            "Inizio: " + activity.startDateTime + '\n\n'+
                                            "Fine: " + activity.endDateTime + '\n\n'+
                                            "Tempo impiegato: " + activity.totalTime + '\n\n'+
                                            "Firma: _____________________"
                                        );
                                        top = top+90;
                                        break;
                                }
                            });
                            doc.save('Registro Attività.pdf');
                        }); 
            }
            
            function deleteActivity(idActivity) {
                $.getJSON("DeleteActivity", {idActivity: idActivity},
                        function (data) {                                
                        }
                    );                
                //$("#mytable").find("tr:gt(0)").remove();
                $('#mytable tbody').remove();
                location.reload();
            }
            
            function redirectToInsert(){
                $.getJSON("GetActivityRegister", {fkPhdStudent: email},
                
                        function (data) {
                            var totalMinutes = 0;
                            data.activities.forEach(function(activity, i){
                                totalMinutes = totalMinutes + activity.totalTime;
                            });
                            var totalHours = totalMinutes/60; 
                            if(totalHours < 1500){
                                sessionStorage.setItem("totalHours", totalHours);
                                sessionStorage.setItem("insertFlag", true);
                                location.href = 'insertEditActivity.jsp'; 
                            }
                            else{
                                $("#titleInfo").html("");
                                $("#descriptionInfo").html("");
                                $("#infoDialog").modal();
                                $("#titleInfo").html("Errore inserimento evento!");
                                $("#descriptionInfo").html("Hai raggiunto il limite di 1500 ore annue.");
                            }
                        }
                );    
            }
            
            function redirectToEdit(string){
                sessionStorage.setItem("insertFlag", false);
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
                                var row = $("<tr/></tbody>");                             
                                                                
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
                firstLastName = '<%= loggedPerson.getName()%> <%= loggedPerson.getSurname()%>';
                getActivityRegister(email);
            });
        </script>
    </head>
    <div id="infoDialog" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" >&times;</button>
                    <h4 class="modal-title" id="titleInfo"></h4>
                </div>
                
                <div class="modal-body" id="descriptionInfo">
                </div>
            
            </div>
        </div>
    </div>
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
                                <button type="button" class="btn btn-xs" aria-label="Left Align" onclick="redirectToInsert()">
                                    <span id="showArrow" class="glyphicon glyphicon-plus" aria-hidden="true"> </span> Aggiungi attività
                                </button>
                                <button type="button" class="btn btn-xs" aria-label="Left Align" onclick="printPDF()">
                                    <span id="showArrow" class="glyphicon glyphicon-plus" aria-hidden="true"> </span> Stampa PDF
                                </button>
                            </div>
                            <div class="panel-body">
                                <table id="mytable" width="98%" align="center" class="table table-hover" style="table-layout: fixed; width: 100%">
                                    <thead>
                                        <th width="15%">Nome</th>
                                        <th width="28%">Descrizione</th>
                                        <th width="15%">Inizio</th>
                                        <th width="15%">Fine</th>
                                        <th width="10%">Durata (min)</th>
                                        <th width="10%">Tipologia</th>
                                    </thead>
                                </table>
                            </div>
                        </div>
                    </div>                    
                        <div class="col-sm-1">                            
                        </div>
                </div>
            </div>

        </div>                          
    </body>
</html>
