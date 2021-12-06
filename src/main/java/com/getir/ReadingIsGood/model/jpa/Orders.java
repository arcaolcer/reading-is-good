package com.getir.ReadingIsGood.model.jpa;

import com.getir.ReadingIsGood.model.dto.enumarated.OrderStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Orders extends BaseEntity{


    @Id
    @SequenceGenerator(name = "orderSeq",allocationSize = 1)
    @GeneratedValue(
            generator = "orderSeq",strategy = GenerationType.SEQUENCE)
    private Long orderId;
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
