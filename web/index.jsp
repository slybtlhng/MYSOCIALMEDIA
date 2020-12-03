<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Login Page</title>
        <link href="https://fonts.googleapis.com/css?family=Poppins:600&display=swap" rel="stylesheet">
        <script src="https://kit.fontawesome.com/a81368914c.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <style type="text/css">
            #login label.error{
                color:red;
                width:250px;
                font-style: italic;
            }
        </style>
        <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js" type="text/javascript"></script>
        <script src="js/additional-methods.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" href="css/style1.css">
    </head>
    <body>
        <div class="container">
            <div class="img">
                <img src="image/login1.jpg">
            </div>
            <div class="login-content">
                <div style="width: 100%; justify-content: center;">
                    <form action="MainController" method="POST" id="login" >
                        <h2 class="title">Welcome</h2>
                        <div class="input-div one">
                            <div class="i">
                                <i class="fas fa-user"></i>
                            </div>
                            <div class="div">
                                <h5>Email</h5>
                                <input type="text" class="input" name="email">
                            </div>
                        </div>
                        <div class="input-div pass">
                            <div class="i"> 
                                <i class="fas fa-lock"></i>
                            </div>
                            <div class="div">
                                <h5>Password</h5>
                                <input type="password" class="input" name="password">
                            </div>
                        </div>
                        <div class="register">
                            <a href="register.jsp">Register</a>
                        </div>
                        <input type="submit" class="btn" value="Login" name="action" >
                    </form>
                </div>
                <div style="width: 100%;">
                    <font color="red">
                    ${requestScope.ERROR}
                    </font>
                </div>
                <div style="width: 100%;">
                    <font color="red">
                    ${requestScope.SUCCESS}
                    </font>
                </div>
            </div>
        </div>

        <script type="text/javascript" src="js/main.js"></script>
    </body>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#login").validate({
                rules: {
                    password: {
                        required: true,
                        rangelength: [4, 30]
                    },
                    email: {
                        required: true,
                        email: true
                    }
                }
            });
        });
    </script>
</html>
