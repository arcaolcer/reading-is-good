package com.getir.ReadingIsGood.service;

import com.getir.ReadingIsGood.repository.BookRepository;
import com.getir.ReadingIsGood.repository.OrderRepository;
import com.getir.ReadingIsGood.testbase.ReadingIsGoodTestDOFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.Mockito.when;

import static org.mockito.Mockito.mock;
@TestInstance(PER_CLASS)
@ExtendWith(MockitoExtension.class)
class StatisticsServiceTest {


    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    StatisticsService statisticsService;

    @BeforeEach
    void setUp() {
        orderRepository = mock(OrderRepository.class);
        statisticsService =new StatisticsService(orderRepository);
    }

    @Test
    void monthlyOrderStatisticsSuccessfully() {
        when(orderRepository.getMonthlyOrderStatistics(10L,2021)).thenReturn(java.util.Optional.of(ReadingIsGoodTestDOFactory.generateMonthlyOrderStatisticsDtoList()));
        var response =statisticsService.monthlyOrderStatistics(2021,10L);
        assertNotNull(response);
        assertEquals(true, response.isSuccess());
    }

    @Test
    void monthlyOrderStatisticsUnsuccessfully() {
        var response =statisticsService.monthlyOrderStatistics(2021,10L);
        assertNotNull(response);
        assertEquals(false, response.isSuccess());
    }
}