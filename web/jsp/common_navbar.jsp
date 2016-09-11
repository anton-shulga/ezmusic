<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 29.08.2016
  Time: 21:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="property.page_content"/>
<html>
<head>
    <title>Navbar</title>
    <link type="text/css" rel="stylesheet" href="../../css/styles.css" media="screen,projection"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="../../js/bin/materialize.min.js"></script>
</head>
<body>
<ul id="slide-out" class="side-nav">
    <li><a class="waves-effect" href="#!">Songs</a></li>
    <li><a href="#!">Albums</a></li>
    <li><a href="#!">Authors</a></li>
</ul>
<a href="#" data-activates="slide-out" class="button-collapse"><i class="material-icons">menu</i></a>
<script>$(".button-collapse").sideNav();</script>
</body>
</html>
