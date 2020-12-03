<%-- 
    Document   : register
    Created on : Sep 21, 2020, 8:17:01 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verify Page</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js" type="text/javascript"></script>
        <script src="js/additional-methods.js" type="text/javascript"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
    </head>
    <body>
        <div class="container">
            <div class="card bg-light" style="margin: 15%;border-color: #9400d3;">
                <article class="card-body mx-auto" style="max-width: 400px;">
                    <h4 class="card-title mt-3 text-center" >Create Account</h4>
                    <form action="MainController" method="Post">
                        <div class="form-group input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"> <i class="fas fa-shield"></i> </span>
                            </div>
                            <div>
                                <input name="code" class="form-control" placeholder="Input code verify" required="true">
                            </div>
                        </div> 
                        <div class="form-group">
                            <button type="submit" name="action" value="Regist" class="btn btn-primary btn-block" style="background: #9400d3 ; border-radius:25px; "> Verify Account </button>
                        </div> 
                        <p class="text-center">Have an account? <a href="index.jsp">Log In</a> </p>
                    </form>
                    <c:if test="${not empty requestScope.ERROR}">
                        <div class="alert alert-danger">
                            <strong>ERROR!</strong> ${requestScope.ERROR}
                        </div>
                    </c:if>
                </article>
            </div> <!-- card.// -->
        </div>
    </body>
</html>
