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
<header class="header">
    <form action="../Controller?command=redirect-home-page" method="post" id="logo-text-container" class="header-button-container">
        <button type="submit" id="header-logo-text-button">Classic</button>
    </form>

    <jsp:useBean id="login" scope="session" class="java.lang.String"/>

    <c:choose>
        <c:when test="${empty login}">
            <form action="../Controller?command=redirect-login-form" method="post" id="login-button-container" class="header-button-container">
                <button type="submit" class="header-button">Log in</button>
            </form>
        </c:when>
        <c:otherwise>
            <form action="../Controller?command=logout" method="post" id="logout-button-container" class="header-button-container">
                <button type="submit" class="header-button">Log out</button>
            </form>
            <form action="../Controller?command=redirect-cabinet-page" method="post" id="cabinet-button-container" class="header-button-container">
                <button type="submit" class="header-button">Cabinet</button>
            </form>
        </c:otherwise>
    </c:choose>
</header>
    <h1 style="text-align:center; margin: 1em;">Some error occurred!</h1>
    <div id="explain-text">We apologize for the inconvenience caused.
        If the error or appearance of this page is critical
        for your work - you can read error details and solve your issue.
        If you can't solve issue by your own
        - please contact us. In this case, please
        do not close this page, or save information mentioned below.</div>
    <jsp:useBean id="error" scope="session" class="java.lang.String"/>
    <c:if test="${empty error}">
        <c:redirect url="/pages/main.jsp" />
    </c:if>
     <div id="error-details-container">
         <span id="error-details-title">Error details</span>
         <div id="error-info">
             <c:out value="${error}" />
         </div>
     </div>
</body>
</html>
