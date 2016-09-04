<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 15.07.2016
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link type="text/css" rel="stylesheet" href="css/styles.css" media="screen,projection"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <meta charset="utf-8">
</head>

<body>
  <c:choose>
    <c:when test="${not empty sessionScope.user}">
      <c:if test="${sessionScope.user.isAdmin}" >
        <jsp:forward page="jsp/admin/admin_home.jsp"/>
      </c:if>
      <c:if test="${not sessionScope.user.isAdmin}">
        <jsp:forward page="jsp/user/user_home.jsp"/>
      </c:if>
    </c:when>
    <c:otherwise>
      <jsp:forward page="jsp/login.jsp"/>
    </c:otherwise>
  </c:choose>
</body>
</html>
