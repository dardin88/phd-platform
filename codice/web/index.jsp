<%@page import="java.util.List"%>
<%@page import="it.unisa.dottorato.Cycle.Cycle"%>
<%@page import="it.unisa.dottorato.Cycle.CycleManager"%>
<%@page import="it.unisa.dottorato.Cycle.CycleManager"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.unisa.dottorato.account.AccountManager"%>
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

        <title>DISTRA-MIT</title>

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
        <% List<Cycle> cycles = CycleManager.getInstance().getCycleList();
            
            %>
      

        <div class="page-container">
            <div class="main-content">
                <div class="row">
                    <!--<div class="col-sm-1"></div>-->
                    <div class="col-sm-6">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                Benvenuto su PhD-Platform!
                            </div>
                            <div class="panel-body">
                               <% for(Cycle rcycles : cycles) {%>
                                <p><%=rcycles.getDescription()%></p>
                                <% break; }%>

                            </div>
                        </div>
                    </div>
                    
                    <div class="col-md-6">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                prova ciclo
                            </div>
                            <div class="form-group">
                                <label for="sel1">Seleziona uno dei cicli del dottorato di ricerca</label>
                                    <select class="form-control" id="CycleList" onclick="getCurriculumCicList()">
                                        
                                    </select>
                            </div>
                            
                            <div class="form-group">
                                <label for="sel1">Seleziona uno dei curriculum o tutti </label>
                                <select class="form-control" id="CurriculumCicList" <--onclick="selectedItem()"-->>
                                     <option class='optionItem' value='default' >  - seleziona -   </option>    
                                    </select>
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
