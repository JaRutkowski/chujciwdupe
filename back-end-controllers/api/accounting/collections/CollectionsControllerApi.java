package com.jfs.operations.lite.controller.api.accounting.collections;

import com.jfs.operations.lite.service.collections.dto.TableEntryCollectionDataDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

@RequestMapping(value = "/api/collections", produces = MediaType.APPLICATION_JSON_VALUE)
//@ApiVersion(1) //TODO: /v1
public interface CollectionsControllerApi {
    @GetMapping("/not-collected-entries-by-months")
    ResponseEntity<List<TableEntryCollectionDataDto>> findAllNotCollectedEntries(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                                                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
                                                                                 @RequestParam String grouping);

    @GetMapping("/not-collected-entries-count")
    ResponseEntity<Long> getCountOfNotCollectedEntries();
}
