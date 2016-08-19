<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 10.08.2016
  Time: 22:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="../../css/styles.css" media="screen,projection"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="../../js/bin/materialize.min.js"></script>
</head>
<body>
    <div class="wrapper">
        <c:import url="../header.jsp"/>
        <div class="container">
            <ctg:hello user="${user}"/>
            <div>
                <form action="${pageContext.request.contextPath}/controller" method="POST">
                    <input type="hidden" name="command" value="find_all_songs">
                    <button class="btn" type="submit">All songs</button>
                </form>
            </div>
            <div>
                <form action="${pageContext.request.contextPath}/controller" method="POST">
                    <input type="hidden" name="command" value="find_all_albums">
                    <button class="btn" type="submit">All albums</button>
                </form>
            </div>
            <div>
                <form action="${pageContext.request.contextPath}/controller" method="POST">
                    <input type="hidden" name="command" value="find_all_authors">
                    <button class="btn" type="submit">All authors</button>
                </form>
            </div>
        </div>

    </div>
    <c:import url="${pageContext.request.contextPath}/jsp/footer.jsp"/>
</body>
</html>
