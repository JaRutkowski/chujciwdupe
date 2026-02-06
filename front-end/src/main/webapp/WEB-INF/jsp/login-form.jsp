<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:eval expression="@environment.getProperty('app.security.registration.enabled')" var="security"/>
<spring:eval expression="T(java.time.Year).now().value" var="currentYear" />
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<!doctype html>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.88.1">

    <title>jFeeSoft</title>

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/assets/dist/img/jFeeSoftLogo-full.jpg">
    <link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/sign-in/">

    <!-- Bootstrap core CSS -->
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/assets/dist/css/bootstrap.min.css">

    <!-- Favicons -->
    <meta name="theme-color" content="#7952b3">

    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>

    <!-- Custom styles for this template -->
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/signin.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/dist/css/adminlte.min.css">
</head>
<body class="text-center">

<main class="form-signin">
    <!--form:form action="${pageContext.request.contextPath}/authenticateUser" method="post"-->
    <form:form action="${pageContext.request.contextPath}/web/login/authenticate" modelAttribute="user" method="post">
        <c:if test="${param.invalidCode != null}">
            <div class="form-group">
                <div class="col-xs-15">
                    <div class="alert alert-danger col-xs-offset-1 col-sx-15">
                        Invalid code. Please try again
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${param.error != null}">
            <div class="form-group">
                <div class="col-xs-15">
                    <div class="alert alert-danger col-xs-offset-1 col-sx-15">
                        Invalid username or password
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${param.cookieError != null}">
            <div class="form-group">
                <div class="col-xs-15">
                    <div class="alert alert-danger col-xs-offset-1 col-sx-15">
                        Your session has ended. Please log in
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${param.expiredPassword != null}">
            <div class="form-group">
                <div class="col-xs-15">
                    <div class="alert alert-danger col-xs-offset-1 col-sx-15">
                        The password has expired. Please change your password to continue accessing your account
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${param.logout != null}">
            <div class="form-group">
                <div class="col-xs-15">
                    <div class="alert alert-success col-xs-offset-1 col-sx-15">
                        You have been logged out
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${param.registered != null}">
            <div class="form-group">
                <div class="col-xs-15">
                    <div class="alert alert-success col-xs-offset-1 col-sx-15">
                        You have been registered.
                    </div>
                </div>
            </div>
        </c:if>
        <h1 class="h3 mb-3 fw-normal"><fmt:message key="login-form.label.pleasesignin"/></h1>

        <div class="form-floating mb-3">
            <form:input cssClass="form-control" path="username" type="text" name="username" class="form-control" id="floatingInput" placeholder="${login-form.label.login}" />
            <label for="floatingInput"><fmt:message key="login-form.label.login"/></label>
        </div>
        <div class="form-floating mb-3">
            <form:input cssClass="form-control" path="password" type="password" name="password" class="form-control" id="floatingPassword" placeholder="${login-form.label.password}" />
            <label for="floatingPassword"><fmt:message key="login-form.label.password"/></label>
        </div>
        <div class="form-floating mb-3">
            <form:input cssClass="form-control" path="totpCode" type="text" name="totpCode" class="form-control" id="floatingPassword" placeholder="${login-form.label.code}" />
            <label for="floatingPassword"><fmt:message key="login-form.label.code"/></label>
        </div>

        <%--        <div class="checkbox mb-3">--%>
        <%--            <label>--%>
        <%--                <input type="checkbox" value="remember-me"> Remember me--%>
        <%--            </label>--%>
        <%--        </div>--%>
        <button class="w-100 btn btn-lg btn-dark" type="submit"><fmt:message key="login-form.button.signin"/></button>
        <%-- OAuth --%>
        <%--        <p>--%>
        <%--            <a th:href="/@{/oauth2/authorization/google}">Login with Google</a>--%>
        <%--        </p>--%>
        <c:if test="${security.isEmpty() || security == true}">
            <input type="button" value="Register" onclick="window.location.href='register-page'; return true;"
                   class="w-100 btn btn-lg btn-dark" style="margin-top: 10px">
        </c:if>

        <p class="mt-5 mb-3 text-muted"><fmt:message key="common.brand"/> ${currentYear}</p>
    </form:form>
</main>
</body>
</html>
