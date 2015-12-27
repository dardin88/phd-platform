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
        <script type="text/javascript" src="script/amministrazioneAvvisi.js"></script> <!-- da modificare -->
                                                                                 
    </head>
    
        <body class="page-body">
            <!-- Inclusione della pagina contenente il menù superiore  -->
            <jsp:include page="barraMenu.jsp"/>
            <div class="page-container">
        
            <!-- Inclusione della pagina contenente il menù laterale --> 
            <jsp:include page="leftBar.jsp"/>  
	
                <div class="main-content" id="content">
                    <div class="row">
                        <div class="panel-heading">
                            <h1>Gestione Avvisi</h1> 
                        </div>
                    </div>
                    
                    
                    <div class="row">
                        
                        <div class="panel panel-default" id="tableDiv" hidden>
                            <!-- Default panel contents -->
                            <div class="panel-heading">
                                   
                            <button type="button" class="btn btn-default btn-secondary " onclick="addNewsButton()" >
                                <span class="glyphicon glyphicon-plus" aria-hidden="true" ></span>
                                Aggiungi Avviso
                            </button> 
                    
                            </div>
                                 <div class="panel-body">
                                 <!-- Table -->
                                    <table class="table">
                                        <thead>
                                                <tr>
                                                    <th>Titolo Avviso</th>
                                                    <th>Visualizza</th>
                                                    <th>Modifica</th>
                                                    <th>Elimina</th>
                                                </tr>
                                        </thead>
                                        <tbody id="accountListTable"> </tbody>
                                    </table>
                                 </div>
                                 
                        </div>
                    </div> <!-- chiusura riga -->
                    
                    
                    
                    <div class="row">
                       
                            <div class="panel panel-default" id="divPanelAddORModify" hidden>
                            <div class="panel-heading">
                                <button type="button" class="close" id="buttonCloseCurriculumDialog" onclick="closeModifyORaddDiv()" >&times;</button>
                                <h2 id="phdCurriculumTitle"></h2>
                            </div>
                            <div class="panel-body">

                                <!-- Form contenenti i campi dei curriculum di dottorato -->
                                <form id="curriculum_form" action="#" method="post">

                                    <!-- Campo di testo relativo al nome di un curriculum -->
                                    <div class="form-group">
                                        <label>Titolo Avviso:</label>
                                        <div class="input-group">
                                            <span class="input-group-addon"></span>
                                            <input type="text" id="newsTitle" class="form-control" name="name" placeholder="Inserisci il titolo dell'avviso" pattern="[a-z]+" required/>
                                        </div>
                                    </div>

                                    <!-- Campo di testo relativo alla descrizione di un curriculum -->
                                    <div class="form-group">
                                        <label>Descrizione:</label>
                                        <div class="input-group">
                                            <span class="input-group-addon"></span>
                                            <textarea id="newsDescription" rows="10" class="form-control" name="description" placeholder="Inserisci la descrizione dell'avviso"></textarea>
                                        </div>
                                    </div>

                                    

                                    <!-- Pulsanti di invio e reset del form -->
                                    <div class="form-group">
                                        <input type="button" id="saveNews" class="btn btn-blue" value="Salva"> 
                                        <input type="reset" id="resetNews" class="btn btn-white" value="Reset">
                                    </div>
                                </form >
                            </div>
                        </div>
                     </div>
                    
                    <div class="row">
                        
                        <div class="well-small col-lg-12 col-lg-offset-0 col-md-8 col-md-offset-6 col-sm-10 col-sm-offset-1 col-xs-12">
                        <div class="panel panel-default " id="descriptionPanel" hidden="true" style="margin-top: 5px">
                            <div class="panel-heading">
                                <h3 class="panel-title">Titolo news:  <span style="text-justify: auto" id="NewsNameField">  </h3> 
                            </div>
                            <div class="panel-body">
                                <p id="newsDescriptionField"> 

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
