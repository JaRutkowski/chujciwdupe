package com.jfs.operations.lite.controller.web.general;

import com.jfs.operations.lite.controller.web.utils.RestClient;
import com.jfs.operations.lite.dto.client.ClientDto;
import com.jfs.operations.lite.dto.schedule.CalendarDto;
import com.jfs.operations.lite.dto.schedule.IcsFileDto;
import com.jfs.operations.lite.dto.utils.Utils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/web/app/calendar")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CalendarController {
    RestClient restClient;
    HttpServletRequest httpServletRequest;

    @GetMapping
    public String calendar(Model model) {
        return "calendar-form";
    }

    @GetMapping("/event")
    public ResponseEntity<List<CalendarDto>> findAllCalendarEvents(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok((List<CalendarDto>) restClient.getSync(
                "/api/schedule/calendar?start=" + start + "&end=" + end, List.class, httpServletRequest));
    }

    @GetMapping("/event/clients")
    public ResponseEntity<List<ClientDto>> findAllClientsWithCalendarEvent(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok((List<ClientDto>) restClient.getSync(
                "/api/schedule/unique-clients-between?start=" + start + "&end=" + end, List.class, httpServletRequest));
    }

    @GetMapping("/ics")
    public ResponseEntity<byte[]> generateIcsFileForClient(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,
            @RequestParam String clientName, @RequestParam Integer clientId) {
        IcsFileDto icsFile = (IcsFileDto) restClient.getSync(
                "/api/schedule/calendar/generate-ics?start=" + start + "&end=" + end + "&clientName=" + Utils.encodeString(clientName)
                        + "&clientId=" + clientId,
                IcsFileDto.class, httpServletRequest);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + Utils.removeSpecialCharacters(icsFile.getFileName()) + "\"")
                .body(icsFile.getFileContent());
    }

    @GetMapping("/copy-template")
    public ResponseEntity<String> copyTemplate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        String copyTemplate = (String) restClient.getSync("/api/schedule/calendar/copy-template?start="
                + start + "&end=" + end, String.class, httpServletRequest);
        return ResponseEntity.ok(copyTemplate);
    }
}
