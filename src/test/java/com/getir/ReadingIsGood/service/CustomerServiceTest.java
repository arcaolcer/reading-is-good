package com.getir.ReadingIsGood.service;

import com.getir.ReadingIsGood.model.contracts.baseapi.BaseApiResponse;
import com.getir.ReadingIsGood.repository.CustomerRepository;
import com.getir.ReadingIsGood.testbase.ReadingIsGoodTestDOFactory;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(PER_CLASS)
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerService customerService;

    @BeforeAll
    public void setup(){
        customerRepository = mock(CustomerRepository.class);
        customerService = new CustomerService(customerRepository);
    }
    @Test
    void shouldCreateNewCustomerSuccessfully() {

        var request = ReadingIsGoodTestDOFactory.generateCreateCustomerRequest();
        BaseApiResponse response = customerService.createNewCustomer(request);
        assertNotNull(response);
        assertEquals(true, response.isSuccess());
    }

    @Test
    void shouldGenerateBaseResponseSuccessfully(){
        var response = ReadingIsGoodTestDOFactory.generateSuccessResponse();
        var successResponse = customerService.generateResponse(true);
        assertEquals(response.isSuccess(), successResponse.isSuccess());
    }


}