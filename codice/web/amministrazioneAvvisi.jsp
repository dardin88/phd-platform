<%-- 
    Document   : amministrazioneCurriculum
    Created on : 12-dic-2015, 20.45.01
    Author     : andre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Xenon Boostrap Admin Panel" />
        <meta name="author" content="Andrea Fedele" />
        
        <title>Amministrazione Avvisi</title>
   
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
        <script type="text/javascript" src="script/amministrazione.js"></script> <!-- da modificare -->
                                                                                 
    </head>
    
        <body class="page-body">
            <!-- Inclusione della pagina contenente il menù superiore 
            <jsp:include page="barraMenu.jsp"/><!--da modificare con la nuova -->
            <div class="page-container">
        
            <!-- Inclusione della pagina contenente il menù laterale --> 
            <jsp:include page="leftBar.jsp"/>  
	
                <div class="main-content" id="content">
                    <div class="row">
                        <div class="panel-heading">
                            <h1>Gestione Avvisi</h1> 
                        </div>
                    </div>
                    
                    
                    
                    <div class="row" style="margin-left:5px " >    
                            <button type="button" class="btn btn-default btn-secondary " >
                                <span class="glyphicon glyphicon-plus" aria-hidden="true" ></span>
                                Aggiungi Avviso
                            </button> 
                    </div>
                    
                    
                    <div class="row" style="margin-top: 10px">
                        <div class="panel panel-default">
                            <!-- Default panel contents -->
                              
                                 <div class="panel-body">
                                 <!-- Table -->
                                    <table class="table">
                                        <thead>
                                                <tr>
                                                    <th>Titolo</th>
                                                    <th>Visualizza</th>
                                                    <th>Elimina</th>
                                                    <th>Modifica</th>
                                                </tr>
                                        </thead>
                                    </table>
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
