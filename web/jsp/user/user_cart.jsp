<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 01.09.2016
  Time: 1:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My cart</title>
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
            <ul class="collection">
                <c:forEach items="${requestScope.cart.songList}" var="song">
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

                            <div style="float: right">
                                <form action="controller">
                                    <input type="hidden" name="command" value="delete_song_from_cart"/>
                                    <input type="hidden" name="song_id" value="${song.songId}"/>
                                    <button class="btn-floating waves-effect waves-light green accent-4" onclick="addSongToOrder(${song.songId})"><i class="material-icons">delete</i></button>
                                </form>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <c:import url="../footer.jsp"/>
    </div>
</main>
</body>
</html>
