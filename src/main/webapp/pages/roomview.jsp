<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Search rooms | Classic Hotel</title>
    <link href="${pageContext.request.contextPath}/resources/styles/page-styles.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/styles/search-page.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/styles/search-room-form.css" rel="stylesheet">
</head>

<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
%>

<jsp:useBean id="user" class="com.rudametkin.hotelsystem.businessLogic.UserService" scope="session" />

<c:if test="${user.isAuthenticated == false}">
    <c:redirect url="/pages/login.jsp" />
</c:if>

<body>
<header class="header">
    <div class="left-half">
        <form action="${pageContext.request.contextPath}/Controller?command=redirect-home-page" method="post" id="logo-text-container" class="header-button-container">
            <button type="submit" id="header-logo-text-button">Classic</button>
        </form>
    </div>
    <div class="right-half">
        <form action="${pageContext.request.contextPath}/Controller?command=logout" method="post" id="logout-button-container" class="header-button-container">
            <button type="submit" class="header-button">Log out</button>
        </form>
        <form action="${pageContext.request.contextPath}/Controller?command=redirect-cabinet-page" method="post" id="cabinet-button-container" class="header-button-container">
            <button type="submit" class="header-button">Cabinet</button>
        </form>
    </div>
</header>

<h1 style="margin-top: 1em; text-align: center;">ROOM VIEW | HOTEL CLASSIC</h1>

<footer></footer>
<script src="${pageContext.request.contextPath}/resources/scripts/search-form.js"></script>
</body>

</html>