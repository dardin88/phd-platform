<%-- 
    Document   : yearlySummary
    Created on : 27-mag-2016, 12.16.47
    Author     : Liliana
--%>

<%@page import="it.unisa.dottorato.account.PhdStudent"%>
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
        <link rel="stylesheet" href="style/dottorato.css">

        <script src="assets/js/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="script/index.js"></script>
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
        <script src="assets/js/jsPDF-1.2.60/autotablePlugin.js"></script>
        <script type="text/javascript" src="script/yearlySummaryPDF.js"></script>
        <script type="text/javascript" src="script/yearlySummary.js"></script>
        
        <style> 
        .scrollableContainer {
            height: 300px; 
            overflow-y: scroll;
            display:block;
            word-wrap:break-word;
            width: 100%;
        }
        table th{
            width: auto;
        }
        </style>
    </head>
    <c:choose>
        <c:when test="${sessionScope.account != null}">
            <% PhdStudent loggedPerson = ((PhdStudent) session.getAttribute("account"));
                if (loggedPerson.getTypeAccount().equals("phdstudent")) {
            %>
            <body class="page-body">
                <!-- Inclusione della pagina contenente il menù superiore -->
                <jsp:include page="barraMenu.jsp" flush="true"/>
                <div class="page-container">
                    <!-- Contenuto della pagina -->
                    <div class="main-content" id="content">
                        <div class="row">
                            <div class="col-sm-1"></div>
                            <div class="col-sm-10">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h1> Relazione di Fino Anno <span id="year"></span></h1>
                                    </div>
                                    <div class="panel-body">
                                        <div>
                                            <div id="courseSeminar">
                                                <h1>Attività di formazione relativa a corsi e seminari seguiti. </h1>
                                                    <h2>Corsi & Seminari</h2>
                                                    <table id="courseSeminarTable" align="center" class="table table-hover scrollableContainer" style="table-layout: fixed;">
                                                        <tbody id="courseSeminarTBody"></tbody>
                                                    </table>
                                            </div>
                                            <div id="publication">
                                                <h1>Attività di studio e ricerca con riferimento ad eventuali pubblicazioni prodotte</h1>
                                                    <h2>Pubblicazioni</h2>
                                                        <table id="publicationTable" class="table table-hover scrollableContainer" style="width: auto;">
                                                            <tbody id="publicationTBody"></tbody>
                                                        </table>
                                            </div>
                                            <div id="mission">
                                                    <h2>Missioni</h2>
                                                        <table id="missionTable" align="center" class="table table-hover scrollableContainer">
                                                            <tbody id="missionTBody"></tbody>
                                                        </table>
                                            </div>
                                            <div id="collaboration">
                                                    <h2>Collaborazioni</h2>
                                                        <table id="collaborationTable" align="center" class="table table-hover">
                                                            <tbody id="collaborationTBody"></tbody>
                                                        </table>
                                           </div>                           
                                            <div id="otherSchool">
                                                <h1>Partecipazione a scuole di dottorato e convegni, indicando se anche in qualita' di relatore</h1>
                                                    <button type="button" class="btn btn-white plusButton" title="add" >
                                                        <span class="glyphicon glyphicon-plus" aria-hidden="true" > Aggiungi Riga </span>
                                                    </button>
                                                    <table id="convegni" class="table table-hover scrollableContainer">
                                                    </table>
                                            </div>
                                            <div id="otherActivity">
                                                <h1>Eventuali altre attivita'</h1>
                                                <h2>ad es. membro di comitato di programma, membro di segreteria scientifica di scuole di dottorato, attività di revisione di articoli scientifici, etc.</h2>
                                                    <button type="button" class="btn btn-white plusButton" title="add" >
                                                        <span class="glyphicon glyphicon-plus" aria-hidden="true" > Aggiungi Atre Attività</span>
                                                    </button>
                                                    <table id="altreAttivita" class="table table-hover scrollableContainer">
                                                    </table>
                                            </div>
                                            <p style ='text-align: center;margin-top: 10%'>
                                                <button type="button" class="btn btn-white" onclick="createPDFJson('<%= loggedPerson.getName()%>','<%= loggedPerson.getSurname()%>','<%= loggedPerson.getSecondaryEmail()%>','<%= loggedPerson.getfkCurriculum()%>')">
                                                    <span id="showArrow" class="glyphicon glyphicon-download-alt" aria-hidden="true"> Stampa PDF </span> 
                                                </button>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-1"></div>
                        </div>
                    </div>
                </div>
            </body>
            <%}else{%>
                <c:redirect url="index.jsp" />
            <%  } %>
        </c:when>
    </c:choose>
</html>
