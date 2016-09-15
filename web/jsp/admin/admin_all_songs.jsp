<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 12.08.2016
  Time: 19:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="property.page_content"/>
<html>
<head>
    <title><fmt:message key="title.all_songs"/></title>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" media="screen,projection"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bin/materialize.min.js"></script>
</head>
<body>
    <c:import url="../header.jsp"/>
    <c:import url="admin_navbar.jsp"/>
    <main>
        <div class="wrapper">
            <div class="container">
                <div class="row">
                    <div class="col s8 offset-s2">
                        <div class="card z-depth-4">
                            <div class="card-content">
                                <span class="card-title"><fmt:message key="title.all_songs"/> </span>
                                <c:if test="${not empty requestScope.all_songs}">
                                    <ul class="collection">
                                        <c:forEach items="${requestScope.all_songs}" var="song">
                                            <li class="collection-item avatar">
                                                <i class="material-icons circle red">play_arrow</i>
                                                <span class="title"><b><fmt:message key="title.name"/></b>${song.name}</span>
                                                <p>
                                                    <b><fmt:message key="title.authors"/></b>
                                                    <c:forEach items="${song.authorList}" var="author">
                                                        ${author.name};
                                                    </c:forEach><br>
                                                    <b><fmt:message key="title.albums"/></b>
                                                    <c:forEach items="${song.albumList}" var="album">
                                                        ${album.name};
                                                    </c:forEach><br>
                                                    <b><fmt:message key="title.cost"/></b>${song.cost}

                                                </p>
                                                <div class="secondary-content">
                                                    <div style="float: left">
                                                        <form action="${pageContext.request.contextPath}/controller" method="POST">
                                                            <input type="hidden" name="command" value="find_song_for_update">
                                                            <input type="hidden" name="song_id" value="${song.songId}">
                                                            <button class="btn-floating black" type="submit">
                                                                <i class="material-icons">mode_edit</i>
                                                            </button>
                                                        </form>
                                                    </div>
                                                    <div style="float: right">
                                                        <form action="${pageContext.request.contextPath}/controller" method="POST">
                                                            <input type="hidden" name="command" value="delete_song">
                                                            <input type="hidden" name="song_id" value="${song.songId}">
                                                            <button class="btn-floating red" type="submit">
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
                                            <input type="hidden" name="command" value="find_song_for_create">
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
        <script> Materialize.toast('${requestScope.message}', 4000);</script>
    </c:if>

</body>
</html>
