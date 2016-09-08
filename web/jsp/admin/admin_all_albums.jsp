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
<c:import url="../../jsp/header.jsp"/>
<c:import url="admin_navbar.jsp"/>
<main>
    <div class="wrapper">
        <div class="container">
            <div class="row">
                <div class="col s8 offset-s2">
                    <div class="card z-depth-4">
                        <div class="card-content">
                            <span class="card-title">All albums</span>
                            <ul class="collection">
                                <c:if test="${not empty requestScope.all_albums}">
                                    <c:forEach items="${requestScope.all_albums}" var="album">
                                        <li class="collection-item avatar">
                                            <img src="${pageContext.request.contextPath}/${album.imageFilePath}" alt="album" class="circle">
                                            <span class="title"><b>Name: </b>${album.name}</span>
                                            <p><b>Year: </b>${album.year}</p>
                                            <div class="secondary-content">
                                                <div style="float: left">
                                                    <form action="controller" method="POST">
                                                        <input type="hidden" name="command" value="find_album_for_update">
                                                        <input type="hidden" name="album_id" value="${album.albumId}">
                                                        <button class="waves-effect waves-circle waves-light btn-floating black" type="submit">
                                                            <i class="material-icons">mode_edit</i>
                                                        </button>
                                                    </form>
                                                </div>
                                                <div style="float: right">
                                                    <form action="controller" method="POST">
                                                        <input type="hidden" name="command" value="delete_album">
                                                        <input type="hidden" name="album_id" value="${album.albumId}">
                                                        <button class="waves-effect waves-circle waves-light btn-floating red" type="submit">
                                                            <i class="material-icons">delete</i>
                                                        </button>
                                                    </form>
                                                </div>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </c:if>
                            </ul>
                            <div class="card-action">
                                <div class="row">
                                    <form action="${pageContext.request.contextPath}/controller" method="POST">
                                        <input type="hidden" name="command" value="find_album_for_create">
                                        <button class="col s12 waves-effect btn waves-light black" type="submit">
                                            <i class="material-icons">add</i>
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<c:if test="${requestScope.message != null}">
    <script> Materialize.toast('${requestScope.message}', 4000);</script>
</c:if>
<c:import url="../../jsp/footer.jsp"/>
</body>
</html>
