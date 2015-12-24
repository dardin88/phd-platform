<%-- 
    Document   : amministrazioneCurriculum
    Created on : 12-dic-2015, 20.45.01
    Author     : andre
--%>

<%@page import="it.unisa.dottorato.Curriculum.CurriculumManager"%>
<%@page import="it.unisa.dottorato.Curriculum.Curriculum"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Xenon Boostrap Admin Panel" />
        <meta name="author" content="Andrea Fedele" />

        <title>Amministrazione Curriculum</title>

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
        <script type="text/javascript" src="script/amministrazioneCurriculum.js"></script> <!-- da modificare -->

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
                        <h1>Gestione Curriculum</h1> 
                    </div>
                </div>


                <div class="row">
                    <div class="well-small col-lg-6 col-lg-offset-0 col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-xs-12">
                        <div class="form-group">
                            <label for="sel1">Seleziona uno dei curriculum attivi nel dottorato di ricerca</label>
                            <select class="form-control" id="CurriculumList" onclick="selectedItem()">
                            </select>
                        </div>
                    </div>
                    <div class="well-small col-lg-6 col-lg-offset-0 col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-xs-12">
                        <button type="button" class="btn  btn-default btn-secondary btn-block" onclick="addCurriculumButton()" style="margin-top: 22px">
                            <span class="glyphicon glyphicon-plus" aria-hidden="true" ></span>
                            Aggiungi Curriculum
                        </button> 
                    </div>
                </div>
                    
                <div>    
                    <div class="well-small col-lg-12 col-lg-offset-0 col-md-8 col-md-offset-6 col-sm-10 col-sm-offset-1 col-xs-12">
                        <div class="panel panel-default " id="descriptionPanel" hidden="true" style="margin-top: 5px">
                            <div class="panel-heading">
                                <h3 class="panel-title">Nome Curriculum: <span style="text-justify: auto" id="CurriculumNameField">  </h3> 
                            </div>
                            <div class="panel-body">
                                <p id="CurriculumDescriptionField"> 

                            </div>
                            <div class="panel-footer " style="background-color: transparent">
                                <div class="row">
                                 <div class="well-small col-lg-6 col-lg-offset-0 col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-xs-12" id="removeButtonSpace">
                                
                                 </div> 
                                 <div class="well-small col-lg-6 col-lg-offset-0 col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-xs-12" id="modifyButtonSpace">
                       
                                 </div>  
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
                
                
                        <!-- Pannello per creazione di un nuovo curriculum o la modifica di un curriculum selezionato --> 
                    <div class="well-small col-lg-12 col-lg-offset-0 col-md-8 col-md-offset-6 col-sm-10 col-sm-offset-1 col-xs-12">
                        <div class="panel panel-default" id="divPanelAddORModify" hidden>
                            <div class="panel-heading">
                                <button type="button" class="close" id="buttonCloseCurriculumDialog" onclick="closeModifyORaddDiv()" >&times;</button>
                                <h2 id="phdCurriculumTitle"></h2>
                            </div>
                            <div class="panel-body">

                                <!-- Form contenenti i campi dei curriculum di dottorato -->
                                <form id="curriculum_form">

                                    <!-- Campo di testo relativo al nome di un curriculum -->
                                    <div class="form-group">
                                        <label>Nome:</label>
                                        <div class="input-group">
                                            <span class="input-group-addon"></span>
                                            <input type="text" id="phdCurriculumName" class="form-control" name="name" placeholder="Inserisci il nome del curriculum" pattern="[a-z]+" required/>
                                        </div>
                                    </div>

                                    <!-- Campo di testo relativo alla descrizione di un curriculum -->
                                    <div class="form-group">
                                        <label>Descrizione:</label>
                                        <div class="input-group">
                                            <span class="input-group-addon"></span>
                                            <textarea id="phdCurriculumDescription" rows="10" class="form-control" name="description" placeholder="Inserisci la descrizione del curriculum"></textarea>
                                        </div>
                                    </div>

                                    

                                    <!-- Pulsanti di invio e reset del form -->
                                    <div class="form-group">
                                        <input type="button" id="savePhdCurriculum" class="btn btn-blue" value="Salva"> 
                                        <input type="reset" id="resetCurriculumButton" class="btn btn-white" value="Reset">
                                    </div>
                                </form >
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
