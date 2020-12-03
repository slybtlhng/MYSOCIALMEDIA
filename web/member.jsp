<%-- 
    Document   : member.jsp
    Created on : Sep 22, 2020, 10:08:24 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Member Page</title>
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
        <script src="js/main.js"></script>
    </head>
    <body>
        <!--navbar start-->
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
                <div class="nav navbar-nav navbar-right">
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
        <div class="container-fluid gedf-wrapper" style="margin-top: 54px;">
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
                    <!--phan Search result start-->
                    <c:if test="${requestScope.SEARCH!=null}" var="checkSearch">
                        <c:if test="${not empty requestScope.LISTARTICLE}" var="checkList">
                            <c:forEach var="article" items="${requestScope.LISTARTICLE}">
                                <br /><br />
                                <c:url var="detailArticle" value="MainController">
                                    <c:param name="articleID" value="${article.articleID}" />
                                    <c:param name="action" value="Detail" />
                                </c:url>
                                <div class="card gedf-card" >
                                    <div class="card-header">
                                        <div class="d-flex justify-content-between align-items-center">
                                            <div class="d-flex justify-content-between align-items-center">
                                                <div class="mr-2">
                                                    <img class="rounded-circle" width="45" src="image/avatar.jpg" alt="">
                                                </div>
                                                <div class="ml-2">
                                                    <div class="h5 m-0">${article.articlePostBy.userEmail}</div>
                                                    <div class="h7 text-muted">${article.articlePostBy.userName}</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <div class="text-muted h7 mb-2"> <i class="fa fa-clock-o"></i>
                                            <fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${article.articleDate}" />
                                        </div>
                                        <a class="card-link" href="${detailArticle}">
                                            <h5 class="card-title" style="color:#6b0098;">${article.articleTitle}</h5>
                                        </a>

                                        <p class="card-text">
                                            ${article.articleDescription}
                                        </p>
                                    </div>
                                    <c:if test="${not empty article.articleImage}">
                                        <img class="card-img-bottom" src="image/${article.articleImage}" alt="Card image cap">
                                    </c:if>
                                </div>
                            </c:forEach>
                            <ul id="pagination-demo" class="pagination-sm"></ul>
                            <form action="MainController" method="GET" id="pagingSubmit">
                                <input type="hidden" name="content" value="${requestScope.SEARCH}">
                                <input type="hidden" name="action" value="Search">
                                <input type="hidden" value="" id="currentPage" name="currentPage" />
                            </form>
                        </c:if>
                        <c:if test="${!checkList}">
                            <font color="red">
                            No record found!
                            </font>
                        </c:if>
                    </c:if>
                    <!--- \\\\\\\Post-->

                    <!-- post article start -->
                    <c:if test="${!checkSearch and sessionScope.INFO.userRole eq'Member'}">
                        <div class="card gedf-card">
                            <div class="card-body">
                                <form action="MainController" method="POST" enctype="multipart/form-data">
                                    <div class="tab-content" id="myTabContent">
                                        <div class="tab-pane fade show active" id="posts" role="tabpanel"
                                             aria-labelledby="posts-tab">
                                            <div class="form-group">
                                                <label class="sr-only" for="message">title</label>
                                                <input required="true" name="title" class="form-control" id="message" rows="3"
                                                       placeholder="Title" />
                                            </div>
                                            <div class="form-group">
                                                <label class="sr-only" for="message">post</label>
                                                <textarea required="true" name="description" class="form-control" id="message"
                                                          rows="3" placeholder="What are you thinking?"></textarea>
                                            </div>
                                            <div class="form-group custom-file">
                                                <input type="file" name="fileImage" class="custom-file-input" id="file-input" rows="3"
                                                       placeholder="Image" accept="image/*"/>
                                                <label class="custom-file-label" for="customFile" id="txtFileName">Choose file</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="btn-toolbasr justify-content-between">
                                        <div class="btn-group" style="margin-top: 10px; ">
                                            <button type="submit" value="Post Article" name="action"
                                                    class="btn btn-primary" style="background:#6b0098; color: #ffffff;">Post Article</button>
                                        </div>
                                    </div>
                                    <input type="hidden" name="postBy" value="${sessionScope.INFO.userEmail}" />
                                </form>
                            </div>
                        </div>
                        <c:if test="${requestScope.VIEW!=null}" var="checkView">
                            <c:forEach var="article" items="${requestScope.VIEW}">
                                <div class="card gedf-card">
                                    <div class="card-header">
                                        <div class="d-flex justify-content-between align-items-center">
                                            <div class="d-flex justify-content-between align-items-center">
                                                <div class="mr-2">
                                                    <img class="rounded-circle" width="45" src="image/avatar.jpg" alt="">
                                                </div>
                                                <div class="ml-2">
                                                    <div class="h5 m-0">${sessionScope.INFO.userEmail}</div>
                                                    <div class="h7 text-muted">${sessionScope.INFO.userName}</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <div class="text-muted h7 mb-2"> <i class="fa fa-clock-o"></i>
                                            <fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${article.articleDate}" />
                                        </div>
                                        <c:url var="detailArticle" value="MainController">
                                            <c:param name="articleID" value="${article.articleID}" />
                                            <c:param name="action" value="Detail" />
                                        </c:url>
                                        <a class="card-link" href="${detailArticle}">
                                            <h5 class="card-title" style="color:#6b0098;">${article.articleTitle}</h5>
                                        </a>
                                        <p class="card-text">
                                            ${article.articleDescription}
                                        </p>
                                    </div>
                                    <c:if test="${not empty article.articleImage}">
                                        <img class="card-img-bottom" src="image/${article.articleImage}" alt="Card image cap">
                                    </c:if>
                                </div>
                            </c:forEach>
                        </c:if>
                    </c:if>
                </div>
            </div>
        </div>
        <!--phan post Article end-->
    </body>
    <script type="text/javascript">
        let totalPages = ${requestScope.TOTAL};
        let currentPage = ${requestScope.CURRENT};
        $('#pagination-demo').twbsPagination({
            totalPages: totalPages,
            visiblePages: 5,
            startPage: currentPage,
            onPageClick: function (event, page) {
                if (currentPage !== page) {
                    $('#currentPage').val(page);
                    $('#pagingSubmit').submit();
                }
            }
        });

    </script>
</html>
