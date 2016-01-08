<%-- 
    Document   : amministrazioneCurriculum
    Created on : 12-dic-2015, 20.45.01
    Author     : andre
--%>

<%@page import="it.unisa.dottorato.account.Account"%>
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
        <link rel="stylesheet" href="assets/css/fonts/linecons/css/linecons.css">
        <link rel="stylesheet" href="assets/css/fonts/fontawesome/css/font-awesome.css">
        <link rel="stylesheet" href="assets/css/bootstrap.css">
        <link rel="stylesheet" href="assets/css/xenon-core.css">
        <link rel="stylesheet" href="assets/css/xenon-forms.css">
        <link rel="stylesheet" href="assets/css/xenon-components.css">
        <link rel="stylesheet" href="assets/css/xenon-skins.css">
        <link rel="stylesheet" href="assets/css/custom.css">  
        <link rel="stylesheet" href="style/dottorato.css">
        
        
        <style>
            #bodyTable {
                color:black;
            }
            
            #emailInput {
                width:25%;
            }
            
            #spanSpace {
               visibility: hidden;
            }
        </style>
        
        <script src="assets/js/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="script/amministrazioneAccount.js"></script> <!-- da modificare -->
                
                                                                                 
    </head>
    
        <body class="page-body">
            <!-- Inclusione della pagina contenente il menù superiore -->
            <jsp:include page="barraMenu.jsp"/><!--da modificare con la nuova -->
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
                            <div class="input-group" style="width: 100%;">
                               <input type="text" class="form-control" id = "word" placeholder="Cerca un utente (inizia a digitare)">
                                </div><!-- /input-group -->
                         </div><!-- /.col-lg-6 -->
                    </div>
                    
                    <div class="row" style="margin-top: 10px">
                        <div class="panel panel-default">
                            <!-- Default panel contents -->
                              <div class="panel-heading">Lista utenti</div>
                                 <div class="panel-body">
                                 <!-- Table -->
                                    <table class="table table-hover table-striped table-condensed" id = "accountListTable">
                                        <thead>
                                                <tr>
                                                    <th>Nome</th>
                                                    <th>Email</th>
                                                    <th>Tipologia</th>
                                                    <th> </th>
                                                    
                                                </tr>                               
                                        </thead>
                                        <tbody id="bodyTable">
                                            
                                        </tbody>
                                    </table>
                                 </div>
                                 <br>
                                 <br>
                                 <div class="panel-footer" id="changeDiv" style="background-color: transparent">
                                     
                                     <h2>Hai selezionato:<span id="selectedName"></span></h2>
                                     <select value="type" id="typeSelect">
                                         <option selected> Seleziona una tipologia </option>
                                     </select>
                                     <br>
                                     <br>
                                     <input type="checkbox" id="adminBox"> Privilegi Admin </input>
                                 </div>
                                 
                                 
                                 <div class="panel-footer" id ="emailDiv" style="background-color:transparent">
                                     <h2>Invita Utente</h2>
                                     
                                     <form action="InviteUserServlet"> <!-- da fixare il servlet !-->
                                         <input id ="emailInput" type="text" name="email" placeholder="Email privata dell'utente"/>
                                         <span id = "spanSpace" >asdasjsdfij      </span>
                                         <input class="btn btn-blue" type="submit" value="Invita"></input>
                                     </form>
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
