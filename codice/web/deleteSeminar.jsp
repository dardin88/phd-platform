<%-- 
    Document   : deleteSeminar
    Created on : 14-gen-2016, 11.46.24
    Author     : matteo
--%>
<%@page import="it.unisa.dottorato.phdCourse.Seminar"%>
<%@page import="it.unisa.dottorato.phdCourse.CalendarManager"%>
<%@page import="it.unisa.dottorato.phdCourse.Lesson"%>
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

        <title>Elimina Seminario</title>

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
                    <button type="button" class="close" data-dismiss="modal" onclick="history.go(-1);">&times;</button>
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
                                <%
                                       int idSeminar = (Integer.parseInt(request.getParameter("idSeminar2")));
                                       Seminar seminar = CalendarManager.getInstance().getSeminarById(idSeminar);%>
                                <div class="panel panel-default">
                                    <form class="form-horizontal" method="POST" action=<%= "DeleteSeminarServlet?idSeminar=" + idSeminar%>>

                                        <div class="panel-heading">
                                            <h1> Elimina Seminario </h1>
                                            <div class="panel-body">
                                                <div class="panel-heading">
                                                    Sei sicuro di eliminare il seminario <%= seminar.getName()%>?                     
                                                </div>
                                                <div>
                                                    <input type="submit" class="btn btn-blue" value="Si"> 
                                                    <button type="button" class="btn btn-white" onclick="history.go(-1);">No</button>
                                                    <br>
                                                    <br>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>        
            </body>
            <%} else {%>
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
</html>
</html>

