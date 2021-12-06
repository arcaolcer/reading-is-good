package com.getir.ReadingIsGood.api.controller;


import com.getir.ReadingIsGood.model.constants.ApiEndpoints;
import com.getir.ReadingIsGood.model.contracts.monthlyorderstatistics.MonthlyOrderStatisticsResponse;
import com.getir.ReadingIsGood.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = ApiEndpoints.STATISTICS_URL)
public class StatisticsController {
    @Autowired
    StatisticsService statisticsService;

    @GetMapping("/montlyorderstatistics")
    @Transactional(rollbackFor = Throwable.class)
    public MonthlyOrderStatisticsResponse listOrdersByDateInterval(@RequestParam("year") int year, @RequestParam("customerId") long customerId) {
        return statisticsService.monthlyOrderStatistics(year,customerId);
    }
}
