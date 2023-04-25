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

<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
%>

<body>


<jsp:include page="./page-parts/header.jsp" />


    <h1 style="text-align:center; margin: 1em;">Some error occurred!</h1>
    <div id="explain-text">We apologize for the inconvenience caused.
        If the error or appearance of this page is critical
        for your work - you can read error details (if it attached) and solve your issue.
        If you can't solve issue by your own
        - please contact us, in this case if some error info is attached - don't close this page.</div>

    <div id="error-details-container">
        <span id="error-details-title">Error details</span>
        <div id="error-info">
        </div>
    </div>

<jsp:include page="./page-parts/footer.jsp" />

</body>

<script>
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const errorText = urlParams.get("error");

    let detailsContainer = document.querySelector("#error-details-container");
    if(errorText == null)
        detailsContainer.style.display = "none";
    else
        document.querySelector("#error-info").innerHTML = errorText;

</script>
</html>
