<%-- 
    Document   : addActivity
    Created on : 9-mag-2016, 18.42.12
    Author     : Liliana
--%>

<%@page import="it.unisa.dottorato.account.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:choose>
    <c:when test="${sessionScope.account == null}">
        <c:redirect url="login.jsp" />
    </c:when>
</c:choose>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta name="description" content="Xenon Boostrap Admin Panel" />
        <meta name="author" content="" />

        <title>Aggiungi Attività nel Registro</title>

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
        <script type="text/javascript" src="script/activityRegister.js"></script>
        <script type="text/javascript" src="script/bootstrap-timepicker.js"></script>
        <script type="text/javascript" src="script/bootstrap-timepicker.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/TweenMax.min.js"></script>
        <script src="assets/js/resizeable.js"></script>
        <script src="assets/js/joinable.js"></script>
        <script src="assets/js/xenon-api.js"></script>
        <script src="assets/js/xenon-toggles.js"></script>
        <script src="assets/js/jquery-ui/jquery-ui.min.js"></script>

        <!-- Imported scripts on this page -->
        <script src="assets/js/tocify/jquery.tocify.min.js"></script>

        <!-- Imported scripts on this page -->
        <script src="assets/js/jquery-validate/jquery.validate.min.js"></script>

        <!-- JavaScripts initializations and stuff -->
        <script src="assets/js/xenon-custom.js"></script>

        <script type="text/javascript"> 
            $(document).ready(function(){
                window.allSeminars=[];
                getTypology();
                if(sessionStorage.getItem('insertFlag') === 'true')
                    insertFunction();
                else
                    updateFunction();
                
                getSeminar();//inserito qua così viene caricato 1 sola volta
            })
            
        </script>
        <style>
            p {padding: 10px;}
            .select-editable { position:relative; background-color:white;  width:130px; height:18px; margin-bottom: 10px; }
            .select-editable select { position:absolute; top:0px; left:0px; font-size:14px; width:135px; margin:0; height:25px; margin-bottom: 10px;}
            .select-editable input { position:absolute; top:0px; left:0px; width:118px; height:25px; padding:1px; font-size:13px; color:black; margin-bottom: 10px;}
            .select-editable select:focus, .select-editable input:focus { outline:none; }

        </style>
    </head>
     <div id="infoDialog" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" >&times;</button>
                    <h4 class="modal-title" id="titleInfo"></h4>
                </div>
                
                <div class="modal-body" id="descriptionInfo">
                </div>
            
            </div>
        </div>
    </div>
    <c:choose>
        <c:when test="${sessionScope.account != null}">
            <% Account loggedPerson = ((Account) session.getAttribute("account"));
                if (loggedPerson.getTypeAccount().equals("phdstudent")) {
            %> 
        <body class="page-body">

            <!-- Inclusione della pagina contenente il menù superiore -->
            <jsp:include page="barraMenu.jsp" flush="true"/>
            <!-- Contenuto della pagina -->
            <div class="page-container">
               <div class="main-content" id="content">
                    <div class="row">
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <div class="panel panel-default">
                                <div id='Intestazione' class="panel-heading">

                                </div>
                                <div class="panel-body">
                                    <!-- Form per inserimento e modifica di un'attività -->
                                    <div class="form-group">
                                        <table width="90%" align="center">
                                                <tr>
                                                    <div class="input-group">
                                                       <div class="panel panel-default " style="margin-top: 5px">
                                                           
                                                            <h1> Nuova Attività</h1>
                                                           
                                                            <p>Seleziona la tipologia</p>
                                                            <div class="select-editable">                                                               
                                                                <select id="typologySelect" onchange="this.nextElementSibling.value=this.value;onSelectChange(this.value)">
                                                                </select>  
                                                                
                                                                <input type="text" id="typology" onkeyup="checkSeminarTypology()" name="format" value="" placeholder="seleziona tipologia" maxlength="30"/>
                                                            </div>
                                                           
                                                           <p>Nome Attività</p>
                                                           <div class="input-group">
                                                               <select id="seminarSelect" style="visibility:hidden; width:250px;" onchange="onChangeSeminar()" >
                                                               </select>  
                                                               
                                                               <input id="name" name="name" maxlength="30" type="text" class="form-control" onblur="checkCharacter(this.value)" onfocus="changeColor()" required>
                                                           </div>

                                                           <p>Descrizione:</p>
                                                            <div class="input-group">
                                                                 <textarea id="description" name="description" maxlength="60" rows="5" cols="50" class="form-control" > </textarea>
                                                            </div>
 
                                                           <p>Data :</p>
                                                            <div class="input-group">
                                                                <input id="dateActivity" name="date" type='date' placeholder="aaaa-mm-gg"  class="form-control" required>
                                                            </div>
                                                           
                                                            <p> Orario di Inizio:</p>
                                                            <div class="input-group bootstrap-timepicker timepicker" >
                                                                 <input id="startTimeActivity" name="startTime" type="text" class="form-control input-small">
                                                            </div> 
                                                            
                                                            <p> Orario di Fine:</p>
                                                                <div class="input-group bootstrap-timepicker timepicker">
                                                                    <input id="endTimeActivity" name ="endTime" type="text" class="form-control input-small">
                                                                </div>
                                                                    <script type="text/javascript">
                                                                            $('#startTimeActivity').timepicker();
                                                                    </script>
                                                                    <script type="text/javascript">
                                                                            $('#endTimeActivity').timepicker();
                                                                    </script>
                                                           
                                                            <!--Div che contiene il bottone di Inserisci o Modifica attività-->
                                                            <div style="margin-top: 40px" id="bottoneInsUpdate">

                                                            </div>
                                                       </div>
                                                    </div>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>    
    </body>
    <%}
        else{%>
                <c:redirect url="index.jsp" />
          <%}
    %>
        </c:when>
    </c:choose>
</html>