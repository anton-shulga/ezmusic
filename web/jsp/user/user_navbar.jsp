<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 29.08.2016
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Navbar</title>
    <script>
        function addFunds() {
            var moneyAmount = $('#id-money-amount').val();

            $.ajax({
                url: 'JsonController',
                method: "post",
                data: {
                    money_amount: moneyAmount, command: "add_funds"
                }
            }).done(function (data) {
                location.reload();
            })
        }
    </script>
</head>
<body>
    <ul id="slide-out" class="side-nav fixed">
        <li>
            <ctg:hello user="${user}"/>
        </li>
        <li>
            <form name="homeForm" action="${pageContext.request.contextPath}/controller" method="POST">
                <input type="hidden" name="command" value="home_user">
                <a class="waves-effect" href="javascript:document.homeForm.submit()">Home</a>
            </form>
        </li>
        <li>
            <form name="songsForm" action="${pageContext.request.contextPath}/controller" method="POST">
                <input type="hidden" name="command" value="find_all_songs_user">
                <a class="waves-effect" href="javascript:document.songsForm.submit()">Songs</a>
            </form>
        </li>
        <li>
            <form name="authorsForm" action="${pageContext.request.contextPath}/controller" method="POST">
                <input type="hidden" name="command" value="find_all_authors_user">
                <a class="waves-effect" href="javascript:document.authorsForm.submit()">Authors</a>
            </form>
        </li>
        <li>
            <form name="albumsForm" action="${pageContext.request.contextPath}/controller" method="POST">
                <input type="hidden" name="command" value="find_all_albums_user">
                <a class="waves-effect"  href="javascript:document.albumsForm.submit()">Albums</a>
            </form>
        </li>
        <li><div class="divider"></div></li>
        <li>
            <form name="cartForm" action="${pageContext.request.contextPath}/controller" method="POST">
                <input type="hidden" name="command" value="find_cart_user">
                <a class="waves-effect" href="javascript:document.cartForm.submit()">My cart<span id="id-badge" class="new badge" data-badge-caption="songs">${fn:length(sessionScope.cart.songList)}</span></a>
            </form>
        </li>
        <li>
            <form name="ordersForm" action="${pageContext.request.contextPath}/controller" method="POST">
                <input type="hidden" name="command" value="find_user_orders">
                <a class="waves-effect" href="javascript:document.ordersForm.submit()">My orders</a>
            </form>
        </li>
        <li><a href="#!">My settings</a></li>
    </ul>

</body>
</html>
