package com.getir.ReadingIsGood.service;

import com.getir.ReadingIsGood.exception.ReadingIsGoodException;
import com.getir.ReadingIsGood.model.contracts.baseapi.BaseApiResponse;
import com.getir.ReadingIsGood.model.contracts.createcustomer.CreateCustomerRequest;
import com.getir.ReadingIsGood.model.jpa.Customer;
import com.getir.ReadingIsGood.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.getir.ReadingIsGood.model.constants.ExceptionConstants.*;
@Service
@Slf4j
public class CustomerService {


    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }


    @Transactional(rollbackFor = Exception.class ,isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public BaseApiResponse createNewCustomer(CreateCustomerRequest request){

        if(findCustomerByEmailOrUsername(request.getEmail(),request.getUsername())){
            log.info(DUPLICATE_EMAIL_OR_USERNAME);
            return generateErrorResponse(DUPLICATE_EMAIL_OR_USERNAME,DUPLICATE_EMAIL_OR_USERNAME_CODE);
        }
        Customer customer = Customer.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .fistName(request.getFistName())
                .secondName(request.getSecondName())
                .surname(request.getSurname())
                .address(request.getAddress())
                .password(request.getPassword())
                .build();
        try {
            customerRepository.save(customer);
        } catch (ReadingIsGoodException e) {
            log.error("save() : " + e.getLocalizedMessage());
            return generateResponse(false);
        }
        log.info("Create new customer successfully.");
    return generateResponse(true);
    }
    public boolean findCustomerByEmailOrUsername(String email, String username){
        if(customerRepository.findCustomerByEmailOrUsername(email,username) < 1 ){
            return false;
        }
        return true;
    }

    public BaseApiResponse generateResponse (boolean success){
        var response= new BaseApiResponse();
        response.setSuccess(success);
        return response;
    }
    public BaseApiResponse generateErrorResponse (String message , int code){
        var response= new BaseApiResponse();
        response.setMessage(message);
        response.setCode(code);
        response.setSuccess(false);
        return response;
    }

    public List<Customer> getAllCustomers(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Customer> pagedResult = customerRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Customer>();
        }
    }


}
