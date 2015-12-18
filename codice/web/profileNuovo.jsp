<%-- 
    Document   : profile
    Created on : 25-dic-2014, 11.54.56
    Author     : gemmacatolino
--%>

<%@page import="it.unisa.dottorato.account.*;"%>
<%@page import="it.unisa.dottorato.Cycle.*;"%>
<%@page import="it.unisa.dottorato.Curriculum.*;"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
                <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
                <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]--> 

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
                        <!--Qui chiama servlet update che prende infomazioni person--> 
                        <% Account loggedPerson = ((Account) session.getAttribute("account"));
                          
                        %>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h1> <%= loggedPerson.getName()%> <%= loggedPerson.getSurname() %>  <span class="glyphicon glyphicon-cog pointer" aria-hidden="true" onclick="location.href = 'editProfile.jsp'" style="float: right; display: inline;"></span></h1>
                                 <%  if(loggedPerson.getTypeAccount().equals("phdstudent")) { %>
                                 <h4> <%= ((PhdStudent)loggedPerson).getfkCycle()%>° ciclo di dottorato in <%= ((PhdStudent)loggedPerson).getfkCurriculum()%></h4>
                               <% } else if(loggedPerson.getTypeAccount().equals("professor")) { %>
                                     <h4><%= ((Professor)loggedPerson).getDepartment()%></h4>
                                   <% } %>
                            </div>
                            <div class="panel-body">
                                <table width="97%" align="center">
                                    <tr>
                                        <td width="180px" >
                                            <img class="img-polaroid" style='width: 150px ; height: 150px ;' src="Immagini/scam_facebook_fake_tutela_amici.jpg" alt="nome immagine" >
                                        </td>
                                        
                                       <% if(loggedPerson.getTypeAccount().equals("basic")) { %>
                                       <td>
                                          <h3> Contatti </h3>
                                          E-mail: <%= loggedPerson.getSecondaryEmail()%> <br>
                                          
                                          <% } %>
                                       <% if(loggedPerson.getTypeAccount().equals("phdstudent")) { %>
                                        <td>
                                            <h3> Contatti </h3>
                                            <p> 
                                                Telefono: <%= ((PhdStudent)loggedPerson).getTelephone()%> <br>
                                                E-mail: <%= loggedPerson.getSecondaryEmail()%> <br>
                                                <% if (((PhdStudent)loggedPerson).getLink() != null) {%>
                                                Sito web: <a href="<%= ((PhdStudent)loggedPerson).getLink()%>" target="_blank"><%= ((PhdStudent)loggedPerson).getLink()%></a> <br>
                                                <%}
                                                    if (((PhdStudent)loggedPerson).getDepartment() != null) {%>
                                                <%= ((PhdStudent)loggedPerson).getDepartment() %> <br>
                                                <% }%>
                                            

                                            </p>
                                        </td>
                                         <% } %>
                                        <% if(loggedPerson.getTypeAccount().equals("professor")) { %>
                                            <td>
                                                <h3> Contatti</h3>
                                            <p> 
                     
                                                E-mail: <%= loggedPerson.getSecondaryEmail()%> <br>
                                                <% if (((Professor)loggedPerson).getLink() != null) {%>
                                                Sito web: <a href="<%= ((Professor)loggedPerson).getLink()%>" target="_blank"><%= ((Professor)loggedPerson).getLink()%></a> <br>
                                                <%}
                                                    if (((Professor)loggedPerson).getDepartment() != null) {%>
                                                <%= ((Professor)loggedPerson).getDepartment() %> <br>
                                                <% }%>

                                            </p>
                                            </td>
                                        <% } %>
                                            
                                    </tr>
                                    
                                    <% if(loggedPerson.getTypeAccount().equals("phdstudent")) { %>
                                    <tr>
                                        <td colspan="2">
                                            <br>
                                            <h3> Attività di ricerca </h3> <br>
                                            <p class="text-justify" > 
                                                <%= ((PhdStudent)loggedPerson).getResearchInterest()%> <br> <br>
                                            </p>

                                            <p class="text-justify"> 
                                            <h3>Registro attività: <span class="glyphicon glyphicon-eye-open pointer" aria-hidden="true" onclick="location.href = 'publicationActivity.jsp'"></span></h3>
                                            </p>
                                        </td>
                                    </tr>
                                  <% } %>
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