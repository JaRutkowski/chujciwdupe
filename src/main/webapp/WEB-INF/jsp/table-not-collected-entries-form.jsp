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
    <title>jfs | Not Collected Entries</title>

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
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/assets/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/assets/plugins/datatables-responsive/css/responsive.bootstrap4.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/assets/plugins/datatables-buttons/css/buttons.bootstrap4.min.css">
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
                            <li class="breadcrumb-item active">Not Collected Entries</li>
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
                        <div class="card card-primary card-outline">
                            <div class="card-header">
                                <h3 class="card-title"><fmt:message key="not-collected-entries-form.header.h3"/></h3>
                                    <div class="card-tools">
                                        <button type="button" class="btn btn-tool" data-card-widget="collapse">
                                            <i class="fas fa-minus"></i>
                                        </button>
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-tool dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                <i class="fas fa-wrench"></i>
                                            </button>

                                            <div class="dropdown-menu dropdown-menu-right p-0" role="menu"
                                                 style="min-width:260px;">
                                                <h6 class="dropdown-header text-muted">Quick actions</h6>
                                                <!-- Grouping & Sorting section -->
                                                <nav class="mt-0">
                                                    <ul class="nav nav-pills nav-sidebar flex-column mb-0"
                                                        data-widget="treeview" role="menu" data-accordion="false"
                                                        id="ulMenuNotCollected">

                                                        <!-- Grouping -->
                                                        <li class="nav-item" id="navItem1NotCollected">
                                                            <a href="#"
                                                               class="dropdown-item d-flex align-items-center justify-content-between"
                                                               id="toggleDropdown">
                                                                <span class="d-flex align-items-center">
                                                                  <i class="nav-icon fas fa-calendar mr-2"></i>
                                                                  <span>Group by period</span>
                                                                </span>
                                                                <i class="right fas fa-angle-left ml-2"></i>
                                                            </a>

                                                            <!-- Submenu -->
                                                            <div id="submenu" class="pl-4" style="display: none;">
                                                            <a href="#" id="linkYear"
                                                                   class="dropdown-item d-flex align-items-center">
                                                                    <i class="far fa-circle nav-icon mr-2"></i><span>Year</span>
                                                                </a>
                                                                <a href="#" id="linkMonth"
                                                                   class="dropdown-item d-flex align-items-center">
                                                                    <i class="far fa-circle nav-icon mr-2"></i><span>Month</span>
                                                                </a>
                                                            </div>
                                                        </li>

                                                        <div class="dropdown-divider"></div>

                                                        <!-- Sorting -->
                                                        <li class="nav-item">
                                                            <a href="#" id="linkSortAsc"
                                                               class="dropdown-item d-flex align-items-center">
                                                                <i class="nav-icon fas fa-sort-amount-up mr-2"></i><span>Sort: Ascending</span>
                                                            </a>
                                                        </li>
                                                        <li class="nav-item">
                                                            <a href="#" id="linkSortDesc"
                                                               class="dropdown-item d-flex align-items-center">
                                                                <i class="nav-icon fas fa-sort-amount-down mr-2"></i><span>Sort: Descending</span>
                                                            </a>
                                                        </li>
                                                    </ul>
                                                </nav>

                                                <div class="dropdown-divider"></div>

                                                <!-- No Grouping option -->
                                                <a href="#" class="dropdown-item d-flex align-items-center"
                                                   id="toggleDropdown1">
                                                    <i class="nav-icon fas fa-trash mr-2"></i><span>No grouping</span>
                                                </a>
                                            </div>
                                        </div>

                                        <button type="button" class="btn btn-tool" data-card-widget="remove">
                                            <i class="fas fa-times"></i>
                                        </button>
                                    </div>
                                </div>
                            <!-- /.card-header -->
                            <div class="card-body">
                                <form action="${pageContext.request.contextPath}/web/app/collections/filter" method="post">
                                    <div class="row margin">
                                        <div class="col-sm-6">
                                            <label for="from_date">From:</label>
                                            <input id="from_date" type="date" name="from_date" class="form-control"/>
                                        </div>
                                        <div class="col-sm-6">
                                            <label for="to_date">To:</label>
                                            <input id="to_date" type="date" name="to_date" class="form-control"/>
                                        </div>
                                    </div>
                                    <div class="row mt-4">
                                        <div class="col-12">
                                            <button type="submit" class="btn btn-primary">Submit</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <!-- /.card-body -->
                        </div>
                        <!-- /.card -->
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <!-- Table Section -->
                        <div class="card">
                            <div class="card-header">
                                <h3 class="card-title"><fmt:message key="not-collected-entries-form.header.h3"/></h3>
                                    <div class="card-tools">
                                        <button type="button" class="btn btn-tool" data-card-widget="collapse">
                                            <i class="fas fa-minus"></i>
                                        </button>
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-tool dropdown-toggle"
                                                    data-toggle="dropdown">
                                                <i class="fas fa-wrench"></i>
                                            </button>
                                            <div class="dropdown-menu dropdown-menu-right" role="menu">
                                                <a href="#" class="dropdown-item">Action</a>
                                                <a href="#" class="dropdown-item">Another action</a>
                                                <a href="#" class="dropdown-item">Something else here</a>
                                                <a class="dropdown-divider"></a>
                                                <a href="#" class="dropdown-item">Separated link</a>
                                            </div>
                                        </div>
                                        <button type="button" class="btn btn-tool" data-card-widget="remove">
                                            <i class="fas fa-times"></i>
                                        </button>
                                    </div>
                                </div>

                            <!-- /.card-header -->
                            <div class="card-body">
                                <table id="example1" class="table table-dark table-bordered table-striped">
                                    <thead>
                                    <tr>
                                        <th><fmt:message key="not-collected-entries-form.th.title"/></th>
                                        <th><fmt:message key="not-collected-entries-form.th.paymentType"/></th>
                                        <th><fmt:message key="not-collected-entries-form.th.amountGross"/></th>
                                        <th><fmt:message key="not-collected-entries-form.th.amountNet"/></th>
                                        <th><fmt:message key="not-collected-entries-form.th.time"/></th>
                                        <th><fmt:message key="not-collected-entries-form.th.dateFrom"/></th>
                                        <th><fmt:message key="not-collected-entries-form.th.dateTo"/></th>
                                        <th><fmt:message key="not-collected-entries-form.th.month"/></th>
                                        <th><fmt:message key="not-collected-entries-form.th.description"/></th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <c:forEach var="group" items="${groupedNotCollectedEntries}">
                                        <tr class="table-primary">
                                            <td colspan="9" class="font-weight-bold">${group.key}</td>
                                        </tr>
                                        <c:forEach var="entry" items="${group.value}">
                                            <tr>
                                                <td>${entry.title}</td>
                                                <td>${entry.paymentType}</td>
                                                <td>${entry.amountGross}</td>
                                                <td>${entry.amountNet}</td>
                                                <td>${entry.time}</td>
                                                <td><fmt:formatDate value="${entry.dateFrom.time}" pattern="yyyy-MM-dd"/></td>
                                                <td><fmt:formatDate value="${entry.dateTo.time}" pattern="yyyy-MM-dd"/></td>
                                                <td>${entry.monthFrom}</td>
                                                <td>${entry.description}</td>
                                            </tr>
                                        </c:forEach>
                                    </c:forEach>
                                    </tbody>

                                    <tfoot>
                                    <tr>
                                        <th><fmt:message key="not-collected-entries-form.th.title"/></th>
                                        <th><fmt:message key="not-collected-entries-form.th.paymentType"/></th>
                                        <th><fmt:message key="not-collected-entries-form.th.amountGross"/></th>
                                        <th><fmt:message key="not-collected-entries-form.th.amountNet"/></th>
                                        <th><fmt:message key="not-collected-entries-form.th.time"/></th>
                                        <th><fmt:message key="not-collected-entries-form.th.dateFrom"/></th>
                                        <th><fmt:message key="not-collected-entries-form.th.dateTo"/></th>
                                        <th><fmt:message key="not-collected-entries-form.th.month"/></th>
                                        <th><fmt:message key="not-collected-entries-form.th.description"/></th>
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

