<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<!doctype html>
<html lang="${sessionScope.lang}">
<head>
    <title>jfs | Quick Statistics</title>
    <!-- Bootstrap -->
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/assets/dist/css/bootstrap.min.css">
    <!-- Custom styles for client dynamic popover -->
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/custom-popover-client-dynamic.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/dist/css/adminlte.min.css">
</head>
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
            <div class="col-md-8">
                <p class="text-center">
                    <strong>Incomes: ${chartDateFrom} - ${chartDateTo}</strong>
                </p>

                <div class="chart">
                    <!-- Sales Chart Canvas -->
                    <!-- dashboard2.js should be included - -->
                    <canvas id="quick-statistics-chart" height="270" style="height: 270px;"></canvas>
                </div>
                <!-- /.chart-responsive -->
            </div>
            <!-- /.col -->
            <div class="col-md-4">
                <p class="text-center">
                    <strong>Goal Completion</strong>
                </p>

                <!-- Current day -->
                <div class="progress-group">
                    Burned hours
                    <span class="float-right"><b><fmt:formatNumber value="${dailyBurnedHours}"
                                                                   maxFractionDigits="3"/></b>/<fmt:formatNumber
                            value="${dailyTotalHours}" maxFractionDigits="3"/></span>
                    <div class="progress progress-sm">
                        <div id="dailyBurnedHoursPercentage" class="progress-bar bg-primary" style="width: 0%"></div>
                    </div>
                </div>
                <div class="progress-group">
                    Revenue
                    <span class="float-right"><b><fmt:formatNumber value="${dailyRevenue}"
                                                                   maxFractionDigits="3"/></b>/<fmt:formatNumber
                            value="${dailyTotalRevenue}" maxFractionDigits="3"/></span>
                    <div class="progress progress-sm">
                        <div id="dailyRevenuePercentage" class="progress-bar bg-primary" style="width: 0%"></div>
                    </div>
                </div>

                <!-- Current week -->
                <div class="progress-group">
                    <span class="progress-text">This week burned hours</span>
                    <span class="float-right"><b><fmt:formatNumber value="${weeklyBurnedHours}"
                                                                   maxFractionDigits="3"/></b>/<fmt:formatNumber
                            value="${weeklyTotalHours}" maxFractionDigits="3"/></span>
                    <div class="progress progress-sm">
                        <div id="weeklyBurnedHoursPercentage" class="progress-bar bg-white" style="width: 0%"></div>
                    </div>
                </div>
                <div class="progress-group">
                    This week revenue
                    <span class="float-right"><b><fmt:formatNumber value="${weeklyRevenue}" maxFractionDigits="3"/></b>/<fmt:formatNumber
                            value="${weeklyTotalRevenue}" maxFractionDigits="3"/></span>
                    <div class="progress progress-sm">
                        <div id="weeklyRevenuePercentage" class="progress-bar bg-white" style="width: 0%"></div>
                    </div>
                </div>

                <!-- Last week -->
                <div class="progress-group">
                    <span class="progress-text">Last week burned hours</span>
                    <span class="float-right"><b><fmt:formatNumber value="${lastWeekBurnedHours}"
                                                                   maxFractionDigits="3"/></b>/<fmt:formatNumber
                            value="${lastWeekTotalHours}" maxFractionDigits="3"/></span>
                    <div class="progress progress-sm">
                        <div id="lastWeekBurnedHoursPercentage" class="progress-bar bg-secondary"
                             style="width: 0%"></div>
                    </div>
                </div>
                <div class="progress-group">
                    Last week revenue
                    <span class="float-right"><b><fmt:formatNumber value="${lastWeekRevenue}"
                                                                   maxFractionDigits="3"/></b>/<fmt:formatNumber
                            value="${lastWeekTotalRevenue}" maxFractionDigits="3"/></span>
                    <div class="progress progress-sm">
                        <div id="lastWeekRevenuePercentage" class="progress-bar bg-secondary" style="width: 0%"></div>
                    </div>
                </div>
            </div>
            <!-- /.col -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-4 text-center">
                <input type="text" class="knob" value="${generalCollectionDataNotCollectedAmountPercentage}"
                       data-skin="tron" data-thickness="0.2" data-width="70"
                       data-height="70" data-fgColor="#3c8dbc" data-readonly="true">

                <div class="knob-label">NOT COLLECTED <fmt:formatNumber
                        value="${generalCollectionDataNotCollectedAmount}" maxFractionDigits="3"/></div>
            </div>
            <div class="col-4 text-center">
                <input type="text" class="knob" value="${generalCollectionDataInvoicedAmountPercentage}"
                       data-skin="tron" data-thickness="0.2" data-width="70"
                       data-height="70" data-fgColor="#3c8dbc" data-readonly="true">

                <div class="knob-label">INVOICED <fmt:formatNumber value="${generalCollectionDataInvoicedAmount}"
                                                                   maxFractionDigits="3"/></div>
            </div>
        </div>
    </div>
    <!-- ./card-body -->
    <div class="card-footer">
        <div class="row">
            <div class="col-sm-2 col-6">
                <div class="description-block border-right">
                    <c:if test="${growthPercentage > 0}">
						<span class="description-percentage text-success"><i
                                class="fas fa-caret-up"></i> <fmt:formatNumber type="number" maxFractionDigits="2"
                                                                               value="${growthPercentage}"/>%</span>
                        <h5 class="description-header"><fmt:formatNumber value="${currentYearIncome}"
                                                                         maxFractionDigits="3"/>
                            (
                            <h7 class="description-header"><fmt:formatNumber value="${lastYearIncome}"
                                                                             maxFractionDigits="3"/></h7>
                            )
                        </h5>
                        <span class="description-text">TOTAL REVENUE (PLN)</span>
                    </c:if>
                    <c:if test="${growthPercentage == 0}">
						<span class="description-percentage text-warning"><i
                                class="fas fa-caret-left"></i> <fmt:formatNumber type="number" maxFractionDigits="2"
                                                                                 value="${growthPercentage}"/>%</span>
                        <h5 class="description-header"><fmt:formatNumber value="${currentYearIncome}"
                                                                         maxFractionDigits="3"/>
                            (
                            <h7 class="description-header"><fmt:formatNumber value="${lastYearIncome}"
                                                                             maxFractionDigits="3"/></h7>
                            )
                        </h5>
                        <span class="description-text">TOTAL REVENUE (PLN)</span>
                    </c:if>
                    <c:if test="${growthPercentage < 0}">
						<span class="description-percentage text-danger"><i
                                class="fas fa-caret-down"></i> <fmt:formatNumber type="number" maxFractionDigits="2"
                                                                                 value="${growthPercentage}"/>%</span>
                        <h5 class="description-header"><fmt:formatNumber value="${currentYearIncome}"
                                                                         maxFractionDigits="3"/>
                            (
                            <h7 class="description-header"><fmt:formatNumber value="${lastYearIncome}"
                                                                             maxFractionDigits="3"/></h7>
                            )
                        </h5>
                        <span class="description-text">TOTAL REVENUE (PLN)</span>
                    </c:if>
                </div>
                <!-- /.description-block -->
            </div>
            <!-- /.col -->
            <div class="col-sm-2 col-6">
                <div class="description-block border-right">
                    <c:if test="${growthSamePeriodPercentage == null}">
                        <span class="description-percentage text-muted">
                            <i class="fas fa-question-circle"></i> N/A
                        </span>
                        <h5 class="description-header"><fmt:formatNumber value="${currentYearIncome}"
                                                                         maxFractionDigits="3"/>
                            (
                            <h7 class="description-header"><fmt:formatNumber value="${lastYearSamePeriodIncome}"
                                                                             maxFractionDigits="3"/></h7>
                            )
                        </h5>
                        <span class="description-text">TOTAL REVENUE TILL NOW (PLN)</span>
                    </c:if>
                    <c:if test="${growthSamePeriodPercentage != null && growthSamePeriodPercentage > 0}">
						<span class="description-percentage text-success"><i
                                class="fas fa-caret-up"></i> <fmt:formatNumber type="number" maxFractionDigits="2"
                                                                               value="${growthSamePeriodPercentage}"/>%</span>
                        <h5 class="description-header"><fmt:formatNumber value="${currentYearIncome}"
                                                                         maxFractionDigits="3"/>
                            (
                            <h7 class="description-header"><fmt:formatNumber value="${lastYearSamePeriodIncome}"
                                                                             maxFractionDigits="3"/></h7>
                            )
                        </h5>
                        <span class="description-text">TOTAL REVENUE TILL NOW (PLN)</span>
                    </c:if>
                    <c:if test="${growthSamePeriodPercentage != null && growthSamePeriodPercentage == 0}">
						<span class="description-percentage text-warning"><i
                                class="fas fa-caret-left"></i> <fmt:formatNumber type="number" maxFractionDigits="2"
                                                                                 value="${growthSamePeriodPercentage}"/>%</span>
                        <h5 class="description-header"><fmt:formatNumber value="${currentYearIncome}"
                                                                         maxFractionDigits="3"/>
                            (
                            <h7 class="description-header"><fmt:formatNumber value="${lastYearSamePeriodIncome}"
                                                                             maxFractionDigits="3"/></h7>
                            )
                        </h5>
                        <span class="description-text">TOTAL REVENUE TILL NOW (PLN)</span>
                    </c:if>
                    <c:if test="${growthSamePeriodPercentage != null && growthSamePeriodPercentage < 0}">
						<span class="description-percentage text-danger"><i
                                class="fas fa-caret-down"></i> <fmt:formatNumber type="number" maxFractionDigits="2"
                                                                                 value="${growthSamePeriodPercentage}"/>%</span>
                        <h5 class="description-header"><fmt:formatNumber value="${currentYearIncome}"
                                                                         maxFractionDigits="3"/>
                            (
                            <h7 class="description-header"><fmt:formatNumber value="${lastYearSamePeriodIncome}"
                                                                             maxFractionDigits="3"/></h7>
                            )
                        </h5>
                        <span class="description-text">TOTAL REVENUE TILL NOW (PLN)</span>
                    </c:if>
                </div>
                <!-- /.description-block -->
            </div>
            <!-- /.col -->
            <div class="col-sm-2 col-6">
                <div class="description-block border-right">
                    <%--                                            <span class="description-percentage text-warning"><i--%>
                    <%--													class="fas fa-caret-left"></i> 0%</span>--%>
                    <c:if test="${efficiencyGrowthPercentage > 0}">
						<span class="description-percentage text-success"><i
                                class="fas fa-caret-up"></i> <fmt:formatNumber type="number" maxFractionDigits="2"
                                                                               value="${efficiencyGrowthPercentage}"/>%</span>
                        <h5 class="description-header"><fmt:formatNumber value="${yearlyEfficiency}"
                                                                         maxFractionDigits="3"/>
                            (
                            <h7 class="description-header"><fmt:formatNumber value="${lastYearEfficiency}"
                                                                             maxFractionDigits="3"/></h7>
                            )
                        </h5>
                        <span class="description-text">EFFICIENCY (PLN/H)</span>
                    </c:if>
                    <c:if test="${efficiencyGrowthPercentage == 0}">
						<span class="description-percentage text-warning"><i
                                class="fas fa-caret-left"></i> <fmt:formatNumber
                                type="number" maxFractionDigits="2"
                                value="${efficiencyGrowthPercentage}"/>%</span>
                        <h5 class="description-header"><fmt:formatNumber value="${yearlyEfficiency}"
                                                                         maxFractionDigits="3"/>
                            (
                            <h7 class="description-header"><fmt:formatNumber value="${lastYearEfficiency}"
                                                                             maxFractionDigits="3"/></h7>
                            )
                        </h5>
                        <span class="description-text">EFFICIENCY (PLN/H)</span>
                    </c:if>
                    <c:if test="${efficiencyGrowthPercentage < 0}">
																				<span class="description-percentage text-danger"><i
                                                                                        class="fas fa-caret-down"></i> <fmt:formatNumber
                                                                                        type="number"
                                                                                        maxFractionDigits="2"
                                                                                        value="${efficiencyGrowthPercentage}"/>%</span>
                        <h5 class="description-header"><fmt:formatNumber value="${yearlyEfficiency}"
                                                                         maxFractionDigits="3"/>
                            (
                            <h7 class="description-header"><fmt:formatNumber value="${lastYearEfficiency}"
                                                                             maxFractionDigits="3"/></h7>
                            )
                        </h5>
                        <span class="description-text">EFFICIENCY (PLN/H)</span>
                    </c:if>
                </div>
                <!-- /.description-block -->
            </div>
            <div class="col-sm-2 col-6">
                <div class="description-block border-right">
                    <c:if test="${growPercentageYearlyClientDynamic > 0}">
						<span class="description-percentage text-success" data-toggle="popover"><i
                                class="fas fa-caret-up"></i> <fmt:formatNumber type="number" maxFractionDigits="2"
                                                                               value="${growPercentageYearlyClientDynamic}"/>%</span>
                        <h5 class="description-header"><fmt:formatNumber value="${currentYearYearlyClientDynamic}"
                                                                         maxFractionDigits="2"/>
                            (
                            <h7 class="description-header"><fmt:formatNumber value="${lastYearYearlyClientDynamic}"
                                                                             maxFractionDigits="2"/></h7>
                            )
                        </h5>
                        <span class="description-text">CLIENTS DYNAMIC TILL NOW (%)</span>
                    </c:if>
                    <c:if test="${growPercentageYearlyClientDynamic == 0}">
                    <span class="description-percentage text-warning" data-toggle="popover"><i
                            class="fas fa-caret-left"></i> <fmt:formatNumber type="number"
                                                                             maxFractionDigits="2"
                                                                             value="${growPercentageYearlyClientDynamic}"/>%</span>
                        <h5 class="description-header"><fmt:formatNumber value="${currentYearYearlyClientDynamic}"
                                                                         maxFractionDigits="2"/>
                            (
                            <h7 class="description-header"><fmt:formatNumber value="${lastYearYearlyClientDynamic}"
                                                                             maxFractionDigits="2"/></h7>
                            )
                        </h5>
                        <span class="description-text">CLIENTS DYNAMIC TILL NOW (%)</span>
                    </c:if>
                    <c:if test="${growPercentageYearlyClientDynamic < 0}">
						<span class="description-percentage text-danger" data-toggle="popover"><i
                                class="fas fa-caret-down"></i> <fmt:formatNumber type="number" maxFractionDigits="2"
                                                                                 value="${growPercentageYearlyClientDynamic}"/>%</span>
                        <h5 class="description-header"><fmt:formatNumber value="${currentYearYearlyClientDynamic}"
                                                                         maxFractionDigits="2"/>
                            (
                            <h7 class="description-header"><fmt:formatNumber value="${lastYearYearlyClientDynamic}"
                                                                             maxFractionDigits="2"/>
                            </h7>
                            )
                        </h5>
                        <span class="description-text">CLIENTS DYNAMIC TILL NOW (%)</span>
                    </c:if>
                </div>
                <!-- /.description-block -->
            </div>
            <!-- /.col -->
            <div class="col-sm-2 col-6">
                <div class="description-block border-right">
                                            <span class="description-percentage text-success"><i
                                                    class="fas fa-caret-down"></i> 10%</span>
                    <h5 class="description-header">70%</h5>
                    <span class="description-text">GOAL COMPLETIONS (YEARLY)</span>
                </div>
                <!-- /.description-block -->
            </div>
            <!-- /.col -->
            <div class="col-sm-2 col-6">
                <div class="description-block">
                                            <span class="description-percentage text-danger">
                                                     <h5 class="description-header">
                    <c:if test="${previousToCurrentYearlyLongestWeeklyGoalStreakProportion > 0}">
						<span class="description-percentage text-success"><i
                                class="fas fa-caret-up"></i> <fmt:formatNumber type="number" maxFractionDigits="2"
                                                                               value="${previousToCurrentYearlyLongestWeeklyGoalStreakProportion}"/>%</span>
                    </c:if>
                    <c:if test="${previousToCurrentYearlyLongestWeeklyGoalStreakProportion == 0}">
						<span class="description-percentage text-warning"><i
                                class="fas fa-caret-left"></i> <fmt:formatNumber type="number" maxFractionDigits="2"
                                                                                 value="${previousToCurrentYearlyLongestWeeklyGoalStreakProportion}"/>%</span>
                    </c:if>
                    <c:if test="${previousToCurrentYearlyLongestWeeklyGoalStreakProportion < 0}">
						<span class="description-percentage text-danger"><i
                                class="fas fa-caret-down"></i> <fmt:formatNumber type="number" maxFractionDigits="2"
                                                                                 value="${previousToCurrentYearlyLongestWeeklyGoalStreakProportion}"/>%</span>
                    </c:if>
                                                     </h5>
                                                     </span>
                    <h5 class="description-header"><fmt:formatNumber value="${yearlyLongestWeeklyGoalStreak}" maxFractionDigits="2"/>
                    (
                                                <h7 class="description-header"><fmt:formatNumber value="${previousYearlyLongestWeeklyGoalStreak}"
                                                                                                 maxFractionDigits="3"/></h7>
                                                )
                    </h5>
                    <span class="description-text">LONGEST WEEKLY GOAL STREAK (YEARLY)</span>
                </div>
                <!-- /.description-block -->
            </div>
            <!-- /.col -->
        </div>
        <!-- /.row -->
    </div>
    <!-- /.card-footer -->
