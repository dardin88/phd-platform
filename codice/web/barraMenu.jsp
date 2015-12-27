<%@page contentType="text/html" pageEncoding="UTF-8"%>                                                                 
<%@page import="it.unisa.dottorato.account.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<nav class="navbar horizontal-menu navbar-fixed-top">
  <div class="navbar-inner">
        <ul id="main-menu" class="navbar-nav">
            <!-- add class "multiple-expanded" to allow multiple submenus to open -->
            <!-- class "auto-inherit-active-class" will automatically add "active" class for parent elements who are marked already with class "active" -->
             <div class="navbar-brand">
                 <a href="index.jsp" class="logo">
                    <img src="assets/images/mitforsite.png" width="80" alt="" class="hidden-xs" />
                    <img src="assets/images/mitforsitemini.png" width="80" alt="" class="visible-xs" />
                 </a>
             </div>

            <li>
                <a href="index.jsp">
                    <i class="linecons-desktop"></i>
                    <span class="title">Home</span>
                </a>
            </li>

            <li>
                <a href="searchUser.jsp">
                    <i class="linecons-search"></i>
                    <span class="title">Ricerca Utente</span>
                </a>
            </li>
        </ul>
            
            <c:choose>
                <c:when test="${sessionScope.account == null}">
           <ul class="navbar-nav navbar-right">
            <li class="navbar-right" id ="funzionalitaBase">
                <a href="login.jsp">
                    <i class="linecons-key"></i>
                    <span class="title">Login</span>
                </a>
            </li>
           </ul>
                </c:when>
            </c:choose>

            <c:choose>
                <c:when test="${sessionScope.account != null}">
                    <% Account loggedPerson = ((Account) session.getAttribute("account"));
                        if (loggedPerson.getTypeAccount().equals("phdstudent") || loggedPerson.getTypeAccount().equals("professor")) {
                    %>  
                    <ul class="navbar-nav">
                    <li id="funzionalitaBase">
                          <a href="RegistroPresenze.jsp">
                          <i class="linecons-wallet"></i>
                             <span class="title">Gestione Presenze</span>
                          </a>
                    </li>
                         

                    <li id="funzionalita3Permission_0">
                        <a href="calendario.jsp">
                            <i class="linecons-calendar"></i>
                            <span class="title">Calendario corsi e seminari</span>
                        </a>
                    </li>
                    
                                     


                    <%}  if (loggedPerson.isAdmin()) { %>

                    <li id="funzionalita3Permission_0">
                        <a href="amministrazioneCurriculum.jsp">
                            <i class="linecons-key"></i>
                            <span class="title">Pannello amministratore</span>
                        </a>
                    <%}%>
                    </li>
                    </ul>
                   <ul class="navbar-nav navbar-right"> 
                     <li class="navbar-right" id="menu_profilo">
                        <a href="profileNuovo.jsp">
                            <i class="linecons-user"></i>
                            <span class="title" id="prova"> <b><% out.println(loggedPerson.getName());%>
                                      <%out.println(loggedPerson.getSurname());%></b></span>
                        </a>
                    </li>
                    
                    <li class="navbar-right" id="logout">
                        <a href="logout">
                            <i class ="linecons-key"></i>
                            <span class="logout" id ="prova">Logout</span>
                        </a>
                    </li>
                   </ul>
                </c:when>
            </c:choose>
        </ul>
   </div>
</nav>
