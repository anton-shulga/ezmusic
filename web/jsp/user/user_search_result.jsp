<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 15.09.2016
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.page_content"/>
<html>
<head>
    <title><fmt:message key="title.search_result"/></title>
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
<c:if test="${sessionScope.user.isAdmin}">
    <c:import url="${pageContext.request.contextPath}/jsp/admin/admin_navbar.jsp"/>
</c:if>
<c:if test="${not sessionScope.user.isAdmin}">
    <c:import url="${pageContext.request.contextPath}/jsp/user/user_navbar.jsp"/>
</c:if>
<main>
    <div class="wrapper">
        <div class="container">
            <c:if test="${not empty requestScope.all_songs}">
                <div class="row">
                    <div class="col s8 offset-s2">
                        <div class="card z-depth-4">
                            <div class="card-content">
                                <span class="card-title"><fmt:message key="title.all_songs"/></span>
                                <ul class="collection">
                                    <c:forEach items="${requestScope.all_songs}" var="song">
                                        <li class="collection-item avatar">
                                            <img src="${pageContext.request.contextPath}/img/song-image.jpg"
                                                 alt="song"
                                                 class="circle"/>
                                            <span class="title"><b>Name: </b>${song.name}</span>
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
                                                <c:if test="${not sessionScope.user.isAdmin}">
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
                                                </c:if>
                                                <c:if test="${sessionScope.user.isAdmin}">
                                                    <div style="float: left">
                                                        <form action="${pageContext.request.contextPath}/controller"
                                                              method="POST">
                                                            <input type="hidden" name="command"
                                                                   value="find_song_for_update">
                                                            <input type="hidden" name="song_id" value="${song.songId}">
                                                            <button class="btn-floating black" type="submit">
                                                                <i class="material-icons">mode_edit</i>
                                                            </button>
                                                        </form>
                                                    </div>
                                                    <div style="float: right">
                                                        <form action="${pageContext.request.contextPath}/controller"
                                                              method="POST">
                                                            <input type="hidden" name="command" value="delete_song">
                                                            <input type="hidden" name="song_id" value="${song.songId}">
                                                            <button class="btn-floating red" type="submit">
                                                                <i class="material-icons">delete</i>
                                                            </button>
                                                        </form>
                                                    </div>
                                                </c:if>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${not empty requestScope.all_authors}">
                <div class="row">
                    <div class="col s8 offset-s2">
                        <div class="card z-depth-4">
                            <div class="card-content">
                                <span class="card-title"><fmt:message key="title.all_authors"/></span>
                                <ul class="collection">
                                    <c:forEach items="${requestScope.all_authors}" var="author">
                                        <li class="collection-item avatar">
                                            <img src="${pageContext.request.contextPath}/${author.photoPath}"
                                                 alt="author" class="circle">
                                            <span class="title"><b><fmt:message
                                                    key="title.name"/></b>${author.name}</span>
                                            <p><b><fmt:message key="title.country"/></b>${author.country}</p>
                                            <div class="secondary-content">
                                                <c:if test="${not sessionScope.user.isAdmin}">
                                                    <form action="${pageContext.request.contextPath}/controller"
                                                          method="POST">
                                                        <input type="hidden" name="command" value="find_author_user">
                                                        <input type="hidden" name="author_id"
                                                               value="${author.authorId}">
                                                        <button class="btn-floating black" type="submit"><i
                                                                class="material-icons">info_outline</i></button>
                                                    </form>
                                                </c:if>
                                                <c:if test="${sessionScope.user.isAdmin}">
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
                                            </c:if>

                                        </li>
                                    </c:forEach>
                                </ul>

                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${not empty requestScope.all_albums}">
                <div class="row">
                    <div class="col s8 offset-s2">
                        <div class="card z-depth-4">
                            <div class="card-content">
                                <span class="card-title"><fmt:message key="title.all_albums"/></span>
                                <ul class="collection">
                                    <c:forEach items="${requestScope.all_albums}" var="album">
                                        <li class="collection-item avatar">
                                            <img src="${pageContext.request.contextPath}/${album.imageFilePath}"
                                                 alt="album" class="circle">
                                            <span class="title"><b><fmt:message
                                                    key="title.name"/></b>${album.name}</span>
                                            <p><b><fmt:message key="title.year"/></b>${album.year}</p>
                                            <div class="secondary-content">
                                                <c:if test="${not sessionScope.user.isAdmin}">
                                                    <form action="${pageContext.request.contextPath}/controller"
                                                          method="POST">
                                                        <input type="hidden" name="command" value="find_album_user">
                                                        <input type="hidden" name="album_id" value="${album.albumId}">
                                                        <button class="btn-floating black" type="submit"><i
                                                                class="material-icons">info_outline</i></button>
                                                    </form>
                                                </c:if>
                                                <c:if test="${sessionScope.user.isAdmin}">
                                                    <div style="float: left">
                                                        <form action="${pageContext.request.contextPath}/controller"
                                                              method="POST">
                                                            <input type="hidden" name="command"
                                                                   value="find_album_for_update">
                                                            <input type="hidden" name="album_id"
                                                                   value="${album.albumId}">
                                                            <button class="waves-effect waves-circle waves-light btn-floating black"
                                                                    type="submit">
                                                                <i class="material-icons">mode_edit</i>
                                                            </button>
                                                        </form>
                                                    </div>
                                                    <div style="float: right">
                                                        <form action="${pageContext.request.contextPath}/controller"
                                                              method="POST">
                                                            <input type="hidden" name="command" value="delete_album">
                                                            <input type="hidden" name="album_id"
                                                                   value="${album.albumId}">
                                                            <button class="waves-effect waves-circle waves-light btn-floating red"
                                                                    type="submit">
                                                                <i class="material-icons">delete</i>
                                                            </button>
                                                        </form>
                                                    </div>
                                                </c:if>

                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
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
