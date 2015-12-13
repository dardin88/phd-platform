<%@page contentType="text/html" pageEncoding="UTF-8"%>                                                                 
<%@page import="it.unisa.dottorato.account.*;"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<nav class="navbar horizontal-menu navbar-fixed-top">
  <div class="navbar-inner">
        <ul id="main-menu" class="navbar-nav">
            <!-- add class "multiple-expanded" to allow multiple submenus to open -->
            <!-- class "auto-inherit-active-class" will automatically add "active" class for parent elements who are marked already with class "active" -->
             <div class="navbar-brand">
                 <a href="index.jsp" class="logo">
                    <img src="../assets/images/mitforsite.png" width="80" alt="" class="hidden-xs" />
                    <img src="../assets/images/mitforsitemini.png" width="80" alt="" class="visible-xs" />
                 </a>
             </div>

            <li>
                <a href="index.jsp">
                    <i class="linecons-desktop"></i>
                    <span class="title">Home</span>
                </a>
            </li>

            <li>
                <a href="ricerca.jsp"> <!-- anche se non esiste ancora -->
                    <i class="linecons-search"></i>
                    <span class="title">Ricerca Utente</span>
                </a>
            </li>
            
            <c:choose>
                <c:when test="${sessionScope.account == null}">
            <li class="navbar-right" id ="funzionalitaBase">
                <a href="login.jsp">
                    <i class="linecons-key"></i>
                    <span class="title">Login</span>
                </a>
            </li>
                </c:when>
            </c:choose>

            <c:choose>
                <c:when test="${sessionScope.account != null}">
                    <% Account loggedPerson = ((Account) session.getAttribute("account"));
                        if (loggedPerson.getTypeAccount().equals("phdstudent") || loggedPerson.getTypeAccount().equals("professor")) {
                    %>  
                    
                    <li id="funzionalitaBase">
                          <a href="gestionepresenze.jsp">
                          <i class="linecons-wallet"></i>
                             <span class="title">Gestione Presenze</span>
                          </a>
                    </li>
                         

                    <li id="funzionalita3Permission_0">
                        <a href="#">
                            <i class="linecons-calendar"></i>
                            <span class="title">Calendario <br> corsi e seminari</span>
                        </a>
                    </li>
                                     


                    <%}  if (loggedPerson.isAdmin()) { %>

                    <li id="funzionalita3Permission_0">
                        <a href="amministrazione.jsp">
                            <i class="linecons-key"></i>
                            <span class="title">Pannello <br> amministratore</span>
                        </a>
                    </li>
                    <%}%>
                    
                     <li class="navbar-right" id="menu_profilo">
                        <a href="profile.jsp">
                            <i class="linecons-user"></i>
                            <span class="title" id="prova">Ciao <%{loggedPerson.getName();}%> !</span>
                        </a>
                    </li>
                    
                    <li id="">
                        <a href="/logout">
                            <i class =linecons-key"></i>
                            <span class="logout" id ="prova">Logout</span>
                        </a>
                    </li>

                </c:when>
            </c:choose>
        </ul>
   </div>
</nav>
