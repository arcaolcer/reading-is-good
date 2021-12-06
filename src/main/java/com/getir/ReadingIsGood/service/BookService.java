package com.getir.ReadingIsGood.service;

import com.getir.ReadingIsGood.exception.ReadingIsGoodException;
import com.getir.ReadingIsGood.model.contracts.baseapi.BaseApiResponse;
import com.getir.ReadingIsGood.model.contracts.createbook.CreateBookRequest;

import com.getir.ReadingIsGood.model.dto.enumarated.StockStatus;
import com.getir.ReadingIsGood.model.jpa.Book;
import com.getir.ReadingIsGood.repository.BookRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.integration.support.locks.LockRegistry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import static com.getir.ReadingIsGood.model.constants.ExceptionConstants.*;
import static com.getir.ReadingIsGood.model.constants.ApplicationConstant.*;

@Service
@Slf4j
public class BookService {

    private final BookRepository bookRepository;
    private final LockRegistry registry;


    @Autowired
    public BookService(BookRepository bookRepository, LockRegistry registry) {
        this.bookRepository = bookRepository;
        this.registry = registry;
    }

    @Transactional(rollbackFor = Exception.class ,isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public BaseApiResponse createNewBook(CreateBookRequest request) {
        Book book = Book.builder()
                .id(request.getId())
                .category(request.getCategory())
                .author(request.getAuthor())
                .publisher(request.getPublisher())
                .publisher(request.getTitle())
                .stockCount(request.getStockCount())
                .price(request.getPrice())

                .build();
        if (request.getStockCount() != 0)
            book.setStatus(StockStatus.IN_STOCK);
        else
            book.setStatus(StockStatus.OUT_OF_STOCK);

        try {
            log.info("save new book successfully");
            bookRepository.save(book);
        } catch (Exception e) {
            log.error("save() : " + e.getLocalizedMessage());
            return generateResponse(false);
        }
        return generateResponse(true);
    }

    @SneakyThrows
    public BaseApiResponse updateBooksStock(long count, long id, boolean order)  {

        Lock lock = registry.obtain(Long.toString(id));
        boolean acquired = lock.tryLock(LOCK_TIME_FOR_BOOK_UPDATE, TimeUnit.SECONDS);
        if(acquired) {

            try {
                if(order){
                    performDecreaseCount(count,id);
                }else {
                    updateBookCount(count, id);
                }
            } finally {
                lock.unlock();
            }
        }
        return generateResponse(true);
    }

    public BigDecimal calculateOrderAmount(long count, long id){
        var bookPrice = bookRepository.findPriceById(id);
        if(bookPrice != null){
            return bookPrice.multiply(BigDecimal.valueOf(count));
        }else{
            return null;
        }
    }


    private void performDecreaseCount(long count, long id){
        bookRepository.findById(id)
                .ifPresent(book ->{
                    if(book.getStockCount() > count) {
                        book.setStockCount(Math.subtractExact(book.getStockCount(), count));
                        bookRepository.save(book);
                    }else{
                        log.error(INSUFFICIENT_STOCK);
                        throw new ReadingIsGoodException(INSUFFICIENT_STOCK_CODE, INSUFFICIENT_STOCK);
                    }

                });
    }

    public void updateBookCount(long count , long id){
        bookRepository.findById(id)
                .ifPresent(book ->{
                    if(0 <= count) {
                        book.setStockCount(count);
                        try {
                            bookRepository.save(book);
                        } catch (Exception e) {
                            log.error("save() : " + e.getLocalizedMessage());
                        }
                    }else{
                        log.error(UPDATE_BOOK_COUNT_ERROR);
                        throw new ReadingIsGoodException(UPDATE_BOOK_COUNT_ERROR_CODE,UPDATE_BOOK_COUNT_ERROR);
                    }

                });
    }

    private BaseApiResponse generateResponse (boolean success){
        var response= new BaseApiResponse();
        response.setSuccess(success);
        return response;
    }



}
