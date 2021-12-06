package com.getir.ReadingIsGood.repository;

import com.getir.ReadingIsGood.model.jpa.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE,connection = EmbeddedDatabaseConnection.H2)
public class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void shouldSaveEntity(){
        var customer = new Customer();
        customer.setFistName("Mehmet");
        customer.setSecondName("Ali");
        customer.setSurname("Ozdogan");
        customer.setId(1L);
        customer.setPassword("getir2015!");
        customer.setAddress("Maden Mah. Sarıyer / İstanbul");
        customer.setUsername("mozdogan");
        customer.setEmail("mehmetaliozdogan@gmail.com");
        customerRepository.save(customer);
        assertThat(customerRepository.findAll()).isNotNull();
    }
}




