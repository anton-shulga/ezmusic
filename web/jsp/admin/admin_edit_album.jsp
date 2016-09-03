<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 18.08.2016
  Time: 23:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit album</title>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="../../css/styles.css" media="screen,projection"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="../../js/bin/materialize.min.js"></script>
    <script>
        $(document).ready(function() {
            $('select').material_select();
        });
    </script>
    <script>
        $(document).ready(function() {
            $('#id-authors').on("change", function(event) {
                var selectedAuthors = $('#id-authors').val();
                $.ajax({
                    url: 'JsonController',
                    type: 'post',
                    dataType: 'json',
                    data: {selected_authors:selectedAuthors, command:"find_author_songs_json"},
                    success: function (json) {
                        var $select = $('#id-songs');
                        $select.find('option').remove();
                        $select.material_select('update');
                        $select.closest('.input-field').children('span.caret').remove();
                        $.each(json, function(index,song ) {
                            if(json.length != 0) {
                                $select.append($("<option></option>").val(song.songId).html(song.name));
                                $select.material_select('update');
                                $select.closest('.input-field').children('span.caret').remove();
                            }
                        });
                    }
                });
            });
        });
    </script>
</head>
<body>
<c:import url="../header.jsp"/>
<c:import url="admin_navbar.jsp"/>
<main>
    <div class="wrapper">
        <div class="container">

            <form action="${pageContext.request.contextPath}/controller" method="POST">
                <c:if test="${not empty album}">
                    <input type="hidden" name="command" value="update_album">
                    <input type="hidden" name="album_id" value="${album.albumId}">
                </c:if>
                <c:if test="${empty album}">
                    <input type="hidden" name="command" value="create_album">
                </c:if>
                <div class="input-field col s12">
                    <input type="text" name="album_name" value="${album.name}">
                    <label>Name</label>
                </div>
                <div class="input-field col s12">
                    <input type="text" name="album_year" value="${album.year}">
                    <label>Year</label>
                </div>
                <div class="input-field col s12">
                    <select multiple class="icons" id="id-authors" name="selected_authors">
                        <option value="" disabled selected>Select authors</option>
                        <c:forEach items="${requestScope.all_authors}" var="item">
                            <c:choose>
                                <c:when test="${fn:contains(album_authors, item)}">
                                    <option value="${item.authorId}" selected data-icon="${item.photoPath}" class="circle">${item.name}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${item.authorId}" data-icon="${item.photoPath}" class="circle">${item.name}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                    <label>Authors</label>
                </div>
                <div class="input-field col s12">
                    <select multiple class="icons" id="id-songs" name="selected_songs">
                        <option value="" disabled selected>Select songs</option>
                        <c:forEach items="${requestScope.all_songs}" var="item">
                            <c:choose>
                                <c:when test="${fn:contains(album_songs, item)}">
                                    <option value="${item.songId}" selected>${item.name}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${item.songId}">${item.name}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                    <label>Albums</label>
                </div>
                <div class="file-field input-field">
                    <div class="btn">
                        <span>File</span>
                        <input type="file">
                    </div>
                    <div class="file-path-wrapper">
                        <input class="file-path validate" type="text" name="album_image_file_path" value="${album.imageFilePath}">
                    </div>
                </div>
                <button class="btn" type="submit">Save changes</button>
            </form>
        </div>
    </div>
</main>

<c:import url="/jsp/footer.jsp"/>
</body>
</html>
