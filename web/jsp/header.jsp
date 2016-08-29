<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 08.08.2016
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="property.page_content"/>
<html>
<head>
    <title>Header</title>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="../css/styles.css" media="screen,projection"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta charset="utf-8">
</head>
<body>
<header>
<nav class="nav-wrapper teal">
    <a href="#" class="brand-logo center">EZMusic</a>
    <ul class="right hide-on-med-and-down">
        <c:if test="${empty sessionScope.user }">
            <li>
                <form action="${pageContext.request.contextPath}/controller" method="POST">
                    <input type="hidden" name="command" value="to_login"/>
                    <button class="btn waves-effect waves-light green accent-4" type="submit"><fmt:message key="button.sign_in"/></button>
                </form>
            </li>
            <li>
                <form action="${pageContext.request.contextPath}/controller" method="POST">
                    <input type="hidden" name="command" value="to_register"/>
                    <button class="waves-effect waves-light btn  green accent-4" type="submit"><fmt:message key="button.sign_up"/></button>
                </form>
            </li>
        </c:if>
        <c:if test="${not empty sessionScope.user }">
            <form action="${pageContext.request.contextPath}/controller" method="POST">
                <input type="hidden" name="command" value="logout"/>
                <button class="waves-effect waves-light btn  green accent-4" type="submit">Logout</button>
            </form>
        </c:if>
    </ul>
</nav>
</header>
</body>
</html>
