<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:eval expression="@environment.getProperty('app.front.events.table.url')" var="eventsTablePath" />
<spring:eval expression="@environment.getProperty('app.front.base.url')" var="serverBaseUrl" />
<spring:eval expression="@environment.getProperty('app.front.public.path')" var="frontPublicPath" />
<spring:eval expression="@environment.getProperty('app.paging.default-value.size')" var="defaultPageSize" />
<spring:eval expression="@environment.getProperty('app.paging.default-value.schedule-controller.sort-column')" var="defaultSortColumn" />
<spring:eval expression="@environment.getProperty('app.paging.default-value.schedule-controller.sort-direction')" var="defaultSortDirection" />

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>jfs | Full Schedule</title>

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
                            <h3 class="card-title"><fmt:message key="table-form.header.h3"/></h3>
                        </div>
                        <!-- /.card-header -->
                        <div class="card-body">
                            <table id="example1" class="table table-dark table-bordered table-striped">
                                <thead>
                                <tr>
                                    <th><fmt:message key="table-form.table.th.id"/></th>
                                    <th><fmt:message key="table-form.table.th.title"/></th>
                                    <th><fmt:message key="table-form.table.th.description"/></th>
                                    <th><fmt:message key="table-form.table.th.type"/></th>
                                    <th><fmt:message key="table-form.table.th.status"/></th>
                                    <th><fmt:message key="table-form.table.th.dateFrom"/></th>
                                    <th><fmt:message key="table-form.table.th.dateTo"/></th>
                                    <th><fmt:message key="table-form.table.th.time"/></th>
                                    <th><fmt:message key="table-form.table.th.paymentStatus"/></th>
                                    <th><fmt:message key="table-form.table.th.incomeNet"/></th>
                                    <th><fmt:message key="table-form.table.th.incomeGross"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <!-- Data will be populated here dynamically -->
                                </tbody>
                                <tfoot>
                                <tr>
                                    <th><fmt:message key="table-form.table.th.id"/></th>
                                    <th><fmt:message key="table-form.table.th.title"/></th>
                                    <th><fmt:message key="table-form.table.th.description"/></th>
                                    <th><fmt:message key="table-form.table.th.type"/></th>
                                    <th><fmt:message key="table-form.table.th.status"/></th>
                                    <th><fmt:message key="table-form.table.th.dateFrom"/></th>
                                    <th><fmt:message key="table-form.table.th.dateTo"/></th>
                                    <th><fmt:message key="table-form.table.th.time"/></th>
                                    <th><fmt:message key="table-form.table.th.paymentStatus"/></th>
                                    <th><fmt:message key="table-form.table.th.incomeNet"/></th>
                                    <th><fmt:message key="table-form.table.th.incomeGross"/></th>
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
<script>
    $(function () {
        $("#example1").DataTable({
            "responsive": true, "lengthChange": false, "autoWidth": false,
            "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"],
            "processing": true, "serverSide": true, "paging": true,
            "searching": true, "ordering": true, "info": true,
            "pageLength": ${defaultPageSize},
            "order": [[ ${defaultSortColumn}, "${defaultSortDirection}" ]],
            "ajax": {
                "url": "${frontPublicPath}/web/app/table/findAll",
                "data": function (data) {
                    data.page = data.start / data.length;
                    data.size = ${defaultPageSize};
                    if (data.order.length > 0) {
                        data.sortColumn = data.columns[data.order[0].column].data;
                        data.sortDirection = data.order[0].dir;
                    } else {
                        data.sortColumn = ${defaultSortColumn};
                        data.sortDirection = ${defaultSortDirection};
                    }
                    data.searchValue = data.search.value;
                    return data;
                },
                "dataSrc": "content",
                "dataFilter": function(data) {
                    var json = jQuery.parseJSON(data);
                    json.recordsTotal = json.totalElements;
                    json.recordsFiltered = json.totalElements;
                    return JSON.stringify(json);
                }
            },
            "initComplete": function() {
                var api = this.api();
                new $.fn.dataTable.Buttons(api, {
                    buttons: ["copy", "csv", "excel", "pdf", "print", "colvis"]
                });
                api.buttons().container().appendTo('#example1_wrapper .col-md-6:eq(0)');
            },
            "columns": [
                {"data": "id"},
                {"data": "title"},
                {"data": "description"},
                {"data": "typeValue"},
                {"data": "statusValue"},
                {"data": "dateFrom"},
                {"data": "dateTo"},
                {"data": "time"},
                {"data": "paymentStatusValue"},
                {"data": "incomeNet"},
                {"data": "incomeGross"}
            ]
        });
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
<!-- Page specific script -->
</body>
</html>