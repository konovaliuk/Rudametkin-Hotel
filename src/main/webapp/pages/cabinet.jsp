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
</head>
<body>

<jsp:useBean id="user" class="com.rudametkin.hotelsystem.businessLogic.UserService" scope="session" />

<c:if test="${user.isAuthenticated == false}">
    <c:redirect url="/pages/main.jsp" />
</c:if>

<header class="header">
    <div class="left-half">
        <form action="../Controller?command=redirect-home-page" method="post" id="logo-text-container" class="header-button-container">
            <button type="submit" id="header-logo-text-button">Classic</button>
        </form>
    </div>
    <div class="right-half">
        <form action="../Controller?command=logout" method="post" id="logout-button-container" class="header-button-container">
            <button type="submit" class="header-button">Log out</button>
        </form>
        <div class="header-button-container" style="height: 38px">
            <div id="role-dropdown">
                <div id="current-role">Client</div>
                <div id="role-dropdown-child">
                    <div>Admin</div>
                </div>
            </div>
        </div>
    </div>
</header>
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

<div id="search-rooms-container">
    <div id="search-rooms-title">APARTMENTS SEARCH</div>
    <form action="" id="fields-container">
      <span>
        <img src="${pageContext.request.contextPath}/resources/pictures/person-logo.png" alt="PersonPic" class="form-icon search-form-item">
        <button type="button" class="field-counter-button search-form-item" onclick="decreaseCounterValue(this.nextElementSibling);" disabled>-</button>
        <span id="persons-amount" class="counter search-form-item">1</span>
        <button type="button" class="field-counter-button right-indent search-form-item" onclick="increaseCounterValue(this.previousElementSibling);">+</button>
      </span>
        <span>
        <img src="${pageContext.request.contextPath}/resources/pictures/bed-logo.png" alt="BedPic" class="form-icon search-form-item">
        <button type="button" class="field-counter-button search-form-item" onclick="decreaseCounterValue(this.nextElementSibling);" disabled>-</button>
        <span id="beds-amount" class="counter search-form-item">1</span>
        <button type="button" class="field-counter-button right-indent search-form-item" onclick="increaseCounterValue(this.previousElementSibling);">+</button>
      </span>
        <span>
        <img src="${pageContext.request.contextPath}/resources/pictures/calendar-logo.png" alt="BedPic" class="form-icon search-form-item">
        <input class="form-data-input search-form-item" id="arrival-date" type="date" onchange="updateMinDepartureDate();">
        <span class="search-form-item" style="margin: 0 3px; font-size: 20px;">-</span>
        <input class="form-data-input search-form-item right-indent" id="departure-date" type="date" onchange="updateMinDepartureDate();">
      </span>
        <span>
        <img src="${pageContext.request.contextPath}/resources/pictures/coins-logo.png" alt="CoinsPic" class="form-icon search-form-item">
        <select name="room-type" id="room-type-select" class="search-form-item right-indent" onchange="updateFormStringAndInputs();">
          <option value="Any type" selected>Any</option>
          <option value="Economy">Economy</option>
          <option value="Standard">Standard</option>
          <option value="Deluxe">Deluxe</option>
          <option value="Suite">Suite</option>
          <option value="Presidential">Presidential</option>
        </select>
      </span>
        <input type="text" id="form-result-info" class="search-form-item right-indent" disabled></input>
        <button type="submit" name="button" class="submit-search search-form-item">
            <div class="submit-search-image"></div>
        </button>
    </form>
</div>
</body>

<script src="${pageContext.request.contextPath}/resources/scripts/cabinet.js"></script>

</html>
