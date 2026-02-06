<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!-- Fixed navbar -->
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/web/app/password/list">SAPP</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse"
                aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav me-auto mb-2 mb-md-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page"
                       href="${pageContext.request.contextPath}/web/app/password/list">Home</a>
                </li>
            </ul>
            <c:if test="${empty param.disableSearch}">
                <form:form action="${pageContext.request.contextPath}/web/app/password/search" method="post"
                           class="d-flex">
                    <input class="form-control me-2" type="search" name="searchText" placeholder="Search"
                           aria-label="Search">
                    <button class="btn btn-outline-light" type="submit">Search</button>
                </form:form>
            </c:if>
            <form:form action="${pageContext.request.contextPath}/logout" method="post" class="d-flex">
                <button class="btn btn-outline-light" type="submit" style="margin-left: 5px">Logout</button>
            </form:form>
        </div>
    </div>
</nav>
