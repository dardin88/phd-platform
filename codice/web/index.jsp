<%@page import="it.unisa.dottorato.news.NewsManager"%>
<%@page import="it.unisa.dottorato.news.News"%>
<%@page import="java.util.List"%>
<%@page import="it.unisa.dottorato.Cycle.*"%>
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
        <% List<Cycle> cycles = CycleManager.getInstance().getCycleList();
            List<News> notizie = NewsManager.getInstance().getAllNews();
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
                                <div class="panel-heading" id="descrizioneCiclo">
                                  
                                </div>
                               
                                
                                

                            </div>
                        </div>
                    </div>
                    
                    <div class="col-md-6">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                Cicli - Curriculum
                            </div>
                            <div class="form-group">
                                <label for="sel1">Seleziona uno dei cicli del dottorato di ricerca</label>
                                    <select class="form-control" id="CycleList" onchange="getCurriculumCicList()" onchange="getInfoCycle()">
                                        
                                    </select>
                            </div>
                            
                            <div class="form-group">
                                <label for="sel1">Seleziona uno dei curriculum </label>
                                <select class="form-control" id="CurriculumCicList" onchange="viewInfoCurriculumCic()">
                                     <option class='optionItem' value='default' >  - seleziona -   </option>    
                                    </select>
                            </div>
                            <div class="form-group">
                                
                                    <div class="panel panel-default " id="descriptionPanel" hidden="true" style="margin-top: 5px">
                                         <div class="panel-heading">
                                             Hai selezionato:
                                                                                         
                                        </div>
                                        <div class="panel-body">
                                            Ciclo: <span style="text-justify: distribute" id="CycleNumber"></span><br>
                                            Curriculum: <span style="text-justify: distribute" id="CurriculumName"></span><br>
                                            Coordinatore del ciclo: <span style="text-justify: auto" id="CoordinatorName"><br>
                                        </div>
                                        <div class="panel-footer " style="background-color: transparent">
                                            <div class="col-sm-5">
                                                Docenti:<br><span style="text-justify: auto" id="ProfessorOfCVCic">
                                            </div>
                                            <div class="col-md-5">
                                                Dottorandi:<br><span style="text-justify: auto" id="StudentOfCVCic">
                                            </div>
                                        </div>
                                    </div>
                                
                            </div>
                        </div>
                    </div>
                </div>               
                 <div class="row">
                    <div class="col-sm-6">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                Avvisi
                            </div>
                            <div class="panel-body">
                               <% for(News rnotizie : notizie) {
                               String news = new String (rnotizie.getDescription());
                               int idNews= rnotizie.getId();
                               news =news.substring(0, 30);
                               %>
                               <a href="viewNewsById.jsp?Id=<%=idNews%>" id="newsid" class="link"><p><%=news%>...</p></a>
                                <% } %>
                            </div>
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
