package com.getir.ReadingIsGood.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyOrderStatisticsDto {
    private String month;
    private long totalOrderCount;
    private long totalBookCount;
    private BigDecimal totalPurchasedAmount;
}
