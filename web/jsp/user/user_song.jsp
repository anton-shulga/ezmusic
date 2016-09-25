<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 02.09.2016
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.epam.webpoject.ezmusic.util.RandomTokenGenerator" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.page_content"/>
<html>
<head>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"
          media="screen,projection"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bin/materialize.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta charset="utf-8">
</head>

<body>

<c:import url="${pageContext.request.contextPath}/jsp/header.jsp"/>
<c:import url="user_navbar.jsp"/>
<main>
    <div class="wrapper">
        <div class="container">
            <div class="row">
                <div class="col s8 offset-s2">
                    <div class="card z-depth-4">
                        <div class="card-content">
                            <span class="card-title">${requestScope.song.name}</span>
                            <div class="row">
                                <div class="center">
                                    <img src="${pageContext.request.contextPath}/img/song-image.jpg" alt=""
                                         class="circle responsive-img center">
                                </div>
                            </div>

                            <c:if test="${not empty requestScope.song.authorList}">
                                <ul class="collection with-header">
                                    <li>
                                        <div class="collection-header">
                                            <h5><fmt:message key="title.song_authors"/></h5>
                                        </div>
                                    </li>
                                    <c:forEach items="${requestScope.song.authorList}" var="author">
                                        <li class="collection-item avatar">
                                            <img src="${pageContext.request.contextPath}/${author.photoPath}"
                                                 alt="author" class="circle">
                                            <span class="title"><b><fmt:message
                                                    key="title.name"/></b>${author.name}</span>
                                            <p><b><fmt:message key="title.country"/></b>${author.country}</p>
                                            <div class="secondary-content">
                                                <form action="${pageContext.request.contextPath}/controller"
                                                      method="POST">
                                                    <input type="hidden" name="command" value="find_author_user">
                                                    <input type="hidden" name="author_id" value="${author.authorId}">
                                                    <button class="btn-floating black" type="submit"><i
                                                            class="material-icons">info_outline</i></button>
                                                </form>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </c:if>

                            <c:if test="${not empty requestScope.song.albumList}">
                                <ul class="collection with-header">
                                    <li>
                                        <div class="collection-header">
                                            <h5><fmt:message key="title.song_albums"/></h5>
                                        </div>
                                    </li>
                                    <c:forEach items="${requestScope.song.albumList}" var="album">
                                        <li class="collection-item avatar">
                                            <img src="${pageContext.request.contextPath}/${album.imageFilePath}"
                                                 alt="album" class="circle">
                                            <span class="title"><b><fmt:message
                                                    key="title.name"/></b>${album.name}</span>
                                            <p><b><fmt:message key="title.year"/></b>${album.year}</p>
                                            <div class="secondary-content">
                                                <form action="${pageContext.request.contextPath}/controller"
                                                      method="POST">
                                                    <input type="hidden" name="command" value="find_album_user">
                                                    <input type="hidden" name="album_id" value="${album.albumId}">
                                                    <button class="btn-floating black" type="submit"><i
                                                            class="material-icons">info_outline</i></button>
                                                </form>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </c:if>

                            <c:if test="${not empty requestScope.song.commentList}">
                                <ul class="collection with-header">
                                    <li>
                                        <div class="collection-header">
                                            <h5><fmt:message key="title.comments"/></h5>
                                        </div>
                                    </li>
                                    <c:forEach items="${requestScope.song.commentList}" var="comment">
                                        <li class="collection-item avatar">
                                            <span class="title">${comment.user.login}</span>
                                            <img src="${pageContext.request.contextPath}/${comment.user.photoPath}"
                                                 alt="user" class="circle">
                                            <p>${comment.text}</p>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </c:if>

                            <div class="card-action">
                                <div class="row">
                                    <form action="${pageContext.request.contextPath}/controller" method="POST">
                                        <div class="input-field col s12">
                                            <i class="material-icons prefix">textsms</i>
                                            <input type="hidden" name="command" value="create_comment"/>
                                            <input type="hidden" name="song_id" value="${requestScope.song.songId}"/>
                                            <input type="hidden" name="token"
                                                   value="${RandomTokenGenerator.nextToken()}">
                                            <textarea id="id_textarea" name="comment_text" class="materialize-textarea"
                                                      required
                                                      maxlength="30"></textarea>
                                            <label for="id_textarea"><fmt:message key="label.comment"/> </label>
                                        </div>
                                        <button class="black btn col s12" type="submit"><i
                                                class="material-icons">textsms</i></button>
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