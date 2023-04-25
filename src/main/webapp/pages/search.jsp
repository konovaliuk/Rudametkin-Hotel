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


<c:if test="${empty sessionScope.user}">
    <c:redirect url="/login" />
</c:if>


<body>

<jsp:include page="./page-parts/header.jsp" />

<h1 style="margin-top: 1em; text-align: center;">ROOM SEARCHING | HOTEL CLASSIC</h1>

<jsp:include page="./page-parts/room-search-form.jsp" />

<div id="found-rooms-container">
    <c:forEach items="${sessionScope.foundRooms}" var="item">
        <form class="room-item-wrapper" action="${pageContext.request.contextPath}/booking" method="post">
            <div class="room-item-main-content-wrapper">
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
                        Rooms amount: <c:out value="${item.roomsAmount}"/><br>
                        Price: <c:out value="${item.price}"/>
                    </p>
                    <div class="bottom-container">
                        <button type="button" class="details-button" onclick="toggleDetails(this);">Show details</button>
                        <button type="submit" class="pick-button">Pick ></button>
                    </div>
                </div>
            </div>

            <div class="room-item-details-wrapper slow-showing-div hidden-div">
                <div class="details-container">
                    <p>
                        Details
                    </p>
                    <p>
                        Details
                    </p>
                    <p>
                        Details
                    </p>
                    <p>
                        Details
                    </p>
                    <p>
                        Details
                    </p>
                </div>
            </div>
            <input type="hidden" name="type" value="${item.type.toString()}">
            <input type="hidden" name="rooms" value="${item.roomsAmount}">
            <input type="hidden" name="sbeds" value="${item.singleBedsAmount}">
            <input type="hidden" name="dbeds" value="${item.doubleBedsAmount}">
            <input type="hidden" name="tv" value="${item.tv}">
            <input type="hidden" name="dryer" value="${item.dryer}">
            <input type="hidden" name="minibar" value="${item.miniBar}">
            <input type="hidden" name="price" value="${item.price}">

            <input type="hidden" name="arrival-date" value="${param['arrival-date']}">
            <input type="hidden" name="departure-date" value="${param['departure-date']}">
        </form>
    </c:forEach>
</div>

<c:if test="${sessionScope.maxPages > 1 and (empty param.page or (param.page <= sessionScope.maxPages and param.page >= 1))}">
    <div id="page-navigation-bar">
        <div style="width: 260px;">
            <c:choose>
                <c:when test="${not empty param.page and param.page > 1}">
                    <button onclick="prevSearchPage();">Previous</button>
                </c:when>
                <c:otherwise>
                    <button onclick="prevSearchPage();" disabled>Previous</button>
                </c:otherwise>
            </c:choose>

                <span id="page-number-span">1</span>

            <c:choose>
                <c:when test="${(empty param.page and 1 < sessionScope.maxPages) or (not empty param.page and param.page < sessionScope.maxPages)}">
                    <button onclick="nextSearchPage();">Next</button>
                </c:when>
                <c:otherwise>
                    <button onclick="nextSearchPage();" disabled>Next</button>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</c:if>

<jsp:include page="./page-parts/footer.jsp" />
</body>
<script src="${pageContext.request.contextPath}/resources/scripts/search-form.js"></script>
<script>
    const pageNumberElement = document.querySelector("#page-number-span");
    assignCurrentSearchPageNumber();

    function toggleDetails(element) {
        let detailsEl = element.parentElement.parentElement.parentElement.nextElementSibling;
        detailsEl.classList.toggle("hidden-div");
        detailsEl.classList.toggle("shown-div");
    }

    function assignCurrentSearchPageNumber() {
        const url = new URL(window.location.href);
        const currentPage = url.searchParams.get("page") == null ?
                            1 : parseInt(url.searchParams.get("page"));
        pageNumberElement.innerHTML = currentPage.toString();
    }

    function prevSearchPage() {
        const url = new URL(window.location.href);
        const currentPage = url.searchParams.get("page") == null ?
            1 : parseInt(url.searchParams.get("page"));
        url.searchParams.set('page', (currentPage-1).toString());
        window.location.href = url.href;
    }

    function nextSearchPage() {
        const url = new URL(window.location.href);
        const currentPage = url.searchParams.get("page") == null ?
            1 : parseInt(url.searchParams.get("page"));
        url.searchParams.set('page', (currentPage+1).toString());
        window.location.href = url.href;
    }
</script>

</script>
</html>