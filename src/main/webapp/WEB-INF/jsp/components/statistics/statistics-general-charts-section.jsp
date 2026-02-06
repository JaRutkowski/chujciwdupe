<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<!doctype html>
<html lang="${sessionScope.lang}">
<div class="card">
    <div class="card-header">
        <h5 class="card-title"><fmt:message key="dashboard-form.h5.quickstatistics"/></h5>
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
        <div class="row">
            <!-- Content Wrapper. Contains page content -->
            <div class="col-md-12">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <div class="container-fluid">
                        <div class="row mb-2">
                            <div class="col-sm-6">
                                <h1>Hours/Income per week</h1>
                            </div>
                            <div class="col-sm-6">
                                <ol class="breadcrumb float-sm-right">
                                    <li class="breadcrumb-item"><a href="#">Home</a></li>
                                    <li class="breadcrumb-item active">Flot</li>
                                </ol>
                            </div>
                        </div>
                    </div><!-- /.container-fluid -->
                </section>

                <!-- Main content -->
                <section class="content">
                    <div class="container-fluid">
                        <!-- NEW! -->
                        <div class="row">
                            <div class="col-12">
                                <!-- interactive chart -->
                                <div class="card card-primary card-outline">
                                    <div class="card-header">
                                        <h3 class="card-title">
                                            <i class="far fa-chart-bar"></i>
                                            Incomes ${chartweeklyTitleLabel}: ${chartweeklyDateFrom} - ${chartweeklyDateTo}
                                        </h3>

                                        <div class="card-tools">
                                            Real time
                                            <div class="btn-group" id="realtime-1" data-toggle="btn-toggle">
                                                <button type="button" class="btn btn-default btn-sm active"
                                                        data-toggle="on">On
                                                </button>
                                                <button type="button" class="btn btn-default btn-sm" data-toggle="off">
                                                    Off
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <!-- Sales Chart Canvas -->
                                        <!-- dashboard2.js should be included - -->
                                        <canvas id="quick-general-statistics-weekly-chart" height="300"
                                                style="height: 300px;"></canvas>
                                    </div>
                                    <!-- /.card-body-->
                                </div>
                                <!-- /.card -->

                                <!-- interactive chart -->
                                <div class="card card-primary card-outline">
                                    <div class="card-header">
                                        <h3 class="card-title">
                                            <i class="far fa-chart-bar"></i>
                                            Incomes ${chartmonthlyTitleLabel}: ${chartmonthlyDateFrom} - ${chartmonthlyDateTo}
                                        </h3>

                                        <div class="card-tools">
                                            Real time
                                            <div class="btn-group" id="realtime-2" data-toggle="btn-toggle">
                                                <button type="button" class="btn btn-default btn-sm active"
                                                        data-toggle="on">On
                                                </button>
                                                <button type="button" class="btn btn-default btn-sm" data-toggle="off">
                                                    Off
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <!-- Sales Chart Canvas -->
                                        <!-- dashboard2.js should be included - -->
                                        <canvas id="quick-general-statistics-monthly-chart" height="300"
                                                style="height: 300px;"></canvas>
                                    </div>
                                    <!-- /.card-body-->
                                </div>
                                <!-- /.card -->

                                <!-- interactive chart -->
                                <div class="card card-primary card-outline">
                                    <div class="card-header">
                                        <h3 class="card-title">
                                            <i class="far fa-chart-bar"></i>
                                            Accumulative Sum ${chartAccumulativeSumweeklyTitleLabel}: ${chartAccumulativeSumweeklyDateFrom}
                                            - ${chartAccumulativeSumweeklyDateTo}
                                        </h3>

                                        <div class="card-tools">
                                            Real time
                                            <div class="btn-group" id="realtime-3" data-toggle="btn-toggle">
                                                <button type="button" class="btn btn-default btn-sm active"
                                                        data-toggle="on">On
                                                </button>
                                                <button type="button" class="btn btn-default btn-sm" data-toggle="off">
                                                    Off
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <!-- Sales Chart Canvas -->
                                        <!-- dashboard2.js should be included - -->
                                        <canvas id="quick-general-statistics-accumulative-sum-weekly-chart" height="300"
                                                style="height: 300px;"></canvas>
                                    </div>
                                    <!-- /.card-body-->
                                </div>
                                <!-- /.card -->

                                <!-- interactive chart -->
                                <div class="card card-primary card-outline">
                                    <div class="card-header">
                                        <h3 class="card-title">
                                            <i class="far fa-chart-bar"></i>
                                            Accumulative Sum ${chartAccumulativeSummonthlyTitleLabel}: ${chartAccumulativeSummonthlyDateFrom}
                                            - ${chartAccumulativeSummonthlyDateTo}
                                        </h3>

                                        <div class="card-tools">
                                            Real time
                                            <div class="btn-group" id="realtime-4" data-toggle="btn-toggle">
                                                <button type="button" class="btn btn-default btn-sm active"
                                                        data-toggle="on">On
                                                </button>
                                                <button type="button" class="btn btn-default btn-sm" data-toggle="off">
                                                    Off
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <!-- Sales Chart Canvas -->
                                        <!-- dashboard2.js should be included - -->
                                        <canvas id="quick-general-statistics-accumulative-sum-monthly-chart" height="300"
                                                style="height: 300px;"></canvas>
                                    </div>
                                    <!-- /.card-body-->
                                </div>
                                <!-- /.card -->

                                <!-- interactive chart -->
                                <div class="card card-primary card-outline">
                                    <div class="card-header">
                                        <h3 class="card-title">
                                            <i class="far fa-chart-bar"></i>
                                            Efficiency ${chartEfficiencyweeklyTitleLabel}: ${chartAccumulativeSumweeklyDateFrom}
                                            - ${chartAccumulativeSumweeklyDateTo}
                                        </h3>

                                        <div class="card-tools">
                                            Real time
                                            <div class="btn-group" id="realtime-5" data-toggle="btn-toggle">
                                                <button type="button" class="btn btn-default btn-sm active"
                                                        data-toggle="on">On
                                                </button>
                                                <button type="button" class="btn btn-default btn-sm" data-toggle="off">
                                                    Off
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <!-- Sales Chart Canvas -->
                                        <!-- dashboard2.js should be included - -->
                                        <canvas id="quick-general-statistics-efficiency-weekly-chart" height="300"
                                                style="height: 300px;"></canvas>
                                    </div>
                                    <!-- /.card-body-->
                                </div>
                                <!-- /.card -->

                                <!-- interactive chart -->
                                <div class="card card-primary card-outline">
                                    <div class="card-header">
                                        <h3 class="card-title">
                                            <i class="far fa-chart-bar"></i>
                                            Efficiency ${chartEfficiencymonthlyTitleLabel}: ${chartAccumulativeSummonthlyDateFrom}
                                            - ${chartAccumulativeSummonthlyDateTo}
                                        </h3>

                                        <div class="card-tools">
                                            Real time
                                            <div class="btn-group" id="realtime-6" data-toggle="btn-toggle">
                                                <button type="button" class="btn btn-default btn-sm active"
                                                        data-toggle="on">On
                                                </button>
                                                <button type="button" class="btn btn-default btn-sm" data-toggle="off">
                                                    Off
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <!-- Sales Chart Canvas -->
                                        <!-- dashboard2.js should be included - -->
                                        <canvas id="quick-general-statistics-efficiency-monthly-chart" height="300"
                                                style="height: 300px;"></canvas>
                                    </div>
                                    <!-- /.card-body-->
                                </div>
                                <!-- /.card -->
                            </div>
                            <!-- /.col -->
                        </div>
                        <!-- /.row -->

                        <%--            <!-- NEW! - two bars -->--%>
                        <%--            <div class="row">--%>
                        <%--              <div class="col-12">--%>
                        <%--                <!-- interactive chart -->--%>
                        <%--                <div class="card card-primary card-outline">--%>
                        <%--                  <div class="card-header">--%>
                        <%--                    <h3 class="card-title">--%>
                        <%--                      <i class="far fa-chart-bar"></i>--%>
                        <%--                      Interactive Area Chart--%>
                        <%--                    </h3>--%>

                        <%--                    <div class="card-tools">--%>
                        <%--                      Real time--%>
                        <%--                      <div class="btn-group" id="realtime-2" data-toggle="btn-toggle">--%>
                        <%--                        <button type="button" class="btn btn-default btn-sm active" data-toggle="on">On</button>--%>
                        <%--                        <button type="button" class="btn btn-default btn-sm" data-toggle="off">Off</button>--%>
                        <%--                      </div>--%>
                        <%--                    </div>--%>
                        <%--                  </div>--%>
                        <%--                  <div class="card-body">--%>
                        <%--                    <!--div-- id="donut-chart" style="height: 300px;"></div-->--%>
                        <%--                    <div class="chart">--%>
                        <%--                      <canvas id="barChart" style="min-height: 250px; height: 300px; max-height: 300px; max-width: 100%;"></canvas>--%>
                        <%--                    </div>--%>
                        <%--                  </div>--%>
                        <%--                  <!-- /.card-body-->--%>
                        <%--                </div>--%>
                        <%--                <!-- /.card -->--%>

                        <%--              </div>--%>
                        <%--              <!-- /.col -->--%>
                        <%--            </div>--%>
                        <%--            <!-- /.row -->--%>

                        <%--            <div class="row">--%>
                        <%--              <div class="col-md-6">--%>
                        <%--                <!-- Line chart -->--%>
                        <%--                <div class="card card-primary card-outline">--%>
                        <%--                  <div class="card-header">--%>
                        <%--                    <h3 class="card-title">--%>
                        <%--                      <i class="far fa-chart-bar"></i>--%>
                        <%--                      Line Chart--%>
                        <%--                    </h3>--%>

                        <%--                    <div class="card-tools">--%>
                        <%--                      <button type="button" class="btn btn-tool" data-card-widget="collapse">--%>
                        <%--                        <i class="fas fa-minus"></i>--%>
                        <%--                      </button>--%>
                        <%--                      <button type="button" class="btn btn-tool" data-card-widget="remove">--%>
                        <%--                        <i class="fas fa-times"></i>--%>
                        <%--                      </button>--%>
                        <%--                    </div>--%>
                        <%--                  </div>--%>
                        <%--                  <div class="card-body">--%>
                        <%--                    <div id="line-chart" style="height: 300px;"></div>--%>
                        <%--                  </div>--%>
                        <%--                  <!-- /.card-body-->--%>
                        <%--                </div>--%>
                        <%--                <!-- /.card -->--%>

                        <%--                <!-- Area chart -->--%>
                        <%--                <div class="card card-primary card-outline">--%>
                        <%--                  <div class="card-header">--%>
                        <%--                    <h3 class="card-title">--%>
                        <%--                      <i class="far fa-chart-bar"></i>--%>
                        <%--                      Area Chart--%>
                        <%--                    </h3>--%>

                        <%--                    <div class="card-tools">--%>
                        <%--                      <button type="button" class="btn btn-tool" data-card-widget="collapse">--%>
                        <%--                        <i class="fas fa-minus"></i>--%>
                        <%--                      </button>--%>
                        <%--                      <button type="button" class="btn btn-tool" data-card-widget="remove">--%>
                        <%--                        <i class="fas fa-times"></i>--%>
                        <%--                      </button>--%>
                        <%--                    </div>--%>
                        <%--                  </div>--%>
                        <%--                  <div class="card-body">--%>
                        <%--                    <div id="area-chart" style="height: 338px;" class="full-width-chart"></div>--%>
                        <%--                  </div>--%>
                        <%--                  <!-- /.card-body-->--%>
                        <%--                </div>--%>
                        <%--                <!-- /.card -->--%>

                        <%--              </div>--%>
                        <%--              <!-- /.col -->--%>

                        <%--              <div class="col-md-6">--%>
                        <%--                <!-- Bar chart -->--%>
                        <%--                <div class="card card-primary card-outline">--%>
                        <%--                  <div class="card-header">--%>
                        <%--                    <h3 class="card-title">--%>
                        <%--                      <i class="far fa-chart-bar"></i>--%>
                        <%--                      Bar Chart--%>
                        <%--                    </h3>--%>

                        <%--                    <div class="card-tools">--%>
                        <%--                      <button type="button" class="btn btn-tool" data-card-widget="collapse">--%>
                        <%--                        <i class="fas fa-minus"></i>--%>
                        <%--                      </button>--%>
                        <%--                      <button type="button" class="btn btn-tool" data-card-widget="remove">--%>
                        <%--                        <i class="fas fa-times"></i>--%>
                        <%--                      </button>--%>
                        <%--                    </div>--%>
                        <%--                  </div>--%>
                        <%--                  <div class="card-body">--%>
                        <%--                    <div id="bar-chart" style="height: 300px;"></div>--%>
                        <%--                  </div>--%>
                        <%--                  <!-- /.card-body-->--%>
                        <%--                </div>--%>
                        <%--                <!-- /.card -->--%>

                        <%--                <!-- Donut chart -->--%>
                        <%--                <div class="card card-primary card-outline">--%>
                        <%--                  <div class="card-header">--%>
                        <%--                    <h3 class="card-title">--%>
                        <%--                      <i class="far fa-chart-bar"></i>--%>
                        <%--                      Donut Chart--%>
                        <%--                    </h3>--%>

                        <%--                    <div class="card-tools">--%>
                        <%--                      <button type="button" class="btn btn-tool" data-card-widget="collapse">--%>
                        <%--                        <i class="fas fa-minus"></i>--%>
                        <%--                      </button>--%>
                        <%--                      <button type="button" class="btn btn-tool" data-card-widget="remove">--%>
                        <%--                        <i class="fas fa-times"></i>--%>
                        <%--                      </button>--%>
                        <%--                    </div>--%>
                        <%--                  </div>--%>
                        <%--                  <div class="card-body">--%>
                        <%--                    <div id="donut-chart" style="height: 300px;"></div>--%>
                        <%--                  </div>--%>
                        <%--                  <!-- /.card-body-->--%>
                        <%--                </div>--%>
                        <%--                <!-- /.card -->--%>
                        <%--              </div>--%>
                        <%--              <!-- /.col -->--%>
                        <%--            </div>--%>
                        <%--            <!-- /.row -->--%>
                    </div><!-- /.container-fluid -->
                </section>
                <!-- /.content -->
            </div>
            <!-- /.content-wrapper -->
        </div>
        <!-- /.content-wrapper -->
        <!-- /.row -->
    </div>
    <!-- ./card-body -->
    <!-- /.card-footer -->
</div>
</html>