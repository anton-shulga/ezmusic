<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%--
 Created by IntelliJ IDEA.
 User: Антон
 Date: 31.07.2016
 Time: 1:35
 To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.page_content"/>
<html>
<head>
    <title><fmt:message key="title.sign_up"/></title>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"
          media="screen,projection"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <script src="https://code.jquery.com/jquery-3.1.0.js"
            integrity="sha256-slogkvB1K3VOkzAI8QITxV3VzpOnkeNVsKvtkYLMjfk=" crossorigin="anonymous"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bin/materialize.min.js"></script>
    <meta charset="utf-8">
    <script>
        $(document).ready(function () {
            $('#id-user-login').on("change", function (event) {
                var login = $('#id-user-login').val();
                if (login.length > 2) {
                    $.ajax({
                        url: 'jsoncontroller',
                        type: 'post',
                        dataType: 'json',
                        data: {user_login: login, command: "check_login_availability"},
                        success: function (json) {
                            Materialize.toast(json, 4000);
                        }
                    });
                }
                else {
                    Materialize.toast("Login must be longer than three characters", 4000);
                }
            });
        });
    </script>
</head>

<body>
<c:import url="${pageContext.request.contextPath}/jsp/header.jsp"/>
<main>
    <div class="wrapper">
        <div class="container">
            <div class="row">
                <div class="col s8 offset-s2">
                    <div class="card z-depth-4">
                        <div class="card-content">
                            <span class="card-title grey-text text-darken-2"><fmt:message key="title.sign_up"/></span>
                            <div class="row">
                                <div class="input-field col s12 center">
                                    <img src="${pageContext.request.contextPath}/img/logo/site-logo.png" alt=""
                                         class="responsive-img valign profile-image-login">
                                </div>
                            </div>
                            <form method="POST" action="${pageContext.request.contextPath}/controller">
                                <input name="command" type="hidden" value="register"/>
                                <div class="row">
                                    <div class="input-field col s12 required">
                                        <i class="material-icons prefix">perm_identity</i>
                                        <input id="id-user-login" name="user_login" type="text" required minlength="3"
                                               title="<fmt:message key="validation.username"/>"/>
                                        <label for="id-user-login"><fmt:message key="label.username"/></label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="input-field col s12 required">
                                        <i class="material-icons prefix">email</i>
                                        <input id="id-user-email" name="user_email" type="email" required>
                                        <label for="id-user-email" class=""><fmt:message key="label.email"/></label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="input-field col s12 required">
                                        <i class="material-icons prefix">lock</i>
                                        <input id="id-user-password" name="user_password" type="password" required
                                               minlength="3">
                                        <label for="id-user-password" class=""><fmt:message
                                                key="label.password"/></label>

                                        <div class="right">
                                            <input id="id-show-password" type="checkbox"
                                                   onchange="document.getElementById('id-user-password').type = this.checked ? 'text' : 'password'"/>
                                            <label for="id-show-password">Show password</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="input-field col s12">
                                        <i class="material-icons prefix">person_pin</i>
                                        <input id="id-user-first-name" name="user_first_name" type="text" required>
                                        <label for="id-user-first-name" class=""><fmt:message
                                                key="label.first_name"/></label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="input-field col s12">
                                        <i class="material-icons prefix">person_pin</i>
                                        <input id="id-user-surname" name="user_surname" type="text" required>
                                        <label for="id-user-surname"><fmt:message key="label.surname"/></label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="input-field col s12">
                                        <i class="material-icons prefix">phone</i>
                                        <input id="id-user-phone" name="user_phone" type="text" required
                                               pattern="[+]{0,1}[0-9]{12}" title="+375251112223">
                                        <label for="id-user-phone"><fmt:message key="label.phone"/> </label>
                                    </div>
                                </div>
                                <div class="card-action">
                                    <div class="row">
                                        <button id="id-submit" class="col s12 waves-effect waves-light btn black"
                                                type="submit"><fmt:message key="button.sign_up"/></button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <c:import url="${pageContext.request.contextPath}/jsp/footer.jsp"/>
    </div><!-- End wrapper-->
</main>
<c:if test="${requestScope.message != null}">
    <c:set var="message"><fmt:message key="${requestScope.message}"/></c:set>
    <ctg:message message="${message}"/>
</c:if>
</body>
</html>
