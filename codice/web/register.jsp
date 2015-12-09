<%-- 
    Document   : register
    Created on : 4-dic-2014, 11.33.49
    Author     : gemmacatolino
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta name="description" content="Xenon Boostrap Admin Panel" />
        <meta name="author" content="" />

        <title>Phd-Platform</title>

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
        

        <script language="Javascript" type="text/javascript">
            function testpass(modulo) {
                // Verifico che il campo password sia valorizzato in caso contrario
                // avverto dell'errore tramite un Alert
                if (modulo.password.value === "") {
                    alert("Errore: inserire una password!");
                    modulo.password.focus();
                    return false;
                }
                // Verifico che le due password siano uguali, in caso contrario avverto
                // dell'errore con un Alert
                if (modulo.password.value !== modulo.password_2.value) {
                    alert("La password inserita non coincide con la prima!");
                    modulo.password.focus();
                    modulo.password.select();
                    return false;
                }

                var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                var patt = new RegExp(re);

                if (patt.test(modulo.email.value) === false) {
                    alert("L'e-mail inserita non è corretta!");
                    modulo.email.focus();
                    return false;
                }

                return true;
            }
        </script>

    </head>
    <body class="page-body">

        <jsp:include page="barraMenu.jsp" flush="true"/>
        <!--BODY-->

        <div class="page-container">
            <div class="main-content">
                <div class="row">
                    <div class="col-sm-2"></div>
                    <div class="col-sm-8">
                        <div class="panel">
                            <div class="panel-heading">
                                <h3 class="panel-title">Registrati a Phd-Platform</h3>
                            </div>



                            <form id="registrationForm" class="form-horizontal" method="POST" action="registration" onsubmit="return testpass(this)"/>
                            <div id="all-form">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Nome: </label>
                                    <div class="col-sm-10">
                                        <input type="text" name="name" class="form-control company-required phd-required student-required professor-required" id="name" style="width: 75%"/>
                                        <span id="name" style="color:#cc3f44"></span>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Cognome: </label>
                                    <div class="col-sm-10">
                                        <input type="text" name="surname"class="form-control company-required phd-required student-required professor-required" id="surname" style="width: 75%"/>
                                        <span id="surname" style="color:#cc3f44"></span>
                                    </div>
                                </div>
                   
         
                                <div class="form-group-separator"></div>
                                <br>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">E-mail: </label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control phd-required company-required student-required professor-required" id="email" name="email" placeholder="@unisa, @studenti.unisa, mail azienda" style="width: 75%"/>
                                        <span id="email" style="color:#cc3f44"></span>
                                    </div>
                                </div>
                                
                                <div class ="form-group">
                                    <label class="col-sm-2 control-label">E-mail Secondaria: </label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id ="secondaryEmail" name="secondaryEmail" style="width: 75%"/>
                                    </div>
                                </div>
                                
                                
                                <div class="form-group-separator"></div>
                                <br>
                                
                        

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Password: </label>
                                    <div class="col-sm-10">
                                        <input type='password' name="password" class="form-control company-required phd-required student-required professor-required" name="password" id='password' style="width: 75%" />
                                        <span id="password" style="color:#cc3f44"></span>

                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Ripeti Password: </label>
                                    <div class="col-sm-10">
                                        <input type="password" class="form-control phd-required company-required student-required professor-required" id="repeatPassword" name="password_2" style="width: 75%">
                                        <span id="repeatPassword" style="color:#cc3f44"></span>
                                    </div>
                                </div>

                                <div align="center">
                                    <input type="submit" id="register" class="btn btn-red" value="Registrati"> 

                                </div>
                            </div>

                            </form>
                        
                    </div>
                </div>
                <div class="col-sm-2"></div>
            </div>
            <footer class="main-footer sticky footer-type-1">
                <div class="footer-inner">
                    <!-- Add your copyright text here -->
                    <div class="footer-text">
                        &copy;
                        <a href="http://www.unisa.it" target="_blank"><strong>Unisa</strong> </a>
                    </div>
                    <!-- Go to Top Link, just add rel="go-top" to any link to add this functionality -->
                    <div class="go-up">
                        <a href="#" rel="go-top">
                            <i class="fa-angle-up"></i>
                        </a>
                    </div>
                </div>
            </footer>
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

    <!-- JavaScripts initializations and stuff -->
    <script src="assets/js/xenon-custom.js"></script>

</body>
</html>