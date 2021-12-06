package com.getir.ReadingIsGood.repository;

import com.getir.ReadingIsGood.model.dto.enumarated.OrderStatus;
import com.getir.ReadingIsGood.model.jpa.Customer;
import com.getir.ReadingIsGood.model.jpa.Orders;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE,connection = EmbeddedDatabaseConnection.H2)
public class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;

    @Test
    void shouldSaveEntity(){
        var order = new Orders();
        order.setDate(LocalDate.now());
        order.setBookId(1);
        order.setStatus(OrderStatus.CREATE);
        order.setBookCount(2);
        order.setPurchasedAmount(BigDecimal.TEN);
        order.setShipping("Maden mah. Sarıyer / İstanbul");
        orderRepository.save(order);
        assertThat(orderRepository.findAll()).isNotNull();
    }
}




