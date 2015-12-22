<%-- 
    Document   : searchUser
    Created on : 12-dic-2015, 18.36.16
    Author     : Rembor
--%>

<%@page import="java.util.List"%>
<%@page import="it.unisa.dottorato.account.Account"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="it.unisa.dottorato.account.AccountManager"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Search User</title>

    <!-- Bootstrap e i fogli di stile vado a fiducia  -->
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
        <script type="text/javascript" src="script/search.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
  </head >

  
    <body class="page-body">
        <!-- Inclusione della pagina contenente il menù superiore -->
   
       <% 
                List<Account> rAccount = AccountManager.getInstance().getAccountList();
                             %>  
        
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

    <!--Qui chiama servlet update che prende infomazioni Account--> 
              
         
  

<div class="page-body">
     <jsp:include page="barraMenu.jsp"/>
    <div class="page-container"> 
     <div  class="well col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-xs-12">
         
        <form id="myForm" class="form-inline" action="#" method="post">
            <div class="input-group col-lg-8 col-md-8 col-sm-8 pull-left col-xs-8">
                <input id="word" class="form-control" type="text" value="" placeholder="Search" name="q" autofocus="autofocus" />
                
         
                
                
                <div class="input-group-btn" >
  <select class="form-control" id="sel1"  name="sel1" >
    <option value=null>Seleziona il tipo di utente</option>
    <option value="Dottorando">Dottorando</option>
    <option value="Docente">Docente</option>
</select>

</div>  
                <!-- /btn-group -->
            </div>
            <button id="search" data-style="slide-left" class="btn btn-success col-lg-3 col-md-3 col-sm-3 col-xs-3 pull-right" type="submit"> <i id="icon" class="fa fa-search"></i>Search</button>
               
        </form>
    </div>
    <div id="results" class="well col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-xs-12">
        <table id="results" class="table table-hover table-striped table-condensed">
            <thead>
                <tr>
                    
                    <th data-field="typeAccount" data-sortable="true">Tipo di Account</th>
                    <th data-field="name" data-sortable="true">Nome</th>
                    <th data-field= "surname" data-sortable="true">Cognome</th>
                    <th data-field="email" data-sortable="true">Email</th>
                </tr>
            </thead>
            <tbody id="gattini">
                <% for (Account rsAccount : rAccount) {%>
                
                <tr>
                    
                    <td><%= rsAccount.getTypeAccount()%></td>
                    <td><%= rsAccount.getName() %></td>
                    <td><%= rsAccount.getSurname()%></td>
                    <td><%= rsAccount.getEmail()%></td>
                    
                    
                </tr><% }%>
            </tbody>
        </table>
    </div>
</div>

    </div>
    </body>
</html>