<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:eval expression="@environment.getProperty('app.front.public.path')" var="frontPublicPath"/>
<spring:eval expression="@environment.getProperty('app.front.calendar.defaultView')" var="calendarDefaultView"/>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>jfs | Calendar</title>

    <!-- Google Font: Source Sans Pro -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/assets/dist/img/jFeeSoftLogo-full.jpg">
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
    <!-- Font Awesome -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/assets/plugins/fontawesome-free/css/all.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/assets/plugins/fontawesome-free/css/v4-shims.min.css">
    <!-- fullCalendar -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/plugins/fullcalendar/main.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/dist/css/adminlte.min.css">
    <!-- MultiMonthYear -->
    <link href='https://cdn.jsdelivr.net/npm/@fullcalendar/daygrid@5.10.1/main.min.css' rel='stylesheet'/>
    <link href='https://cdn.jsdelivr.net/npm/@fullcalendar/resource-timeline@5.10.1/main.min.css' rel='stylesheet'/>
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
                    <div class="col-md-3">
                        <div class="sticky-top mb-3">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title">Draggable Events</h4>
                                </div>
                                <div class="card-body">
                                    <!-- the events -->
                                    <div id="external-events">
                                        <div class="external-event bg-success">Lunch</div>
                                        <div class="external-event bg-warning">Go home</div>
                                        <div class="external-event bg-info">Do homework</div>
                                        <div class="external-event bg-primary">Work on UI design</div>
                                        <div class="external-event bg-danger">Sleep tight</div>
                                        <div class="checkbox">
                                            <label for="drop-remove">
                                                <input type="checkbox" id="drop-remove">
                                                remove after drop
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <!-- /.card-body -->
                            </div>
                            <!-- /.card -->
                            <div class="card">
                                <div class="card-header">
                                    <h3 class="card-title">Create Event</h3>
                                </div>
                                <div class="card-body">
                                    <div class="btn-group" style="width: 100%; margin-bottom: 10px;">
                                        <ul class="fc-color-picker" id="color-chooser">
                                            <li><a class="text-primary" href="#"><i class="fas fa-square"></i></a></li>
                                            <li><a class="text-warning" href="#"><i class="fas fa-square"></i></a></li>
                                            <li><a class="text-success" href="#"><i class="fas fa-square"></i></a></li>
                                            <li><a class="text-danger" href="#"><i class="fas fa-square"></i></a></li>
                                            <li><a class="text-muted" href="#"><i class="fas fa-square"></i></a></li>
                                        </ul>
                                    </div>
                                    <!-- /btn-group -->
                                    <div class="input-group">
                                        <input id="new-event" type="text" class="form-control"
                                               placeholder="Event Title">

                                        <div class="input-group-append">
                                            <button id="add-new-event" type="button" class="btn btn-primary">Add
                                            </button>
                                        </div>
                                        <!-- /btn-group -->
                                    </div>
                                    <!-- /input-group -->
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- /.col -->
                    <div class="col-md-9">
                        <div class="card card-primary">
                            <div id="copyTemplateMessage"></div>

                            <div class="card-body p-0">
                                <!-- THE CALENDAR -->
                                <div id="calendar"></div>
                            </div>
                            <!-- /.card-body -->
                            <div class="card-footer text-right d-flex flex-column align-items-end">
                                <div class="col-12 col-sm-4 p-0">
                                    <label for="clientDropdown" class="sr-only"><fmt:message key="calendar-form.calendar.iCalendar.selectClient"/></label>
                                    <select id="clientDropdown" class="form-control" required>
                                        <option value="" disabled selected><fmt:message key="calendar-form.calendar.iCalendar.selectClient"/></option>
                                    </select>
                                </div>
                                <div class="mt-3 d-flex align-items-center">
                                    <button id="generateIcs" class="btn btn-primary mr-2">
                                        <fmt:message key="calendar-form.calendar.iCalendar.generateIcsFile"/>
                                    </button>
                                    <button id="copyTemplate" class="btn btn-secondary" title="Copy Template">
                                        <i class="fas fa-copy"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                        <!-- /.card -->
                    </div>
                    <!-- /.col -->
                </div>
                <!-- /.row -->
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
<script src='${pageContext.request.contextPath}/resources/assets/plugins/fullcalendar/dist/index.global.js'></script>
<script src='https://cdn.jsdelivr.net/npm/@fullcalendar/core@5.10.1/main.min.js'></script>
<script src='https://cdn.jsdelivr.net/npm/@fullcalendar/daygrid@5.10.1/main.min.js'></script>
<script src='https://cdn.jsdelivr.net/npm/@fullcalendar/resource-timeline@5.10.1/main.min.js'></script>

