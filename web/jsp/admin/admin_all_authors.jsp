<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 19.08.2016
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authors</title>
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
                            <span class="card-title">All authors</span>
                            <c:if test="${not empty requestScope.all_authors}">
                                <ul class="collection">
                                    <c:forEach items="${requestScope.all_authors}" var="author">
                                        <li class="collection-item avatar">
                                            <img src="${author.photoPath}" alt="author" class="circle">
                                            <span class="title"><b>Name: </b>${author.name}</span>
                                            <p><b>Country:</b>${author.country}</p>
                                            <div class="secondary-content">
                                                <div style="float: left">
                                                    <form action="${pageContext.request.contextPath}/controller" method="POST">
                                                        <input type="hidden" name="command" value="find_author_for_update">
                                                        <input type="hidden" name="author_id" value="${author.authorId}">
                                                        <button class="waves-effect waves-circle waves-light btn-floating teal" type="submit">
                                                            <i class="material-icons">mode_edit</i>
                                                        </button>
                                                    </form>
                                                </div>
                                                <div style="float: right">
                                                    <form action="${pageContext.request.contextPath}/controller" method="POST">
                                                        <input type="hidden" name="command" value="delete_author">
                                                        <input type="hidden" name="author_id" value="${author.authorId}">
                                                        <button class="waves-effect waves-circle waves-light btn-floating red" type="submit">
                                                            <i class="material-icons">delete</i>
                                                        </button>
                                                    </form>
                                                </div>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </c:if>
                            <div class="card-action">
                                <div class="row">
                                    <form action="${pageContext.request.contextPath}/controller" method="POST">
                                        <input type="hidden" name="command" value="find_author_for_create">
                                        <button class="btn col s12 teal" type="submit">
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
<c:import url="../../jsp/footer.jsp"/>

</body>
</html>
