<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 02.09.2016
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="../../css/styles.css"  media="screen,projection"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="../../js/bin/materialize.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta charset="utf-8">
</head>

<body>
<c:import url="../header.jsp"/>
<c:import url="user_navbar.jsp"/>
<main>
    <div class="wrapper">
        <div class="container">
            <div class="card">
                <div class="row">
                    <div class="col s7 push-s5">
                        <div class="s12">
                            <h2 class="center light">${requestScope.song.name}</h2>
                        </div>
                        <div class="col s12">
                            <h3 class="center light"><c:forEach items="${song.authorList}" var="author">
                                ${author.name};
                            </c:forEach></h3>
                        </div>
                        <div class="col s12">
                            <h3 class="center light"><c:forEach items="${song.albumList}" var="album">
                                ${album.name};
                            </c:forEach></h3>
                        </div>
                    </div>
                    <div class="col s5 pull-s7">
                        <img src="${pageContext.request.contextPath}/img/album.jpeg" alt="" class="circle user-img responsive-img">
                    </div>
                </div>
                <div class="col s12">
                    <ul class="collection">
                        <c:forEach items="${song.commentList}" var="comment">
                            <li class="collection-item avatar">
                                <img src="${pageContext.request.contextPath}/${comment.user.photoPath}" alt="" class="circle">
                                <span class="title">${comment.user.login}</span>
                                <p>${comment.text}</p>
                                <div class="secondary-content">
                                   <p>${comment.rating}</p>
                                </div>
                            </li>
                        </c:forEach>

                        <div class="row">
                            <form class="col s12">
                                    <div class="input-field col s12">
                                        <i class="material-icons prefix">mode_edit</i>
                                        <input type="hidden" name="command" value="create_comment"/>
                                        <input type="hidden" name="song_id" value="${requestScope.song.songId}"/>
                                        <textarea id="id_textarea" name="comment_text" class="materialize-textarea" length="100"></textarea>
                                        <label for="id_textarea">Textarea</label>
                                        <button class="waves-effect waves-circle waves-light btn-floating green accent-4" type="submit"><i class="material-icons">mode_edit</i></button>
                                    </div>
                            </form>
                        </div>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</main>
<c:if test="${requestScope.message != null}">
    <script> Materialize.toast('${requestScope.message}', 4000);</script>
</c:if>
<c:import url="/jsp/footer.jsp"/>
</body>
</html>