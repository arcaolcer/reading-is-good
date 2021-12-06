package com.getir.ReadingIsGood.model.contracts.createneworder;


import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class CreateNewOrderRequest {

    @DateTimeFormat
    private LocalDate date;
    @NotNull
    @Min(0)
    private Long customerId;
    @NotNull
    private String shipping;
    @NotNull
    private long bookCount;
    @NotNull
    private long bookId;


}
