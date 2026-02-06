<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>jfs | Clients</title>

    <!-- Google Font: Source Sans Pro -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/assets/dist/img/jFeeSoftLogo-full.jpg">
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
    <!-- Font Awesome -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/assets/plugins/fontawesome-free/css/all.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/assets/plugins/fontawesome-free/css/v4-shims.min.css">
    <!-- DataTables -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/plugins/datatables-responsive/css/responsive.bootstrap4.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/plugins/datatables-buttons/css/buttons.bootstrap4.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/dist/css/adminlte.min.css">
</head>
<body class="hold-transition dark-mode sidebar-mini layout-fixed layout-navbar-fixed layout-footer-fixed">
<div class="wrapper">
    <!-- Navbar -->
    <jsp:include page="components/navbar/navbar-section.jsp"/>
    <!-- /.navbar -->

    <!-- Main Sidebar Container -->
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
                        <h1>Calendar</h1>
                    </div>
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">Home</a></li>
                            <li class="breadcrumb-item active">Calendar</li>
                        </ol>
                    </div>
                </div>
            </div><!-- /.container-fluid -->
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12">
                        <!-- Table Section -->
                        <div class="card">
                        <div class="card-header">
                            <h3 class="card-title"><fmt:message key="table-client-form.header.h3"/></h3>
                        </div>
                        <!-- /.card-header -->
                        <div class="card-body">
                            <table id="example1" class="table table-dark table-bordered table-striped">
                                <thead>
                                <tr>
                                    <th><fmt:message key="table-client-form.th.id"/></th>
                                    <th><fmt:message key="table-client-form.th.name"/></th>
                                    <th><fmt:message key="table-client-form.th.displayName"/></th>
                                    <th><fmt:message key="table-client-form.th.source"/></th>
                                    <th><fmt:message key="table-client-form.th.contactDate"/></th>
                                    <th><fmt:message key="table-client-form.th.firstMeetDateTime"/></th>
                                    <th><fmt:message key="table-client-form.th.lastMeetDateTime"/></th>
                                    <th><fmt:message key="table-client-form.th.address"/></th>
                                    <th><fmt:message key="table-client-form.th.postalCode"/></th>
                                    <th><fmt:message key="table-client-form.th.city"/></th>
                                    <th><fmt:message key="table-client-form.th.country"/></th>
                                    <th><fmt:message key="table-client-form.th.email"/></th>
                                    <th><fmt:message key="table-client-form.th.phoneNo"/></th>
                                    <th><fmt:message key="table-client-form.th.onlineMeetURL"/></th>
                                    <th><fmt:message key="table-client-form.th.nip"/></th>
                                    <th><fmt:message key="table-client-form.th.isEntity"/></th>
                                    <th><fmt:message key="table-client-form.th.isActive"/></th>
                                    <th><fmt:message key="table-client-form.th.price"/></th>
                                    <th><fmt:message key="table-client-form.th.paymentUnit"/></th>
                                    <th><fmt:message key="table-client-form.th.paymentDuration"/></th>
                                    <th><fmt:message key="table-client-form.th.paymentType"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="tableClient" items="${tableClients}">
                                    <tr>
                                        <td>${tableClient.id}</td>
                                        <td>${tableClient.name}</td>
                                        <td>${tableClient.displayName}</td>
                                        <td>${tableClient.source}</td>
                                        <td>${tableClient.contactDate}</td>
                                        <td>${tableClient.firstMeetDateTime}</td>
                                        <td>${tableClient.lastMeetDateTime}</td>
                                        <td>${tableClient.address}</td>
                                        <td>${tableClient.postalCode}</td>
                                        <td>${tableClient.city}</td>
                                        <td>${tableClient.country}</td>
                                        <td>${tableClient.email}</td>
                                        <td>${tableClient.phoneNo}</td>
                                        <td>
                                            <c:if test="${not empty tableClient.onlineMeetURL}">
                                                <a href="${tableClient.onlineMeetURL}" target="_blank">
                                                    ${tableClient.onlineMeetURL}
                                                </a>
                                            </c:if>
                                        </td>
                                        <td>${tableClient.nip}</td>
                                        <td>${tableClient.isEntity}</td>
                                        <td>${tableClient.isActive}</td>
                                        <td>${tableClient.price}</td>
                                        <td>${tableClient.paymentUnit}</td>
                                        <td>${tableClient.paymentDuration}</td>
                                        <td>${tableClient.paymentType}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <th><fmt:message key="table-client-form.th.id"/></th>
                                    <th><fmt:message key="table-client-form.th.name"/></th>
                                    <th><fmt:message key="table-client-form.th.displayName"/></th>
                                    <th><fmt:message key="table-client-form.th.source"/></th>
                                    <th><fmt:message key="table-client-form.th.contactDate"/></th>
                                    <th><fmt:message key="table-client-form.th.firstMeetDateTime"/></th>
                                    <th><fmt:message key="table-client-form.th.lastMeetDateTime"/></th>
                                    <th><fmt:message key="table-client-form.th.address"/></th>
                                    <th><fmt:message key="table-client-form.th.postalCode"/></th>
                                    <th><fmt:message key="table-client-form.th.city"/></th>
                                    <th><fmt:message key="table-client-form.th.country"/></th>
                                    <th><fmt:message key="table-client-form.th.email"/></th>
                                    <th><fmt:message key="table-client-form.th.phoneNo"/></th>
                                    <th><fmt:message key="table-client-form.th.onlineMeetURL"/></th>
                                    <th><fmt:message key="table-client-form.th.nip"/></th>
                                    <th><fmt:message key="table-client-form.th.isEntity"/></th>
                                    <th><fmt:message key="table-client-form.th.isActive"/></th>
                                    <th><fmt:message key="table-client-form.th.price"/></th>
                                    <th><fmt:message key="table-client-form.th.paymentUnit"/></th>
                                    <th><fmt:message key="table-client-form.th.paymentDuration"/></th>
                                    <th><fmt:message key="table-client-form.th.paymentType"/></th>
                                </tr>
                                </tfoot>
                            </table>
                        </div>
                        <!-- /.card-body -->
                    </div>
                        <!-- /.card -->
                    </div>
                    <!-- /.col -->
                </div>
            </div><!-- /.container-fluid -->
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- Footer -->
    <jsp:include page="components/footer/footer-section.jsp"/>
    <!-- /.Footer -->

    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark">
        <!-- Control sidebar content goes here -->
    </aside>
    <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->

