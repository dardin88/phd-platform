<%-- 
    Document   : addevent
    Created on : 8-gen-2016, 10.31.31
    Author     : matteo
--%>
<%@page import="it.unisa.dottorato.account.Account"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:choose>
    <c:when test="${sessionScope.account == null}">
        <c:redirect url="login.jsp" />
    </c:when>
</c:choose>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta name="description" content="Xenon Boostrap Admin Panel" />
        <meta name="author" content="" />

        <title>Aggiungi Evento</title>

        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Arimo:400,700,400italic">
        <link rel="stylesheet" href="assets/css/fonts/linecons/css/linecons.css">
        <link rel="stylesheet" href="assets/css/fonts/fontawesome/css/font-awesome.css">
        <link rel="stylesheet" href="assets/css/bootstrap.css">
        <link rel="stylesheet" href="assets/css/xenon-core.css">
        <link rel="stylesheet" href="assets/css/xenon-forms.css">
        <link rel="stylesheet" href="assets/css/xenon-components.css">
        <link rel="stylesheet" href="assets/css/xenon-skins.css">
        <link rel="stylesheet" href="assets/css/custom.css">
        <link rel="stylesheet" href="style/dottorato.css">

        <script src="assets/js/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="script/aggiungievento.js"></script>
        <script type="text/javascript" src="script/bootstrap-timepicker.js"></script>
        <script type="text/javascript" src="script/bootstrap-timepicker.min.js"></script>
        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
                <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
                <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

    </head>
    <div id="infoDialog" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" onclick="calendario.jsp">&times;</button>
                    <h4 class="modal-title" id="titleInfo"></h4>
                </div>
                
                <div class="modal-body" id="descriptionInfo">
                </div>
            
            </div>
        </div>
    </div>
     <c:choose>
        <c:when test="${sessionScope.account != null}">
                    <% Account loggedPerson = ((Account) session.getAttribute("account"));
                        if (loggedPerson.isAdmin() || loggedPerson.getTypeAccount().equals("professor")) {
                    %> 
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
                                <h1> Aggiungi Evento</h1>
                            </div>
                            <div class="panel-body">
                                <!--<form class="form-horizontal" method="POST" action="AddCourseServlet">-->
                                    <div class="form-group">
                                        <table width="90%" align="center">
                                            <tr><p>Tipo Evento:</p>
                                                    <div class="input-group">
                                                            <label for="sel1">Seleziona il tipo di evento</label>
                                                                    <select class="form-control" id="typeEvent" onchange="selectedTypeEvent()">
                                                                           <option class='optionItem' value='default' >  - seleziona -   </option>
                                                                           <option class='optionItem' value='lezione' >   Lezione   </option>
                                                                           <option class='optionItem' value='seminario' >   Seminario   </option>
                                                                    </select>
                                                    
                                                            <div class="panel panel-default " id="descriptionPanelLesson" hidden="true" style="margin-top: 5px">
                                                                    <div class="panel-heading">
                                                                         Nuova lezione                      
                                                                    </div>
                                                                    <p>Nome Lezione:</p>
                                                                    <div class="input-group">
                                                                        <span class="input-group-addon"></span>
                                                                        <input class="form-control" name="name" type="text" id="nomelezione" required>
                                                                    </div>
                                                                    <br>
                                                                    <br>                                     
                                                                    <p>Descrizione:</p>
                                                                    <div class="input-group">
                                                                        <span class="input-group-addon"></span>
                                                                        <textarea class="form-control" name="description" rows="5" cols="40" id="description"> </textarea>
                                                                    </div>
                                                                    <br>                          
                                                                    <br>
                                                                    <p>Classe</p>
                                                                    <div class="input-group">
                                                                        <span class="input-group-addon"></span>
                                                                        <textarea class="form-control" name="class" rows="5" cols="40" id="class"> </textarea>
                                                                    </div>
                                                                    <br>                          
                                                                    <br>
                                                                    <p>Corso:</p>
                                                                    <div class="form-group">
                                                                        <select class="form-control" name="fkCourse" id="courselist1"  required> 
                                                                                    
                                                                        </select>
                                                                    </div>
                                                                    <br>
                                                                    <br>
                                                                    <p>Data :</p>
                                                                    <div class="input-group">
                                                                            <span class="input-group-addon"></span>
                                                                            <input class="form-control" id="date" name="date" type="date" placeholder="aaaa-mm-gg" required>
                                                                    </div>
                                                                    <br>
                                                                    <br>
                                                                    <p> Orario di Inizio:</p>
                                                                    <div class="input-group bootstrap-timepicker timepicker">
                                                                        <input id="timepicker1" name="startTime" type="text" class="form-control input-small">
                                                                    </div>
                                                                    <br>
                                                                    <br>
                                                                    <p> Orario di Fine:</p>
                                                                    <div class="input-group bootstrap-timepicker timepicker">
                                                                        <input id="timepicker2" name ="endTime" type="text" class="form-control input-small">
                                                                    </div>
                                                                    <br>
                                                                    <br>
                                                                    <script type="text/javascript">
                                                                        $('#timepicker1').timepicker();
                                                                    </script>
                                                                    <script type="text/javascript">
                                                                            $('#timepicker2').timepicker();
                                                                    </script>
                                                           
                                                    

                                                                    <div>
                                                                        <input type="submit" class="btn btn-blue" value="Inserisci" onclick="insertLesson()"> 
                                                                        <br>
                                                                        <br>
                                                                    </div>
                                                                
                                                            </div>
                                                        
                                                        
                                                        
                                                            <div class="panel panel-default " id="descriptionPanelSeminar" hidden="true" style="margin-top: 5px">
                                                            <div class="panel-heading">
                                                            Nuovo seminario
                                                                                         
                                                            </div>
                                                            <p>Nome Seminario:</p>
                                                            <div class="input-group">
                                                                <span class="input-group-addon"></span>
                                                                <input class="form-control" name="name" type="text" id="nameseminar" required>
                                                            </div>
                                                            <br>
                                                            <br>
                                                            <p>Descrizione del Seminario:</p>
                                                            <div class="input-group">
                                                                <span class="input-group-addon"></span>
                                                                <textarea class="form-control" name="description" id="description" rows="5" cols="40"> </textarea>
                                                            </div>
                                                            <br>                      
                                                            <br>
                                                            
                                                                   
                                                            <p>Nome Speaker:</p>
                                                            <div class="input-group">
                                                                <span class="input-group-addon"></span>
                                                                <input class="form-control" name="nameSpeacker" id="nomespeacker" type="text" required>
                                                            </div>
                                                            <br>
                                                            <br>     
                                                            <p>Nome Luogo:</p>
                                                            <div class="input-group">
                                                                <span class="input-group-addon"></span>
                                                                <input class="form-control" name="place" id="place" type="text" required>
                                                            </div>
                                                            <br>
                                                            <br>
                                                            <p>Corso:</p>
                                                            <div class="form-group">
                                                                        <select class="form-control" name="fkCourse" id="courselist2"   required> 
                                                                           
                                                                        </select>
                                                                    </div>
                                                                    <br>
                                                                    <br>
                                                            <p>Data :</p>
                                                            <div class="input-group">
                                                                <span class="input-group-addon"></span>
                                                                <input class="form-control" id="dateseminar" name="date" type="date" placeholder="aaaa-mm-gg" required>
                                                            </div>
                                                            <br>
                                                            <br>
                                                            <p> Orario di Inizio:</p>
                                                            <div class="input-group bootstrap-timepicker timepicker">
                                                                <input id="timepicker3" name="startTime" type="text" class="form-control input-small">
                                                            </div>
                                                            <br>    
                                                            <br>
                                                            <p> Orario di Fine:</p>
                                                            <div class="input-group bootstrap-timepicker timepicker">
                                                                <input id="timepicker4" name ="endTime" type="text" class="form-control input-small">
                                                            </div>
                                                            <br>
                                                            <br>
                                                            <script type="text/javascript">
                                                                $('#timepicker3').timepicker();
                                                            </script>
                                                            <script type="text/javascript">
                                                                $('#timepicker4').timepicker();
                                                            </script>
                                                        <div>
                                                        <input type="submit" class="btn btn-blue" value="Inserisci" onclick="insertSeminar()"> 
                                                        <br>
                                                        <br>
                                                        </div>
                                                        
                                                    </div>
                                                </div>
                                            
                                    
                                    <br>
                                    <br>
                                    </tr>
                                    </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>    
    </body>
    <%}
        else{%>
                <c:redirect url="index.jsp" />
          <%  }
    %>
            </c:when>
        </c:choose>
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
</html>
