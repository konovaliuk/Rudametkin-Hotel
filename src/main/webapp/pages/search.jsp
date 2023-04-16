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

<jsp:useBean id="searchRooms" class="com.rudametkin.hotelsystem.businessLogic.RoomsSearchingService" scope="session"/>

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

<h1 style="margin-top: 1em; text-align: center;">ROOM SEARCHING | HOTEL CLASSIC</h1>

<div id="search-rooms-container">
    <div id="search-rooms-title">APARTMENTS SEARCH FORM</div>
    <form action="${pageContext.request.contextPath}/Controller?command=start-search" id="fields-container" method="post">
      <span>
        <img src="${pageContext.request.contextPath}/resources/pictures/person-logo.png" alt="PersonPic" class="form-icon search-form-item">
        <button type="button" class="field-counter-button search-form-item" onclick="decreaseCounterValue(this.nextElementSibling);" disabled>-</button>
        <span id="persons-amount" class="counter search-form-item">
            <c:choose>
                <c:when test="${empty searchRooms}">
                    <c:out value="1" />
                </c:when>
                <c:otherwise>
                    <c:out value="${searchRooms.capacity}" />
                </c:otherwise>
            </c:choose>
        </span>
        <button type="button" class="field-counter-button right-indent search-form-item" onclick="increaseCounterValue(this.previousElementSibling);">+</button>
        <input id="input-persons-amount" type="hidden" name="persons-amount" value="1"/>
      </span>
        <span>
          <img src="${pageContext.request.contextPath}/resources/pictures/bed-logo.png" alt="BedPic" class="form-icon search-form-item">
          <button type="button" class="field-counter-button search-form-item" onclick="decreaseCounterValue(this.nextElementSibling);" disabled>-</button>
          <span id="beds-amount" class="counter search-form-item">
              <c:choose>
                  <c:when test="${empty searchRooms}">
                      <c:out value="1" />
                  </c:when>
                  <c:otherwise>
                      <c:out value="${searchRooms.bedsAmount}" />
                  </c:otherwise>
              </c:choose>
          </span>
          <button type="button" class="field-counter-button right-indent search-form-item" onclick="increaseCounterValue(this.previousElementSibling);">+</button>
          <input id="input-beds-amount" type="hidden" name="beds-amount" value="1" hidden />
      </span>
        <span>
        <img src="${pageContext.request.contextPath}/resources/pictures/calendar-logo.png" alt="BedPic" class="form-icon search-form-item">
        <input name="arrival-date" class="form-data-input search-form-item" id="arrival-date" type="date" onchange="updateMinDepartureDate();">
        <span class="search-form-item" style="margin: 0 3px; font-size: 20px;">-</span>
        <input name="departure-date" class="form-data-input search-form-item right-indent" id="departure-date" type="date" onchange="updateMinDepartureDate();">
      </span>
        <span>
        <img src="${pageContext.request.contextPath}/resources/pictures/coins-logo.png" alt="CoinsPic" class="form-icon search-form-item">
        <select name="room-type" id="room-type-select" class="search-form-item right-indent" onchange="updateFormStringAndInputs();">
          <option value="Any" selected>Any</option>
          <option value="Economy">Economy</option>
          <option value="Standard">Standard</option>
          <option value="Deluxe">Deluxe</option>
          <option value="Suite">Suite</option>
          <option value="Presidential">Presidential</option>
        </select>
      </span>
        <input type="text" id="form-result-info" class="search-form-item right-indent" disabled />
        <button type="submit" name="button" class="submit-search search-form-item">
            <div class="submit-search-image"></div>
        </button>
    </form>
</div>

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
<script src="${pageContext.request.contextPath}/resources/scripts/search-form.js"></script>
<script>
    window.addEventListener("load", (event) => {
        let persAmountView = document.querySelector("#persons-amount");
        let persAmountInput = document.querySelector("#input-persons-amount");

        persAmountView.innerHTML = persAmountView.innerHTML.trim();
        persAmountInput.value = parseInt(persAmountView.innerHTML);

        let bedsAmountView = document.querySelector("#beds-amount");
        let bedsAmountInput = document.querySelector("#input-beds-amount");

        bedsAmountView.innerHTML = bedsAmountView.innerHTML.trim();
        bedsAmountInput.value = parseInt(bedsAmountView.innerHTML);

        updateFormStringAndInputs();
        setFormButtonsCondition();
    })

    function setFormButtonsCondition() {
        let persAmountView = document.querySelector("#persons-amount");
        persAmountView.previousElementSibling.disabled = Number(persAmountView.innerHTML) === 1;

        let bedsAmountView = document.querySelector("#beds-amount");
        bedsAmountView.previousElementSibling.disabled = Number(bedsAmountView.innerHTML) === 1;
    }
</script>
</body>

</html>