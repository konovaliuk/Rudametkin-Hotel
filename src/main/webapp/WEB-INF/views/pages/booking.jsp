<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Booking | Classic Hotel</title>
    <link href="/styles/page-styles.css" rel="stylesheet">
    <link href="/styles/booking.css" rel="stylesheet">
</head>

<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
%>


<c:if test="${empty sessionScope.user}">
    <c:redirect url="/login" />
</c:if>


<body>

<jsp:include page="./page-parts/header.jsp" />

<h1 style="margin-top: 1em; text-align: center;">ROOM BOOKING | HOTEL CLASSIC</h1>

<form action="${pageContext.request.contextPath}/book" method="post">

    <div class="apartments-stats-container">
        <div class="body-wrapper">
            <div class="half">
                <h2 style="text-align: center; display: block; width: 100%;">Apartments</h2>
                <pre style="margin-top: 15px; font-size: 16px; line-height: 20px;">
                Type: <c:out value="${requestScope.bookingRoom.type}"/>
                Rooms amount:  <c:out value="${requestScope.bookingRoom.rooms}"/>
                Single beds amount:  <c:out value="${requestScope.bookingRoom.sbeds}"/>
                Double beds amount:  <c:out value="${requestScope.bookingRoom.dbeds}"/>
                Maximum capacity:  <c:out value="${requestScope.bookingRoom.sbeds + 2*requestScope.bookingRoom.dbeds}"/>
            </pre>
            </div>
            <div class="half">
                <h2 style="text-align: center; display: block; width: 100%;">Details</h2>
                <pre style="margin-top: 15px; font-size: 16px; line-height: 20px;">
                TV:  <c:out value="${requestScope.bookingRoom.tv}"/>
                Mini bar:  <c:out value="${requestScope.bookingRoom.miniBar}"/>
                Dryer:  <c:out value="${requestScope.bookingRoom.dryer}"/>

                Arrival date:  <c:out value="${requestScope.bookingRoom.arrivalDateFormatted}"/>
                Departure date:  <c:out value="${requestScope.bookingRoom.departureDateFormatted}"/>
            </pre>
            </div>
        </div>
    </div>

    <div class="form-data-container">
        <h2 style="margin-bottom: 1em; text-align: center;">Booking form</h2>
        <label class="field-label">Name: </label>
        <input type="text" value="${sessionScope.user.name}" disabled>
        <label class="field-label">Surname: </label>
        <input type="text" value="${sessionScope.user.surname}" disabled>
        <label class="field-label">Phone: </label>
        <input type="text" value="${sessionScope.user.phone}" disabled>
        <label class="field-label">Email: </label>
        <input type="text" value="${sessionScope.user.email}" disabled>
        <br>
        <label class="field-label">Paying card: </label>
        <input class="card-input" type="text" value="">

        <button class="submit-booking" type="submit">Book</button>
    </div>
</form>

<jsp:include page="./page-parts/footer.jsp" />
</body>
</html>