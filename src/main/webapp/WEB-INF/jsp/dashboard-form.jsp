<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:eval expression="@environment.getProperty('app.revenue.weekly.goal')" var="revenueWeeklyGoal" />
<spring:eval expression="@environment.getProperty('app.revenue.weekly.goal.min')" var="revenueWeeklyGoalMin" />

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>jfs | Dashboard</title>

    <!-- Google Font: Source Sans Pro -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/assets/dist/img/jFeeSoftLogo-full.jpg">
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
    <!-- Font Awesome Icons -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/assets/plugins/fontawesome-free/css/all.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/assets/plugins/fontawesome-free/css/v4-shims.min.css">
    <!-- overlayScrollbars -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/assets/plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/dist/css/adminlte.min.css">

    <%--    todo--%>
    <%--    <!-- Ionicons -->--%>
    <%--    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">--%>
    <%--    <!-- Tempusdominus Bootstrap 4 -->--%>
    <%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">--%>
    <%--    <!-- iCheck -->--%>
    <%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/plugins/icheck-bootstrap/icheck-bootstrap.min.css">--%>
    <%--    <!-- JQVMap -->--%>
    <%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/plugins/jqvmap/jqvmap.min.css">--%>
    <%--    <!-- Daterange picker -->--%>
    <%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/plugins/daterangepicker/daterangepicker.css">--%>
    <!-- summernote -->
    <%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/plugins/summernote/summernote-bs4.min.css">--%>
    <%--    todo--%>
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
        <div class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1 class="m-0">Dashboard</h1>
                    </div><!-- /.col -->
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">Home</a></li>
                            <li class="breadcrumb-item active">Dashboard</li>
                        </ol>
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.container-fluid -->
        </div>
        <!-- /.content-header -->

        <!-- Main content -->
        <section class="content">
            <div class="container-fluid">
                <!-- Quick Statistics -->
                <div class="row">
                    <div class="col-md-12">
                        <!-- Dashboard Quick Statistics Section -->
                        <jsp:include page="components/dashboard/dashboard-quick-statistics-section.jsp"/>
                        <!-- /.card -->
                    </div>
                    <!-- /.col -->
                </div>
                <%--todo--%>


                <%--todo--%>
                <!-- Today -->
                <div class="row">
                    <div class="col-md-12">
                        <!-- Dashboard Today list Section -->
                        <jsp:include page="components/dashboard/dashboard-today-todo-list-section.jsp"/>
                        <!-- /.card -->
                    </div>
                </div>
                <!-- /.row -->
            </div><!--/. container-fluid -->
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark">
        <!-- Control sidebar content goes here -->
    </aside>
    <!-- /.control-sidebar -->

    <!-- Footer -->
    <jsp:include page="components/footer/footer-section.jsp"/>
    <!-- /.Footer -->
</div>
<!-- ./wrapper -->

<!-- REQUIRED SCRIPTS -->
<!-- jQuery -->
<script src="${pageContext.request.contextPath}/resources/assets/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="${pageContext.request.contextPath}/resources/assets/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- overlayScrollbars -->
<script src="${pageContext.request.contextPath}/resources/assets/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/resources/assets/dist/js/adminlte.js"></script>

<!-- PAGE PLUGINS -->
<!-- jQuery Mapael -->
<script src="${pageContext.request.contextPath}/resources/assets/plugins/jquery-mousewheel/jquery.mousewheel.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/plugins/raphael/raphael.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/plugins/jquery-mapael/jquery.mapael.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/plugins/jquery-mapael/maps/usa_states.min.js"></script>
<!-- ChartJS -->
<%--<script src="${pageContext.request.contextPath}/resources/assets/plugins/chart.js/Chart.min.js"></script>--%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.9.1/chart.min.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="${pageContext.request.contextPath}/resources/assets/dist/js/pages/dashboard2.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/dist/js/pages/dashboard3.js"></script>

