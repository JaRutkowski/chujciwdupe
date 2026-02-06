<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:eval expression="@environment.getProperty('app.app-version ')" var="appVersion" />

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title><fmt:message key="error-page.title"/></title>

  <!-- Google Font: Source Sans Pro -->
  <link rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome -->
  <link rel="stylesheet"
        href="${pageContext.request.contextPath}/resources/assets/plugins/fontawesome-free/css/all.min.css">
  <link rel="stylesheet"
        href="${pageContext.request.contextPath}/resources/assets/plugins/fontawesome-free/css/v4-shims.min.css">

  <!-- fullCalendar -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/plugins/fullcalendar/main.css">

  <!-- Theme style (MISSING BEFORE!) -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/dist/css/adminlte.min.css">

  <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/assets/dist/img/jFeeSoftLogo-full.jpg">
</head>

<body class="hold-transition dark-mode sidebar-mini layout-fixed layout-navbar-fixed layout-footer-fixed">
<div class="wrapper">
  <!-- Navbar -->
  <jsp:include page="components/navbar/navbar-section.jsp"/>
  <!-- /.navbar -->

  <!-- Sidebar -->
  <jsp:include page="components/sidebar/sidebar-section.jsp"/>
  <!-- /.sidebar -->

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1>500 Error Page</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">500 Error Page</li>
            </ol>
          </div>
        </div>
      </div>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="error-page">
        <h2 class="headline text-danger">500</h2>

        <div class="error-content">
          <h3><i class="fas fa-exclamation-triangle text-danger"></i> Oops! Something went wrong.</h3>

          <p>
            We will work on fixing that right away.
            Meanwhile, you may <a href="${pageContext.request.contextPath}/web/app/dashboard">return to dashboard</a>.
          </p>

          <p class="mb-2">
            <a class="btn btn-tool dropdown-toggle"
               data-toggle="collapse"
               href="#exceptionDetails"
               role="button"
               aria-expanded="false"
               aria-controls="exceptionDetails">
              Details
            </a>
          </p>

          <div class="row">
            <div class="col">
              <div class="collapse" id="exceptionDetails">
                <div class="card card-body">
                  <div><strong>URL:</strong> <c:out value="${url}"/></div>
                  <div><strong>Exception:</strong> <c:out value="${exception}"/></div>
                </div>
              </div>
            </div>
          </div>

          <form class="search-form mt-3">
            <div class="input-group">
              <input type="text" name="search" class="form-control" placeholder="Search">
              <div class="input-group-append">
                <button type="submit" name="submit" class="btn btn-danger">
                  <i class="fas fa-search"></i>
                </button>
              </div>
            </div>
          </form>

        </div>
        <!-- /.error-content -->
      </div>
      <!-- /.error-page -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <!-- Footer -->
  <jsp:include page="components/footer/footer-section.jsp"/>
  <!-- /.Footer -->

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark"></aside>
  <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->

<!-- jQuery -->
<script src="${pageContext.request.contextPath}/resources/assets/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="${pageContext.request.contextPath}/resources/assets/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/resources/assets/dist/js/adminlte.min.js"></script>
</body>
</html>
