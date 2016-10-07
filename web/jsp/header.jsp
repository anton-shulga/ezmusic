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
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.page_content"/>
<html>
<head>
    <title><fmt:message key="title.header"/></title>
</head>
<body>

<nav class="nav-wrapper black">
    <c:choose>
        <c:when test="${not empty sessionScope.user}">
            <c:if test="${sessionScope.user.isAdmin}">
                <form name="adminHome" action="${pageContext.request.contextPath}/controller" method="POST">
                    <input type="hidden" name="command" value="home_admin">
                    <a class="brand-logo center" href="javascript:document.adminHome.submit()"><fmt:message
                            key="logo"/></a>
                </form>
            </c:if>
            <c:if test="${not sessionScope.user.isAdmin}">
                <form name="userHome" action="${pageContext.request.contextPath}/controller" method="POST">
                    <input type="hidden" name="command" value="home_user">
                    <a class="brand-logo center" href="javascript:document.userHome.submit()"><fmt:message
                            key="logo"/></a>
                </form>
            </c:if>
        </c:when>
        <c:otherwise>
            <form name="home" action="${pageContext.request.contextPath}/controller" method="POST">
                <input type="hidden" name="command" value="go_home">
                <a class="brand-logo center" href="javascript:document.home.submit()"><fmt:message key="logo"/></a>
            </form>
        </c:otherwise>
    </c:choose>
    <ul class="right hide-on-med-and-down">
        <li>
            <form action="${pageContext.request.contextPath}/controller" method="GET">
                <div class="input-field">
                    <input type="hidden" name="command" value="search"/>
                    <input id="search" type="search" name="search_request" required>
                    <label for="search"><i class="material-icons white-text">search</i></label>
                </div>
            </form>
        </li>
        <li>
            <c:if test="${empty sessionScope.user }">
                <form name="toLoginForm" action="${pageContext.request.contextPath}/controller" method="POST">
                    <input type="hidden" name="command" value="to_login">
                    <a href="javascript:document.toLoginForm.submit()"><i
                            class="material-icons white-text small">power_settings_new</i>
                    </a>
                </form>
            </c:if>
            <c:if test="${not empty sessionScope.user }">
                <form name="logoutForm" action="${pageContext.request.contextPath}/controller" method="POST">
                    <input type="hidden" name="command" value="logout">
                    <a href="javascript:document.logoutForm.submit()"><i class="material-icons white-text small">power_settings_new</i>
                    </a>
                </form>
            </c:if>
        </li>
    </ul>
</nav>

</body>
</html>
