<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<header class="header">
  <div class="left-half">
    <form action="${pageContext.request.contextPath}/" method="post" id="logo-text-container" class="header-button-container">
      <button type="submit" id="header-logo-text-button">Classic</button>
    </form>
  </div>
  <div class="right-half">
    <c:choose>
      <c:when test="${empty sessionScope.user or empty sessionScope.user.login}">
        <c:if test="${pageContext.request.requestURI != '/WEB-INF/views/pages/login.jsp'}">
          <a href="${pageContext.request.contextPath}/login" id="login-button-container" class="header-button-container">
            <button type="submit" class="header-button">Log in</button>
          </a>
        </c:if>
      </c:when>
      <c:otherwise>
        <form action="${pageContext.request.contextPath}/logout" method="post" id="logout-button-container" class="header-button-container">
          <button type="submit" class="header-button">Log out</button>
        </form>


      <c:if test="${'/WEB-INF/views/pages/cabinet.jsp' != pageContext.request.requestURI}">
        <form action="${pageContext.request.contextPath}/cabinet" method="post" id="cabinet-button-container" class="header-button-container">
          <button type="submit" class="header-button">Cabinet</button>
        </form>
      </c:if>
      </c:otherwise>
    </c:choose>
  </div>
</header>






