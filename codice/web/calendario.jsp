<%-- 
    Document   : calendario
    Created on : 15-dic-2015, 16.52.45
    Author     : Rembor
--%>

<%@page import="it.unisa.dottorato.account.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<c:choose>
    <c:when test="${sessionScope.account == null}">
        <c:redirect url="login.jsp" />
    </c:when>
</c:choose>
<html>
    <head>
     
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Xenon Boostrap Admin Panel" />
        <meta name="author" content="Matteo Nardone" />
        <title>Calendario</title>


        
       <!-- Bootstrap e i fogli di stile vado a fiducia  -->
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
        <!--<link rel="stylesheet" href="http://getbootstrap.com/dist/css/bootstrap.min.css" >-->
	<link rel="stylesheet" href="http://getbootstrap.com/assets/css/docs.min.css">
	<link rel="stylesheet" href="assets/js/calendar/jin.calendar.style.css">
       
        <script type="text/javascript" src="assets/js/calendar/jin.calendar.js"></script>
        <script src="assets/js/jquery-1.11.1.min.js"></script>
        <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
  
    
    </head>
    <!-- MODAL PER CORSI -->
    <div id="infoDialog" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title" id="titleInfo"></h4>
                </div>
                
                <div class="modal-body" id="descriptionInfo">
                </div>
            </div>
        </div>
    </div>
    <!-- MODAL PER LEZIONI -->
    <div id="infoDialog1" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title" id="titleInfo1"></h4>
                </div>
                
                <div class="modal-body" id="descriptionInfo1">
                </div>
                <% Account loggedPerson = ((Account) session.getAttribute("account"));
                        if (loggedPerson.isAdmin() || loggedPerson.getTypeAccount().equals("professor")) {
                         %>
                          <form class="form-inline" name ="lessonform" method="GET" action="editLesson.jsp">
                              <input type='hidden' name="idLesson" id="idLess" >
                              <input type="submit" class="btn btn-blue" value="Modifica">
                          </form>
                          <form class="form-inline" name ="lessonform2" method="GET" action="deleteLesson.jsp">
                              <input type='hidden' name="idLesson2" id="idLess2" >
                              <input type="submit" class="btn btn-white" value="Elimina">
                          </form>
                        <% } %>
            
            </div>
        </div>
    </div>
    <!-- MODAL PER SEMINARI -->
    <div id="infoDialog2" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title" id="titleInfo2"></h4>
                </div>
                
                <div class="modal-body" id="descriptionInfo2">
                </div>
                <% 
                        if (loggedPerson.isAdmin() || loggedPerson.getTypeAccount().equals("professor")) {
                         %>
                          <form class="form-inline" name ="seminarform" method="GET" action="editSeminar.jsp">
                              <input type='hidden' name="idSeminar" id="idSemi" >
                              <input type="submit" class="btn btn-blue" value="Modifica">
                          </form>
                          <form class="form-inline" name ="seminarform2" method="GET" action="deleteSeminar.jsp">
                              <input type='hidden' name="idSeminar2" id="idSemi2" >
                              <input type="submit" class="btn btn-white" value="Elimina">
                          </form>
                        <% } %>
            
            </div>
        </div>
    </div>
    <body class="page-body">
        <jsp:include page="barraMenu.jsp"/>
        <div class="page-container"> 
            <div class="main-content" id="content">

                <div class="row">
                    <div class="panel panel-default">
                    <div class="panel-heading">
                        <h1>Calendario </h1> 
                        <%
                        if (loggedPerson.isAdmin() || loggedPerson.getTypeAccount().equals("professor")) {
                         %>  
                        <ul class="nav navbar-nav">
                            <li class="active"><a href="addcorso.jsp">Aggiungi Corso</a></li>
                            <li><a href="addevent.jsp">Aggiungi Eventi</a></li>
                            
                        </ul>
                        <% } %>
                            
                    </div>
                
        
         
        

                <div class="div-month row">
                    <div class="col-xs-3 text-right">
			<h4>
			<button id="btn-prev" type="button" class="btn btn-default" onclick="prev()">
				<span class="glyphicon glyphicon-circle-arrow-left"></span> Prev
			</button>
			</h4>
                    </div>
		
                    <div class="col-xs-6">
			<h4 class="h4-month text-center"></h4>
                    </div>
		
                    <div class="col-xs-3 text-left">
			<h4>
			<button id="btn-next" type="button" class="btn btn-default" onclick="next()">
				Next <span class="glyphicon glyphicon-circle-arrow-right"></span>
			</button>
			</h4>
                    </div>
                </div>

                <div class="div-main">
                    <div class="div-calendar row">
			<div class="div-init col-sm-12"></div>
                    </div>
                </div>

                <script>
                    var tipo ='<%=loggedPerson.getTypeAccount()%>';
                    var emailPrimaria ='<%=loggedPerson.getEmail()%>';
                    jinCalendar.init(tipo, emailPrimaria);

		
                </script>
    
                </div>
            </div>
        
</body>


</html>
