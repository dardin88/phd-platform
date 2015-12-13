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
        
        <title>Amministrazione Account</title>
   
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Arimo:400,700,400italic">
        <link rel="stylesheet" href="../assets/css/fonts/linecons/css/linecons.css">
        <link rel="stylesheet" href="../assets/css/fonts/fontawesome/css/font-awesome.css">
        <link rel="stylesheet" href="../assets/css/bootstrap.css">
        <link rel="stylesheet" href="../assets/css/xenon-core.css">
        <link rel="stylesheet" href="../assets/css/xenon-forms.css">
        <link rel="stylesheet" href="../assets/css/xenon-components.css">
        <link rel="stylesheet" href="../assets/css/xenon-skins.css">
        <link rel="stylesheet" href="../assets/css/custom.css">  
        <link rel="stylesheet" href="style/dottorato.css">
        
        <script src="../assets/js/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="script/amministrazione.js"></script> <!-- da modificare -->
                                                                                 
    </head>
    
        <body class="page-body">
            <!-- Inclusione della pagina contenente il menù superiore -->
            <div class="page-container">
        
            <!-- Inclusione della pagina contenente il menù laterale --> 
            <jsp:include page="leftBar.jsp"/>  
            
                <div class="main-content" id="content">
                    <div class="row">
                        <div class="panel-heading">
                            <h1>Gestione Account</h1> 
                        </div>
                    </div>    
                    
            <!-- Barra per filtrare la ricerca -->  
            
                    <div class="row" style="margin-top: 5px;">
                         <div class="col-lg-6">
                            <div class="input-group">
                               <input type="text" class="form-control" placeholder="ricerca utente...">
                                 <span class="input-group-btn">
                                     <button class="btn btn-default" type="button">Cerca</button>
                                 </span>
                            </div><!-- /input-group -->
                         </div><!-- /.col-lg-6 -->
                    </div>
                    
                    <div class="row" style="margin-top: 10px">
                        <div class="panel panel-default">
                            <!-- Default panel contents -->
                              <div class="panel-heading">Lista utenti</div>
                                 <div class="panel-body">
                                 <!-- Table -->
                                    <table class="table">
                                        <thead>
                                                <tr>
                                                    <th>Nome</th>
                                                    <th>Tipologia</th>
                                                    <th>Email</th>
                                                    <th>Stato</th>
                                                </tr>
                                        </thead>
                                    </table>
                                 </div>
                                 <div class="panel-footer" style="background-color: transparent">
                                <button type="button" class="btn btn-default btn-orange "  style="margin-top: 10px">
                                <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
                                Cambia lo stato
                            </button> 
                            <button type="button" class="btn btn-default btn-orange " style="margin-top: 10px">
                                <span class="glyphicon glyphicon-edit" aria-hidden="true" ></span>
                                Cambia tipologia
                            </button> 
                            </div>
                        </div>
                    </div>
                </div> 
            </div> 

      
    <!-- Bottom Scripts -->
            <script src="../assets/js/bootstrap.min.js"></script>
            <script src="../assets/js/TweenMax.min.js"></script>
            <script src="../assets/js/resizeable.js"></script>
            <script src="../assets/js/joinable.js"></script>
            <script src="../assets/js/xenon-api.js"></script>
            <script src="../assets/js/xenon-toggles.js"></script>

            <!-- JavaScripts initializations and stuff -->
            <script src="../assets/js/xenon-custom.js"></script>     
    </body>          
</html>
