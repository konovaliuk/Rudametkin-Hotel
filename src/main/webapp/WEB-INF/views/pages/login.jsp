<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <title>Login | Hotel</title>
    <link href="/styles/page-styles.css" rel="stylesheet">
    <link href="/styles/login-page.css" rel="stylesheet">
</head>

<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
%>


<c:if test="${not empty sessionScope.user and not empty sessionScope.user.login}">
    <c:redirect url="/cabinet" />
</c:if>

<body class="page-stop-transition">
    <jsp:include page="./page-parts/header.jsp" />

    <div class="login-container-border-wrap">
        <div class="login-container">
            <form id="login-form" action="${pageContext.request.contextPath}/login" method="post">
                <div id="login-title">Login Form</div>
                <div class="field-label">Login: </div>
                <input type="text" class="field-input" name="login" required>
                <div class="field-label">Password: </div>
                <input type="text" class="field-input" name="password" required>
                <button type="submit" name="login-submit" id="login-submit-button">Log in</button>
                <button class="change-mode" type="button" onclick="signupMode();">Signup</button>
            </form>

            <form id="signup-form" action="${pageContext.request.contextPath}/signup" method="post">
                <div id="signup-title">Signup Form</div>
                <div class="field-label">Login: </div>
                <input type="text" class="field-input" name="login" required>
                <div class="field-label">Email: </div>
                <input type="email" class="field-input" name="email" required>
                <div class="field-label">Phone: </div>
                <input type="tel" class="field-input" name="phone" required>
                <div class="field-label">Name: </div>
                <input type="text" class="field-input" name="name" required>
                <div class="field-label">Surname: </div>
                <input type="text" class="field-input" name="surname" required>
                <div class="field-label">Password: </div>
                <input type="text" class="field-input" name="password" required>
                <button type="submit" name="login-submit" id="signup-submit-button">Sign up</button>
                <button class="change-mode" type="button" onclick="loginMode();">Login</button>
            </form>

        </div>
    </div>

    <jsp:include page="./page-parts/footer.jsp" />
</body>

<script>
    document.body.classList.remove('page-stop-transition');

    function loginMode() {
        document.querySelector("#login-form").style.display = "inline";
        document.querySelector("#signup-form").style.display = "none";
        document.querySelector(".login-container").style.height = "21.5em";
    }

    function signupMode() {
        document.querySelector("#login-form").style.display = "none";
        document.querySelector("#signup-form").style.display = "inline";
        document.querySelector(".login-container").style.height = "41.4em";
    }
</script>

</html>