<!-- jQuery -->
<script src="${pageContext.request.contextPath}/resources/assets/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="${pageContext.request.contextPath}/resources/assets/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- jQuery UI -->
<script src="${pageContext.request.contextPath}/resources/assets/plugins/jquery-ui/jquery-ui.min.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/resources/assets/dist/js/adminlte.min.js"></script>
<!-- fullCalendar 2.2.5 -->
<script src="${pageContext.request.contextPath}/resources/assets/plugins/moment/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/plugins/fullcalendar/main.js"></script>
<!-- Page specific script -->
<!-- DataTables  & Plugins -->
<script src="${pageContext.request.contextPath}/resources/assets/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/plugins/datatables-responsive/js/dataTables.responsive.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/plugins/datatables-responsive/js/responsive.bootstrap4.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/plugins/datatables-buttons/js/dataTables.buttons.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/plugins/datatables-buttons/js/buttons.bootstrap4.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/plugins/jszip/jszip.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/plugins/pdfmake/pdfmake.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/plugins/pdfmake/vfs_fonts.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/plugins/datatables-buttons/js/buttons.html5.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/plugins/datatables-buttons/js/buttons.print.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/plugins/datatables-buttons/js/buttons.colVis.min.js"></script>
<!-- Page specific script -->
<script>
    $(function () {
        $("#example1").DataTable({
            "responsive": true, "lengthChange": false, "autoWidth": false,
            "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"]
        }).buttons().container().appendTo('#example1_wrapper .col-md-6:eq(0)');
        $('#example2').DataTable({
            "paging": true,
            "lengthChange": false,
            "searching": false,
            "ordering": true,
            "info": true,
            "autoWidth": false,
            "responsive": true,
        });
    });
</script>
</body>
</html>