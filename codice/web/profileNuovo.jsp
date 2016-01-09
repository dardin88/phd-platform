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
                var links = document.querySelectorAll(".bottoni > a");
                if (x === 'table-pub') {
                    links[0].style.background = 'white';
                    links[1].style.background = '#e5e5e5';
                    links[2].style.background = '#e5e5e5';
                    document.getElementsByTagName("li")[0].className = 'active';
                    var blessed = document.getElementById(x);
                    var other = document.getElementById("table-coll");
                    var other1 = document.getElementById("table-miss");
                    blessed.style.display = 'block';
                    other.style.display = 'none';
                    other1.style.display = 'none';
                }
                if (x === 'table-coll') {
                    links[1].style.background = 'white';
                    links[0].style.background = '#e5e5e5';
                    links[2].style.background = '#e5e5e5';
                    document.getElementsByTagName("li")[1].className = 'active';
                    var blessed = document.getElementById(x);
                    var other = document.getElementById('table-pub');
                    var other1 = document.getElementById('table-miss');
                    blessed.style.display = 'block';
                    other.style.display = 'none';
                    other1.style.display = 'none';
                }
                if (x === 'table-miss') {
                    links[2].style.background = 'white';
                    links[1].style.background = '#e5e5e5';
                    links[0].style.background = '#e5e5e5';
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
            
            .bottoni > a:hover {
                cursor:pointer;
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
                        <% Account loggedPerson = ((Account) session.getAttribute("account"));

                        %>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h1> <%= loggedPerson.getName()%> <%= loggedPerson.getSurname()%>  <span class="glyphicon glyphicon-cog pointer" aria-hidden="true" onclick="location.href = 'editProfile.jsp'" style="float: right; display: inline;"></span></h1>
                                    <%  if (loggedPerson.getTypeAccount().equals("phdstudent")) {%>
                                <h4> <%= ((PhdStudent) loggedPerson).getfkCycle()%>° ciclo di dottorato in <%= ((PhdStudent) loggedPerson).getfkCurriculum()%></h4>
                                <% } else if (loggedPerson.getTypeAccount().equals("professor")) {%>
                                <h4><%= ((Professor) loggedPerson).getDepartment()%></h4>
                                <% } %>
                            </div>
                            <div class="panel-body">
                                <table width="97%" align="center">
                                    <tr>
                                        <td width="180px" >
                                            <img class="img-polaroid" style='width: 150px ; height: 150px ;' src="Immagini/scam_facebook_fake_tutela_amici.jpg" alt="nome immagine" >
                                        </td>

                                        <% if (loggedPerson.getTypeAccount().equals("basic")) {%>
                                        <td>
                                            <h3> Contatti </h3>
                                            E-mail: <%= loggedPerson.getSecondaryEmail()%> <br>

                                            <% } %>
                                            <% if (loggedPerson.getTypeAccount().equals("phdstudent")) {%>
                                        <td>
                                            <h3> Contatti </h3>
                                            <p> 
                                                Telefono: <%= ((PhdStudent) loggedPerson).getTelephone()%> <br>
                                                E-mail: <%= loggedPerson.getSecondaryEmail()%> <br>
                                                <% if (((PhdStudent) loggedPerson).getLink() != null) {%>
                                                Sito web: <a href="<%= ((PhdStudent) loggedPerson).getLink()%>" target="_blank"><%= ((PhdStudent) loggedPerson).getLink()%></a> <br>
                                                <%}
                                                    if (((PhdStudent) loggedPerson).getDepartment() != null) {%>
                                                <%= ((PhdStudent) loggedPerson).getDepartment()%> <br>
                                                <% }%>


                                            </p>
                                        </td>
                                        <% } %>
                                        <% if (loggedPerson.getTypeAccount().equals("professor")) {%>
                                        <td>
                                            <h3> Contatti</h3>
                                            <p> 

                                                E-mail: <%= loggedPerson.getSecondaryEmail()%> <br>
                                                <% if (((Professor) loggedPerson).getLink() != null) {%>
                                                Sito web: <a href="<%= ((Professor) loggedPerson).getLink()%>" target="_blank"><%= ((Professor) loggedPerson).getLink()%></a> <br>
                                                <%}
                                                    if (((Professor) loggedPerson).getDepartment() != null) {%>
                                                <%= ((Professor) loggedPerson).getDepartment()%> <br>
                                                <% }%>

                                            </p>
                                        </td>
                                        <% } %>

                                    </tr>

                                    <% if (loggedPerson.getTypeAccount().equals("phdstudent")) {%>

                                    <%
                                        List<Publication> publications = PublicationManager.getInstance().getAllPublicationsOf((PhdStudent) loggedPerson);
                                        List<Collaboration> collaborations = CollaborationManager.getInstance().getAllCollaborationOf((PhdStudent) loggedPerson);
                                        List<Mission> missions = MissionManager.getInstance().getAllMissionsOf((PhdStudent) loggedPerson);
                                    %>
                                    <tr>
                                        <td colspan="2">
                                            <% File file = new File(getServletContext().getRealPath("") + "\\tesi" + File.separator + loggedPerson.getEmail() + ".pdf");
                                               if(file.exists()) {
                                            %>
                                            <br>
                                            <br>
                                            <a href="tesi\<%= loggedPerson.getEmail() + ".pdf"%>" download><b>Download Tesi</b></a>
                                         <%} %>
                                            <br>
                                            <h3 id = "diocane"> Attività di ricerca </h3> <br>
                                            <p class="text-justify" > 
                                                <%= ((PhdStudent) loggedPerson).getResearchInterest()%> <br> <br>
                                            </p>

                                            <p class="text-justify"> 
                                            <h3>Registro attività: <span id ="showArrow" class="glyphicon glyphicon-chevron-down pointer" aria-hidden="true" onclick="show();"></span></h3>
                                            </p>
                                            <div id="rawr">
                                                <div class="panel-heading">
                                                    <ul class="nav nav-tabs" >
                                                        <li class="bottoni" role="presentation" onclick="display('table-pub')"><a>Pubblicazioni</a></li>
                                                        <li class="bottoni" role="presentation" onclick="display('table-coll')"><a>Collaborazioni</a></li>
                                                        <li class="bottoni" role="presentation" onclick="display('table-miss')"><a>Mission</a></li>
                                                    </ul>
                                                    <br>



                                                </div>
                                                <div class="panel-body" id="table-pub">
                                                    <button type="button" class="btn btn-xs" aria-label="Left Align" onclick="location.href = 'addPublication.jsp'">
                                                        <span id="showArrow" class="glyphicon glyphicon-plus" aria-hidden="true"> </span> Aggiungi pubblicazione
                                                    </button>
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

                                                            <% session.setAttribute("idPublication" + publications.indexOf(publication),publication.getIdPublication());%>

                                                            <td width="20px"> <button type="button" class="btn btn-white" title="modifica">
                                                                    <span class="glyphicon glyphicon-cog" aria-hidden="true" onclick="location.href = '<%= "editPublication.jsp?id=" + publication.getIdPublication()%>'" ></span>
                                                                </button>
                                                            </td>
                                                            <td width="20px">
                                                                <button type="button" class="btn btn-white"title="delete">
                                                                    <span class="glyphicon glyphicon-remove" aria-hidden="true" onclick="location.href = '<%= "DeletePublicationServlet?id=" + publication.getIdPublication()%>'" ></span>
                                                                </button>
                                                            </td>
                                                        </tr>
                                                        <% }%>
                                                    </table>
                                                </div>

                                                <div class="panel-body" id="table-coll">
                                                    <button type="button" class="btn btn-xs " aria-label="Left Align" onclick="location.href = 'addCollaboration.jsp'">
                                                        <span class="glyphicon glyphicon-plus" aria-hidden="true" ></span> Aggiungi collaborazione
                                                    </button>
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

                                                            <% session.setAttribute("idCollaboration" + collaborations.indexOf(collaboration), collaboration.getIdCollaboration());%>

                                                            <td width="20px"> <button type="button" class="btn btn-white" title="modifica">
                                                                    <span class="glyphicon glyphicon-cog" aria-hidden="true" onclick="location.href = '<%= "editCollaboration.jsp?id=" + collaboration.getIdCollaboration()%>'" ></span>
                                                                </button>
                                                            </td>
                                                            <td width="20px">
                                                                <button type="button" class="btn btn-white" title="delete">
                                                                    <span class="glyphicon glyphicon-remove" aria-hidden="true" onclick="location.href = '<%= "DeleteCollaborationServlet?id=" + collaboration.getIdCollaboration()%>'" ></span>
                                                                </button>
                                                            </td>
                                                        </tr>
                                                        <% }%>
                                                    </table>
                                                </div>
                                                <div class="panel-body" id="table-miss">
                                                    <button type="button" class="btn btn-xs" aria-label="Left Align" onclick="location.href = 'addMission.jsp'">
                                                        <span class="glyphicon glyphicon-plus" aria-hidden="true" ></span> Aggiungi mission
                                                    </button>
                                                    <table class="table table-hover" width="98%" align="center" >
                                                        <thead>
                                                        <th>Luogo</th>
                                                        <th>Descrizione</th>
                                                        <th>Referenza</th>
                                                        <th>Data Di Inizio</th>
                                                        <th>Data Di Fine</th>
                                                        </thead>

                                                        <% for (Mission mission : missions) {%>

                                                        <tr>
                                                            <td><%= mission.getPlace()%></td>
                                                            <td><%= mission.getDescription()%></td>
                                                            <td><%= mission.getReference()%></td>
                                                            <td><%= mission.getStartDate()%></td>
                                                            <td><%= mission.getEndDate()%></td>

                                                            <% session.setAttribute("idMission" + missions.indexOf(mission), mission.getIdMission());%>



                                                            <td width="20px"> <button type="button" class="btn btn-white" title="modifica">
                                                                    <span class="glyphicon glyphicon-cog" aria-hidden="true"onclick="location.href = '<%= "editMission.jsp?id=" + mission.getIdMission()%>'" ></span>
                                                                </button>
                                                            </td>
                                                            <td width="20px">
                                                                <button type="button" class="btn btn-white"title="delete">
                                                                    <span class="glyphicon glyphicon-remove" aria-hidden="true" onclick="location.href = '<%= "DeleteMissionServlet?id=" + mission.getIdMission()%>'" ></span>
                                                                </button>
                                                            </td>
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