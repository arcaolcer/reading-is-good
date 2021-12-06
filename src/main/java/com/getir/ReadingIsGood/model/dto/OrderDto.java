package com.getir.ReadingIsGood.model.dto;

import com.getir.ReadingIsGood.model.dto.enumarated.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class OrderDto {

    @NotNull
    private LocalDate date;
    @NotNull
    private Long customerId;
    @NotNull
    private String shipping;
    @NotNull
    private OrderStatus status;
    @NotNull
    private long bookCount;
    @NotNull
    private long bookId;
    @NotNull
    private BigDecimal purchasedAmount;
}
