<%@ taglib prefix="ctg" uri="customtags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 25.07.2016
  Time: 1:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="property.page_content"/>
<html>
<head>
    <title><fmt:message key="title.home"/></title>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" media="screen,projection"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src=".${pageContext.request.contextPath}/js/bin/materialize.min.js"></script>
    <script>
        (function($){
            $(function(){
                $('.parallax').parallax();
            }); // end of document ready
        })(jQuery); // end of jQuery name space
    </script>
</head>
<body>
<c:import url="${pageContext.request.contextPath}/jsp/header.jsp"/>
<c:import url="${pageContext.request.contextPath}/jsp/user/user_navbar.jsp"/>
<main>
    <div id="index-banner" class="parallax-container">
        <div class="section no-pad-bot">
            <div class="container">
                <br><br>
                <h1 class="header center teal-text black-text">EZMusic</h1>
                <div class="row center">
                    <h5 class="header col s12 black-text">Let music flow in your heart and enrich your soul</h5>
                </div>
                <br><br>
            </div>
        </div>
        <div class="parallax"><img src="../../img/paralax-user.jpg" alt="guitar"></div>
    </div>

    <div class="container">
        <div class="section">
            <div class="row">
                <div class="col s12 m4">
                    <div class="icon-block">
                        <h2 class="center black-text"><i class="material-icons small">search</i></h2>
                        <h5 class="center">Find your favourite song</h5>
                        <p class="light">We did most of the heavy lifting for you to provide a default stylings that incorporate our custom components. Additionally, we refined animations and transitions to provide a smoother experience for developers.</p>
                    </div>
                </div>

                <div class="col s12 m4">
                    <div class="icon-block">
                        <h2 class="center black-text"><i class="material-icons small">playlist_add</i></h2>
                        <h5 class="center">Add song to shopping cart</h5>

                        <p class="light">By utilizing elements and principles of Material Design, we were able to create a framework that incorporates components and animations that provide more feedback to users. Additionally, a single underlying responsive system across all platforms allow for a more unified user experience.</p>
                    </div>
                </div>

                <div class="col s12 m4">
                    <div class="icon-block">
                        <h2 class="center black-text"><i class="material-icons small">shopping_cart</i></h2>
                        <h5 class="center">Buy song and download it</h5>

                        <p class="light">We have provided detailed documentation as well as specific code examples to help new users get started. We are also always open to feedback and can answer any questions a user may have about Materialize.</p>
                    </div>
                </div>
            </div>

        </div>
    </div>


    <div class="parallax-container valign-wrapper">
        <div class="section no-pad-bot">
            <div class="container">
                <div class="row center">
                    <h5 class="header col s12 white-text">A modern responsive front-end framework based on Material Design</h5>
                </div>
            </div>
        </div>
        <div class="parallax"><img src="../../img/user-parallax2.jpg  " alt="Unsplashed background img 2"></div>
    </div>
</main>
<c:if test="${requestScope.message != null}">
    <script> Materialize.toast('${requestScope.message}', 4000);</script>
</c:if>
<c:import url="${pageContext.request.contextPath}/jsp/footer.jsp"/>
</body>
</html>
