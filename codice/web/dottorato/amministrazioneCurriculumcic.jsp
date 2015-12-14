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
        
        <title>Amministrazione Curriculum-Ciclo</title>
   
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
            <!--da modificare con la nuova -->
            <div class="page-container">
        
            <!-- Inclusione della pagina contenente il menù laterale --> 
            <jsp:include page="leftBar.jsp"/>  
            
                <div class="main-content" id="content">
                    <div class="row">
                        <div class="panel-heading">
                            <h1>Gestione Ciclo-Curriculum</h1> 
                        </div>
                    </div>      
                     
                    <div class="row"  > 
                        <div class="well-small col-lg-3 col-lg-offset-0 col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-xs-12">
                            <div class="dropdown" >
                            <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                              -- seleziona uno fra i cicli attivi nel dottorato di ricerca -- 
                              <span class="caret" ></span>
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
                              <li><a href="#">Action</a></li>
                              <li><a href="#">Another action</a></li>
                              <li><a href="#">Something else here</a></li>
                            </ul>
                        </div>
                        </div>    
                    
                        <div class="well-small col-lg-6 col-lg-offset-3 col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-xs-12">
                        <button type="button" class="btn btn-block btn-secondary " >
                                <span class="glyphicon glyphicon-plus" aria-hidden="true" ></span>
                                Aggiungi Ciclo
                            </button> 
                        </div>
                        
                    </div>
                    
                    <div class="row"> 
                    <div class="well-small col-lg-6 col-lg-offset-6 col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-xs-12" style="margin-top: 8px"> 
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Coordinatore del ciclo: <span style="font-style: italic" id="TutorNameField" class="text-center"> -- coordinatore selezionato -- </span> 
                                    <button type="button" class="btn btn-large btn-block btn-red" style="column-span: all">
                                        <span class="glyphicon glyphicon-remove-sign" aria-hidden="true">
                                        </span> Rimuovi coordinatore
                                    </button> </h3> 
                            </div>
                            <div class="panel-body">
                                <table class="table">
                                    <thead>
                                        <tr>
                                           <th>Nome</th>
                                           <th>Cognome</th>
                                        </tr>
                                    </thead>
                                </table>
                            </div>
                              
                              <div class="panel-footer" style="background-color: transparent">
                                  <p class="text-center"> -- Seleziona il coordinatore -- 
                              </div>  
                          </div>
                    </div>
                    </div>  
                    
                    
                    
                   
                    
                    
                    
                    
                    <div class="row">
                    <div class="well-small col-lg-6 col-lg-offset-0 col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-xs-12">
                           <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 id="cycleName">--Ciclo selezionato-- <button type="button" class="btn btn-default btn-red ">
                                            <span class="glyphicon glyphicon-remove-sign" aria-hidden="true" style="margin-top: 2px"></span> Rimuovi Ciclo
                                    </button>  </h3>  
                                <h4 id="cycleYear"> anno del ciclo </h4>
                            </div>
                            <div class="panel-footer" style="background-color: transparent">
                                  <button type="button" class="btn btn-large btn-block ">
                                        Visualizza il colleggio docenti
                                  </button>
                              </div>  
                        </div>
                    </div>
                    
                    
                    
                    <div class="well-small col-lg-6 col-lg-offset-0 col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-xs-12">
                          <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Curriculum attivi nel ciclo :
                                    <span style="font-style: italic" id="CyclyeNumberfield" class="text-center"> -- n.ciclo -- </span> 
                                </h3> 
                            </div>
                            <div class="panel-body">
                                <table class="table">
                                    <thead>
                                        <tr>
                                           <th>Nome Curriculum</th>
                                           <th>Elimina Curriculum</th>
                                        </tr>
                                    </thead>
                                </table>
                            </div>
                              
                              <div class="panel-footer" style="background-color: transparent">
                                  <button type="button" class="btn btn-large btn-block btn-secondary" style="column-span: all">
                                        <span class="glyphicon glyphicon-plus" aria-hidden="true">
                                        </span> Aggiungi Curriculum al Ciclo
                                    </button>
                              </div>  
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
