<%-- 
    Document   : searchUser
    Created on : 12-dic-2015, 18.36.16
    Author     : Rembor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="it.unisa.dottorato.account.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Search User</title>

    <!-- Bootstrap e i fogli di stile vado a fiducia  -->
    <link rel="stylesheet" href="../assets/css/fonts/linecons/css/linecons.css">
        <link rel="stylesheet" href="../assets/css/fonts/fontawesome/css/font-awesome.css">
        <link rel="stylesheet" href="../assets/css/bootstrap.css">
        <link rel="stylesheet" href="../assets/css/xenon-core.css">
        <link rel="stylesheet" href="../assets/css/xenon-forms.css">
        <link rel="stylesheet" href="../assets/css/xenon-components.css">
        <link rel="stylesheet" href="../assets/css/xenon-skins.css">
        <link rel="stylesheet" href="../assets/css/custom.css">
        <link rel="stylesheet" href="style/dottorato.css">
        <script src="../assets/js/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="script/index.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head >

  
    <body class="page-body">
        <!-- Inclusione della pagina contenente il menù superiore -->
        <jsp:include page="barraMenu.jsp" flush="true"/>
      
        
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

    <!--Qui chiama servlet update che prende infomazioni Account--> 
                        <% Account persona = ((Account) session.getAttribute("account"));
                           String id = request.getParameter("id");
//se è stato aggiunto un nome al search
if (id != null){
    //acquisisci i dati del CD
    String artista=request.getParameter("artista");
    String titolo=request.getParameter("titolo");
    float prezzo = Float.parseFloat(request.getParameter("prezzo"));
    //e aggiungilo al carrello
    car.aggiungiCd (id, artista, titolo, prezzo);
}//if

                        %>
   

<div class="container-fluid">
    <div class="well col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-xs-12">
        <form class="form-inline" action="#" method="post">
            <div class="input-group col-lg-8 col-md-8 col-sm-8 pull-left col-xs-8">
                <input id="word" class="form-control" type="text" value="" placeholder="Search" name="q" autofocus="autofocus" />
                <div class="input-group-btn">
                    
                    <button type="button" class="btn btn-default dropdown-toggle" id="x" data-toggle="dropdown">Seleziona il tipo di utente<span class="caret"></span>
            </button>
                    
                    <ul class="dropdown-menu">
                        <li><a href="#">Docente</a>

                        </li>
                        <li><a href="#">Dottorando</a>

                        </li>
                        
                    </ul>
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
                    <th></th>
                    <th>Tipo di Account</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </tbody>
        </table>
    </div>
</div>

    </body>
</html>
