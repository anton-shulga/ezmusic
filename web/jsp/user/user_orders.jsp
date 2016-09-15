<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 07.09.2016
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="property.page_content"/>
<html>
<head>
    <title>My orders</title>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" media="screen,projection"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bin/materialize.min.js"></script>
</head>

<body>
<c:import url="${pageContext.request.contextPath}/jsp/header.jsp"/>
<c:import url="${pageContext.request.contextPath}/jsp/user/user_navbar.jsp"/>

<main>
    <div class="wrapper">
        <div class="container">
            <c:if test="${not empty requestScope.orders}">
                <c:forEach items="${requestScope.orders}" var="order">
                    <div class="row">
                        <div class="col s8 offset-s2">
                            <div class="card z-depth-4">
                                <div class="card-content">
                                    <span class="card-title"><fmt:message key="title.order"/>${order.orderId}</span>
                                    <c:if test="${not empty order.songList}">
                                        <ul class="collection">
                                            <c:forEach items="${order.songList}" var="song">
                                                <li class="collection-item avatar">
                                                    <img src="${pageContext.request.contextPath}/img/song-image.jpg"
                                                         alt="song"
                                                         class="circle"/>                                                    <span class="title"><b><fmt:message key="title.name"/></b>${song.name}</span>
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
                                                        <button class="btn-floating black" onclick="addSongToOrder(${song.songId})"><i class="material-icons">system_update_alt</i></button>
                                                    </div>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
        </div>
        <c:import url="${pageContext.request.contextPath}/jsp/footer.jsp"/>
    </div>
</main>

</body>
</html>
