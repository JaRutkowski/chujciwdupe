package com.jfs.operations.lite.controller.api.statistics.client.impl;

import com.jfs.operations.lite.controller.api.statistics.client.ClientStatisticsControllerApi;
import com.jfs.operations.lite.model.schedule.domain.ScheduleEventStatus;
import com.jfs.operations.lite.model.schedule.repository.projection.ClientSumWeeklyTimeAllocation;
import com.jfs.operations.lite.service.client.ClientService;
import com.jfs.operations.lite.service.client.dto.ChartIncomeTimeLengthStatisticsDto;
import com.jfs.operations.lite.service.client.dto.ChartYearlyNetRevenuePerClientStatisticsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClientStatisticsController implements ClientStatisticsControllerApi {
    private final ClientService clientService;

    @Override
    public ResponseEntity<ChartYearlyNetRevenuePerClientStatisticsDto> buildChartYearlyNetRevenuePerClientStatistics(LocalDateTime from, LocalDateTime to) {
        return ResponseEntity.ok(clientService.getChartYearlyNetRevenuePerClientStatistics(from, to, new String[]{ScheduleEventStatus.DONE.getName()}));
    }

    @Override
    public ResponseEntity<ChartIncomeTimeLengthStatisticsDto> buildChartIncomeTimeLengthPerClientStatistics(LocalDateTime from, LocalDateTime to) {
        return ResponseEntity.ok(clientService.getChartIncomeTimeLengthStatisticsDto(from, to, new String[]{ScheduleEventStatus.DONE.getName()}));
    }

    @Override
    public ResponseEntity<List<ClientSumWeeklyTimeAllocation>> clientSumWeeklyTimeAllocation(LocalDateTime from, LocalDateTime to, Integer clientId) {
        return ResponseEntity.ok(clientService.getClientSumWeeklyTimeAllocation(from, to, new String[]{ScheduleEventStatus.DONE.getName()}, clientId));
    }

    @Override
    public ResponseEntity<Double> clientAvgWeeklyTimeAllocation(LocalDateTime from, LocalDateTime to, Integer clientId) {
        return ResponseEntity.ok(clientService.getClientAvgWeeklyTimeAllocation(from, to, new String[]{ScheduleEventStatus.DONE.getName()}, clientId));
    }

    @Override
    public ResponseEntity<Long> clientCooperationLength(@RequestParam Integer clientId) {
        return ResponseEntity.ok(clientService.getClientCooperationLength(clientId));
    }

    @Override
    public ResponseEntity<LocalDateTime> clientEarliestEventDateFrom() {
        return ResponseEntity.ok(clientService.getEarliestEventDateFrom());
    }
}
