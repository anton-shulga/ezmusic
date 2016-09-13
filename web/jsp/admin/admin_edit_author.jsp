<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 19.08.2016
  Time: 20:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.epam.webpoject.ezmusic.util.RandomTokenGenerator"%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="property.page_content"/>
<html>
<head>
    <title><fmt:message key="title.edit_author"/> </title>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" media="screen,projection"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bin/materialize.min.js"></script>
    <script>
        $(document).ready(function() {
            $('select').material_select();
        });
    </script>
    <script>
        $(document).ready(function() {
            $('#id-albums').on("change", function(event) {
                var selectedAlbums = $('#id-albums').val();
                $.ajax({
                    url: 'JsonController',
                    type: 'post',
                    dataType: 'json',
                    data: {selected_albums:selectedAlbums, command:"find_album_songs_json"},
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
<c:import url="${pageContext.request.contextPath}/jsp/header.jsp"/>
<c:import url="admin_navbar.jsp"/>
<main>
<div class="wrapper">
    <div class="container">
        <div class="row">
            <div class="col s8 offset-s2">
                <div class="card z-depth-4">
                    <div class="card-content">
                        <span class="card-title text-darken-2"><fmt:message key="title.author"/></span>
                        <form action="${pageContext.request.contextPath}/controller" method="POST">
                            <c:if test="${not empty author}">
                                <input type="hidden" name="command" value="update_author">
                                <input type="hidden" name="author_id" value="${author.authorId}">
                            </c:if>
                            <c:if test="${empty author}">
                                <input type="hidden" name="command" value="create_author">
                            </c:if>
                            <input type="hidden" name="token" value="${RandomTokenGenerator.nextToken()}">
                            <div class="row">
                                <div class="input-field col s12">
                                    <input id="id-author-name" type="text" name="author_name" value="${author.name}" required>
                                    <label for="id-author-name"><fmt:message key="label.name"/></label>
                                </div>
                            </div>

                            <div class="row">
                                <div class="input-field col s12">
                                    <input id="id-author-country" type="text" name="author_country" value="${author.country}" required>
                                    <label for="id-author-country"><fmt:message key="label.country"/></label>
                                </div>
                            </div>

                            <div class="row">
                                <div class="input-field col s12">
                                    <select multiple class="icons" id="id-albums" name="selected_albums">
                                        <c:forEach items="${requestScope.all_albums}" var="item">
                                            <c:choose>
                                                <c:when test="${fn:contains(author_albums, item)}">
                                                    <option value="${item.albumId}" selected data-icon="${pageContext.request.contextPath}/img/album.jpeg" class="circle">${item.name}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${item.albumId}" data-icon="${pageContext.request.contextPath}/img/album.jpeg" class="circle">${item.name}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                    <label for="id-albums"><fmt:message key="label.albums"/></label>
                                </div>
                            </div>

                            <div class="row">
                                <div class="input-field col s12">
                                    <select multiple class="icons" id="id-songs" name="selected_songs">
                                        <c:forEach items="${requestScope.all_songs}" var="item">
                                            <c:choose>
                                                <c:when test="${fn:contains(author_songs, item)}">
                                                    <option value="${item.songId}" selected >${item.name}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${item.songId}">${item.name}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                    <label for="id-songs"><fmt:message key="label.songs"/></label>
                                </div>
                            </div>

                            <div class="row">
                                <div class="file-field input-field">
                                    <div class="btn black">
                                        <span><fmt:message key="button.file"/></span>
                                        <input type="file">
                                    </div>
                                    <div class="file-path-wrapper">
                                        <input class="file-path validate" type="text" name="author_photo_path" value="${author.photoPath}" required>
                                    </div>
                                </div>
                            </div>

                            <div class="card-action">
                                <div class="row">
                                    <button class="btn col s12 black" type="submit"><fmt:message key="button.save_changes"/> </button>
                                </div>

                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <c:import url="${pageContext.request.contextPath}/jsp/footer.jsp"/>
</div>
</main>
<c:if test="${requestScope.message != null}">
    <script> Materialize.toast('${requestScope.message}', 4000);</script>
</c:if>

</body>
</html>
