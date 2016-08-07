<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 31.07.2016
  Time: 1:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="../css/styles.css" media="screen,projection"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta charset="utf-8">
</head>

<body>
<div class="wrapper">
    <nav class="nav-wrapper cyan darken-4">
        <a href="#" class="brand-logo">EZMusic</a>
        <div class="row right">
            <a href="${pageContext.request.contextPath}/jsp/login.jsp" class="waves-effect waves-light btn  green accent-4">Sign in</a>
            <a href="${pageContext.request.contextPath}/jsp/register.jsp" class="waves-effect waves-light btn  green accent-4">Sign up</a>
        </div>
    </nav>

    <div class="container col s6" >
        <div class="card s6">
            <form method="POST" action="${pageContext.request.contextPath}/controller">
                <input name="command" type="hidden" value="register"/>
                <div class="card-content">
                    <span class="card-title grey-text text-darken-2">Registration</span>
                    <div class="row">
                        <div class="input-field col s12 required">
                            <i class="material-icons prefix">account_box</i><input id="id-user-login" name="user-login" type="text" required minlength="3">
                            <label for="id-user-login">Username</label>
                        </div>
                    </div>

                    <div class="row">
                        <div class="input-field col s12 required" >
                            <i class="material-icons prefix">email</i><input id="id-user-email" name="user-email" type="email" required>
                            <label for="id-user-email" class="">Email Address</label>
                        </div>
                    </div>

                    <div class="layout-row row">
                        <div class="col s12 m6">
                            <div class="row">
                                <div class="input-field col s12 required">
                                    <i class="material-icons prefix">lock_open</i><input id="id-user-password" name="user-password" type="password" required>
                                    <label for="id-user-password" class="">Password</label>
                                </div>
                            </div>
                        </div>
                        <div class="col s12 m6">
                            <div class="row">
                                <div class="input-field col s12 required">
                                    <input id="id-password-confirm" name="user-password-confirm" type="password" required>
                                    <label for="id-password-confirm">Confirm password</label>
                                </div>
                            </div>
                        </div>
                        <div class="col s12 m6">
                            <div class="row">
                                <div class="input-field col s12">
                                    <input id="id-user-first-name" name="user-first-name" type="text" required>
                                    <label for="id-user-first-name" class="">First name</label>
                                </div>
                            </div>
                        </div>
                        <div class="col s12 m6">
                            <div class="row">
                                <div class="input-field col s12">
                                    <input id="id-user-surname" name="user-surname" type="text" required>
                                    <label for="id-user-surname">Last name</label>
                                </div>
                            </div>
                        </div>
                        <div class="col s12 m12">
                            <div class="row">
                                <div class="input-field col s12">
                                    <input id="id-user-phone" name="user-phone" type="text" required pattern="[+]{0,1}[0-9]{12}" title="Phone example: +375251112223">
                                    <label for="id-user-phone">Phone</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card-action">
                    <div class="right-align">
                        <button id="id-submit" class="waves-effect waves-light btn green accent-4" type="submit">Submit</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <c:if test="${requestScope.message != null}">
        <div class="card-title center">
                ${requestScope.message}
        </div>
    </c:if>
</div> <!-- End wrapper-->



<footer class="page-footer cyan darken-4">
    <div class="footer-copyright">
        <div class="container center">
            <p>© 2016 EZMusic inc.</p>
        </div>
    </div>
</footer>


<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="../js/bin/materialize.min.js"></script>

<script>
    $( document ).ready(function(){
        $(".button-collapse").sideNav();
        $('select').material_select();

        $('.datepicker').pickadate({
            selectMonths: true,
            selectYears: 15
        });
    });
</script>
<script type="text/javascript">

        $("#id-user-login").on("change",function() {
            var login = $(this).val();
            if(login.length > 3) {
                $(".status").html("Checking availability...");
                $.ajax({
                    type: "POST",
                    url: "LoginAvailabilityController",
                    data: "user-login=" + login + "&" +
                    "command=" + "check_login_availability",
                    success: function(msg) {
                        Materialize.toast(msg, 4000);
                    }
                });
            }
            else {
                Materialize.toast("Login must be longer than three characters", 4000);
            }
        });
</script>
<script>
    var password = document.getElementById("id-user-password");
    var confirm_password = document.getElementById("id-password-confirm");

    function validatePassword(){
        if(password.value != confirm_password.value) {
            confirm_password.setCustomValidity("Passwords don't match");
        } else {
            confirm_password.setCustomValidity('');
        }
    }

    password.onchange = validatePassword;
    confirm_password.onchange = validatePassword;
</script>
</body>
</html>
