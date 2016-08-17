<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 17.08.2016
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Albums</title>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="../../css/styles.css" media="screen,projection"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="../../js/bin/materialize.min.js"></script>
</head>
<body>

    <div class="wrapper">
        <c:import url="../header.jsp"/>
        <div class="container">
            <div class="card">
                <ul class="collection">
                    <c:forEach items="${requestScope.all_songs}" var="song">
                        <li class="collection-item avatar">
                            <i class="material-icons circle red">play_arrow</i>
                            <span class="title">${song.name}</span>
                            <p>${song.year}<br>
                                    ${song.publicationDate}
                            </p>
                            <div class="secondary-content">
                                <div style="float: left">
                                    <form action="${pageContext.request.contextPath}/controller" method="POST">
                                        <input type="hidden" name="command" value="find_song_by_id">
                                        <input type="hidden" name="song_id" value="${song.songId}">
                                        <button class="waves-effect waves-light btn  green accent-4" type="submit">Edit</button>
                                    </form>
                                </div>
                                <div style="float: right">
                                    <form action="${pageContext.request.contextPath}/controller" method="POST">
                                        <input type="hidden" name="command" value="delete_song">
                                        <input type="hidden" name="song_id" value="${song.songId}">
                                        <button class="waves-effect waves-light btn red" type="submit">Del</button>
                                    </form>
                                </div>

                            </div>
                        </li>
                    </c:forEach>
                    <li class="collection-item right">
                        <form action="${pageContext.request.contextPath}/jsp/admin/admin_edit_song.jsp">
                            <button class="waves-effect waves-light btn  green accent-4" type="submit">Create new song</button>
                        </form>
                    </li>
                </ul>
            </div>
        </div>
    </div>
<c:import url="../footer.jsp"/>
</body>
</html>
