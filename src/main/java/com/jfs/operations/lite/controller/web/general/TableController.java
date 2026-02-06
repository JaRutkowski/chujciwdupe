package com.jfs.operations.lite.controller.web.general;

import com.jfs.operations.lite.controller.web.utils.RestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/web/app/table")
@RequiredArgsConstructor
public class TableController {
    private final RestClient restClient;
    private final HttpServletRequest httpServletRequest;

    @GetMapping
    public String calendar(Model model) {
        return "table-form";
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAllCalendarEvents(
            @RequestParam(defaultValue = "${app.paging.default-value.page}") int page,
            @RequestParam(defaultValue = "${app.paging.default-value.size}") int size,
            @RequestParam(defaultValue = "${app.paging.default-value.schedule-controller.sort-column}", required = false) String sortColumn,
            @RequestParam(defaultValue = "${app.paging.default-value.schedule-controller.sort-direction}", required = false) String sortDirection,
            @RequestParam(required = false) String searchValue) {  // Add searchValue parameter
        String url = "/api/schedule/table?page=" + page + "&size=" + size;
        if (sortColumn != null && !sortColumn.isEmpty())
            url += "&sortColumn=" + sortColumn + "&sortDirection=" + sortDirection;
        if (searchValue != null && !searchValue.isEmpty())
            url += "&searchValue=" + URLEncoder.encode(searchValue, StandardCharsets.UTF_8);
        Object response = restClient.getSync(url, String.class, httpServletRequest);
        return ResponseEntity.ok(response);
    }
}
