<%-- 
    Document   : amministrazioneCurriculum
    Created on : 12-dic-2015, 20.45.01
    Author     : andre
--%>
<%@page import="it.unisa.dottorato.account.Account"%>
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

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Xenon Boostrap Admin Panel" />
        <meta name="author" content="Andrea Fedele" />

        <title>Amministrazione Tutorato</title>

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
        <script type="text/javascript" src="script/amministrazioneTutorato.js"></script> <!-- da modificare -->

    </head>
    <c:choose>
        <c:when test="${sessionScope.account != null}">
                    <% Account loggedPerson = ((Account) session.getAttribute("account"));
                        if (loggedPerson.isAdmin()) {
                    %> 
                    
    <div id="ModificaDialog" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content ">
                <div class="modal-header  ">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title"> <span class="glyphicon glyphicon-ok  " style="color: #00e367"   aria-hidden="true"></span>Operazione effettuata con successo.</h4>
                </div>
                <div class="modal-body ">
                    <p>
                        La modifica è avvenuta con successo.<br>
                    </p>
                </div>
            </div>
        </div>
    </div>
                    
    <div id="CancellazioneDialog" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content ">
                <div class="modal-header  ">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title"> <span class="glyphicon glyphicon-ok  " style="color: #00e367"    aria-hidden="true"></span>Operazione effettuata con successo.</h4>
                </div>
                <div class="modal-body ">
                    <p>
                        La cancellazione è avvenuta con successo.<br>
                    </p>
                </div>
            </div>
        </div>
    </div> 
           
    <div id="ErroreDialog" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content ">
                <div class="modal-header  ">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title"> <span class="glyphicon glyphicon glyphicon-warning-sign" style="color: #FFA54E"   aria-hidden="true"></span>Attenzione!</h4>
                </div>
                <div class="modal-body ">
                    <p>
                        Hai selezionato il tutor attuale.<br>
                    </p>
                </div>
            </div>
        </div>
    </div>
                    
                    
    <body class="page-body">
        <!-- Inclusione della pagina contenente il menù superiore -->
        <jsp:include page="barraMenu.jsp"/><!--da modificare con la nuova -->
        <div class="page-container">

            <!-- Inclusione della pagina contenente il menù laterale --> 
            <jsp:include page="leftBar.jsp"/>  

            <div class="main-content" id="content">

                <div class="row">
                    <div class="panel-heading">
                        <h1>Gestione Tutorato</h1> 
                    </div>
                </div>

                <div class="row">
                    <div class=" col-lg-4 col-lg-offset-0 col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-xs-12">
                        <div class="form-group">
                            <label for="sel1">Seleziona il dottorando interessato:</label>
                            <select class="form-control" id="phdStudentsList" onclick="selectedItem()">
                                <option value="default" > - seleziona - </option>
                            </select>
                        </div>
                    </div>

                    <div class="col-lg-8 col-lg-offset-0 col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-xs-12">    
                        <div class="panel panel-default" id="panelDiv" hidden>
                            <div class="panel-heading">
                                <i class="panel-title">Tutor assegnato: </i> 
                                <span style="margin-left: 100px" id="TutorNameField"  > </span>
                                <div>
                                <button  type="button" class="btn btn-default btn-red btn-block" id="removeTutorButton" onclick="removeTutorButton()">
                                        <span class="glyphicon glyphicon-remove-sign" aria-hidden="true" ></span> Rimuovi tutor
                                    </button> 
                                </div>
                            </div>
                            <div class="panel-body">
                                <table class="table" >
                                    <thead>
                                        <tr>
                                            <th>Nome</th>
                                            <th>Cognome</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody id="professorListTable"> </tbody>
                                </table>
                                <div class="panel-footer" style="background-color: transparent">
                                    <p class="text-center"> -- Seleziona il tutor -- 
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div> 
        </div> 


            <%}else{%>
                <c:redirect url="index.jsp" />
          <%  }
    %>
            </c:when>
        </c:choose>
        <!-- Bottom Scripts -->
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/TweenMax.min.js"></script>
        <script src="assets/js/resizeable.js"></script>
        <script src="assets/js/joinable.js"></script>
        <script src="assets/js/xenon-api.js"></script>
        <script src="assets/js/xenon-toggles.js"></script>

        <!-- JavaScripts initializations and stuff -->
        <script src="assets/js/xenon-custom.js"></script>     
    </body>          
</html>
