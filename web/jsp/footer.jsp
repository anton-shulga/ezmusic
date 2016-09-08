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
<footer class="page-footer black">
        <div class="container">
            <span class="white-text">© 2016 Anton Shulha</span>
        </div>
</footer>
</body>
</html>
