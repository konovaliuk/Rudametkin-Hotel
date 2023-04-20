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

<jsp:useBean id="user" class="com.rudametkin.hotelsystem.EntityObjects.UserWithRoles" scope="session" />
<jsp:useBean id="searchRooms" class="com.rudametkin.hotelsystem.Services.RoomsSearchingService" scope="session"/>

<c:if test="${empty user.login}">
    <c:redirect url="/login" />
</c:if>


<body>

<jsp:include page="./page-parts/header.jsp" />

<h1 style="margin-top: 1em; text-align: center;">ROOM SEARCHING | HOTEL CLASSIC</h1>

<jsp:include page="./page-parts/room-search-form.jsp" />

<div id="found-rooms-container">
    <c:if test="${not empty searchRooms}">
        <c:forEach items="${searchRooms.foundRooms}" var="item">
            <div class="room-item">
                <div class="item-image-wrapper">
                    <img class="room-item-image" src="https://place-hold.it/600x250" alt="img">
                </div>
                <div class="item-content-wrapper">
                    <h1 class="header">Apartments</h1>
                    <p>
                        Type: <c:out value="${item.type}"/> <br>
                        Single beds amount: <c:out value="${item.singleBedsAmount}"/> <br>
                        Double beds amount: <c:out value="${item.doubleBedsAmount}"/> <br>
                        Capacity: <c:out value="${item.singleBedsAmount + item.doubleBedsAmount * 2}"/> <br>
                        Price: <c:out value="${item.price}"/>
                    </p>
                    <button>View</button>
                </div>
            </div>
        </c:forEach>
    </c:if>
</div>

<c:if test="${not empty searchRooms && ((searchRooms.page != 1) || (searchRooms.maxPages != searchRooms.page))}">
    <div id="page-navigation-bar">
        <div>
            <c:if test="${searchRooms.page > 1}">
                <form action="${pageContext.request.contextPath}/Controller?command=prev-search-page" method="post">
                    <button>Previous</button>
                </form>
            </c:if>
            <span><c:out value="${searchRooms.page}"/></span>
            <c:if test="${searchRooms.maxPages > searchRooms.page}">
                <form action="${pageContext.request.contextPath}/Controller?command=next-search-page" method="post">
                    <button>Next</button>
                </form>
            </c:if>
        </div>
    </div>
</c:if>

<footer></footer>
</body>
<script src="${pageContext.request.contextPath}/resources/scripts/search-form.js"></script>
</html>