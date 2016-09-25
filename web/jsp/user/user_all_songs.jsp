<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 29.08.2016
  Time: 12:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.page_content"/>
<html>
<head>
    <title><fmt:message key="title.all_songs"/></title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"
          media="screen,projection"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bin/materialize.min.js"></script>
    <script>
        function addSongToOrder(songId) {
            var $previousBadge = $('#id-badge').text();
            $.ajax({
                url: 'jsoncontroller',
                type: 'post',
                dataType: 'json',
                data: {song_id: songId, command: "add_song_to_order"},
                success: function (json) {
                    var $badge = $('#id-badge');
                    $badge.text(json);
                    if ($previousBadge == $badge.text()) {
                        Materialize.toast("This song is already in cart", 1000);
                    } else {
                        Materialize.toast("Successfully added song to cart", 1000)
                    }
                }
            });
        }
    </script>
</head>
<body>
<c:import url="${pageContext.request.contextPath}/jsp/header.jsp"/>
<c:import url="${pageContext.request.contextPath}/jsp/user/user_navbar.jsp"/>
<main>
    <div class="wrapper">
        <div class="container">
            <div class="row">
                <div class="col s8 offset-s2">
                    <div class="card z-depth-4">
                        <div class="card-content">
                            <span class="card-title"><fmt:message key="title.all_songs"/></span>
                            <c:if test="${not empty requestScope.all_songs}">
                                <ul class="collection">
                                    <c:forEach items="${requestScope.all_songs}" var="song">
                                        <li class="collection-item avatar">
                                            <img src="${pageContext.request.contextPath}/img/song-image.jpg"
                                                 alt="song"
                                                 class="circle"/> <span class="title"><b>Name: </b>${song.name}</span>
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
                                                    <form action="${pageContext.request.contextPath}/controller"
                                                          method="POST">
                                                        <input type="hidden" name="command" value="find_song_user">
                                                        <input type="hidden" name="song_id" value="${song.songId}">
                                                        <button class="btn-floating black" type="submit"><i
                                                                class="material-icons">info_outline</i></button>
                                                    </form>
                                                </div>
                                                <div style="float: right">
                                                    <button class="btn-floating black"
                                                            onclick="addSongToOrder(${song.songId})"><i
                                                            class="material-icons">shopping_cart</i></button>
                                                </div>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </c:if>
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

