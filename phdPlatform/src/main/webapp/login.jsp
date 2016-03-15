<%-- 
    Document   : login
    Created on : 2-dic-2014, 23.05.03
    Author     : gemmacatolino
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:choose>
    <c:when test="${sessionScope.account != null}">
        <c:redirect url="index.jsp" />
    </c:when>
</c:choose>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DISTRA-MIT</title>

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
        

          <% if(session.getAttribute("loginError") != null)  { %>
              <script type="text/javascript">
                    $(function () {
                        $("#loginErrorDialog").modal();
                    });
              </script>
              
          <%} %>
          
       
    </head>

    <div id="loginErrorDialog" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Errore nell'autenticazione</h4>
                </div>
                <div class="modal-body">
                    <p>
                        Le tue credenziali sono errate.<br>
                        Controlla email e/o password.
                    </p>
                </div>
            </div>
        </div>
    </div>

    <body>
        <jsp:include page="barraMenu.jsp" flush="true"/>
        <!--BODY-->

        <div class="page-container">
            <div class="main-content">
                <div class="col-sm-2"></div>
                <div class="col-sm-8">
                    <div class="panel panel-default">

                        <div class="panel-heading">
                            <h3 class="panel-title">Accedi a Phd-Platform</h3>
                        </div>

                        <div class="panel-body">
                            <form id="loginForm" method="POST" action="login">

                                <div id="form-group-username" class="form-group">
                                    <label>Indirizzo Email:</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa-user"></i></span>
                                        <input type="text" id="username" class="form-control" name="email" placeholder="Inserisci l'indirizzo email" required/>
                                    </div>
                                    <span id="validate_username" style="color:#cc3f44"></span>
                                </div>

                                <div class="form-group">
                                    <label>Password:</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa-key"></i></span>
                                        <input type="password" id="password" class="form-control" name="password" placeholder="Inserisci la password" required/>
                                    </div>
                                    <span id="validate_password" style="color:#cc3f44"></span>
                                </div>

                                <div class="form-group">
                                    <label>
                                        <input type="checkbox" class="cbr cbr-done" id="rememberMeForLogin" checked>
                                        Ricorda le mie credenziali
                                    </label>
                                </div>

                                <div class="col-sm-12">
                                    <div id="messageControl" align="center">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <input type="submit" id="signin" class="btn btn-blue" value="Sign in"> 
                                    <button type="reset"  id="reset"  class="btn btn-white">Reset</button>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
                <div class="col-sm-2"></div>
            </div>
        </div>


        <!-- Bottom Scripts -->
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
    </body>
</html>
