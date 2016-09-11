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
    <title><fmt:message key="title.header"/> </title>
</head>
<body>

<nav class="nav-wrapper black">
    <a href="#" class="brand-logo center">EZMusic</a>
    <ul class="right hide-on-med-and-down">
        <li>
        <c:if test="${empty sessionScope.user }">
                <form name="toLoginForm" action="${pageContext.request.contextPath}/controller" method="POST">
                    <input type="hidden" name="command" value="to_login">
                    <a href="javascript:document.toLoginForm.submit()"><img  src="${pageContext.request.contextPath}/img/logo/login-logo.png"></a>
                </form>

        </c:if>
        <c:if test="${not empty sessionScope.user }">
            <form name="logoutForm" action="${pageContext.request.contextPath}/controller" method="POST">
                <input type="hidden" name="command" value="logout">
                <a href="javascript:document.logoutForm.submit()"><img  src="${pageContext.request.contextPath}/img/logo/login-logo.png"></a>
            </form>
        </c:if>
        </li>
    </ul>
</nav>

</body>
</html>
