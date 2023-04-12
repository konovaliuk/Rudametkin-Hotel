<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Error</title>
    <link href="${pageContext.request.contextPath}/resources/styles/page-styles.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/styles/error-page.css" rel="stylesheet">
</head>
<body>

<jsp:useBean id="user" class="com.rudametkin.hotelsystem.businessLogic.UserService" scope="session" />

<header class="header">
    <div class="left-half">
        <form action="${pageContext.request.contextPath}/Controller?command=redirect-home-page" method="post" id="logo-text-container" class="header-button-container">
            <button type="submit" id="header-logo-text-button">Classic</button>
        </form>
    </div>
    <div class="right-half">
        <c:choose>
            <c:when test="${user.isAuthenticated == false}">
                <form action="${pageContext.request.contextPath}/Controller?command=redirect-login-form" method="post" id="login-button-container" class="header-button-container">
                    <button type="submit" class="header-button">Log in</button>
                </form>
            </c:when>
            <c:otherwise>
                <form action="${pageContext.request.contextPath}/Controller?command=logout" method="post" id="logout-button-container" class="header-button-container">
                    <button type="submit" class="header-button">Log out</button>
                </form>
                <form action="${pageContext.request.contextPath}/Controller?command=redirect-cabinet-page" method="post" id="cabinet-button-container" class="header-button-container">
                    <button type="submit" class="header-button">Cabinet</button>
                </form>
            </c:otherwise>
        </c:choose>
    </div>
</header>
    <h1 style="text-align:center; margin: 1em;">Some error occurred!</h1>
    <div id="explain-text">We apologize for the inconvenience caused.
        If the error or appearance of this page is critical
        for your work - you can read error details (if it attached) and solve your issue.
        If you can't solve issue by your own
        - please contact us. If some error info is attached - don't close and refresh this page.</div>
    <jsp:useBean id="error" scope="session" class="java.lang.String"/>
    <c:if test="${not empty error}">
        <div id="error-details-container">
            <span id="error-details-title">Error details</span>
            <div id="error-info">
                <c:out value="${error}" />
                <c:remove var="error" scope="session"/>
            </div>
        </div>
    </c:if>

</body>
</html>
