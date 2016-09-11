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
    <title><fmt:message key="title.sign_in"/></title>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" media="screen,projection"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bin/materialize.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta charset="utf-8">

</head>

<body>
<c:import url="${pageContext.request.contextPath}/jsp/header.jsp"/>
<main>
<div class="wrapper">
    <div class="container">
        <div class="row">
            <div class="col s8 offset-s2">
                <div class="card z-depth-5">
                    <div class="card-content">
                        <span class="card-title text-darken-2"><fmt:message key="title.sign_in"/></span>
                        <div class="row">
                            <div class="input-field col s12 center">
                                <img src="${pageContext.request.contextPath}/img/logo/site-logo.png" alt="" class="responsive-img valign profile-image-login">
                            </div>
                        </div>
                        <form method="POST" action="${pageContext.request.contextPath}/controller">
                            <input type="hidden" name="command" value="login"/>
                            <div class="row">
                                <div class="input-field col s12 required">
                                    <i class="material-icons prefix">perm_identity</i>
                                    <input id="id-user-login" name="user_login" type="text" required>
                                    <label for="id-user-login"><fmt:message key="label.username"/> </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12 required">
                                    <i class="material-icons prefix">lock</i><input id="id-user-password" name="user_password" type="password" required>
                                    <label for="id-user-password"><fmt:message key="label.password"/></label>
                                </div>
                            </div>
                            <div class="row">
                                <button id="id-sing-in-button" class="col s12 waves-effect waves-light btn black" type="submit"><fmt:message key="button.sign_in"/></button>
                            </div>
                            </form>

                            <div class="row">
                                <form action="controller" method="POST">
                                    <input type="hidden" name="command" value="to_register">
                                    <button class="col s12 waves-effect waves-light btn black" type="submit"><fmt:message key="button.sign_up"/> </button>
                                </form>
                            </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <c:import url="${pageContext.request.contextPath}/jsp/footer.jsp"/>
</div>
</main>
<c:if test="${requestScope.message != null}">
    <script> Materialize.toast('${requestScope.message}', 4000);</script>
</c:if>


</body>
</html>