<!-- Page specific script -->
<script>
    $(function () {
        // Load clients who have events in period of time
        function initializeCalendar() {
            var calendarEl = document.getElementById('calendar');
            var checkbox = document.getElementById('drop-remove');

            // Create a new FullCalendar instance
            var calendar = new FullCalendar.Calendar(calendarEl, {
                initialView: '${calendarDefaultView}',
                headerToolbar: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'dayGridMonth,dayGridWeek,timeGridWeek,listWeek,timeGridDay,multiMonthYear' // Custom button
                },
                views: {
                    multiMonthYear: {
                        type: 'dayGrid',  // Using dayGrid for multi-month/year view
                        duration: {months: 12}, // Duration of 12 months
                        buttonText: 'Year' // Button text
                    },
                    multiMonth: {
                        type: 'dayGrid',
                        duration: {months: 3}, // Example of 3 months duration
                        buttonText: '3 Months'
                    }
                },
                nowIndicator: true,
                themeSystem: 'bootstrap',
                events: "${frontPublicPath}/web/app/calendar/event",
                firstDay: 1,
                editable: true,
                droppable: true,  // Allows events to be dropped onto the calendar
                drop: function (info) {
                    if (checkbox.checked) {
                        info.draggedEl.parentNode.removeChild(info.draggedEl);
                    }
                }
            });

            // Render the calendar
            calendar.render();
        }

        /* initialize the external events
         -----------------------------------------------------------------*/
        function ini_events(ele) {
            ele.each(function () {

                // create an Event Object (https://fullcalendar.io/docs/event-object)
                // it doesn't need to have a start or end
                var eventObject = {
                    title: $.trim($(this).text()) // use the element's text as the event title
                }

                // store the Event Object in the DOM element so we can get to it later
                $(this).data('eventObject', eventObject)

                // make the event draggable using jQuery UI
                $(this).draggable({
                    zIndex: 1070,
                    revert: true, // will cause the event to go back to its
                    revertDuration: 0  //  original position after the drag
                })

            })
        }

        ini_events($('#external-events div.external-event'))

        /* initialize the calendar
         -----------------------------------------------------------------*/
        //Date for the calendar events (dummy data)
        var date = new Date()
        var d = date.getDate(),
            m = date.getMonth(),
            y = date.getFullYear()

        var Calendar = FullCalendar.Calendar;
        var Draggable = FullCalendar.Draggable;

        var containerEl = document.getElementById('external-events');
        var checkbox = document.getElementById('drop-remove');
        var calendarEl = document.getElementById('calendar');

        // initialize the external events
        // -----------------------------------------------------------------

        new Draggable(containerEl, {
            itemSelector: '.external-event',
            eventData: function (eventEl) {
                return {
                    title: eventEl.innerText,
                    backgroundColor: window.getComputedStyle(eventEl, null).getPropertyValue('background-color'),
                    borderColor: window.getComputedStyle(eventEl, null).getPropertyValue('background-color'),
                    textColor: window.getComputedStyle(eventEl, null).getPropertyValue('color'),
                };
            }
        });

        var calendar = new Calendar(calendarEl, {
            initialView: '${calendarDefaultView}',
            headerToolbar: {
                left: 'prev,next today',
                center: 'title',
                right: 'dayGridMonth,dayGridWeek,timeGridWeek,listWeek,timeGridDay'
            },
            nowIndicator: true,
            themeSystem: 'bootstrap',
            events: "${frontPublicPath}/web/app/calendar/event",
            firstDay: 1,
            editable: true,
            droppable: true,
            eventContent: function(arg) {
                    let domNodes = [];

                    let contentEl = document.createElement('div');
                    contentEl.style.position = 'relative';
                    contentEl.style.width = '100%';
                    contentEl.style.height = '100%';
                    contentEl.style.overflow = 'hidden';
                    contentEl.style.display = 'flex';
                    contentEl.style.flexDirection = 'column';
                    contentEl.style.padding = '2px';
                    contentEl.style.boxSizing = 'border-box';

                    if (arg.timeText) {
                        let timeEl = document.createElement('div');
                        timeEl.innerText = arg.timeText;
                        timeEl.style.fontSize = '0.85em';
                        timeEl.style.whiteSpace = 'nowrap';
                        timeEl.style.overflow = 'hidden';
                        timeEl.style.flexShrink = '0';
                        contentEl.appendChild(timeEl);
                    }

                    let titleEl = document.createElement('div');
                    titleEl.innerText = arg.event.title;
                    titleEl.style.fontSize = '0.85em';
                    titleEl.style.fontWeight = 'bold';

                    titleEl.style.whiteSpace = 'normal';
                    titleEl.style.wordBreak = 'break-word';
                    titleEl.style.lineHeight = '1.1';
                    titleEl.style.marginTop = '1px';
                    titleEl.style.flexGrow = '1';
                    titleEl.style.minHeight = '0';
                    titleEl.style.overflow = 'hidden';

                    contentEl.appendChild(titleEl);

                    let meetUrl = arg.event.extendedProps.onlineMeetURL;
                    if (meetUrl) {
                        let iconLink = document.createElement('a');
                        iconLink.href = meetUrl;
                        iconLink.target = '_blank';
                        iconLink.style.color = '#fff';

                        iconLink.style.opacity = '0.6';
                        iconLink.style.position = 'absolute';
                        iconLink.style.bottom = '1px';
                        iconLink.style.right = '1px';

                        iconLink.style.zIndex = '50';
                        iconLink.style.textDecoration = 'none';
                        iconLink.style.lineHeight = '1';

                         iconLink.style.backgroundColor = 'rgba(0,0,0,0.3)';
                         iconLink.style.borderRadius = '3px';
                         iconLink.style.padding = '1px 2px';

                        iconLink.innerHTML = '<i class="fas fa-video"></i>';

                        iconLink.addEventListener('click', function(e) { e.stopPropagation(); });
                        iconLink.addEventListener('mousedown', function(e) { e.stopPropagation(); });

                        contentEl.appendChild(iconLink);
                    }

                    domNodes.push(contentEl);
                    return { domNodes: domNodes };
                },
            drop: function (info) {
                if (checkbox.checked) {
                    info.draggedEl.parentNode.removeChild(info.draggedEl);
                }
            }
        });
        calendar.render();
        // $('#calendar').fullCalendar()

        $(function () {
            let isClientsLoaded = false;
            let currentStartDate;
            let currentEndDate;

            // Load clients dynamically based on the selected date range
            function loadClients(startDate, endDate) {
                let formattedStartDate = moment(startDate).startOf('day').format('YYYY-MM-DDTHH:mm:ss.SSS');
                let formattedEndDate = moment(endDate).startOf('day').format('YYYY-MM-DDTHH:mm:ss.SSS');
                $.ajax({
                    url: "${frontPublicPath}/web/app/calendar/event/clients?start=" + formattedStartDate + "&end=" + formattedEndDate,
                    type: 'GET',
                    success: function (clients) {
                        const clientDropdown = $('#clientDropdown');
                        clientDropdown.find('option').not(':first').remove();
                        clients.forEach(client => {
                            const option = $('<option>').val(client.id).text(client.name);
                            clientDropdown.append(option);
                        });
                        isClientsLoaded = true;
                    }
                });
            }

            calendar.on('datesSet', function () {
                $('#clientDropdown').val('');
                if (isClientsLoaded) {
                    isClientsLoaded = false;
                }
            });

            $('#clientDropdown').on('focus click', function () {
                if (!isClientsLoaded) {
                    currentStartDate = new Date(calendar.view.currentStart);
                    currentEndDate = new Date(calendar.view.currentEnd);
                    loadClients(currentStartDate, currentEndDate);
                }
            });

            // Functionality of the 'Generate ICS' button
            $('#generateIcs').click(function () {
                const clientId = $('#clientDropdown').val();
                const clientName = $('#clientDropdown option:selected').text();
                let formattedStartDate = moment(currentStartDate).startOf('day').format('YYYY-MM-DDTHH:mm:ss.SSS');
                let formattedEndDate = moment(currentEndDate).startOf('day').format('YYYY-MM-DDTHH:mm:ss.SSS');

                if (!clientId) {
                    alert("Please select a client");
                    return;
                }

                $.ajax({
                    url: `${frontPublicPath}/web/app/calendar/ics`,
                    type: 'GET',
                    data: {
                        start: formattedStartDate,
                        end: formattedEndDate,
                        clientId: clientId,
                        clientName: clientName
                    },
                    xhrFields: {
                        responseType: 'blob'
                    },
                    success: function (response, status, xhr) {
                        const fileName = xhr.getResponseHeader('Content-Disposition').split('filename=')[1].replace(/"/g, '');
                        const blob = new Blob([response], {type: 'text/calendar'});
                        const link = document.createElement('a');
                        link.href = window.URL.createObjectURL(blob);
                        link.download = fileName;
                        document.body.appendChild(link);
                        link.click();
                        document.body.removeChild(link);
                    }
                });
            });


        });

        $(document).ready(function () {
            $('#copyTemplate').click(function () {
                let currentStartDate = new Date(calendar.view.currentStart);
                let currentEndDate = new Date(calendar.view.currentEnd);
                let formattedStartDate = moment(currentStartDate).startOf('day').format('YYYY-MM-DDTHH:mm:ss.SSS');
                let formattedEndDate = moment(currentEndDate).startOf('day').format('YYYY-MM-DDTHH:mm:ss.SSS');

                $.ajax({
                    url: `${frontPublicPath}/web/app/calendar/copy-template`,
                    type: 'GET',
                    data: {
                        start: formattedStartDate,
                        end: formattedEndDate
                    },
                    // success: function (response) {
                    //     navigator.clipboard.writeText(response).then(function () {
                    //         showMessage("Template copied to clipboard!");
                    //     }).catch(function () {
                    //         showMessage("Failed to copy to clipboard.");
                    //     });
                    // },
                    success: function (response) {
                        if (navigator.clipboard && navigator.clipboard.writeText) {
                            navigator.clipboard.writeText(response).then(function () {
                                showMessage("Template copied to clipboard!");
                            }).catch(function () {
                                fallbackCopy(response);
                            });
                        } else {
                            fallbackCopy(response);
                        }
                    },
                    error: function () {
                        showMessage("Error fetching template.");
                    }
                });
            });

            let messageTimeout;

            function showMessage(message) {
                let messageDiv = document.getElementById('copyTemplateMessage');

                messageDiv.className = 'alert alert-primary fade show';
                messageDiv.textContent = message;

                messageDiv.style.position = 'fixed';
                messageDiv.style.top = '15px';
                messageDiv.style.left = '50%';
                messageDiv.style.transform = 'translateX(-50%)';
                messageDiv.style.margin = '1rem';
                messageDiv.style.padding = '1rem';
                messageDiv.style.zIndex = '1050';
                messageDiv.style.width = 'auto';
                messageDiv.style.maxWidth = '80%';

                messageDiv.style.opacity = '1';
                messageDiv.style.display = 'block';

                clearTimeout(messageTimeout);

                messageTimeout = setTimeout(() => {
                    messageDiv.style.opacity = '0';
                    setTimeout(() => {
                        messageDiv.style.display = 'none';
                    }, 500);
                }, 1500);
            }

            function fallbackCopy(text) {
                let textArea = document.createElement("textarea");
                textArea.value = text;
                document.body.appendChild(textArea);
                textArea.select();
                try {
                    document.execCommand('copy');
                    showMessage("Template copied to clipboard!");
                } catch (err) {
                    showMessage("Failed to copy to clipboard.");
                }
                document.body.removeChild(textArea);
            }
        });

        /* ADDING EVENTS */
        var currColor = '#3c8dbc' //Red by default
        // Color chooser button
        $('#color-chooser > li > a').click(function (e) {
            e.preventDefault()
            // Save color
            currColor = $(this).css('color')
            // Add color effect to button
            $('#add-new-event').css({
                'background-color': currColor,
                'border-color': currColor
            })
        })
        $('#add-new-event').click(function (e) {
            e.preventDefault()
            // Get value and make sure it is not null
            var val = $('#new-event').val()
            if (val.length == 0) {
                return
            }

            // Create events
            var event = $('<div />')
            event.css({
                'background-color': currColor,
                'border-color': currColor,
                'color': '#fff'
            }).addClass('external-event')
            event.text(val)
            $('#external-events').prepend(event)

            // Add draggable funtionality
            ini_events(event)

            // Remove event from text input
            $('#new-event').val('')
        })
    })
</script>
</body>
</html>