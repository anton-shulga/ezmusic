<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 25.07.2016
  Time: 0:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ooops! Something is wrong!</title>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="../css/styles.css" media="screen,projection"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="../js/bin/materialize.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta charset="utf-8">
</head>
<body>
    <div class="wrapper">
        <c:import url="${pageContext.request.contextPath}/jsp/header.jsp"/>
        <div class="container">
            <div class="card">
                <h1>Oops...</h1>

                <table width="100%" border="1">
                    <tr valign="top">
                        <td width="40%"><b>Error:</b></td>
                        <td>${pageContext.exception}</td>
                    </tr>
                    <tr valign="top">
                        <td><b>URI:</b></td>
                        <td>${pageContext.errorData.requestURI}</td>
                    </tr>
                    <tr valign="top">
                        <td><b>Status code:</b></td>
                        <td>${pageContext.errorData.statusCode}</td>
                    </tr>
                    <tr valign="top">
                        <td><b>Stack trace:</b></td>
                        <td>
                            <c:forEach var="trace"
                                       items="${pageContext.exception.stackTrace}">
                                <p>${trace}</p>
                            </c:forEach>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <c:import url="${pageContext.request.contextPath}/jsp/footer.jsp"/>
</body>
</html>
