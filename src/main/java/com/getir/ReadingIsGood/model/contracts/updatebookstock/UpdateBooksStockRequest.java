package com.getir.ReadingIsGood.model.contracts.updatebookstock;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateBooksStockRequest {

    @Min(0)
    @NotNull
    private long stockCount;
}
