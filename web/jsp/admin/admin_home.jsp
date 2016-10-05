<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 10.08.2016
  Time: 22:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.page_content"/>
<html>
<head>
    <title><fmt:message key="title.home"/></title>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"
          media="screen,projection"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bin/materialize.min.js"></script>
    <script>
        (function ($) {
            $(function () {
                $('.parallax').parallax();
            }); // end of document ready
        })(jQuery); // end of jQuery name space
    </script>
</head>
<body>
<c:import url="${pageContext.request.contextPath}/jsp/header.jsp"/>
<c:if test="${sessionScope.user.isAdmin}">
    <c:import url="${pageContext.request.contextPath}/jsp/admin/admin_navbar.jsp"/>
</c:if>
<main>
    <div id="index-banner" class="parallax-container">
        <div class="section no-pad-bot">
            <div class="container">
                <br><br>
                <h1 class="header center teal-text black-text"><fmt:message key="logo"/></h1>
                <div class="row center">
                    <h5 class="header col s12 black-text"><fmt:message key="slogan"/></h5>
                </div>
                <br><br>
            </div>
        </div>
        <div class="parallax"><img src="${pageContext.request.contextPath}/img/paralax-user.jpg" alt="guitar"></div>
    </div>

    <div class="container">
        <div class="section">
            <div class="row">
                <div class="col s12 m4">
                    <div class="icon-block">
                        <h2 class="center black-text"><i class="material-icons small">playlist_add</i></h2>
                        <h5 class="center"><fmt:message key="admin.home.add.short"/></h5>
                        <p class="light"><fmt:message key="admin.home.add"/></p>
                    </div>
                </div>

                <div class="col s12 m4">
                    <div class="icon-block">
                        <h2 class="center black-text"><i class="material-icons small">edit</i></h2>
                        <h5 class="center"><fmt:message key="admin.home.edit.short"/></h5>
                        <p class="light"><fmt:message key="admin.home.edit"/></p>
                    </div>
                </div>

                <div class="col s12 m4">
                    <div class="icon-block">
                        <h2 class="center black-text"><i class="material-icons small">delete</i></h2>
                        <h5 class="center"><fmt:message key="admin.home.delete.short"/></h5>

                        <p class="light"><fmt:message key="admin.home.delete"/></p>
                    </div>
                </div>
            </div>

        </div>
    </div>
</main>
<c:import url="${pageContext.request.contextPath}/jsp/footer.jsp"/>
<c:if test="${requestScope.message != null}">
    <c:set var="message"><fmt:message key="${requestScope.message}"/></c:set>
    <ctg:message message="${message}"/>
</c:if>
</body>
</html>
