<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Cabinet | Classic Hotel</title>
    <link href="${pageContext.request.contextPath}/resources/styles/page-styles.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/styles/cabinet-page.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/styles/search-room-form.css" rel="stylesheet">
</head>

<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
%>

<jsp:useBean id="user" class="com.rudametkin.hotelsystem.EntityObjects.UserWithRoles" scope="session" />

<c:if test="${empty user.login}">
    <c:redirect url="/login" />
</c:if>

<body>

<jsp:include page="./page-parts/header.jsp" />


<h1 style="margin-top: 1em; text-align: center;">CABINET | HOTEL CLASSIC</h1>
<h3 style="margin-top: 0.3em; text-align: center;">welcome, <jsp:getProperty name="user" property="name" /></h3>
<div id = "order-info-container">
    <div class="half">
        <div id= "last-orders-container">
            <div id="last-orders-title">LAST ORDERS</div>
            <div style="height: 300px;">
                <span id="last-orderds-empty-text">Empty</span>
            </div>
        </div>
    </div>
    <div class="half">
        <div id="user-info">
      <pre>
        Name: <jsp:getProperty name="user" property="name" />
        Surname: <jsp:getProperty name="user" property="surname" />
        Login: <jsp:getProperty name="user" property="login" />
        Email: <jsp:getProperty name="user" property="email" />
        Phone: <jsp:getProperty name="user" property="phone" />
        Additional info: <jsp:getProperty name="user" property="info" />
      </pre>
        </div>
    </div>
</div>

<jsp:include page="./page-parts/room-search-form.jsp" />

<footer></footer>
</body>
<script src="${pageContext.request.contextPath}/resources/scripts/search-form.js"></script>
</html>
