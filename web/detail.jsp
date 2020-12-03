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
                <c:if test="${not empty ARTICLE}" var="checkNull">
                    <div class="col-md-6 gedf-main">
                        <!-- post article start -->
                        <div class="card gedf-card">
                            <div class="card-header">
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="d-flex justify-content-between align-items-center">
                                        <div class="mr-2">
                                            <img class="rounded-circle" width="45" src="image/avatar.jpg" alt="">
                                        </div>
                                        <div class="ml-2">
                                            <div class="h5 m-0">${ARTICLE.articlePostBy.userEmail}</div>
                                            <div class="h7 text-muted" >${ARTICLE.articlePostBy.userName}</div>
                                        </div>
                                    </div>
                                    <div>
                                        <c:if
                                            test="${sessionScope.INFO.userRole == 'Admin' or sessionScope.INFO.userEmail==ARTICLE.articlePostBy.userEmail}">
                                            <c:url var="deleteArticle" value="MainController">
                                                <c:param name="articleID" value="${ARTICLE.articleID}" />
                                                <c:param name="notiUserID" value="${ARTICLE.articlePostBy.userEmail}"/>
                                                <c:param name="action" value="DeleteArticle" />
                                            </c:url>
                                            <a href="${deleteArticle}" style="color: #6b0098;" onclick="return confirm('Are you sure?')">
                                                <span class="fa fa-trash"></span> Delete</a>
                                            </c:if>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <div class="text-muted h7 mb-2"> <i class="fa fa-clock-o"></i>
                                    <fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${ARTICLE.articleDate}" />
                                </div>
                                <c:url var="detailArticle" value="MainController">
                                    <c:param name="articleID" value="${ARTICLE.articleID}" />
                                    <c:param name="action" value="Detail" />
                                </c:url>
                                <a class="card-link" href="${detailArticle}">
                                    <h5 class="card-title" style="color:#6b0098;">${ARTICLE.articleTitle}</h5>
                                </a>
                                <p class="card-text">
                                    ${ARTICLE.articleDescription}
                                </p>
                            </div>
                            <c:if test="${not empty ARTICLE.articleImage}">
                                <img class="card-img-bottom" src="image/${ARTICLE.articleImage}" alt="Card image cap">
                            </c:if>
                            <div class="card-footer">
                                <c:url var="LikeEmotion" value="MainController">
                                    <c:param name="articleID" value="${ARTICLE.articleID}" />
                                    <c:param name="userEmail" value="${sessionScope.INFO.userEmail}" />
                                    <c:param name="notiUserEmail" value="${ARTICLE.articlePostBy.userEmail}" />
                                    <c:param name="emotionContent" value="Like" />
                                    <c:param name="action" value="AddEmotion" />
                                </c:url>
                                <c:url var="DislikeEmotion" value="MainController">
                                    <c:param name="articleID" value="${ARTICLE.articleID}" />
                                    <c:param name="userEmail" value="${sessionScope.INFO.userEmail}" />
                                    <c:param name="notiUserEmail" value="${ARTICLE.articlePostBy.userEmail}" />
                                    <c:param name="emotionContent" value="Dislike" />
                                    <c:param name="action" value="AddEmotion" />
                                </c:url>
                                <c:if test="${sessionScope.INFO.userRole eq'Member'}" var="checkRole">
                                    <a href="${LikeEmotion}" class="card-link" style="color:#6b0098;"><i class="fa fa-thumbs-up"></i>
                                        Like(${ARTICLE.articleNumOfLike})</a>
                                    <a href="${DislikeEmotion}" class="card-link" style="color:#6b0098;"><i class="fa fa-thumbs-down"></i>
                                        Dislike(${ARTICLE.articleNumOfDislike})</a>
                                    </c:if>
                                    <c:if test="${!checkRole}">
                                    <span>                                
                                        <i class="fa fa-thumbs-up" style="color:#6b0098;">Like(${ARTICLE.articleNumOfLike})</i>
                                    </span>
                                    <span>
                                        <i class="fa fa-thumbs-down" style="color:#6b0098;">Dislike(${ARTICLE.articleNumOfDislike})</i>
                                    </span>
                                </c:if>
                            </div>
                            <!--phan comment-->
                            <div class="card border border-right-0 border-left-0 border-bottom-0 mt-1">
                                <!-- new comment form -->
                                <c:if test="${sessionScope.INFO.userRole eq'Member'}">
                                    <section class="mt-3">
                                        <form action="MainController" method="POST">
                                            <div class="input-group input-group">
                                                <input type="text" name="comment" class="form-control"
                                                       placeholder="Write Comment" aria-label="Recipient's username"
                                                       aria-describedby="basic-addon2">
                                                <div class="input-group-append">
                                                    <input type="hidden" name="postBy"
                                                           value="${sessionScope.INFO.userEmail}" />
                                                    <input type="hidden" name="articleID"
                                                           value="${ARTICLE.articleID}" />
                                                    <input type="hidden" name="notiUserEmail"
                                                           value="${ARTICLE.articlePostBy.userEmail}" />
                                                    <input type="submit"
                                                           class="text-decoration-none text-white btn btn-primary"
                                                           value="Comment" name="action" />
                                                </div>
                                            </div>
                                        </form>
                                    </section>
                                    <!-- comment card bgins -->
                                </c:if>
                                <section>
                                    <c:if test="${requestScope.COMMENT!=null}" var="checkSearch">
                                        <c:forEach var="cmt" items="${requestScope.COMMENT}">
                                            <div class="card p-2 mt-3">
                                                <!-- comment header -->
                                                <div class="d-flex">
                                                    <div class="">
                                                        <img class="profile-pic" src="image/avatar.jpg" width="40"
                                                             height="40" alt="...">
                                                    </div>
                                                    <div class="flex-grow-1 pl-2">
                                                        <a class="text-decoration-none text-capitalize h6 m-0"
                                                           href="#">${cmt.commentPostBy.userEmail}</a>
                                                    </div>
                                                    <div>
                                                        <c:if
                                                            test="${sessionScope.INFO.userRole == 'Admin' or sessionScope.INFO.userEmail==cmt.commentPostBy.userEmail}">
                                                            <c:url var="deleteComment" value="MainController">
                                                                <c:param name="commentID"
                                                                         value="${cmt.commentID}" />
                                                                <c:param name="notiUserEmail"
                                                                         value="${cmt.commentPostBy.userEmail}" />
                                                                <c:param name="articleID"
                                                                         value="${ARTICLE.articleID}" />
                                                                <c:param name="action" value="DeleteComment" />
                                                            </c:url>
                                                            <a href="${deleteComment}" style="color: #6b0098;" onclick="return confirm('Are you sure?')">
                                                                <span class="fa fa-trash"></span></a>
                                                            </c:if>
                                                    </div>
                                                </div>
                                                <!-- comment header -->
                                                <!-- comment body -->
                                                <div class="card-body p-0">
                                                    <p class="card-text h7 mb-1">${cmt.commentContent}</p>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </c:if>
                                </section>
                                <!-- comment card ends -->
                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${!checkNull}">
                    <div class="alert alert-danger">
                        <strong>ERROR!</strong> Post not exist!
                    </div>
                </c:if>      
            </div>
        </div>
    </body>
</html>
