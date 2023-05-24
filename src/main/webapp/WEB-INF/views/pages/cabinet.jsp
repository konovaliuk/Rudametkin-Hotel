<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Cabinet | Classic Hotel</title>
    <link href="/styles/page-styles.css" rel="stylesheet">
    <link href="/styles/cabinet-page.css" rel="stylesheet">
    <link href="/styles/search-room-form.css" rel="stylesheet">
</head>

<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
%>

<body>

<jsp:include page="./page-parts/header.jsp" />


<h1 style="margin-top: 1em; text-align: center;">CABINET | HOTEL CLASSIC</h1>
<h3 style="margin-top: 0.3em; text-align: center;">welcome, <c:out value="${sessionScope.user.name}"/></h3>

<c:choose>
    <c:when test="${sessionScope.user.hasRole('Client')}">
        <div id = "order-info-container">
            <div class="half">
                <div id= "last-orders-container">
                    <div id="last-orders-title">LAST ORDERS</div>
                    <div id="orders-body">
                        <c:forEach items="${requestScope.orders}" var="item">
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
    </c:when>
    <c:otherwise>
        <form action="${pageContext.request.contextPath}/admin-search" method="post">
            <c:choose>
                <c:when test="${not empty sessionScope.clientLogin}">
                    <input name="client-login" id="client-search-input" type="text" placeholder="Input login of Client" value="${sessionScope.clientLogin}">
                </c:when>
                <c:otherwise>
                    <input name="client-login" id="client-search-input" type="text" placeholder="Input login of Client">
                </c:otherwise>
            </c:choose>
            <button type="submit" style="display: none;"></button>
        </form>

        <c:if test="${not empty sessionScope.clientOrders}">
            <div style="padding: 20px;">
                <c:forEach items="${sessionScope.clientOrders}" var="item">
                    <form action="${pageContext.request.contextPath}/cancelOrder" method="post">
                        <div class="order-item" style="font-size: 24px;">${item.toString()}
                            <button class="cancel-order-button" type="submit">Cancel</button>
                            <input name="room-register-id" type="hidden" value="${item.roomRegisterId}">
                        </div>
                    </form>
                </c:forEach>
            </div>
        </c:if>

    </c:otherwise>
</c:choose>



<jsp:include page="./page-parts/footer.jsp" />
</body>

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
