<%-- 
    Document   : profile
    Author     : armando
--%>

<%@page import="java.io.File"%>
<%@page import="it.unisa.dottorato.phdProfile.collaborations.CollaborationManager"%>
<%@page import="it.unisa.dottorato.phdProfile.missions.MissionManager"%>
<%@page import="it.unisa.dottorato.phdProfile.collaborations.Collaboration"%>
<%@page import="it.unisa.dottorato.phdProfile.missions.Mission"%>
<%@page import="it.unisa.dottorato.phdProfile.publications.PublicationManager"%>
<%@page import="it.unisa.dottorato.phdProfile.publications.Publication"%>
<%@page import="java.util.List"%>
<%@page import="it.unisa.dottorato.account.*"%>
<%@page import="it.unisa.dottorato.Cycle.*"%>
<%@page import="it.unisa.dottorato.Curriculum.*"%>

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
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <link rel="stylesheet" href="style/dottorato.css">
        <link rel="stylesheet" href="/resources/demos/style.css">

        <script type="text/javascript">
            function show() {
                var x = document.getElementById("rawr");

                if (x.style.display === 'block')
                    x.style.display = 'none';
                else
                    x.style.display = 'block';

                if (document.getElementById("showArrow").className === 'glyphicon glyphicon-chevron-down pointer') {
                    document.getElementById("showArrow").className = 'glyphicon glyphicon-chevron-up pointer';
                } else
                    document.getElementById("showArrow").className = 'glyphicon glyphicon-chevron-down pointer';
            }
            function display(x) {
                if (x === 'table-pub') {
                    document.getElementsByTagName("li")[0].className = 'active';
                    var blessed = document.getElementById(x);
                    var other = document.getElementById("table-coll");
                    var other1 = document.getElementById("table-miss");
                    blessed.style.display = 'block';
                    other.style.display = 'none';
                    other1.style.display = 'none';
                }
                if (x === 'table-coll') {
                    document.getElementsByTagName("li")[1].className = 'active';
                    var blessed = document.getElementById(x);
                    var other = document.getElementById('table-pub');
                    var other1 = document.getElementById('table-miss');
                    blessed.style.display = 'block';
                    other.style.display = 'none';
                    other1.style.display = 'none';
                }
                if (x === 'table-miss') {
                    document.getElementsByTagName("li")[2].className = 'active';
                    var blessed = document.getElementById(x);
                    var other = document.getElementById('table-coll');
                    var other1 = document.getElementById('table-pub');
                    blessed.style.display = 'block';
                    other.style.display = 'none';
                    other1.style.display = 'none';
                }

            }




        </script>

        <style>
            #rawr {
                display: none;
            }

            #table-coll {
                display:none;
            }

            #table-miss {
                display:none;
            }
            
            #showArrow {
                font-size:70%;
            }
        </style>





        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
                <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
                <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]--> 

    </head>
    <body class="page-body">

        <!-- Inclusione della pagina contenente il menù superiore --> 
        <jsp:include page="barraMenu.jsp" flush="true"/> 
        <div class="page-container">
            <!-- Inclusione della pagina contenente il menù laterale --> 

            <!-- Contenuto della pagina --> 

            <div class="main-content" id="content">

                <div class="row">

                    <div class="col-sm-1"></div>

                    <div class="col-sm-10">
                        
                        <%
                            Account profile = ((Account) AccountManager.getInstance().getAccountByEmail(request.getParameter("mail")));

                        %>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h1> <%= profile.getName()%> <%= profile.getSurname()%></h1>
                                    <%  if (profile.getTypeAccount().equals("phdstudent")) {%>
                                <h4> <%= ((PhdStudent) profile).getfkCycle()%>° ciclo di dottorato in <%= ((PhdStudent) profile).getfkCurriculum()%></h4>
                                <% } else if (profile.getTypeAccount().equals("professor")) {%>
                                <h4><%= ((Professor) profile).getDepartment()%></h4>
                                <% } %>
                            </div>
                            <div class="panel-body">
                                <table width="97%" align="center">
                                    <tr>
                                        <td width="180px" >
                                            <img class="img-polaroid" style='width: 150px ; height: 150px ;' src="Immagini/scam_facebook_fake_tutela_amici.jpg" alt="nome immagine" >
                                        </td>

                                        <% if (profile.getTypeAccount().equals("basic")) {%>
                                        <td>
                                            <h3> Contatti </h3>
                                            E-mail: <%= profile.getSecondaryEmail()%> <br>

                                            <% } %>
                                            <% if (profile.getTypeAccount().equals("phdstudent")) {%>
                                        <td>
                                            <h3> Contatti </h3>
                                            <p> 
                                                Telefono: <%= ((PhdStudent) profile).getTelephone()%> <br>
                                                E-mail: <%= profile.getSecondaryEmail()%> <br>
                                                <% if (((PhdStudent) profile).getLink() != null) {%>
                                                Sito web: <a href="<%= ((PhdStudent) profile).getLink()%>" target="_blank"><%= ((PhdStudent) profile).getLink()%></a> <br>
                                                <%}
                                                    if (((PhdStudent) profile).getDepartment() != null) {%>
                                                <%= ((PhdStudent) profile).getDepartment()%> <br>
                                                <% }%>


                                            </p>
                                        </td>
                                        <% } %>
                                        <% if (profile.getTypeAccount().equals("professor")) {%>
                                        <td>
                                            <h3> Contatti</h3>
                                            <p> 

                                                E-mail: <%= profile.getSecondaryEmail()%> <br>
                                                <% if (((Professor) profile).getLink() != null) {%>
                                                Sito web: <a href="<%= ((Professor) profile).getLink()%>" target="_blank"><%= ((Professor) profile).getLink()%></a> <br>
                                                <%}
                                                    if (((Professor) profile).getDepartment() != null) {%>
                                                <%= ((Professor) profile).getDepartment()%> <br>
                                                <% }%>

                                            </p>
                                        </td>
                                        <% } %>

                                    </tr>

                                    <% if (profile.getTypeAccount().equals("phdstudent")) {%>

                                    <%
                                        List<Publication> publications = PublicationManager.getInstance().getAllPublicationsOf((PhdStudent) profile);
                                        List<Collaboration> collaborations = CollaborationManager.getInstance().getAllCollaborationOf((PhdStudent) profile);
                                        List<Mission> missions = MissionManager.getInstance().getAllMissionsOf((PhdStudent) profile);
                                    %>
                                    <tr>
                                        <td colspan="2">
                                            <% File file = new File(getServletContext().getRealPath("") + "\\tesi" + File.separator + profile.getName() + "_" + profile.getSurname() + ".pdf");
                                               if(file.exists()) {
                                            %>
                                            <br>
                                            <br>
                                            <a href="tesi\<%= profile.getName() + "_" + profile.getSurname() + ".pdf"%>" download><b>Download Tesi</b></a>
                                         <%} %>
                                            <br>
                                            <h3 id = "diocane"> Attività di ricerca </h3> <br>
                                            <p class="text-justify" > 
                                                <% String a = ((PhdStudent) profile).getResearchInterest();
                                                if (a != null){
                                                    out.println(a);
                                                  } %> 
                                                <br> <br>
                                            </p>

                                            <p class="text-justify"> 
                                            <h3>Registro attività: <span id ="showArrow" class="glyphicon glyphicon-chevron-down pointer" aria-hidden="true" onclick="show();"></span></h3>
                                            </p>
                                            <div id="rawr">
                                                <div class="panel-heading">
                                                    <ul class="nav nav-tabs" >
                                                        <li role="presentation" onclick="display('table-pub')"><a>Pubblicazioni</a></li>
                                                        <li role="presentation" onclick="display('table-coll')"><a>Collaborazioni</a></li>
                                                        <li role="presentation" onclick="display('table-miss')"><a>Mission</a></li>
                                                    </ul>
                                                    <br>



                                                </div>
                                                <div class="panel-body" id="table-pub">
                                                  
                                                    <table class="table table-hover" width="98%" align="center" >
                                                        <thead>
                                                        <th width="15%">Titolo</th>
                                                        <th width="15%">Autori</th>		
                                                        <th width="30%">Abstract</th>
                                                        <th width="10%">Anno</th>
                                                        <th width="10%">Type</th>
                                                        <th width="10%">Pubblication Issue</th>
                                                        <th width="10%">Numero Pagine</th>
                                                        </thead>

                                                        <% for (Publication publication : publications) {%>
                                                        <tr>
                                                            <td><%= publication.getTitle()%></td>
                                                            <td><%= publication.getAuthors()%> </td>		
                                                            <td><%= publication.getAbstract()%></td>
                                                            <td><%= publication.getYear()%></td>
                                                            <td><%= publication.getType()%></td>
                                                            <td><%= publication.getPublicationIssue()%></td>		
                                                            <td><%= publication.getNumberPages()%></td>

                                                            <% session.setAttribute("idPublication", publication.getIdPublication());%>

                                                         
                                                        </tr>
                                                        <% }%>
                                                    </table>
                                                </div>

                                                <div class="panel-body" id="table-coll">
                                                   
                                                    <table class="table table-hover" width="98%" align="center" >
                                                        <thead>
                                                        <th>Istituzione</th>
                                                        <th>Descrizione</th>		
                                                        <th>Data Di Inizio</th>
                                                        <th>Data Di Fine</th>
                                                        </thead>

                                                        <% for (Collaboration collaboration : collaborations) {%>
                                                        <tr>
                                                            <td><%= collaboration.getIstitution()%></td>
                                                            <td><%= collaboration.getDescription()%></td>		
                                                            <td><%= collaboration.getStartDate()%></td>
                                                            <td><%= collaboration.getEndDate()%></td>

                                                            <% session.setAttribute("idCollaboration", collaboration.getIdCollaboration());%>

                                                            
                                                        </tr>
                                                        <% }%>
                                                    </table>
                                                </div>
                                                <div class="panel-body" id="table-miss">
                                                    
                                                    <table class="table table-hover" width="98%" align="center" >
                                                        <thead>
                                                        <th>Luogo</th>
                                                        <th>Descrizione</th>		
                                                        <th>Data Di Inizio</th>
                                                        <th>Data Di Fine</th>
                                                        </thead>

                                                        <% for (Mission mission : missions) {%>

                                                        <tr>
                                                            <td><%= mission.getPlace()%></td>
                                                            <td><%= mission.getDescription()%></td>		
                                                            <td><%= mission.getStartDate()%></td>
                                                            <td><%= mission.getEndDate()%></td>

                                                            <% session.setAttribute("idMission", mission.getIdMission());%>



                                                         
                                                        </tr>
                                                        <% }%>

                                                    </table>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <% }%>

                                </table>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-1"></div>

                </div>
            </div>

        </div>                          
    </body>
</html>