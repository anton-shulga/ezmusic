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
</head>
<body>
<c:import url="../header.jsp"/>
<c:import url="user_navbar.jsp"/>
    <main>
        <div class="wrapper">
            <div class="container">
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
                                    <form action="${pageContext.request.contextPath}/controller" method="POST">
                                        <input type="hidden" name="command" value="find_song_info">
                                        <input type="hidden" name="song_id" value="${song.songId}">
                                        <button class="waves-effect waves-circle waves-light btn-floating green accent-4" type="submit">
                                            <i class="material-icons">info_outline</i>
                                        </button>
                                    </form>
                                </div>
                                <div style="float: right">
                                    <form action="${pageContext.request.contextPath}/controller" method="POST">
                                        <input type="hidden" name="command" value="add_song_to_order">
                                        <input type="hidden" name="song_id" value="${song.songId}">
                                        <button class="waves-effect waves-circle waves-light btn-floating  green accent-4" type="submit">
                                            <i class="material-icons">shopping_cart</i>
                                        </button>
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
