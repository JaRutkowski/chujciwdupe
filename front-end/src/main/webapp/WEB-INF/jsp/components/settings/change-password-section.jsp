<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<!doctype html>
<html lang="${sessionScope.lang}">

<body class="text-center">

<main class="form-signin">
    <form:form action="${pageContext.request.contextPath}/web/settings" modelAttribute="passwords" method="post">
        <c:if test="${param.mismatchPassword != null}">
            <div class="form-group">
                <div class="col-xs-15">
                    <div class="alert alert-danger col-xs-offset-1 col-sx-15">
                        <fmt:message key="change-password-section.error.mismatchPassword"/>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${param.invalidPassword != null}">
            <div class="form-group">
                <div class="col-xs-15">
                    <div class="alert alert-danger col-xs-offset-1 col-sx-15">
                        <fmt:message key="change-password-section.error.invalidPassword"/>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${param.matchingPassword != null}">
            <div class="form-group">
                <div class="col-xs-15">
                    <div class="alert alert-danger col-xs-offset-1 col-sx-15">
                        <fmt:message key="change-password-section.error.matchingPassword"/>
                    </div>
                </div>
            </div>
        </c:if>
        <div class="login-box">
            <div class="login-logo">
               <b><fmt:message key="change-password-section.label.youCanChangeYourPassword"/></b>
            </div>
            <!-- /.login-logo -->
            <div class="card">
                <div class="card-body login-card-body">
                    <p class="login-box-msg"><fmt:message key="change-password-section.label.description"/></p>

                    <div class="input-group mb-3">
                        <form:input cssClass="form-control" path="newPassword" type="password" name="newPassword" class="form-control" id="floatingInput1" placeholder="New Password" />
                        <div class="input-group-append">
                            <div class="input-group-text">
                                <span class="fas fa-lock"></span>
                            </div>
                        </div>
                    </div>
                    <div class="input-group mb-3">
                        <form:input cssClass="form-control" path="confirmPassword" type="password" name="confirmPassword" class="form-control" id="floatingInput2" placeholder="Confirm Password" />
                        <div class="input-group-append">
                            <div class="input-group-text">
                                <span class="fas fa-lock"></span>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <button type="submit" class="btn btn-primary btn-block"><fmt:message key="change-password-section.label.button"/></button>
                        </div>
                        <!-- /.col -->
                    </div>

                </div>
                <!-- /.login-card-body -->
            </div>
        </div>
    </form:form>
</main>

</body>

</html>
