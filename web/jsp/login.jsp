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
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta charset="utf-8">
</head>

<body>
<div class="wrapper">
   <c:import url="header.jsp"/>
    <div class="container">
        <div class="card">
            <form method="POST" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="login"/>
                <div class="card-content">
                    <span class="card-title grey-text text-darken-2">Login</span>
                    <div class="row">
                        <div class="input-field col s12 required" id="id-user-login-container">
                            <input id="id-user-login" name="user-login" type="text" required>
                            <label for="id-user-login">Username</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12 required" id="id-user-password-container">
                            <input id="id-user-password" name="user-password" type="password" required>
                            <label for="id-user-password">Password</label>
                        </div>
                    </div>
                </div>
                <div class="card-action">
                    <div class="right-align">
                        <button class="waves-effect waves-light btn green accent-4" type="submit">Submit</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <c:if test="${requestScope.message != null}">
        <div class="card-title center">
                ${requestScope.message}
        </div>
    </c:if>
</div>
<c:import url="footer.jsp"/>


<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="../js/bin/materialize.min.js"></script>

<script>
    $( document ).ready(function(){
        $(".button-collapse").sideNav();
        $('select').material_select();

        $('.datepicker').pickadate({
            selectMonths: true,
            selectYears: 15
        });
    });
</script>
</body>
</html>

