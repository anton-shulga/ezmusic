<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 29.08.2016
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.page_content"/>
<html>
<head>
    <title><fmt:message key="title.navbar"/></title>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"
          media="screen,projection"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bin/materialize.min.js"></script>
    <script>
        function addFunds() {
            var moneyAmount = $('#id-money-amount').val();
            $.ajax({
                url: 'jsoncontroller',
                method: "post",
                data: {
                    money_amount: moneyAmount, command: "add_funds"
                },
                success: function (data) {
                    var $balance = $('#id-balance');
                    $balance.text(data);
                }
            });
        }
    </script>
</head>
<body>
<ul id="slide-out" class="side-nav">
    <li>
        <div class="userView">
            <div class="black-text center-align">
                <span class="black-text center">${sessionScope.user.login}</span>
                <br>
                <span id="id-balance" class="black-text">${sessionScope.user.balance}</span>
            </div>
            <div class="row">
                <div class="input-field col s12 required">
                    <input id="id-money-amount" name=type="text" required>
                </div>
                <button class="btn col s12" onclick="addFunds()">
                    <i class="material-icons">add</i>
                </button>
            </div>
        </div>
    </li>
    <li>
        <div class="divider"></div>
    </li>
    <li>
        <form name="homeForm" action="${pageContext.request.contextPath}/controller" method="POST">
            <input type="hidden" name="command" value="home_user">
            <a class="waves-effect" href="javascript:document.homeForm.submit()"><fmt:message key="title.home"/></a>
        </form>
    </li>
    <li>
        <form name="songsForm" action="${pageContext.request.contextPath}/controller" method="POST">
            <input type="hidden" name="command" value="find_all_songs_user">
            <a class="waves-effect" href="javascript:document.songsForm.submit()"><fmt:message
                    key="title.all_songs"/> </a>
        </form>
    </li>
    <li>
        <form name="authorsForm" action="${pageContext.request.contextPath}/controller" method="POST">
            <input type="hidden" name="command" value="find_all_authors_user">
            <a class="waves-effect" href="javascript:document.authorsForm.submit()"><fmt:message
                    key="title.all_authors"/></a>
        </form>
    </li>
    <li>
        <form name="albumsForm" action="${pageContext.request.contextPath}/controller" method="POST">
            <input type="hidden" name="command" value="find_all_albums_user">
            <a class="waves-effect" href="javascript:document.albumsForm.submit()"><fmt:message
                    key="title.all_albums"/></a>
        </form>
    </li>
    <li>
        <div class="divider"></div>
    </li>
    <li>
        <form name="cartForm" action="${pageContext.request.contextPath}/controller" method="POST">
            <input type="hidden" name="command" value="find_cart_user">
            <a class="waves-effect" href="javascript:document.cartForm.submit()"><fmt:message key="title.cart"/><span
                    id="id-badge" class="new badge"
                    data-badge-caption="songs">${fn:length(sessionScope.cart.songList)}</span></a>
        </form>
    </li>
    <li>
        <form name="ordersForm" action="${pageContext.request.contextPath}/controller" method="POST">
            <input type="hidden" name="command" value="find_orders_user">
            <a class="waves-effect" href="javascript:document.ordersForm.submit()"><fmt:message
                    key="title.all_orders"/> </a>
        </form>
    </li>
    <li><a href="#!"><fmt:message key="title.settings"/> </a></li>

</ul>
<a href="#" data-activates="slide-out" class="button-collapse"><i class="material-icons">menu</i></a>
<script>$(".button-collapse").sideNav();</script>
</body>
</html>
