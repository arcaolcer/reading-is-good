package com.getir.ReadingIsGood.repository;

import com.getir.ReadingIsGood.model.dto.MonthlyOrderStatisticsDto;
import com.getir.ReadingIsGood.model.jpa.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository  extends JpaRepository<Orders, Long> {

    @Query("select o " +
            "from Orders o " +
            "where o.date >= :startDate and o.date <= :endDate ")
   Optional <List<Orders>> findOrdersByDateInterval(@Param("startDate") LocalDate startDate , @Param("endDate") LocalDate endDate);

    @Query( "select " +
            "new com.getir.ReadingIsGood.model.dto.MonthlyOrderStatisticsDto(function('to_char', o.date , 'MONTH'),COUNT(o),SUM(o.bookCount),SUM(o.purchasedAmount))  " +
            "from Orders o " +
            "where function('year', o.date) = :year and o.status = 2 and o.customerId=:customerId " +
            "GROUP BY function('to_char', o.date , 'MONTH') ")
    Optional <List<MonthlyOrderStatisticsDto>> getMonthlyOrderStatistics(@Param("customerId") long customerId , @Param("year") int year);
}
