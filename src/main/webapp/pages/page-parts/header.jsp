<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<header class="header">
  <div class="left-half">
    <form action="${pageContext.request.contextPath}/" method="post" id="logo-text-container" class="header-button-container">
      <button type="submit" id="header-logo-text-button">Classic</button>
    </form>
  </div>
  <div class="right-half">
    <c:choose>
      <c:when test="${empty sessionScope.user}">
        <c:if test="${requestScope['javax.servlet.forward.request_uri'] != pageContext.request.contextPath.concat('/login')}">
          <a href="${pageContext.request.contextPath}/login" id="login-button-container" class="header-button-container">
            <button type="submit" class="header-button">Log in</button>
          </a>
        </c:if>
      </c:when>
      <c:otherwise>
        <form action="${pageContext.request.contextPath}/Controller?command=logout" method="post" id="logout-button-container" class="header-button-container">
          <button type="submit" class="header-button">Log out</button>
        </form>


        <c:choose>
          <c:when test="${requestScope['javax.servlet.forward.request_uri'] == pageContext.request.contextPath.concat('/cabinet')}">
            <div class="header-button-container" style="height: 38px">
              <div id="role-dropdown">
                <div id="current-role">Client</div>
                <div id="role-dropdown-child">
                  <div>Admin</div>
                </div>
              </div>
            </div>
          </c:when>
          <c:otherwise>
            <form action="${pageContext.request.contextPath}/cabinet" method="post" id="cabinet-button-container" class="header-button-container">
              <button type="submit" class="header-button">Cabinet</button>
            </form>
          </c:otherwise>
        </c:choose>

      </c:otherwise>
    </c:choose>
  </div>
</header>






