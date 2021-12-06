package com.getir.ReadingIsGood.model.jpa;

import com.getir.ReadingIsGood.model.dto.enumarated.StockStatus;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Table
@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book extends BaseEntity {
    @Id
    @Column(unique = true)
    private Long id;
    private String publisher;
    private String title;
    private String author;
    private String category;
    private long stockCount;
    private BigDecimal price;
    private StockStatus status;
}