<%--    todo--%>
<%--<script src="${pageContext.request.contextPath}/resources/assets/plugins/jquery-ui/jquery-ui.min.js"></script>--%>
<%--<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->--%>
<%--<script>--%>
<%--    $.widget.bridge('uibutton', $.ui.button)--%>
<%--</script>--%>
<!-- Sparkline -->
<script src="${pageContext.request.contextPath}/resources/assets/plugins/sparklines/sparkline.js"></script>
<%--<!-- JQVMap -->--%>
<%--<script src="${pageContext.request.contextPath}/resources/assets/plugins/jqvmap/jquery.vmap.min.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/resources/assets/plugins/jqvmap/maps/jquery.vmap.usa.js"></script>--%>
<!-- jQuery Knob Chart -->
<script src="${pageContext.request.contextPath}/resources/assets/plugins/jquery-knob/jquery.knob.min.js"></script>
<%--<!-- AdminLTE dashboard demo (This is only for demo purposes) -->--%>
<%--<script src="${pageContext.request.contextPath}/resources/assets/dist/js/pages/dashboard.js"></script>--%>
<%--    todo--%>

<script>
    $(function () {
        $('.knob').knob({
            /*change : function (value) {
             //console.log("change : " + value);
             },
             release : function (value) {
             console.log("release : " + value);
             },
             cancel : function () {
             console.log("cancel : " + this.value);
             },*/
            draw: function () {

                // "tron" case
                if (this.$.data('skin') == 'tron') {

                    var a   = this.angle(this.cv)  // Angle
                        ,
                        sa  = this.startAngle          // Previous start angle
                        ,
                        sat = this.startAngle         // Start angle
                        ,
                        ea                            // Previous end angle
                        ,
                        eat = sat + a                 // End angle
                        ,
                        r   = true

                    this.g.lineWidth = this.lineWidth

                    this.o.cursor
                    && (sat = eat - 0.3)
                    && (eat = eat + 0.3)

                    if (this.o.displayPrevious) {
                        ea = this.startAngle + this.angle(this.value)
                        this.o.cursor
                        && (sa = ea - 0.3)
                        && (ea = ea + 0.3)
                        this.g.beginPath()
                        this.g.strokeStyle = this.previousColor
                        this.g.arc(this.xy, this.xy, this.radius - this.lineWidth, sa, ea, false)
                        this.g.stroke()
                    }

                    this.g.beginPath()
                    this.g.strokeStyle = r ? this.o.fgColor : this.fgColor
                    this.g.arc(this.xy, this.xy, this.radius - this.lineWidth, sat, eat, false)
                    this.g.stroke()

                    this.g.lineWidth = 2
                    this.g.beginPath()
                    this.g.strokeStyle = this.o.fgColor
                    this.g.arc(this.xy, this.xy, this.radius - this.lineWidth + 1 + this.lineWidth * 2 / 3, 0, 2 * Math.PI, false)
                    this.g.stroke()

                    return false
                }
            }
        })
        /* END JQUERY KNOB */

        //INITIALIZE SPARKLINE CHARTS
        // var sparkline1 = new Sparkline($('#sparkline-1')[0], { width: 240, height: 70, lineColor: '#92c1dc', endColor: '#92c1dc' })
        // var sparkline2 = new Sparkline($('#sparkline-2')[0], { width: 240, height: 70, lineColor: '#f56954', endColor: '#f56954' })
        // var sparkline3 = new Sparkline($('#sparkline-3')[0], { width: 240, height: 70, lineColor: '#3af221', endColor: '#3af221' })

        // sparkline1.draw([1000, 1200, 920, 927, 931, 1027, 819, 930, 1021])
        // sparkline2.draw([515, 519, 520, 522, 652, 810, 370, 627, 319, 630, 921])
        // sparkline3.draw([15, 19, 20, 22, 33, 27, 31, 27, 19, 30, 21])

        /* initialize the external events
         -----------------------------------------------------------------*/
        'use strict'

        var ticksStyle = {
            fontColor: '#495057',
            fontStyle: 'bold'
        }
        var mode = 'index'
        var intersect = true

        const plugin = {
            id: 'horizontalLines',
            defaults: {
                lines: []
            },
            afterDraw: (chart, _, options) => {
                const {ctx} = chart
                const {left, right} = chart.chartArea

                const lines = options.lines
                if (!lines) return
                for (const line of lines) {
                    const scale = chart.scales.y

                    const width = line.width || 1
                    const color = line.color || 'rgba(169,169,169, .6)'
                    const value = line.value ? scale.getPixelForValue(line.value) : 0
                    const label = line.label || null

                    if (value) {
                        ctx.beginPath()
                        ctx.lineWidth = width
                        ctx.moveTo(left, value)
                        ctx.lineTo(right, value)
                        ctx.strokeStyle = color
                        ctx.stroke()
                    }

                    if (label) {
                        ctx.fillStyle = color
                        ctx.fillText(label, right - label.length * 2, value + width)
                    }
                }

                return
            }
        }
        Chart.register(plugin)

        var $quickStatisticChart = $('#quick-statistics-chart')
        // eslint-disable-next-line no-unused-vars
        var visitorsChart = new Chart($quickStatisticChart, {
            data: {
                //labels: ['18th', '20th', '22nd', '24th', '26th', '28th', '30th'],
                labels: ${chartPeriodLabels},
                datasets: [{
                    label: 'Weekly Revenue [PLN]',
                    type: 'line',
                    //data: [100, 120, 170, 167, 180, 177, 160],
                    data: ${chartRevenueValues},
                    backgroundColor: 'transparent',
                    borderColor: '#007bff',
                    pointBorderColor: '#007bff',
                    pointBackgroundColor: '#007bff',
                    fill: false
                    // pointHoverBackgroundColor: '#007bff',
                    // pointHoverBorderColor    : '#007bff'
                },
                    {
                        label: 'Weekly burned hours [hour]',
                        type: 'line',
                        //data: [60, 80, 70, 67, 80, 77, 100],
                        data: ${chartHourValues},
                        backgroundColor: 'transparent',
                        borderColor: '#ced4da',
                        pointBorderColor: '#ced4da',
                        pointBackgroundColor: '#ced4da',
                        fill: false
                        // pointHoverBackgroundColor: '#ced4da',
                        // pointHoverBorderColor    : '#ced4da'
                    }]
            },
            options: {
                maintainAspectRatio: false,
                plugins: {
                    'horizontalLines': {
                        lines: [{
                            value: ${revenueWeeklyGoal * 10},
                            color: 'green',
                            label: '${revenueWeeklyGoal}k',
                        },
                            {
                                value: ${revenueWeeklyGoalMin * 10},
                                color: 'red',
                                label: '${revenueWeeklyGoalMin}k',
                            }]
                    },
                },
                tooltips: {
                    mode: mode,
                    intersect: intersect
                },
                hover: {
                    mode: mode,
                    intersect: intersect
                },
                legend: {
                    display: false
                },
                scales: {
                    yAxes: [{
                        // display: false,
                        gridLines: {
                            display: true,
                            lineWidth: '4px',
                            color: 'rgba(0, 0, 0, .2)',
                            zeroLineColor: 'transparent'
                        },
                        ticks: $.extend({
                            beginAtZero: true,
                            suggestedMax: 100
                        }, ticksStyle)
                    }],
                    xAxes: [{
                        display: true,
                        ticks: ticksStyle
                    }]
                }
            }
        })

        /* initialize the progress bars
         -----------------------------------------------------------------*/
        //Daily progress
        var dailyBurnedHoursPercentageVal = eval('('+'${dailyBurnedHoursPercentage}'+')');
        document.getElementById('dailyBurnedHoursPercentage').style.width = dailyBurnedHoursPercentageVal + "%";
        var dailyRevenuePercentageVal = eval('('+'${dailyRevenuePercentage}'+')');
        document.getElementById('dailyRevenuePercentage').style.width = dailyRevenuePercentageVal + "%";
        //Weekly progress
        var weeklyBurnedHoursPercentageVal = eval('('+'${weeklyBurnedHoursPercentage}'+')');
        document.getElementById('weeklyBurnedHoursPercentage').style.width = weeklyBurnedHoursPercentageVal + "%";
        var weeklyRevenuePercentageVal = eval('('+'${weeklyRevenuePercentage}'+')');
        document.getElementById('weeklyRevenuePercentage').style.width = weeklyRevenuePercentageVal + "%";
        // Last week progress
        var lastWeekBurnedHoursPercentageVal = eval('('+'${lastWeekBurnedHoursPercentage}'+')');
        document.getElementById('lastWeekBurnedHoursPercentage').style.width = lastWeekBurnedHoursPercentageVal + "%";
        var lastWeekRevenuePercentageVal = eval('('+'${lastWeekRevenuePercentage}'+')');
        document.getElementById('lastWeekRevenuePercentage').style.width = lastWeekRevenuePercentageVal + "%";
        // initialize the external events
        // -----------------------------------------------------------------
    })
</script>
</body>
</html>
