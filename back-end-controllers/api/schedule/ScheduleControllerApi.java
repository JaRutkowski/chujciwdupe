package com.jfs.operations.lite.controller.api.schedule;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;

@RequestMapping(value = "/api/schedule", produces = MediaType.APPLICATION_JSON_VALUE)
//@ApiVersion(1) //TODO: /v1
public interface ScheduleControllerApi {
    @GetMapping("/calendar/hardcoded")
    String findAllHardcodedCalendarEvents();

    @GetMapping("/calendar")
    ResponseEntity findAllCalendarEvents(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate start,
                                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate end);

    @GetMapping("/todo")
    ResponseEntity findAllTodoEvents(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate start,
                                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate end);

    @GetMapping("/unique-clients-between")
    ResponseEntity findClientsWithEventsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate end);

    @GetMapping("/calendar/generate-ics")
    ResponseEntity generateIcsFileForClient(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate end,
            @RequestParam String clientName, @RequestParam Integer clientId) throws IOException;

    @GetMapping("/calendar/copy-template")
    ResponseEntity copyTemplate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate end);

    @GetMapping("/table")
    ResponseEntity findAllTableEvents(@RequestParam(defaultValue = "${app.paging.default-value.page}") int page,
                                      @RequestParam(defaultValue = "${app.paging.default-value.size}") int size,
                                      @RequestParam(defaultValue = "${app.paging.default-value.schedule-controller.sort-column}", required = false) String sortColumn,
                                      @RequestParam(defaultValue = "${app.paging.default-value.schedule-controller.sort-direction}", required = false) String sortDirection,
                                      @RequestParam(required = false) String searchValue);

    @PostMapping("/feed-all")
    ResponseEntity mapFileToSchedule(@RequestParam String path) throws IOException;

    //TODO: Remove
    @GetMapping("/test")
    ResponseEntity findAllVScheduleEvent();

    @DeleteMapping
    ResponseEntity delete(@RequestParam Integer id);
    //TODO: to remove
}
