<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Classic Hotel | Home Page</title>
    <link href="${pageContext.request.contextPath}/resources/styles/page-styles.css" rel="stylesheet">
</head>
<body style="height: 1400px;">

<jsp:useBean id="user" class="com.rudametkin.hotelsystem.businessLogic.UserService" scope="session" />

<header class="header">
    <div class="left-half">
        <form action="" method="post" id="logo-text-container" class="header-button-container">
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
<div id="preview-container">
    <span id="preview-text">Classic Hotel</span>
</div>
<div id="home-page-info-container">
    <h1 id="info-title-header">CLASSIC HOTEL, KYIV</h1>
    <div>
        <div id="info-text">
            <p>
                The hotel "Classic" is situated in the center of the Kyiv.
                Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                Accusantium cupiditate dolore minus optio quis quisquam, sed voluptatibus?
                Consequuntur debitis dolores earum, error excepturi libero numquam recusandae.
                Corporis fuga natus quod.
            </p>

            <p style="margin-top: 1em;">
                Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                At doloremque eaque fugit ipsam ipsum mollitia placeat quo,
                tenetur unde velit.
            </p>
        </div>
        <div id="reasons-container">
            <div id="reasons-block">
                <div style="height: 2em; background-color: #22003e; color: white;
                text-align: center; padding-top: 0.4em; font-size: 20px;">
                    WHY YOU SHOULD STAY IN OUR HOTEL
                </div>
                <div id="reasons-text">
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                    Ab cum delectus eveniet expedita ipsa libero maxime modi
                    molestiae perspiciatis saepe?
                    <br><br>
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                    Cumque dolorum ducimus laborum omnis praesentium voluptatibus.
                    <br><br>
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                    Adipisci beatae consequatur cumque, dolores facilis in iste
                    laborum minus molestiae nisi omnis quibusdam quo soluta!
                    Ab ad animi nisi nostrum repudiandae?
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
