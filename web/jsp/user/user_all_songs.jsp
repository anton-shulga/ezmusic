<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 29.08.2016
  Time: 12:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Songs</title>
    <link type="text/css" rel="stylesheet" href="../../css/styles.css" media="screen,projection"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="../../js/bin/materialize.min.js"></script>
    <script>
            function addSongToOrder(songId) {
                $.ajax({
                    url: 'JsonController',
                    type: 'post',
                    dataType: 'json',
                    data: {song_id:songId, command:"add_song_to_order"},
                    success: function (json) {
                        var $select = $('#id-badge');
                        $select.text(json);
                    }
                });
            }
   </script>
</head>
<body>
<c:import url="../header.jsp"/>
<c:import url="user_navbar.jsp"/>
    <main>
        <div class="wrapper">
            <div class="container">
                <div class="row">
                    <div class="col s8 offset-s2">
                        <div class="card z-depth-4">
                            <div class="card-content">
                                <span class="card-title">All songs</span>
                                <c:if test="${not empty requestScope.all_songs}">
                                    <ul class="collection">
                                        <c:forEach items="${requestScope.all_songs}" var="song">
                                            <li class="collection-item avatar">
                                                <i class="material-icons circle red">play_arrow</i>
                                                <span class="title"><b>Name: </b>${song.name}</span>
                                                <p>
                                                    <b>Authors:</b>
                                                    <c:forEach items="${song.authorList}" var="author">
                                                        ${author.name};
                                                    </c:forEach><br>
                                                    <b>Albums :</b>
                                                    <c:forEach items="${song.albumList}" var="album">
                                                        ${album.name};
                                                    </c:forEach>
                                                </p>
                                                <div class="secondary-content">
                                                    <div style="float: left">
                                                        <form action="controller" method="POST">
                                                            <input type="hidden" name="command" value="find_song_user">
                                                            <input type="hidden" name="song_id" value="${song.songId}">
                                                            <button class="btn-floating black" type="submit"><i class="material-icons">info_outline</i></button>
                                                        </form>
                                                    </div>
                                                    <div style="float: right">
                                                        <button class="btn-floating black" onclick="addSongToOrder(${song.songId})"><i class="material-icons">shopping_cart</i></button>
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
            </div>
        </main>
    <c:import url="../footer.jsp"/>
</body>
</html>
