<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<!doctype html>
<html lang="${sessionScope.lang}">
<aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="${pageContext.request.contextPath}/resources/assets/index3.html" class="brand-link">
        <img src="${pageContext.request.contextPath}/resources/assets/dist/img/jFeeSoftLogo.png" alt="jFeeSoft Logo"
             class="brand-image img-circle elevation-3" style="opacity: .8">
        <span class="brand-text font-weight-light"><fmt:message key="common.brand"/></span>
    </a>
    <div class="sidebar">
        <!-- Sidebar user panel (optional) -->
        <div class="user-panel mt-3 pb-3 mb-3 d-flex">
            <div class="image">
                <img src="${pageContext.request.contextPath}/resources/assets/dist/img/user9-160x160.png"
                     class="img-circle elevation-2" alt="User Image">
            </div>
            <div class="info">
                <a href="#" class="d-block">Jakub Rutkowski</a>
            </div>
        </div>

        <!-- SidebarSearch Form -->
        <div class="form-inline">
            <div class="input-group" data-widget="sidebar-search">
                <input class="form-control form-control-sidebar" type="search" placeholder="Search" aria-label="Search">
                <div class="input-group-append">
                    <button class="btn btn-sidebar">
                        <i class="fas fa-search fa-fw"></i>
                    </button>
                </div>
            </div>
        </div>

        <!-- Sidebar Menu -->
        <nav class="mt-2">
            <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
                <!-- Add icons to the links using the .nav-icon class
                     with font-awesome or any other icon font library -->
                <!-- Dashboard -->
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/web/app/dashboard" class="nav-link">
                        <i class="nav-icon fas fa-tachometer-alt"></i>
                        <p>
                            <fmt:message key="sidebar-section.p.dashboard"/>
                        </p>
                    </a>
                </li>
                <!-- Calendar -->
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/web/app/calendar" class="nav-link">
                        <i class="nav-icon far fa-calendar-alt"></i>
                        <p>
                            <fmt:message key="sidebar-section.p.calendar"/>
                            <span class="badge badge-info right">2</span>
                        </p>
                    </a>
                </li>
                <!-- Table -->
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/web/app/table" class="nav-link">
                        <i class="nav-icon fas fa-table"></i>
                        <p>
                            <fmt:message key="sidebar-section.p.table"/>
                            <span class="badge badge-info right">2</span>
                        </p>
                    </a>
                </li>
                <li class="nav-header"><fmt:message key="sidebar-section.li.accounting"/></li>
                <!-- Invoices -->
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/web/app/error/404-page" class="nav-link">
                        <i class="nav-icon fas fa-file-invoice-dollar"></i>
                        <p>
                            <fmt:message key="sidebar-section.p.invoices"/>
                            <i class="right fas fa-angle-left"></i>
                        </p>
                    </a>
                    <ul class="nav nav-treeview">
                        <!-- All documents -->
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/web/app/error/404-page" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p><fmt:message key="sidebar-section.p.alldocuments"/></p>
                            </a>
                        </li>
                        <!-- Management -->
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/web/app/error/404-page" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p><fmt:message key="sidebar-section.p.management"/></p>
                            </a>
                        </li>
                        <!-- Financial Statements -->
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/web/app/error/404-page" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p><fmt:message key="sidebar-section.p.financialstatements"/></p>
                            </a>
                        </li>
                    </ul>
                </li>
                <!-- Payments -->
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/web/app/error/404-page" class="nav-link">
                        <i class="nav-icon far fa fa-wallet"></i>
                        <p>
                            Payments
                            <span class="badge badge-info right"><fmt:formatNumber value="${notCollectedEntriesCount}" maxFractionDigits = "3"/></span>
                        </p>
                    </a>
                    <ul class="nav nav-treeview">
                        <!-- All pending payment -->
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/web/app/collections" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p><fmt:message key="sidebar-section.p.notcollectedentries"/></p>
                            </a>
                        </li>
                        <!-- Matching -->
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/web/app/error/404-page" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p><fmt:message key="sidebar-section.p.matching"/></p>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="nav-header"><fmt:message key="sidebar-section.li.statistics"/></li>
                <!-- General -->
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/web/app/statistics/general" class="nav-link">
                        <i class="nav-icon far fa fa-chart-line"></i>
                        <p>
                            <fmt:message key="sidebar-section.p.general"/>
                        </p>
                    </a>
                </li>
                <!-- Client -->
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/web/app/statistics/client" class="nav-link">
                        <i class="nav-icon far fa fa-users"></i>
                        <p>
                            <fmt:message key="sidebar-section.p.clients"/>
                        </p>
                    </a>
                </li>
                <!-- Payments -->
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/web/app/statistics/payments" class="nav-link">
                        <i class="nav-icon far fa fa-wallet"></i>
                        <p>
                            <fmt:message key="sidebar-section.p.payments"/>
                        </p>
                    </a>
                </li>
                <!-- Reports -->
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/web/app/error/404-page" class="nav-link">
                        <i class="nav-icon far fa fa-briefcase"></i>
                        <p>
                            <fmt:message key="sidebar-section.p.reports"/>
                        </p>
                    </a>
                </li>
                <li class="nav-header">DATA</li>
                <!-- Clients -->
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/web/app/table/client" class="nav-link">
                        <i class="nav-icon far fa fa-users"></i>
                        <p>
                            <fmt:message key="sidebar-section.p.clients"/>
                        </p>
                    </a>
                </li>
                <!-- Products -->
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/web/app/table/product" class="nav-link">
                        <i class="nav-icon far fa fa-box-open"></i>
                        <p>
                            <fmt:message key="sidebar-section.p.products"/>
                        </p>
                    </a>
                </li>
            </ul>
        </nav>
        <!-- /.sidebar-menu -->
    </div>
</aside>
</html>