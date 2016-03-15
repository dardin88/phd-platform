<%@page import="it.unisa.dottorato.news.NewsManager"%>
<%@page import="it.unisa.dottorato.news.News"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta name="description" content="Xenon Boostrap Admin Panel" />
        <meta name="author" content="matteonardone" />

        <title>PhD-Platform</title>

        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Arimo:400,700,400italic">
        <link rel="stylesheet" href="assets/css/fonts/linecons/css/linecons.css">
        <link rel="stylesheet" href="assets/css/fonts/fontawesome/css/font-awesome.css">
        <link rel="stylesheet" href="assets/css/bootstrap.css">
        <link rel="stylesheet" href="assets/css/xenon-core.css">
        <link rel="stylesheet" href="assets/css/xenon-forms.css">
        <link rel="stylesheet" href="assets/css/xenon-components.css">
        <link rel="stylesheet" href="assets/css/xenon-skins.css">
        <link rel="stylesheet" href="assets/css/custom.css">

        <script src="assets/js/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="script/index.js"></script>
        
    </head>
    <body class="page-body">

        <jsp:include page="barraMenu.jsp" flush="true"/>

        <!--BODY-->
        <%
            String d = request.getParameter("Id");
            News notizia = NewsManager.getInstance().getNewsById(Integer.parseInt(d));
            
        %>
      

        <div class="page-container">
            <div class="main-content">
                <div class="row">
                    <!--<div class="col-sm-1"></div>-->
                    <div class="col-sm-6">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <p><%=notizia.getTitle()%></p>
                                  
                            </div>
                            <div class="panel-body">
                                <p><%=notizia.getDescription()%><br><br></p>

                            </div>
                                <div class="panel-footer " style="background-color: transparent">
                                    <button type="back"  id="indietro"  class="btn btn-white" onclick="history.go(-1);">Indietro</button>
                                </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
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