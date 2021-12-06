package com.getir.ReadingIsGood.api.controller;


import com.getir.ReadingIsGood.model.constants.ApiEndpoints;
import com.getir.ReadingIsGood.model.contracts.baseapi.BaseApiResponse;
import com.getir.ReadingIsGood.model.contracts.createcustomer.CreateCustomerRequest;

import com.getir.ReadingIsGood.model.jpa.Customer;
import com.getir.ReadingIsGood.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RequestMapping(value = ApiEndpoints.CUSTOMER_URL)
@RestController
public class CustomerController {

    @Autowired
   CustomerService customerService;

    @PostMapping("/create")
    public BaseApiResponse create(@RequestBody @Validated CreateCustomerRequest request) {
        return customerService.createNewCustomer(request);
    }

    @GetMapping("/all")
    @Transactional(rollbackFor = Throwable.class)
    public ResponseEntity<List<Customer>> getAll( @RequestParam(defaultValue = "0") Integer pageNo,
                                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                                  @RequestParam(defaultValue = "username") String sortBy) {
        List<Customer> list = customerService.getAllCustomers(pageNo, pageSize, sortBy);

        return new ResponseEntity<List<Customer>>(list, new HttpHeaders(), HttpStatus.OK);

    }


}
