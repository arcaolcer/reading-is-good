package com.getir.ReadingIsGood.repository;

import com.getir.ReadingIsGood.configuration.JpaConfiguration;
import com.getir.ReadingIsGood.model.dto.enumarated.StockStatus;
import com.getir.ReadingIsGood.model.jpa.Book;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE,connection = EmbeddedDatabaseConnection.H2)
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    void shouldSaveEntity(){
        var book = new Book();
        book.setAuthor("Fyodor Dostoyevsky");
        book.setId(1L);
        book.setStatus(StockStatus.IN_STOCK);
        book.setCategory("Crime");
        book.setStockCount(10);
        book.setPrice(BigDecimal.TEN);
        book.setPublisher("The Russian Messenger");
        bookRepository.save(book);
        assertThat(bookRepository.findAll()).isNotNull();
        assertThat(bookRepository.findPriceById(1L).compareTo(BigDecimal.TEN));

    }
}



