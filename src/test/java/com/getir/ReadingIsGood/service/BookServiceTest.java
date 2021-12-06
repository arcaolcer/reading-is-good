package com.getir.ReadingIsGood.service;

import com.getir.ReadingIsGood.model.contracts.baseapi.BaseApiResponse;
import com.getir.ReadingIsGood.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.getir.ReadingIsGood.testbase.ReadingIsGoodTestDOFactory;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.integration.support.locks.LockRegistry;
import java.math.BigDecimal;
import java.util.concurrent.locks.Lock;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

import static org.mockito.Mockito.when;

@TestInstance(PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @Mock
    BookRepository bookRepository;

    @Mock
    LockRegistry registry;

    @InjectMocks
    BookService bookService;

    @Mock
    Lock lock;




    @BeforeEach
    void setUp() {
        bookRepository = mock(BookRepository.class);
        bookService = new BookService(bookRepository,registry);
        registry = mock(LockRegistry.class);
        lock = mock(Lock.class);
    }

    @Test
    void createNewBookSuccessfully() {
        var request = ReadingIsGoodTestDOFactory.generateCreateBookRequest();
        BaseApiResponse response = bookService.createNewBook(request);
        assertNotNull(response);
        assertEquals(true, response.isSuccess());
    }
    @Test
    void createNewBookOutOfStockSuccessfully(){
        var request = ReadingIsGoodTestDOFactory.generateCreateBookRequest();
        request.setStockCount(0);
        BaseApiResponse response = bookService.createNewBook(request);
        assertNotNull(response);
        assertEquals(true, response.isSuccess());
    }
/*
    @SneakyThrows
    @Test
    void updateBooksStockSuccessfully() {

        when(bookRepository.findById(1L)).thenReturn(Optional.of(ReadingIsGoodTestDOFactory.generateBook()));
        var response =bookService.updateBooksStock(1,1,false);
        assertEquals(true, response.isSuccess());
    }
*/
    @Test
    void calculateOrderAmountSuccessfully() {
        when(bookRepository.findPriceById(1)).thenReturn(BigDecimal.TEN);
        BigDecimal amount = bookService.calculateOrderAmount(2,1);
        assertNotNull(amount);
    }
    @Test
    void calculateOrderAmountUnSuccessfully() {
        BigDecimal amount = bookService.calculateOrderAmount(2,1);
        assertNull(amount);
    }


}