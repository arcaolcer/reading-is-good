package com.getir.ReadingIsGood.model.contracts.listordersbydateinterval;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Getter
@Setter
public class ListOrdersByDateIntervalRequest {
    @DateTimeFormat
    private LocalDate startDate;
    @DateTimeFormat
    private LocalDate endDate;
}
