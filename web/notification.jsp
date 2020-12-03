<%-- 
    Document   : detail
    Created on : Sep 27, 2020, 4:08:35 PM
    Author     : ASUS
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Notification Page</title>
        <link rel="stylesheet" href="css/stylePage.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="js/jquery.twbsPagination.js"></script>
        <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
              crossorigin="anonymous">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
    </head>
    <body>
        <nav class="navbar navbar-expand-sm fixed-top" style="background:#6b0098;">
            <div class="container-fluid">
                <div class="navbar-header">
                    <c:url var="ViewArticle" value="MainController">
                        <c:param name="action" value= "View"/>
                    </c:url>
                    <a href="${ViewArticle}" style="color: #ffffff;"><span class="fa fa-home"></span> Home</a>
                </div>
                <div class="nav navbar-nav">
                    <form class="navbar-form" method="POST" action="MainController">
                        <div class="input-group">
                            <input type="hidden" name="currentPage" value="1" />
                            <input type="text" class="form-control" placeholder="Search" name="content">
                            <div class="input-group-btn">
                                <button class="btn btn-default" type="submit" name="action" value="Search">
                                    <i class="fa fa-search" style="color: #ffffff;"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="nav navbar-nav navbar-right" >
                    <c:url var="Notification" value="MainController">
                        <c:param name="action" value="Notification"/>
                    </c:url>
                    <div style="margin-right: 10px;"><a href="${Notification}" style="color: #ffffff;"><span class="fa fa-bell"></span> Notification</a></div>
                    <c:url var="Logout" value="MainController">
                        <c:param name="action" value="Logout"/>
                    </c:url>
                    <a href="${Logout}" style="color: #ffffff;"><span class="fa fa-sign-out"></span> Logout</a>
                </div>
            </div>
        </nav>
        <!--navbar end-->
        <c:if test="${not empty requestScope.ERROR}">
            <div class="alert alert-danger">
                <strong>ERROR!</strong> ${requestScope.ERROR}
            </div>
        </c:if>
        <c:if test="${not empty requestScope.SUCCESS}">
            <div class="alert alert-success">
                <strong>SUCCESS!</strong> ${requestScope.SUCCESS}
            </div>
        </c:if>
        <!--phan gioi thieu starts-->
        <div class="container-fluid gedf-wrapper"style="margin-top: 54px;">
            <div class="row">
                <div class="col-md-3">
                    <div class="card" >
                        <div class="card-body" >
                            <div class="h5">${sessionScope.INFO.userEmail}</div>
                            <div class="h7 text-muted">Fullname : ${sessionScope.INFO.userName}</div>
                            <div class="h7 text-muted">Role : ${sessionScope.INFO.userRole}</div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 gedf-main">
                    <div class="card gedf-card">
                        <c:if test="${ not empty requestScope.NOTIFICATION_LIST}" var="checkList">
                            <c:forEach var="noti" items="${requestScope.NOTIFICATION_LIST}">
                                <div class="card-body">
                                    <div class="text-muted h7 mb-2"> <i class="fa fa-clock-o"></i>
                                        <fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${noti.notificationDate}" />
                                    </div>
                                    <c:url var="detailArticle" value="MainController">
                                        <c:param name="articleID" value="${noti.articleID.articleID}" />
                                        <c:param name="action" value="Detail" />
                                    </c:url>
                                    <a class="card-link" href="${detailArticle}">
                                        <h5 class="card-title" style="color:#6b0098;"> ${noti.userID.userEmail} ${noti.notificationContent} ${noti.articleID.articleTitle}</h5>
                                    </a>
                                </div>    
                            </c:forEach>
                        </c:if>
                        <c:if test="${!checkList}">
                            <div class="alert alert-danger">
                                <strong>Don't have any Notification!</strong> 
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
