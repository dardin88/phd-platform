<%-- 
    Document   : TestPres
    Created on : 26-apr-2016, 16.41.04
    Author     : cadav
--%>

<%@page import="it.unisa.dottorato.account.Account"%>
<%@page import="it.unisa.dottorato.presence.PresenceManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:choose>
    <c:when test="${sessionScope.account == null}">
        <c:redirect url="login.jsp" />
    </c:when>
</c:choose>
        
<html>
    <head>
           <script src="assets/js/jquery-1.11.1.min.js"></script>
          <script type="text/javascript" src="script/getStats.js"></script>
           	<script type='text/javascript' src='//code.jquery.com/jquery-1.9.1.js'></script>   
        <script type='text/javascript' src="https://raw.githubusercontent.com/nnnick/Chart.js/v2.0-dev/dist/Chart.js"></script>
        
        

        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Statistiche studente</title>
           <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Arimo:400,700,400italic">
        <link rel="stylesheet" href="assets/css/fonts/linecons/css/linecons.css">
        <link rel="stylesheet" href="assets/css/fonts/fontawesome/css/font-awesome.min.css">
        <link rel="stylesheet" href="assets/css/bootstrap.css">
        <link rel="stylesheet" href="assets/css/xenon-core.css">
        <link rel="stylesheet" href="assets/css/xenon-forms.css">
        <link rel="stylesheet" href="assets/css/xenon-components.css">
        <link rel="stylesheet" href="assets/css/xenon-skins.css">
        <link rel="stylesheet" href="assets/css/custom.css">
                <link rel="stylesheet" href="assets/css/ChartScale.css">
    </head>
        <c:choose>
                <c:when test="${sessionScope.account != null}">
                    <% Account loggedPerson = ((Account) session.getAttribute("account"));
                        if (loggedPerson.getTypeAccount().equals("phdstudent")) {
                    %> 
                    
    <body class="page-body">
        <div class="page-body">
             <!-- Inclusione della pagina contenente il menù superiore -->
             <jsp:include page="barraMenu.jsp"/>
             <!-- Contenuto della pagina -->
        <div class="page-container">
                <div class="main-content">
                    <div class="row">
 
                        <div class="col-sm-1"></div>
 
                        <div class="col-sm-10">
                           
                            <div class="form-group">
   <FORM ACTION="Submit" METHOD="POST"> 
                                <label  > Seleziona uno dei corsi seguiti</label>
                                
                                <select name="Corsoseguito" class="form-control" id="Corsoseguito" onchange="getPresenze()">
                       
                                    <option value="default"  >  - selezionate il vostro  corso  -  </option>
                                    
               
  
                                </select>
   </form>
                                </div>
                            </div>
                        </div>
              </div>               
           </div>
  </div>
       <div id="resulthead">
           <p id="summary" style="font-size: 20px; color:black; text-align: center; margin-top: 3% ">
               
           </p>
                                             
                                        </div>
        
        <div class="graphic" > 
               <canvas id="chart2" >
 
</canvas>
        </div>
        
      
<div class="graphic">
             <canvas id="chart" >
 
</canvas>
    </div>
        
    </body>
    <%}else{%>
                <c:redirect url="index.jsp" />
          <%  }
    %>
            </c:when>
        </c:choose>
</html>