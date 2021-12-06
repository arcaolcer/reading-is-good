package com.getir.ReadingIsGood.model.contracts.createbook;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Getter
@Setter
public class CreateBookRequest {
    @Min(1)
    private Long id;
    @Pattern(regexp = "^[A-Z][a-z]+$")
    private String publisher;
    private String title;
    private String author;
    private String category;
    @Min(0)
    private long stockCount;
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;
}
