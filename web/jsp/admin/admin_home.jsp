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
</head>
<body>
    <div class="wrapper">
        <c:import url="../header.jsp"/>
        <div class="container">
            <ctg:hello user="${user}"/>
            <div>
                <form action="${pageContext.request.contextPath}/controller" method="POST">
                    <input type="hidden" name="command" value="find_all_songs">
                    <button type="submit">All songs</button>
                </form>
            </div>
        </div>

    </div>
    <c:import url="/jsp/footer.jsp"/>
</body>
</html>
