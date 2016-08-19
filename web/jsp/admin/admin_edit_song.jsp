<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="../../css/styles.css" media="screen,projection"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="../../js/bin/materialize.min.js"></script>
    <script>
        $('.datepicker').pickadate({
            selectMonths: true, // Creates a dropdown to control month
            selectYears: 15 // Creates a dropdown of 15 years to control year
        });
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
                    data: {selected_authors:selectedAuthors, command:"find_author_albums_json"},
                    success: function (json) {
                        var $select = $('#id-album');
                        $select.find('option').remove();
                        $select.material_select('update');
                        $select.closest('.input-field').children('span.caret').remove();
                        $.each(json, function(index, album) {
                            if(json.length != 0) {
                                $select.append($("<option></option>").val(album.albumId).html(album.name));
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
<div class="wrapper">
    <c:import url="../header.jsp"/>
    <div class="container">

            <form action="${pageContext.request.contextPath}/controller" method="POST">
                <c:if test="${not empty song}">
                    <input type="hidden" name="command" value="update_song">
                    <input type="hidden" name="song_id" value="${song.songId}">
                </c:if>
                <c:if test="${empty song}">
                    <input type="hidden" name="command" value="create_song">
                </c:if>
                <div class="input-field col s12">
                    <input type="text" name="song_name" value="${song.name}">
                    <label>Name</label>
                </div>
                <div class="input-field col s12">
                    <input type="text" name="song_year" value="${song.year}">
                    <label>Year</label>
                </div>
                <div class="input-field col s12">
                    <select multiple class="icons" id="id-authors" name="selected_authors">
                    <option value="" disabled selected>Select authors</option>
                    <c:forEach items="${requestScope.all_authors}" var="item">
                        <c:choose>
                            <c:when test="${fn:contains(song_authors, item)}">
                                <option value="${item.authorId}" selected data-icon="${pageContext.request.contextPath}/img/album.jpeg" class="circle">${item.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${item.authorId}" data-icon="${pageContext.request.contextPath}/img/album.jpeg" class="circle">${item.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    </select>
                    <label>Authors</label>
                </div>
                <div class="input-field col s12">
                    <select multiple class="icons" id="id-album" name="selected_albums">
                        <option value="" disabled selected>Select album</option>
                        <c:forEach items="${requestScope.all_albums}" var="item">
                            <c:choose>
                                <c:when test="${fn:contains(song_albums, item)}">
                                    <option value="${item.albumId}" selected>${item.name}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${item.albumId}">${item.name}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                    <label>Albums</label>
                </div>

                <div class="input-field col s12">
                    <input type="text" name="song_cost" value="${song.cost}">
                    <label>Cost</label>
                </div>
                <div class="input-field col s12">
                    <input type="date" class="datepicker" name="song_publication_date" value="${song.publicationDate}">
                </div>
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
<c:import url="/jsp/footer.jsp"/>

</body>
</html>
