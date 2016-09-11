<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 29.08.2016
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="property.page_content"/>
<html>
<head>
    <title><fmt:message key="title.navbar"/> </title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" media="screen,projection"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bin/materialize.min.js"></script>
</head>
<body>
    <ul id="slide-out" class="side-nav">
        <li>
            <form name="homeForm" action="${pageContext.request.contextPath}/controller" method="POST">
                <input type="hidden" name="command" value="home_admin">
                <a class="waves-effect" href="javascript:document.homeForm.submit()"><fmt:message key="title.home"/></a>
            </form>
        </li>
        <li>
            <form name="songsForm" action="${pageContext.request.contextPath}/controller" method="POST">
                <input type="hidden" name="command" value="find_all_songs_admin">
                <a class="waves-effect " href="javascript:document.songsForm.submit()"><fmt:message key="title.all_songs"/></a>
            </form>
        </li>
        <li>
            <form name="authorsForm" action="${pageContext.request.contextPath}/controller" method="POST">
                <input type="hidden" name="command" value="find_all_authors_admin">
                <a class="waves-effect" href="javascript:document.authorsForm.submit()"><fmt:message key="title.all_authors"/></a>
            </form>
        </li>
        <li>
            <form name="albumsForm" action="${pageContext.request.contextPath}/controller" method="POST">
                <input type="hidden" name="command" value="find_all_albums_admin">
                <a class="waves-effect" href="javascript:document.albumsForm.submit()"><fmt:message key="title.all_albums"/></a>
            </form>
        </li>
    </ul>
    <a href="#" data-activates="slide-out" class="button-collapse"><i class="material-icons">menu</i></a>
    <script>$(".button-collapse").sideNav();</script>
</body>
</html>
