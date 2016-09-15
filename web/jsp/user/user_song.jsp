<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 02.09.2016
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.epam.webpoject.ezmusic.util.RandomTokenGenerator"%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="property.page_content"/>
<html>
<head>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"  media="screen,projection"/>
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
                                    <img src="${pageContext.request.contextPath}/img/song-image.jpg" alt="" class="circle responsive-img center">
                                </div>
                            </div>
                            <div class="row">
                                <div class="center text">
                                    <h4>
                                        <fmt:message key="title.name"/>
                                        ${song.name}
                                    </h4>
                                </div>
                            </div>
                            <div class="row">
                                <div class="center text">
                                    <h4>
                                        <fmt:message key="title.authors"/>
                                        <c:forEach items="${song.authorList}" var="author">
                                            ${author.name};
                                        </c:forEach>
                                    </h4>
                                </div>
                            </div>
                            <div class="row">
                                <div class="center text">
                                    <h4>
                                        <fmt:message key="title.albums"/>
                                        <c:forEach items="${song.albumList}" var="album">
                                            ${album.name};
                                        </c:forEach>
                                    </h4>
                                </div>
                            </div>
                            <div class="row">
                                <c:if test="${not empty song.commentList}">
                                    <ul class="collection">
                                        <c:forEach items="${song.commentList}" var="comment">
                                            <li class="collection-item avatar">
                                                <img src="${pageContext.request.contextPath}/${comment.user.photoPath}" alt="" class="circle">
                                                <span class="title">${comment.user.login}</span>
                                                <p>${comment.text}</p>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </c:if>
                            </div>
                            <div class="card-action">
                                <div class="row">
                                    <form>
                                        <div class="input-field col s12">
                                                <i class="material-icons prefix">mode_edit</i>
                                                <input type="hidden" name="command" value="create_comment"/>
                                                <input type="hidden" name="song_id" value="${requestScope.song.songId}"/>
                                                <input type="hidden" name="token" value="${RandomTokenGenerator.nextToken()}">
                                                <textarea id="id_textarea" name="comment_text" class="materialize-textarea" length="100"></textarea>
                                                <label for="id_textarea"><fmt:message key="label.comment"/> </label>
                                        </div>
                                        <button class="black btn col s12" type="submit"><i class="material-icons">mode_edit</i></button>
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