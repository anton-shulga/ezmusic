<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 12.08.2016
  Time: 19:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Songs</title>
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
                                <form action="${pageContext.request.contextPath}/controller" method="POST">
                                    <input type="hidden" name="command" value="find_song_by_id">
                                    <input type="hidden" name="song_id" value="${song.songId}">
                                    <button class="waves-effect waves-light btn  green accent-4" type="submit">Edit</button>
                                </form>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
    <c:import url="../footer.jsp"/>

</body>
</html>
