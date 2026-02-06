<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<!doctype html>
<html lang="${sessionScope.lang}">
<div class="card">
    <div class="card-header">
        <h3 class="card-title">
            <i class="ion ion-clipboard mr-1"></i>
            Today
        </h3>

        <div class="card-tools">
            <ul class="pagination pagination-sm">
                <li class="page-item"><a href="#" class="page-link">&laquo;</a></li>
                <li class="page-item"><a href="#" class="page-link">1</a></li>
                <li class="page-item"><a href="#" class="page-link">2</a></li>
                <li class="page-item"><a href="#" class="page-link">3</a></li>
                <li class="page-item"><a href="#" class="page-link">&raquo;</a></li>
            </ul>
        </div>
    </div>
    <div class="card-body">
        <ul class="todo-list" data-widget="todo-list">
            <c:forEach var="todoEvent" items="${todayTodoList}">
                <li>
                    <!-- drag handle -->
                    <span class="handle">
                    <i class="fas fa-ellipsis-v"></i>
                    <i class="fas fa-ellipsis-v"></i>
                </span>
                    <!-- checkbox -->
                    <div class="icheck-primary d-inline ml-2">
                        <input type="checkbox" value="" name="todo1" id="todoCheck1"
                               <c:if test="${todoEvent.checked==true}">checked</c:if>>
                        <label for="todoCheck1"></label>
                    </div>
                    <!-- todo text -->
                    <span class="text">
                        ${todoEvent.title}
                        <c:if test="${not empty todoEvent.onlineMeetURL}">
                            <a href="${todoEvent.onlineMeetURL}" target="_blank" class="ml-2 text-primary">
                                <i class="fas fa-video"></i>
                            </a>
                        </c:if>
                    </span>
                    <!-- Emphasis label -->
                    <c:choose>
                        <c:when test="${todoEvent.type=='badge-warning'}">
                        <small class="badge badge-warning"><i class="far fa-clock"></i> ${todoEvent.start}
                            - ${todoEvent.end} ${todoEvent.duration} hour(s)</small>
                        </c:when>
                        <c:when test="${todoEvent.type=='badge-danger'}">
                            <small class="badge badge-danger"><i class="far fa-clock"></i> ${todoEvent.start}
                                - ${todoEvent.end} ${todoEvent.duration} hour(s)</small>
                        </c:when>
                        <c:when test="${todoEvent.type=='badge-primary'}">
                            <small class="badge badge-primary"><i class="far fa-clock"></i> ${todoEvent.start}
                                - ${todoEvent.end} ${todoEvent.duration} hour(s)</small>
                        </c:when>
                        <c:when test="${todoEvent.type=='badge-success'}">
                            <small class="badge badge-success"><i class="far fa-clock"></i> ${todoEvent.start}
                                - ${todoEvent.end} ${todoEvent.duration} hour(s)</small>
                        </c:when>
                    </c:choose>
                    <!-- General tools such as edit or delete-->
                    <div class="tools">
                        <i class="fas fa-edit"></i>
                        <i class="fas fa-trash-o"></i>
                    </div>
                </li>
            </c:forEach>
            <%--            <li>--%>
            <%--                <!-- drag handle -->--%>
            <%--                <span class="handle">--%>
            <%--                    <i class="fas fa-ellipsis-v"></i>--%>
            <%--                    <i class="fas fa-ellipsis-v"></i>--%>
            <%--                </span>--%>
            <%--                <!-- checkbox -->--%>
            <%--                <div class="icheck-primary d-inline ml-2">--%>
            <%--                    <input type="checkbox" value="" name="todo1" id="todoCheck1" checked>--%>
            <%--                    <label for="todoCheck1"></label>--%>
            <%--                </div>--%>
            <%--                <!-- todo text -->--%>
            <%--                <span class="text">Meeting with Any</span>--%>
            <%--                <!-- Emphasis label -->--%>
            <%--                <small class="badge badge-warning"><i class="far fa-clock"></i> 11:00:00 AM - 1:00:00 PM 2 hours</small>--%>
            <%--                <!-- General tools such as edit or delete-->--%>
            <%--                <div class="tools">--%>
            <%--                    <i class="fas fa-edit"></i>--%>
            <%--                    <i class="fas fa-trash-o"></i>--%>
            <%--                </div>--%>
            <%--            </li>--%>
            <%--            <li>--%>
            <%--                <span class="handle">--%>
            <%--                    <i class="fas fa-ellipsis-v"></i>--%>
            <%--                    <i class="fas fa-ellipsis-v"></i>--%>
            <%--                </span>--%>
            <%--                <div class="icheck-primary d-inline ml-2">--%>
            <%--                    <input type="checkbox" value="" name="todo2" id="todoCheck2" checked>--%>
            <%--                    <label for="todoCheck2"></label>--%>
            <%--                </div>--%>
            <%--                <span class="text">Project for UBS - Business Meeting</span>--%>
            <%--                <small class="badge badge-primary"><i class="far fa-clock"></i> 1:00:00 PM - 2:00:00 PM 1 hours</small>--%>
            <%--                <div class="tools">--%>
            <%--                    <i class="fas fa-edit"></i>--%>
            <%--                    <i class="fas fa-trash-o"></i>--%>
            <%--                </div>--%>
            <%--            </li>--%>
            <%--            <li>--%>
            <%--                <span class="handle">--%>
            <%--                    <i class="fas fa-ellipsis-v"></i>--%>
            <%--                    <i class="fas fa-ellipsis-v"></i>--%>
            <%--                </span>--%>
            <%--                <div class="icheck-primary d-inline ml-2">--%>
            <%--                    <input type="checkbox" value="" name="todo3" id="todoCheck3">--%>
            <%--                    <label for="todoCheck3"></label>--%>
            <%--                </div>--%>
            <%--                <span class="text">Meeting with Sam</span>--%>
            <%--                <small class="badge badge-danger"><i class="far fa-clock"></i> 2:00:00 PM - 3:30:00 PM 1.5 hours</small>--%>
            <%--                <div class="tools">--%>
            <%--                    <i class="fas fa-edit"></i>--%>
            <%--                    <i class="fas fa-trash-o"></i>--%>
            <%--                </div>--%>
            <%--            </li>--%>
            <%--            <li>--%>
            <%--                <span class="handle">--%>
            <%--                    <i class="fas fa-ellipsis-v"></i>--%>
            <%--                    <i class="fas fa-ellipsis-v"></i>--%>
            <%--                </span>--%>
            <%--                <div class="icheck-primary d-inline ml-2">--%>
            <%--                    <input type="checkbox" value="" name="todo4" id="todoCheck4">--%>
            <%--                    <label for="todoCheck4"></label>--%>
            <%--                </div>--%>
            <%--                <span class="text">Meeting with Georgia</span>--%>
            <%--                <small class="badge badge-primary"><i class="far fa-clock"></i> 3:30:00 PM - 5:30:00 PM 2 hours</small>--%>
            <%--                <div class="tools">--%>
            <%--                    <i class="fas fa-edit"></i>--%>
            <%--                    <i class="fas fa-trash-o"></i>--%>
            <%--                </div>--%>
            <%--            </li>--%>
            <%--            <li>--%>
            <%--                <span class="handle">--%>
            <%--                    <i class="fas fa-ellipsis-v"></i>--%>
            <%--                    <i class="fas fa-ellipsis-v"></i>--%>
            <%--                </span>--%>
            <%--                <div class="icheck-primary d-inline ml-2">--%>
            <%--                    <input type="checkbox" value="" name="todo5" id="todoCheck5">--%>
            <%--                    <label for="todoCheck5"></label>--%>
            <%--                </div>--%>
            <%--                <span class="text">Project for BCA - Integration issue related meeting</span>--%>
            <%--                <small class="badge badge-primary"><i class="far fa-clock"></i> 5:30:00 PM - 7:30:00 PM 2 hours</small>--%>
            <%--                <div class="tools">--%>
            <%--                    <i class="fas fa-edit"></i>--%>
            <%--                    <i class="fas fa-trash-o"></i>--%>
            <%--                </div>--%>
            <%--            </li>--%>
            <%--            <li>--%>
            <%--                <span class="handle">--%>
            <%--                    <i class="fas fa-ellipsis-v"></i>--%>
            <%--                    <i class="fas fa-ellipsis-v"></i>--%>
            <%--                </span>--%>
            <%--                <div class="icheck-primary d-inline ml-2">--%>
            <%--                    <input type="checkbox" value="" name="todo6" id="todoCheck6">--%>
            <%--                    <label for="todoCheck6"></label>--%>
            <%--                </div>--%>
            <%--                <span class="text">Meeting with Sandy</span>--%>
            <%--                <small class="badge badge-primary"><i class="far fa-clock"></i> 7:30:00 PM - 10:00:00 PM 2.5 hours</small>--%>
            <%--                <div class="tools">--%>
            <%--                    <i class="fas fa-edit"></i>--%>
            <%--                    <i class="fas fa-trash-o"></i>--%>
            <%--                </div>--%>
            <%--            </li>--%>
        </ul>
    </div>
</div>
</html>