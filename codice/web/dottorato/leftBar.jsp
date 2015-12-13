<%-- 
    Document   : leftBar
    Created on : 12-dic-2015, 21.20.01
    Author     : andre
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
     <!-- Left MenuBar--> 
        
        <div class="sidebar-menu toggle-others">
            <div class="sidebar-menu-inner"> 
                <ul id="main-menu" class="main-menu">
	            <!-- add class "multiple-expanded" to allow multiple submenus to open -->
	            <!-- class "auto-inherit-active-class" will automatically add "active" class for parent elements who are marked already with class "active" -->
                     <li>
                        <a href="amministrazioneCurriculum.jsp">
	                   <span class="title">Gestione Curriculum</span>
                        </a>
                     </li>
                     <li>
                        <a href="amministrazioneTutorato.jsp">
	                   <span class="title">Gestione Tutorato</span>
                        </a>
                     </li>
                     <li>
                        <a href="amministrazioneAccount.jsp">
	                   <span class="title">Gestione Account</span>
                        </a>
                     </li>
                     <li>
                        <a href="amministrazioneCurriculumcic.jsp">
	                   <span class="title">Gestione Ciclo-Curriculum</span>
                        </a>
                     </li>
                     <li>
                        <a href="amministrazioneAvvisi.jsp">
	                   <span class="title">Gestione Avvisi</span>
                        </a>
                     </li>
                </ul>
            </div>
	</div>
</html>
