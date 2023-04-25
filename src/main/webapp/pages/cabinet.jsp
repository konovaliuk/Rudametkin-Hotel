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

<body>

<jsp:include page="./page-parts/header.jsp" />


<h1 style="margin-top: 1em; text-align: center;">CABINET | HOTEL CLASSIC</h1>
<h3 style="margin-top: 0.3em; text-align: center;">welcome, <c:out value="${sessionScope.user.name}"/></h3>
<div id = "order-info-container">
    <div class="half">
        <div id= "last-orders-container">
            <div id="last-orders-title">LAST ORDERS</div>
            <div id="orders-body">
                <c:forEach items="${sessionScope.orders}" var="item">
                    <div class="order-item">${item.toString()}</div>
                </c:forEach>
            </div>
        </div>
    </div>
    <div class="half">
        <div id="user-info">
        <pre>
        Name: <c:out value="${sessionScope.user.name}"/>
        Surname: <c:out value="${sessionScope.user.surname}"/>
        Login: <c:out value="${sessionScope.user.login}"/>
        Email: <c:out value="${sessionScope.user.email}"/>
        Phone: <c:out value="${sessionScope.user.phone}"/>
        Additional info: <c:out value="${sessionScope.user.userInfo}"/>
        </pre>
        </div>
    </div>
</div>

<jsp:include page="./page-parts/room-search-form.jsp" />

<jsp:include page="./page-parts/footer.jsp" />
</body>
<script src="${pageContext.request.contextPath}/resources/scripts/search-form.js"></script>
<script>
    let orders = document.querySelector("#orders-body");
    if(orders.children.length === 0) {
        let emptySpan = document.createElement("span");
        emptySpan.id = "last-orderds-empty-text";
        emptySpan.innerText = "Empty";
        orders.append(emptySpan)
    }

</script>
</html>
