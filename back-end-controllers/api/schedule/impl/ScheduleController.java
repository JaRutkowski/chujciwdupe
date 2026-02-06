package com.jfs.operations.lite.controller.api.schedule.impl;

import com.google.gson.Gson;
import com.jfs.operations.lite.controller.api.schedule.ScheduleControllerApi;
import com.jfs.operations.lite.infrastructure.aspect.annotation.AutoLoggable;
import com.jfs.operations.lite.infrastructure.aspect.annotation.AutoMeasuringTime;
import com.jfs.operations.lite.model.schedule.domain.VScheduleEvent;
import com.jfs.operations.lite.model.schedule.repository.VScheduleEventRepository;
import com.jfs.operations.lite.service.schedule.ScheduleService;
import com.jfs.operations.lite.service.schedule.dto.CalendarDto;
import com.jfs.operations.lite.service.schedule.dto.TableDto;
import com.jfs.operations.lite.service.schedule.impl.ScheduleMapper;
import com.jfs.operations.lite.service.utils.Utils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ScheduleController implements ScheduleControllerApi {
    ScheduleService scheduleService;
    ScheduleMapper scheduleMapper;

    @AutoMeasuringTime
    public String findAllHardcodedCalendarEvents() {
        return new Gson().toJson(List.of(
                CalendarDto.builder().id(1).start("2022-02-13").end("2022-02-14").title("All day").build(),
                CalendarDto.builder().id(2).start("2022-02-14").end("2022-02-15").title("Valentines day with Wife - All day").build(),
                CalendarDto.builder().id(3).start("2022-02-14 15:00:00.000").end("2022-02-14 16:00:00.000").title("Meeting with Greg").build(),
                CalendarDto.builder().id(4).start("2022-02-14 15:00:00.000").end("2022-02-14 16:30:00.000").title("Meeting with Xolix").build(),
                CalendarDto.builder().id(5).start("2022-02-14 09:30:00.000").end("2022-02-14 10:30:00.000").title("Meeting with Andy").build(),
                CalendarDto.builder().id(3).start("2022-05-14").end("2022-05-18").title("OOO").build(),
                CalendarDto.builder().id(6).start("2022-05-14 15:00:00.000").end("2022-05-14 16:30:00.000").title("Contract meeting BioTech").build(),
                CalendarDto.builder().id(7).start("2022-05-14 16:00:00.000").end("2022-05-14 19:30:00.000").title("Advisory Go!").build(),
                CalendarDto.builder().id(8).start("2022-05-15 15:00:00.000").end("2022-05-15 19:00:00.000").title("Meeting with Sari").build(),
                CalendarDto.builder().id(9).start("2022-05-16 10:30:00.000").end("2022-05-16 15:00:00.000").title("Meeting with Oleg").build()
        ));
    }

    @Override
    public ResponseEntity findAllCalendarEvents(LocalDate start, LocalDate end) {
        return ResponseEntity.ok(scheduleService.findAllCalendarEvents(start, end));
    }

    @Override
    public ResponseEntity findAllTodoEvents(LocalDate start, LocalDate end) {
        return ResponseEntity.ok(scheduleService.findAllTodoEvents(start, end));
    }

    @Override
    public ResponseEntity findClientsWithEventsByDateRange(LocalDate start, LocalDate end) {
        return ResponseEntity.ok(scheduleService.findClientsWithEventsByDateRange(start, end));
    }

    @Override
    public ResponseEntity generateIcsFileForClient(LocalDate start, LocalDate end, String clientName, Integer clientId) throws IOException {
        return ResponseEntity.ok(scheduleService.generateICalendarForClient(start, end, clientName, clientId));
    }

    @Override
    public ResponseEntity copyTemplate(LocalDate start, LocalDate end) {
        return ResponseEntity.ok(scheduleService.copyTemplate(start, end));
    }

    @Override
    public ResponseEntity findAllTableEvents(int page, int size,String sortColumn, String sortDirection, String searchValue) {
        return ResponseEntity.ok(scheduleService.findAllTableEvents(PageRequest.of(page, size,
                sortDirection.equals("asc") ? Sort.by(sortColumn).ascending() : Sort.by(sortColumn).descending()), searchValue));
    }

    private Object getFieldValue(TableDto dto, String fieldName) {
        try {
            Field field = dto.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(dto);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Invalid sort field: " + fieldName, e);
        }
    }

    @AutoLoggable
    @AutoMeasuringTime
    public ResponseEntity mapFileToSchedule(String path) throws IOException {
        return ResponseEntity.ok(scheduleService.map(path, scheduleMapper.getMapper()).size());
    }

    //TODO: to remove
    private final VScheduleEventRepository vScheduleEventRepository;

    @Override
    public ResponseEntity findAllVScheduleEvent() {
        List<VScheduleEvent> scheduleEvents = vScheduleEventRepository.findAllByDateFromAfterAndDateFromBefore(Utils.toCalendar(LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.MIN)),
                Utils.toCalendar(LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.MAX)));
        return ResponseEntity.ok(scheduleEvents);
    }

    @Override
    public ResponseEntity delete(Integer id) {
        return ResponseEntity.ok(scheduleService.deleteById(id));
    }
    //TODO: to remove
}
