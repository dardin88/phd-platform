<%--
    Document   : profile
    Created on : 25-dic-2014, 11.54.56
    Author     : gemmacatolino
--%>

<%@page import="java.io.File"%>
<%@page import="it.unisa.dottorato.account.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta name="description" content="Xenon Boostrap Admin Panel" />
        <meta name="author" content="" />

        <title>DISTRA-MIT Dottorato</title>

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
        <script type="text/javascript" src="script/index.js"></script>
        
        <style>
            #uploadDiv {
                display: none;
            }
        </style>

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
                <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
                <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
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

        <!-- Inclusione della pagina contenente il menù superiore -->
        <jsp:include page="barraMenu.jsp" flush="true"/>
        <div class="page-container">

            <!-- Inclusione della pagina contenente il menù laterale -->

            <!-- Contenuto della pagina -->
            <% Account loggedPerson = ((Account) session.getAttribute("account"));
            %>

            <div class="main-content" id="content">

                <div class="row">

                    <div class="col-sm-1"></div>

                    <div class="col-sm-10">

                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h1> Modifica profilo</h1>
                            </div>
                            <div class="panel-body">
                                <form class="form-horizontal" name ="profileform" method="POST" action="UpdateProfileServlet" onsubmit="return testpass(this)">
                                    <div class="form-group">
                                        <table width="90%" align="center">
                                            <tr><td>
                                                    <p>Nome:</p>
                                                    <div class="input-group">
                                                        <span class="input-group-addon"></span>
                                                        <input class="form-control phd-required company-required student-required professor-required" name="name" type="text" value="<%= loggedPerson.getName()%>" required/>
                                                    </div>
                                                    <br>
                                                    <br>
                                                    <p>Cognome:</p>
                                                    <div class="input-group">
                                                        <span class="input-group-addon"></span>
                                                        <input class="form-control phd-required company-required student-required professor-required" name="surname" type="text" value="<%= loggedPerson.getSurname()%>" required/>
                                                    </div>
                                                    <br>
                                                    <br>
                                                    <p>Password:</p>
                                                    <div class="input-group">
                                                        <span class="input-group-addon"></span>
                                                        <input class="form-control phd-required company-required student-required professor-required" name="password" type="password" value="<%= loggedPerson.getPassword()%>" required/>
                                                    </div>
                                                    <br>
                                                    <br>
                                                    <p>Conferma Password:</p>
                                                    <div class="input-group">
                                                        <span class="input-group-addon"></span>
                                                        <input class="form-control phd-required company-required student-required professor-required" name="password_2" type="password" value="<%= loggedPerson.getPassword()%>" required/>
                                                    </div>
                                                    <br>
                                                    <br>

                                                    <p>Email Secondaria:</p>
                                                    <div class="input-group">
                                                        <span class="input-group-addon"></span>
                                                        <input class="form-control" name="secondaryEmail" type="text" value="<%= loggedPerson.getSecondaryEmail()%>" required>
                                                    </div>
                                                    <br>
                                                    <br>

                                                    <p>Modifica Contatti</p>
                                                    <div class="form-group-separator"></div>
                                                    <br>

                                                    <% if (loggedPerson.getTypeAccount().equals("professor")) {%>
                                                    <p>Web Page:</p>
                                                    <div class="input-group">
                                                        <span class="input-group-addon"></span>
                                                        <input class="form-control" name="link" type="text" value="<%= ((Professor) loggedPerson).getLink()%>" >
                                                    </div>
                                                    <br>
                                                    <br>
                                                    <p>Dipartimento:</p>
                                                    <div class="input-group">
                                                        <span class="input-group-addon"></span>
                                                        <input class="form-control" name="department" type="text" value="<%= ((Professor) loggedPerson).getDepartment()%>" >
                                                    </div>
                                                    <br>
                                                    <br>
                                                    <% } %>
                                                    <% if (loggedPerson.getTypeAccount().equals("phdstudent")) {%>
                                                    <p >Telefono:</p>
                                                    <div class="input-group">
                                                        <span class="input-group-addon"></span>
                                                        <input class="form-control" name="telephone" type="text" value="<%= ((PhdStudent) loggedPerson).getTelephone()%>" required>
                                                    </div>
                                                    <br>
                                                    <br>
                                                    <p>Web Page:</p>
                                                    <div class="input-group">
                                                        <span class="input-group-addon"></span>
                                                        <input class="form-control" name="link" type="text" value="<%= ((PhdStudent) loggedPerson).getLink()%>" >
                                                    </div>
                                                    <br>
                                                    <br>
                                                    <p>Dipartimento:</p>
                                                    <div class="input-group">
                                                        <span class="input-group-addon"></span>
                                                        <input class="form-control" name="department" type="text" value="<%= ((PhdStudent) loggedPerson).getDepartment()%>" >
                                                    </div>
                                                    <br>
                                                    <br>
                                                    <p>Interessi di Ricerca:</p>
                                                    <div class="input-group">
                                                        <span class="input-group-addon"></span>
                                                        <textarea class="form-control" name="researchInterest" rows="5" cols="40"> <%= ((PhdStudent) loggedPerson).getResearchInterest()%> </textarea>
                                                    </div>
                                                    <br>
                                                    <br>
                                                    
                                              <%} %>

                                                    <div>
                                                        <input type="submit" class="btn btn-blue" value="Modifica">
                                                        <br>
                                                        <br>

                                                    </div>
                                                </td></tr>
                                        </table>

                                    </div>
                                </form>

                                <!-- test  !-->     
                                <div id="uploadForm">
                                <form class="form-group" action="UploadFile" enctype="multipart/form-data" method="post" >
                                    <input type="file" name="tesi" accept=".pdf"/>
                                    <input type="submit" class="btn btn-blue" value="Upload"/>
                                </form>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-1"></div>

                </div>
            </div>



    </body>
</html>
