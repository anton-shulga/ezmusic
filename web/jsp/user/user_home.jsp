<%@ taglib prefix="ctg" uri="customtags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 25.07.2016
  Time: 1:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User home</title>
</head>
<body>
    <div class="wrapper">
        <c:import url="../header.jsp"/>
            <div class="container">
                <ctg:hello user="${user}"/>
            </div>
    </div>
    <c:import url="../footer.jsp"/>
</body>
</html>
