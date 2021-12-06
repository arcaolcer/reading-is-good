package com.getir.ReadingIsGood.model.contracts.monthlyorderstatistics;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MonthlyOrderStatisticsRequest {
     @NotNull
     private int year;
     @NotNull
     private long customerId;

}
