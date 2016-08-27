
<%--
    Document   : risorse
    Created on : 26-ago-2016,
    Author     : Vincenzo
--%>

<%@page import="it.unisa.dottorato.account.*"%>
<%@ page import="java.io.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<c:choose>
	<c:when test="${sessionScope.account == null}">
		<c:redirect url="login.jsp" />
	</c:when>
</c:choose>
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Xenon Boostrap Admin Panel" />
<meta name="author" content="Vincenzo Vicidomini" />
<title>Risorse</title>

<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Arimo:400,700,400italic">
<link rel="stylesheet" href="assets/css/fonts/linecons/css/linecons.css">
<link rel="stylesheet"
	href="assets/css/fonts/fontawesome/css/font-awesome.css">
<link rel="stylesheet" href="assets/css/bootstrap.css">
<link rel="stylesheet" href="assets/css/xenon-core.css">
<link rel="stylesheet" href="assets/css/xenon-forms.css">
<link rel="stylesheet" href="assets/css/xenon-components.css">
<link rel="stylesheet" href="assets/css/xenon-skins.css">
<link rel="stylesheet" href="assets/css/custom.css">
<link rel="stylesheet" href="style/dottorato.css">
<link rel="stylesheet"
	href="http://getbootstrap.com/assets/css/docs.min.css">
<link rel="stylesheet" href="assets/js/calendar/jin.calendar.style.css">

<!-- <script type="text/javascript" src="assets/js/calendar/jin.calendar.js"></script> -->
<script src="assets/js/jquery-1.11.1.min.js"></script>
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>


</head>

<body class="page-body">
	<jsp:include page="barraMenu.jsp" />
	<div class="page-container">
		<div class="main-content" id="content">

			<!-- list files -->
			<H3>Risorse lezione</H3>
			<% 
            String file = application.getRealPath("")+"/files/"+request.getParameter("idLesson3");
            File f = new File(file);
            String [] fileNames = f.list();
            File [] fileObjects= f.listFiles();
        %>
			<UL>
				<%
				if(fileObjects!=null){
		            for (int i = 0; i < fileObjects.length; i++) {
		                if(!fileObjects[i].isDirectory()){
        %>
				<LI><A HREF="<%= fileNames[i] %>"><%= fileNames[i] %></A>
					&nbsp;&nbsp;&nbsp;&nbsp; (<%= Long.toString(fileObjects[i].length()) %>
					bytes long) <%
	                }
	            }
			} else {%>
			<p>Nessun file presente</p>
			<%}
        %>
			</UL>

		</div>
	</div>
</body>


</html>
