package com.getir.ReadingIsGood.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import com.getir.ReadingIsGood.model.contracts.createneworder.CreateNewOrderRequest;
import com.getir.ReadingIsGood.model.contracts.listordersbydateinterval.ListOrdersByDateIntervalRequest;
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


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.Mockito.mock;

@TestInstance(PER_CLASS)
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    OrderService orderService;

    @Mock
    BookService bookService;

    @BeforeEach
    void setUp() {
        bookRepository = mock(BookRepository.class);
        orderRepository = mock(OrderRepository.class);
        orderService = new OrderService(orderRepository,bookService);
        bookService =mock(BookService.class);
    }

    @Test
    void createNewOrder() {
    }

    @Test
    void getOrderByIdSuccessfully() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(ReadingIsGoodTestDOFactory.generateOrder()));
        var response = orderService.getOrderById(1);
        assertNotNull(response);
        assertEquals(true, response.isSuccess());
    }

    @Test
    void getOrderByIdUnsuccessfully() {
        var response = orderService.getOrderById(1);
        assertNotNull(response);
        assertEquals(false, response.isSuccess());
    }

    @Test
    void listOrdersByDateIntervalSuccessfully() {
        var request = new ListOrdersByDateIntervalRequest();
        request.setEndDate(LocalDate.of(2021, 5, 8));
        request.setEndDate(LocalDate.of(2020, 10, 8));
        List orderlist=new ArrayList();
        orderlist.add(ReadingIsGoodTestDOFactory.generateOrder());
        when(orderRepository.findOrdersByDateInterval(request.getStartDate(),request.getEndDate())).thenReturn(Optional.of(orderlist));
        var response =orderService.listOrdersByDateInterval(request);
        assertNotNull(response);
        assertEquals(true, response.isSuccess());

    }
    @Test
    void listOrdersByDateIntervalUnsuccessfully() {
        var request = new ListOrdersByDateIntervalRequest();
        request.setEndDate(LocalDate.of(2021, 5, 8));
        request.setEndDate(LocalDate.of(2020, 10, 8));
        var response =orderService.listOrdersByDateInterval(request);
        assertNotNull(response);
        assertEquals(false, response.isSuccess());

    }

    @Test
    void createNewOrderUnsuccessfully(){
        var request = new CreateNewOrderRequest();
        request.setBookCount(2L);
        request.setDate(LocalDate.now());
        request.setCustomerId(10L);
        request.setBookId(1);
        var response =orderService.createNewOrder(request);
        assertNotNull(response);
        assertEquals(false, response.isSuccess());



    }
    @Test
    void createNewOrderSuccessfully(){

    }


}