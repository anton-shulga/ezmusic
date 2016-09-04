<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 01.08.2016
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="property.page_content"/>
<html>
<head>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="../css/styles.css" media="screen,projection"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="../js/bin/materialize.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta charset="utf-8">
    <title>Login</title>
</head>

<body>
<c:import url="header.jsp"/>
<c:import url="common_navbar.jsp"/>
<main>
<div class="wrapper">
    <div class="container">
        <div class="row">
            <div class="col s6 offset-s3">
                <div class="card z-depth-5">
                    <div class="card-content">
                        <span class="card-title text-darken-2">Sign in</span>
                        <div class="row">
                            <div class="input-field col s12 center">
                                <img src="../img/logo/site-logo.png" alt="" class="responsive-img valign profile-image-login">
                            </div>
                        </div>
                        <form method="POST" action="${pageContext.request.contextPath}/controller">
                            <input type="hidden" name="command" value="login"/>
                            <div class="row">
                                <div class="input-field col s12 required">
                                    <i class="material-icons prefix">perm_identity</i>
                                    <input id="id-user-login" name="user_login" type="text" required>
                                    <label for="id-user-login">Username</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12 required">
                                    <i class="material-icons prefix">lock</i><input id="id-user-password" name="user_password" type="password" required>
                                    <label for="id-user-password">Password</label>
                                </div>
                            </div>
                            <div class="row">
                                <button id="id-sing-in-button" class="col s12 waves-effect waves-light btn teal" type="submit">Sign in</button>
                            </div>
                            </form>

                            <div class="row">
                                <form action="controller" method="POST">
                                    <input type="hidden" name="command" value="to_register">
                                    <button class="col s12 waves-effect waves-light btn teal" type="submit">Sign up</button>
                                </form>
                            </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
    <c:if test="${requestScope.message != null}">
        <script> Materialize.toast('${requestScope.message}', 4000);</script>
    </c:if>
</main>
<c:import url="footer.jsp"/>

</body>
</html>

