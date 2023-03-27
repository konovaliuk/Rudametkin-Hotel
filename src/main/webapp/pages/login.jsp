<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login | Hotel</title>
    <link href="${pageContext.request.contextPath}/resources/styles/page-styles.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/styles/login-page.css" rel="stylesheet">
</head>
<body class="page-stop-transition">
<header class="header">
    <form action="../Controller?command=redirect-home-page" method="post" id="logo-text-container" class="header-button-container">
        <button type="submit" id="header-logo-text-button">Classic</button>
    </form>
</header>
<div class="login-container-border-wrap">
    <div class="login-container">
        <form action="../Controller?command=login" method="post">
            <div id="login-title">Login Form</div>
            <div class="field-label">Login: </div>
            <input type="text" class="field-input" name="login">
            <div class="field-label">Password: </div>
            <input type="text" class="field-input" name="password">
            <button type="submit" name="login-submit" id="login-submit-button">Log in</button>
        </form>
    </div>
</div>
<footer></footer>
</body>
<script>
    document.body.classList.remove('page-stop-transition');
</script>
</html>
