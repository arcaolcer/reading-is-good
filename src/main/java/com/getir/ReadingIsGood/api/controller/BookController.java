package com.getir.ReadingIsGood.api.controller;


import com.getir.ReadingIsGood.model.constants.ApiEndpoints;
import com.getir.ReadingIsGood.model.contracts.baseapi.BaseApiResponse;
import com.getir.ReadingIsGood.model.contracts.createbook.CreateBookRequest;
import com.getir.ReadingIsGood.model.contracts.updatebookstock.UpdateBooksStockRequest;
import com.getir.ReadingIsGood.service.BookService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = ApiEndpoints.BOOK_URL)
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/create")
    @Transactional(rollbackFor = Throwable.class)
    public BaseApiResponse create( @RequestBody @Validated CreateBookRequest request) {
        return bookService.createNewBook(request);
    }
    @PutMapping("/update/{id}")
    @Transactional(rollbackFor = Throwable.class)
    public BaseApiResponse update(@Validated @RequestBody UpdateBooksStockRequest request, @PathVariable long id) throws InterruptedException {
        return bookService.updateBooksStock(request.getStockCount(),id,false);
    }
}
