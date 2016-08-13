<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Edit song</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="../../js/bin/materialize.min.js"></script>
    <script>
        $('.datepicker').pickadate({
            selectMonths: true, // Creates a dropdown to control month
            selectYears: 15 // Creates a dropdown of 15 years to control year
        });
    </script>
</head>
<body>
<div class="wrapper">
    <c:import url="../header.jsp"/>
    <div class="container">
        <div>
            <form action="${pageContext.request.contextPath}/controller" method="POST">
                <input type="hidden" name="command" value="update_song">
                <input type="hidden" name="song_id" value="${song.songId}">
                <input type="text" name="song_name" value="${song.name}">
                <input type="text" name="song_year" value="${song.year}">
                <input type="text" name="song_cost" value="${song.cost}">
                <input type="date" class="datepicker" name="song_publication_date" value="${song.publicationDate}">
                <div class="file-field input-field">
                    <div class="btn">
                        <span>File</span>
                        <input type="file">
                    </div>
                    <div class="file-path-wrapper">
                        <input class="file-path validate" type="text" name="song_file_path" value="${song.filePath}">
                    </div>
                </div>
                <button class="btn" type="submit">Save changes</button>
            </form>
        </div>
    </div>

</div>
<c:import url="/jsp/footer.jsp"/>

</body>
</html>
