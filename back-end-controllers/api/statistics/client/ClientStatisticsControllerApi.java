package com.jfs.operations.lite.controller.api.statistics.client;

import com.jfs.operations.lite.model.schedule.repository.projection.ClientSumWeeklyTimeAllocation;
import com.jfs.operations.lite.service.client.dto.ChartIncomeTimeLengthStatisticsDto;
import com.jfs.operations.lite.service.client.dto.ChartYearlyNetRevenuePerClientStatisticsDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping(value = "/api/statistics/client", produces = MediaType.APPLICATION_JSON_VALUE)
public interface ClientStatisticsControllerApi {
    // client
    @GetMapping("/chart-yearly-net-revenue-per-client")
    ResponseEntity<ChartYearlyNetRevenuePerClientStatisticsDto> buildChartYearlyNetRevenuePerClientStatistics(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                                                                                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to);

    @GetMapping("/chart-income-time-length-per-client")
    ResponseEntity<ChartIncomeTimeLengthStatisticsDto> buildChartIncomeTimeLengthPerClientStatistics(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                                                                                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to);

    @GetMapping("/client-sum-weekly-time-allocation")
    ResponseEntity<List<ClientSumWeeklyTimeAllocation>> clientSumWeeklyTimeAllocation(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                                                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
                                                                                      @RequestParam Integer clientId);

    @GetMapping("/client-avg-weekly-time-allocation")
    ResponseEntity<Double> clientAvgWeeklyTimeAllocation(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
                                                         @RequestParam Integer clientId);

    @GetMapping("/client-cooperation-time")
    ResponseEntity<Long> clientCooperationLength(@RequestParam Integer clientId);

    @GetMapping("/client-earliest-event-date-from")
    ResponseEntity<LocalDateTime> clientEarliestEventDateFrom();
}
