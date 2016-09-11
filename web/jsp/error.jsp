
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isErrorPage="true" %>
<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 25.07.2016
  Time: 0:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="property.page_content"/>
<html>
<head>
    <title><fmt:message key="title.oops"/>T</title>
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
                    <div class="card z-depth-4">
                        <h1><fmt:message key="title.oops"/></h1>
                        <div class="row">
                            <table width="100%" border="1">
                                <tr valign="top">
                                    <td width="40%"><b><fmt:message key="title.error"/></b></td>
                                    <td>${pageContext.exception}</td>
                                </tr>
                                <tr valign="top">
                                    <td><b><fmt:message key="title.url"/> </b></td>
                                    <td>${pageContext.errorData.requestURI}</td>
                                </tr>
                                <tr valign="top">
                                    <td><b><fmt:message key="title.status_code"/></b></td>
                                    <td>${pageContext.errorData.statusCode}</td>
                                </tr>
                                <tr valign="top">
                                    <td><b><fmt:message key="title.stack_trace"/></b></td>
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
            </div>
            <c:import url="${pageContext.request.contextPath}/jsp/footer.jsp"/>
        </div>
    </main>


</body>
</html>
