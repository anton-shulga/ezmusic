<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 08.08.2016
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Footer</title>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="../css/styles.css" media="screen,projection"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="../js/bin/materialize.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta charset="utf-8">
    <script>
        function changeLocale(locale) {
            $.ajax({
                url : "${pageContext.request.contextPath}/LocaleController",
                method : "post",
                data : {
                    locale : locale,
                    command : "change_locale"
                }
            }).done(function(data) {
                location.reload();
            });
        }
    </script>
</head>
<body>
    <footer class="page-footer cyan darken-4">
        <div class="footer-copyright">
            <div class="row right">
                <input type="image" src="../img/logo/ru.png" onclick="changeLocale('ru_RU')"/>
                <input type="image" src="../img/logo/eng.png" onclick="changeLocale('default')"/>
            </div>
        </div>
    </footer>
</body>
</html>
