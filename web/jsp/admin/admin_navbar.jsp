<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 29.08.2016
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Navbar</title>
    <link type="text/css" rel="stylesheet" href="../../css/styles.css" media="screen,projection"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="../../js/bin/materialize.min.js"></script>
</head>
<body>
    <ul id="slide-out" class="side-nav fixed">
        <li>
            <form name="homeForm" action="${pageContext.request.contextPath}/controller" method="POST">
                <input type="hidden" name="command" value="home_admin">
                <a class="waves-effect" href="javascript:document.homeForm.submit()">Home</a>
            </form>
        </li>
        <li>
            <form name="songsForm" action="${pageContext.request.contextPath}/controller" method="POST">
                <input type="hidden" name="command" value="find_all_songs_admin">
                <a class="waves-effect " href="javascript:document.songsForm.submit()">Songs</a>
            </form>
        </li>
        <li>
            <form name="authorsForm" action="${pageContext.request.contextPath}/controller" method="POST">
                <input type="hidden" name="command" value="find_all_authors_admin">
                <a class="waves-effect" href="javascript:document.authorsForm.submit()">Authors</a>
            </form>
        </li>
        <li>
            <form name="albumsForm" action="${pageContext.request.contextPath}/controller" method="POST">
                <input type="hidden" name="command" value="find_all_albums_admin">
                <a class="waves-effect" href="javascript:document.albumsForm.submit()">Albums</a>
            </form>
        </li>
    </ul>
</body>
</html>
