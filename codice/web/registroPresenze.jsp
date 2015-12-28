<%-- 
    Document   : RegistroPresenze
    Created on : 23-dic-2015, 1.24.09
    Author     : Rembor
--%>

<%@page import="it.unisa.dottorato.account.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta name="description" content="Xenon Boostrap Admin Panel" />
        <meta name="author" content="" />
         <title>Registro Presenze</title>

        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Arimo:400,700,400italic">
        <link rel="stylesheet" href="assets/css/fonts/linecons/css/linecons.css">
        <link rel="stylesheet" href="assets/css/fonts/fontawesome/css/font-awesome.min.css">
        <link rel="stylesheet" href="assets/css/bootstrap.css">
        <link rel="stylesheet" href="assets/css/xenon-core.css">
        <link rel="stylesheet" href="assets/css/xenon-forms.css">
        <link rel="stylesheet" href="assets/css/xenon-components.css">
        <link rel="stylesheet" href="assets/css/xenon-skins.css">
        <link rel="stylesheet" href="assets/css/custom.css">

        <script src="assets/js/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="script/registro.js"></script>
    

    </head>
   
        
    <body class="page-body">
        <!-- Inclusione della pagina contenente il menù superiore -->

      
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

        <!--Qui chiama servlet update che prende infomazioni Account--> 
<div class="page-body">
            <jsp:include page="barraMenu.jsp"/>

            <div class="page-container"> 
                 <div class="well-small col-lg-5 col-lg-offset-0 col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-xs-12">
                            <div class="form-group">
                               
                                        <h1  > Seleziona un corso</h1>
                                        <select class="form-control" id="Corsoprofessore"  onclick="selectedItem()">
                                            <option value="default"  >  - selezionate il vostro  corso  -  </option>

                                        </select>
                            </div>
                        </div>
                 <div id="results" >
                    <table id="resultst" class="table" style="display: inline-block; border: 1px solid; float: left; ">
                        <thead id="resulthead">
                            <tr>
                                
                               
                            </tr>
                        </thead>
                        <tbody id="resultbody">
                        

                            <tr >

                                <td></td>
                                <td></td>
                                <td></td>
                              


                            </tr>
                        </tbody>
                    </table>
   
      
      
    
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
