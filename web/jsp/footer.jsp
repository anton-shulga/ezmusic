<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 08.08.2016
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.page_content"/>
<html>
<head>
    <title><fmt:message key="title.footer"/></title>
    <script>
        $(document).ready(function () {
            $('select').material_select();
        });</script>
    <script>
        $(document).ready(function () {
            $('#id-locale').on("change", function (event) {
                var selectedLocale = $('#id-locale').val();
                $.ajax({
                    url: "jsoncontroller",
                    method: "post",
                    dataType: "Json",
                    data: {
                        locale: selectedLocale,
                        command: "change_locale"
                    }
                }).done(function (data) {
                    location.reload();
                });
            })
        });
    </script>
</head>
<body>
<footer class="page-footer black">
    <div class="container">
        <div class="row">
            <div class="col s2 offset-s5">
                <select id="id-locale" name="locale" class="center white-text">
                    <option value="" disabled selected><fmt:message key="option.language"/></option>
                    <option value="ru_RU">Русский</option>
                    <option value="default">English</option>
                </select>
            </div>
        </div>
    </div>
    <div class="footer-copyright black">
        <div class="white-text center-align">
            <span class="white-text "><fmt:message key="footer.copyright"/></span>
        </div>

    </div>
</footer>

</body>
</html>
