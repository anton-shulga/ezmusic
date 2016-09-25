<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 19.08.2016
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.page_content"/>
<html>
<head>
    <title><fmt:message key="title.all_authors"/></title>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"
          media="screen,projection"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bin/materialize.min.js"></script>
</head>
<body>
<c:import url="${pageContext.request.contextPath}/jsp/header.jsp"/>
<c:import url="${pageContext.request.contextPath}/jsp/admin/admin_navbar.jsp"/>
<main>
    <div class="wrapper">
        <div class="container">
            <div class="row">
                <div class="col s8 offset-s2">
                    <div class="card z-depth-4">
                        <div class="card-content">
                            <span class="card-title"><fmt:message key="title.all_authors"/></span>
                            <c:if test="${not empty requestScope.all_authors}">
                                <ul class="collection">
                                    <c:forEach items="${requestScope.all_authors}" var="author">
                                        <li class="collection-item avatar">
                                            <img src="${pageContext.request.contextPath}/${author.photoPath}"
                                                 alt="author" class="circle">
                                            <span class="title"><b><fmt:message
                                                    key="title.name"/></b>${author.name}</span>
                                            <p><b><fmt:message key="title.country"/></b>${author.country}</p>
                                            <div class="secondary-content">
                                                <div style="float: left">
                                                    <form action="${pageContext.request.contextPath}/controller"
                                                          method="POST">
                                                        <input type="hidden" name="command"
                                                               value="find_author_for_update">
                                                        <input type="hidden" name="author_id"
                                                               value="${author.authorId}">
                                                        <button class="waves-effect waves-circle waves-light btn-floating black"
                                                                type="submit">
                                                            <i class="material-icons">mode_edit</i>
                                                        </button>
                                                    </form>
                                                </div>
                                                <div style="float: right">
                                                    <form action="${pageContext.request.contextPath}/controller"
                                                          method="POST">
                                                        <input type="hidden" name="command" value="delete_author">
                                                        <input type="hidden" name="author_id"
                                                               value="${author.authorId}">
                                                        <button class="waves-effect waves-circle waves-light btn-floating red"
                                                                type="submit">
                                                            <i class="material-icons">delete</i>
                                                        </button>
                                                    </form>
                                                </div>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </c:if>
                            <div class="card-action">
                                <div class="row">
                                    <form action="${pageContext.request.contextPath}/controller" method="POST">
                                        <input type="hidden" name="command" value="find_author_for_create">
                                        <button class="btn col s12 black" type="submit">
                                            <i class="material-icons">add</i>
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <c:import url="${pageContext.request.contextPath}/jsp/footer.jsp"/>
    </div>
</main>
<c:if test="${requestScope.message != null}">
    <c:set var="message"><fmt:message key="${requestScope.message}"/></c:set>
    <ctg:message message="${message}"/>
</c:if>
</body>
</html>
