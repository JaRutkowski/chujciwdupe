<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:eval expression="T(java.time.Year).now().value" var="currentYear" />

<!doctype html>
<html lang="en">
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
    <!-- Theme style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/dist/css/adminlte.min.css">

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
    <form:form action="${pageContext.request.contextPath}/web/login/register" modelAttribute="user" method="post">
        <c:if test="${param.error != null}">
            <div class="form-group">
                <div class="col-xs-15">
                    <div class="alert alert-danger col-xs-offset-1 col-sx-15">
                        Invalid username or password
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${param.passwordInvalid != null}">
            <div class="form-group">
                <div class="col-xs-15">
                    <div class="alert alert-danger col-xs-offset-1 col-sx-15">
                        Password must be at least 8 characters long and contain at least one digit, one uppercase
                        letter, one lowercase letter and one special character.
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${param.loginAlreadyExists != null}">
            <div class="form-group">
                <div class="col-xs-15">
                    <div class="alert alert-danger col-xs-offset-1 col-sx-15">
                        Given username already exists
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${param.captchaInvalid != null}">
            <div class="form-group">
                <div class="col-xs-15">
                    <div class="alert alert-danger col-xs-offset-1 col-sx-15">
                        Wrong captcha answer
                    </div>
                </div>
            </div>
        </c:if>

        <h1 class="h3 mb-3 fw-normal">Please register</h1>

        <c:if test="${param.registered == null}">
            <div class="form-floating mb-3">
                <form:input cssClass="form-control" id="floatingInput" placeholder="Login" path="username"/>
                <label for="floatingInput">Username</label>
            </div>
            <div class="form-floating">
                <form:password cssClass="form-control" id="floatingPassword" placeholder="Password" path="password"/>
                <label for="floatingPassword">Password</label>
            </div>

            <div class="form-group">
                <label for="captchaQuestion">Captcha Security Check</label>
                <p id="captchaQuestion">${captchaQuestion}</p>
            </div>
            <div class="form-floating mb-3">
                <form:input cssClass="form-control" id="captchaAnswer" placeholder="Captcha Answer" path="captchaAnswer"/>
                <label for="captchaAnswer">Enter captcha result</label>
            </div>
            <form:hidden path="captchaToken" value="${captchaToken}"/>

            <p>
                <button class="w-100 btn btn-lg btn-dark" type="submit">Register now!</button>
            </p>
        </c:if>

        <c:if test="${param.registered != null}">
            <p class="mb-0 mt-1">Scan the QR code below with an authenticator app like Google Authenticator, Microsoft
                Authenticator or Authy to set up two-factor authentication (2FA) for your account.
            </p>
            <p class="mb-0 mt-1"> After scanning, PRESS the "back to login" button and FILL in the login fields.
            </p>
            <c:set var="encodedParam" value="${param.param}"/>
            <c:set var="decodedParam" value="${fn:replace(encodedParam, ' ', '+')}"/>
            <div class="d-flex justify-content-center">
                <img src="${decodedParam}" alt="TOTP QR Code" width="333" height="333">
            </div>
        </c:if>

        <div style="clear: both;"></div>
        <p>
            <a href="${pageContext.request.contextPath}/web/login/login-page" class="w-100 btn btn-lg btn-dark">Back to
                login</a>
        </p>

        <p class="mt-5 mb-3 text-muted"><fmt:message key="common.brand"/> ${currentYear}</p>
    </form:form>
</main>
</body>
</html>