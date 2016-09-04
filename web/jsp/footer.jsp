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
        <div class="footer-copyright black">
            <div class="row right">
                <input type="image" src="../img/logo/ru.png" onclick="changeLocale('ru_RU')"/>
                <input type="image" src="../img/logo/eng.png" onclick="changeLocale('default')"/>
            </div>
        </div>
    </footer>
</body>
</html>
