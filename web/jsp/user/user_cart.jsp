<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 01.09.2016
  Time: 1:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.epam.webpoject.ezmusic.util.RandomTokenGenerator" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.page_content"/>
<html>
<head>
    <title><fmt:message key="title.cart"/></title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"
          media="screen,projection"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bin/materialize.min.js"></script>
</head>
<body>
<c:import url="${pageContext.request.contextPath}/jsp/header.jsp"/>
<c:import url="${pageContext.request.contextPath}/jsp/user/user_navbar.jsp"/>
<main>
    <div class="wrapper">
        <div class="container">
            <c:if test="${not empty sessionScope.cart.songList}">
                <div class="row">
                    <div class="col s8 offset-s2">
                        <div class="card z-depth-4">
                            <div class="card-content">
                                <span class="card-title"><fmt:message key="title.cart"/></span>
                                <ul class="collection">
                                    <c:forEach items="${sessionScope.cart.songList}" var="song">
                                        <li class="collection-item avatar">
                                            <img src="${pageContext.request.contextPath}/img/song-image.jpg"
                                                 alt="song"
                                                 class="circle"/> <span class="title"><b><fmt:message
                                                key="title.name"/></b>${song.name}</span>
                                            <p>
                                                <b><fmt:message key="title.authors"/> </b>
                                                <c:forEach items="${song.authorList}" var="author">
                                                    ${author.name};
                                                </c:forEach><br>
                                                <b><fmt:message key="title.authors"/> :</b>
                                                <c:forEach items="${song.albumList}" var="album">
                                                    ${album.name};
                                                </c:forEach><br>
                                                <b><fmt:message key="title.cost"/></b>${song.cost}
                                            </p>
                                            <div class="secondary-content">
                                                <form action="${pageContext.request.contextPath}/controller"
                                                      method="POST">
                                                    <input type="hidden" name="command" value="delete_song_from_cart"/>
                                                    <input type="hidden" name="token"
                                                           value="${RandomTokenGenerator.nextToken()}">
                                                    <input type="hidden" name="song_id" value="${song.songId}"/>
                                                    <button class="btn-floating waves-effect waves-light black"
                                                            type="submit"><i class="material-icons">delete</i></button>
                                                </form>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
                                <div class="card-action">
                                    <div class="row">
                                        <form action="${pageContext.request.contextPath}/controller" method="POST">
                                            <input type="hidden" name="command" value="pay_for_order"/>
                                            <button class="btn col s12 waves-effect waves-light black" type="submit">
                                                <fmt:message key="button.pay"/> ${sessionScope.cart.totalCost}</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
        <c:import url="${pageContext.request.contextPath}/jsp/footer.jsp"/>
    </div>
</main>
<c:if test="${requestScope.message != null}">
    <ctg:message message="${requestScope.message}"/>
</c:if>
</body>
</html>
