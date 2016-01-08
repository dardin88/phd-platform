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

        <title>Amministrazione Ciclo-Curriculum</title>

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
        <script type="text/javascript" src="script/amministrazioneCurriculumcic.js"></script> <!-- da modificare -->

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
                        <h1>Gestione Ciclo-Curriculum</h1> 
                    </div>
                </div>      

                <div class="row"  > 
                    <div class="well-small col-lg-6 col-lg-offset-0 col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-xs-12">
                        <div class="form-group">
                            <label for="sel1">Seleziona uno fra i cicli attivi nel dottorato di ricerca</label>
                            <select class="form-control" id="CycleList" onchange="selectedItem()">
                            </select>
                        </div>
                    </div>    

                    <div class="well-small col-lg-6 col-lg-offset-0 col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-xs-12">
                        <button type="button" class="btn btn-block btn-secondary " style="margin-top: 22px" onclick="addCycleButton()">
                            <span class="glyphicon glyphicon-plus" aria-hidden="true" ></span>
                            Aggiungi Ciclo
                        </button> 
                    </div>

                </div>

                <div class="row"> 


                    <div class="well-small col-lg-6 col-lg-offset-0 col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-xs-12">
                        <div class="panel panel-default" style="margin-top: 10px" id="CycleSelectedDiv" hidden>
                            <div class="panel-heading">
                                <h3 id="cycleName">--Ciclo selezionato-- </h3>  

                                <h4 id="cycleYear"> anno del ciclo </h4>
                            </div>
                            <div class="panel-footer" style="background-color: transparent" >
                                <button type="button" class="btn btn-default btn-red" onclick="removeCycleButton()"> 
                                    Rimuovi Ciclo
                                    <span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span> 
                                </button>

                                <button type="button" class="btn btn-large" onclick="viewCollegio()">
                                    Visualizza il colleggio docenti
                                    <span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span>
                                </button>
                            </div>  
                        </div>
                    </div>    

                    <div class="well-small col-lg-6 col-lg-offset-0 col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-xs-12">

                        <div class="panel panel-default" style="margin-top: 10px" id="collegioDiv" hidden>
                            <div class="panel-heading">
                                <button type="button" class="close" id="buttonCloseCurriculumDialog" onclick="closeCollegioDiv()" >&times;</button>
                                <h3 class="panel-title">Collegio docenti del ciclo </h3> 
                            </div>
                            <div class="panel-body">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>Nome</th>
                                            <th>Cognome</th>
                                        </tr>
                                    </thead>
                                    <tbody id="bodyCollegio"> </tbody>
                                </table>
                            </div>

                        </div>    
                    </div> 



                    <!-- Pannello per creazione di un nuovo ciclo --> 
                    <div class="well-small col-lg-12 col-lg-offset-0 col-md-8 col-md-offset-6 col-sm-10 col-sm-offset-1 col-xs-12">
                        <div class="panel panel-default" id="divPanelAddORModify" hidden>
                            <div class="panel-heading">
                                <button type="button" class="close" id="buttonCloseCurriculumDialog" onclick="closeModifyORaddDiv()" >&times;</button>
                                <h2 id="cycleTitle"></h2>
                            </div>
                            <div class="panel-body">

                                <!-- Form contenenti i campi dei cicli di dottorato -->
                                <form id="curriculum_form">

                                    <!-- Campo di testo relativo all'anno di un ciclo -->
                                    <div class="form-group">
                                        <label>Anno:</label>
                                        <div class="input-group">
                                            <span class="input-group-addon"></span>
                                            <input type="text" id="cycleYearField" class="form-control" name="name" placeholder="Inserisci l'anno del diclo"  pattern="[0-9]{4}" required/>
                                        </div>
                                    </div>

                                    <!-- Campo di testo relativo alla descrizione di un ciclo -->
                                    <div class="form-group">
                                        <label>Descrizione:</label>
                                        <div class="input-group">
                                            <span class="input-group-addon"></span>
                                            <textarea id="cycleDescription" rows="10" class="form-control" name="description" placeholder="Inserisci la descrizione del ciclo"></textarea>
                                        </div>
                                    </div>



                                    <!-- Pulsanti di invio e reset del form -->
                                    <div class="form-group">
                                        <input type="button" id="saveCycle" class="btn btn-blue" value="Salva"> 
                                        <input type="reset" id="resetCurriculumButton" class="btn btn-white" value="Reset">
                                    </div>
                                </form >
                            </div>
                        </div>
                    </div>



                </div>  


                <div class="row">

                    <div class="col-lg-5 col-lg-offset-0 col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-xs-12" > 
                        <div class="panel panel-default" id="coordinatoreDiv" hidden>
                            <div class="panel-heading">
                                <h3 class="panel-title">Coordinatore del ciclo: <span style="font-style: italic" id="TutorNameField" style="margin-left: 4px">  </span> 
                                    <button type="button" class="btn btn-default btn-red btn-block" id="removeTutorButton" hidden onclick="removeTutorButton()">
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
                                            <th>Aggiorna</th>
                                        </tr>
                                    </thead>
                                    <tbody id="tutorTableList"></tbody>
                                </table>
                            </div>

                            <div class="panel-footer" style="background-color: transparent">
                                <p class="text-center"> -- Seleziona il coordinatore -- 
                            </div>  
                        </div>
                    </div>    


                    <div class="well-small col-lg-7 col-lg-offset-0 col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-xs-12">
                        <div class="panel panel-default" id="curriculumsDiv" hidden>
                            <div class="panel-heading">
                                <h3 class="panel-title">Curriculum attivi nel ciclo :
                                    <span style="font-style: italic" id="CyclyeNumberfield" class="text-center"> </span> 
                                </h3> 
                            </div>
                            <div class="panel-body">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>Nome Curriculum</th>
                                            <th>Visualizza Dettagli</th> 
                                            <th>Elimina Curriculum</th>
                                        </tr>
                                    </thead>
                                    <tbody id="curriculumList"></tbody>
                                </table>
                            </div>

                            <div class="panel-footer" style="background-color: transparent">
                                <button type="button" class="btn btn-large btn-block btn-secondary" id="addCurriculumButton" hidden onclick="addCurriculuminCicButton()">
                                    <span class="glyphicon glyphicon-plus" aria-hidden="true">
                                    </span> Aggiungi Curriculum al Ciclo
                                </button>
                            </div>  
                        </div>
                    </div>
                </div>

                <div class="row"> 
                        <div class="well-small col-lg-12 col-lg-offset-0 col-md-8 col-md-offset-6 col-sm-10 col-sm-offset-1 col-xs-12">
                            <div class="panel panel-default " id="descriptionPanel" hidden  style="margin-top: 5px">
                                <div class="panel-heading">
                                    <h3 class="panel-title">Nome Curriculum: <span style="text-justify: auto" id="CurriculumNameField">  </h3> 
                                </div>
                                <div class="panel-body">
                                    <p id="CurriculumDescriptionField"> 

                                </div>
                                <div class="panel-footer " style="background-color: transparent">
                                    
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
