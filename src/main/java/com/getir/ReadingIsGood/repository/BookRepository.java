package com.getir.ReadingIsGood.repository;

import com.getir.ReadingIsGood.model.jpa.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select b.price " +
            "from Book b " +
            "where b.id =:id")
    BigDecimal findPriceById(long id);
}