<script src='${pageContext.request.contextPath}/resources/assets/plugins/fullcalendar/dist/index.global.js'></script>

<!-- Page specific script -->
<script>
  $(function () {
    const fromDate = '${fromDate}';
    const toDate = '${toDate}';
    const grouping = '${grouping != null ? grouping : "none"}';
    const sort = '${sort != null ? sort : "desc"}';
    const cp = '${pageContext.request.contextPath}';

    if (fromDate) {
      $('#from_date').val(fromDate);
    }
    if (toDate) {
      $('#to_date').val(toDate);
    }

    function updateLinks() {
      const from = $('#from_date').val() || '';
      const to = $('#to_date').val() || '';

      $('#linkYear').attr('href', cp + '/web/app/collections?from=' + from + '&to=' + to + '&grouping=year&sort=' + sort);
      $('#linkMonth').attr('href', cp + '/web/app/collections?from=' + from + '&to=' + to + '&grouping=month&sort=' + sort);
      $('#linkSortAsc').attr('href', cp + '/web/app/collections?from=' + from + '&to=' + to + '&grouping=' + grouping + '&sort=asc');
      $('#linkSortDesc').attr('href', cp + '/web/app/collections?from=' + from + '&to=' + to + '&grouping=' + grouping + '&sort=desc');
      $('#toggleDropdown1').attr('href', cp + '/web/app/collections?from=' + from + '&to=' + to + '&grouping=none&sort=' + sort);
    }

    $('#groupByMenu').on('click', function (e) {
      e.stopPropagation();
    });

    $('#toggleDropdown').on('click', function (e) {
      e.preventDefault();
      e.stopPropagation();
      $('#submenu').toggle();
    });

    $('#linkYear, #linkMonth, #linkSortAsc, #linkSortDesc, #toggleDropdown1').on('click', function (e) {
      e.preventDefault();
      window.location.href = $(this).attr('href');
    });

    $('#from_date, #to_date').on('change input', updateLinks);

    $('form').on('submit', function (e) {
      const from = $('#from_date').val() || '';
      const to = $('#to_date').val() || '';
      const url = cp + '/web/app/collections?from=' + from + '&to=' + to + '&grouping=' + grouping + '&sort=' + sort;

      window.location.href = url;
      return false;
    });

    updateLinks();
  });
</script>
</body>
</html>