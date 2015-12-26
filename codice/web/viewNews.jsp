<%@page import="it.unisa.dottorato.news.NewsManager"%>
<%@page import="it.unisa.dottorato.news.News"%>
<%@page import="java.util.List"%>
<%@page import="it.unisa.dottorato.Cycle.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.unisa.dottorato.account.AccountManager"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta name="description" content="Xenon Boostrap Admin Panel" />
        <meta name="author" content="matteonardone" />

        <title>PhD-Platform</title>

        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Arimo:400,700,400italic">
        <link rel="stylesheet" href="assets/css/fonts/linecons/css/linecons.css">
        <link rel="stylesheet" href="assets/css/fonts/fontawesome/css/font-awesome.css">
        <link rel="stylesheet" href="assets/css/bootstrap.css">
        <link rel="stylesheet" href="assets/css/xenon-core.css">
        <link rel="stylesheet" href="assets/css/xenon-forms.css">
        <link rel="stylesheet" href="assets/css/xenon-components.css">
        <link rel="stylesheet" href="assets/css/xenon-skins.css">
        <link rel="stylesheet" href="assets/css/custom.css">

        <script src="assets/js/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="script/index.js"></script>
        
    </head>
    <body class="page-body">

        <jsp:include page="barraMenu.jsp" flush="true"/>

        <!--BODY-->
        <script>
param_name=new Array();
param_value=new Array();

indirizzo=unescape(String(this.location));
params=indirizzo.split("?");
param=params[1].split("&");

for(i=0;i<param.length;i++){
param_temp=param[i].split("=");
param_name[i]=param_temp[0];
param_value[i]=param_temp[1];

if(isNaN(param_value[i])) eval("var "+param_name[i]+"='"+param_value[i]+"';");
else eval("var "+param[i]+";");
}
            alert(param_value[0]);
            News avviso = NewsManager.getInstance().getNewsById(param_value[0]);
            
      </script> 

        <div class="page-container">
            <div class="main-content">
                <div class="row">
                    <!--<div class="col-sm-1"></div>-->
                    <div class="col-sm-6">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <script>=avviso.getDescription()</script>
                                  
                            </div>
                            <div class="panel-body">
                                

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>