</div>
<!-- REQUIRED SCRIPTS -->
<!-- jQuery -->
<script src="${pageContext.request.contextPath}/resources/assets/plugins/jquery/jquery.min.js"></script>
<script>
    $(document).ready(function () {
        $('[data-toggle="popover"]').popover({
            trigger: 'hover focus',
            html: true,
            placement: 'auto',
            container: 'body',
            template: `
        <div class="custom-popover popover" role="tooltip">
            <div class="arrow"></div>
            <div class="popover-body"></div>
        </div>`,
            content: function () {
                return `
            <div>
                <span class="font-weight-bold">${currentYearAllClients}</span>
                (<span class="text-success font-weight-bold">${currentYearNewClients}</span> |
                <span class="text-danger font-weight-bold">${currentYearLostClients}</span>) :
                <span class="font-weight-bold">${currentYear}</span>
                <span><fmt:message key="dashboard-form.span.quickstatistics.client-dynamic-year"/></span><br/>
                <span class="font-weight-bold">${lastYearAllClients}</span>
                (<span class="text-success font-weight-bold">${lastYearNewClients}</span> |
                <span class="text-danger font-weight-bold">${lastYearLostClients}</span>) :
                <span class="font-weight-bold">${lastYear}</span>
                <span><fmt:message key="dashboard-form.span.quickstatistics.client-dynamic-year"/></span><br/>
            </div>`;
            }
        });
    });
</script>
</html>