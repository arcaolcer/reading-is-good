package com.getir.ReadingIsGood.repository;

import com.getir.ReadingIsGood.model.jpa.Customer;
import com.getir.ReadingIsGood.model.jpa.CustomerPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, CustomerPK> {

    @Query("select count(c.email)" +
            "from Customer c " +
            "where c.email=:email or c.username=:username ")
    int findCustomerByEmailOrUsername(@Param("email") String email, @Param("username") String username);
}
