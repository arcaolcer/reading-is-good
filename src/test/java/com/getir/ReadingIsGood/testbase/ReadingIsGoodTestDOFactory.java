package com.getir.ReadingIsGood.testbase;

import com.getir.ReadingIsGood.model.contracts.baseapi.BaseApiResponse;
import com.getir.ReadingIsGood.model.contracts.createbook.CreateBookRequest;
import com.getir.ReadingIsGood.model.contracts.createcustomer.CreateCustomerRequest;
import com.getir.ReadingIsGood.model.contracts.createneworder.CreateNewOrderRequest;
import com.getir.ReadingIsGood.model.dto.MonthlyOrderStatisticsDto;
import com.getir.ReadingIsGood.model.dto.enumarated.OrderStatus;
import com.getir.ReadingIsGood.model.dto.enumarated.StockStatus;
import com.getir.ReadingIsGood.model.jpa.Book;
import com.getir.ReadingIsGood.model.jpa.Customer;
import com.getir.ReadingIsGood.model.jpa.Orders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ReadingIsGoodTestDOFactory {

    public static BaseApiResponse generateSuccessResponse(){
        var response= new BaseApiResponse();
        response.setSuccess(true);
        return response;
    }

    public static CreateCustomerRequest generateCreateCustomerRequest(){
        var request = new CreateCustomerRequest();
        request.setFistName("Mehmet");
        request.setSecondName("Ali");
        request.setSurname("Ozdogan");
        request.setPassword("getir2015!");
        request.setAddress("Maden Mah. Sarıyer / İstanbul");
        request.setUsername("mozdogan");
        request.setEmail("mehmetaliozdogan@gmail.com");
        return request;
    }

    public static Customer generateCustomer(){
        var customer = new Customer();
        customer.setFistName("Mehmet");
        customer.setSecondName("Ali");
        customer.setSurname("Ozdogan");
        customer.setId(1L);
        customer.setPassword("getir2015!");
        customer.setAddress("Maden Mah. Sarıyer / İstanbul");
        customer.setUsername("mozdogan");
        customer.setEmail("mehmetaliozdogan@gmail.com");
        return customer;
    }

    public static CreateNewOrderRequest generateCreateNewOrderRequest(){
        var request = new CreateNewOrderRequest();
        request.setCustomerId(1L);
        request.setBookCount(1L);
        request.setShipping("Maden Mah. Sariyer/Istanbul");
        request.setBookId(1L);
        return  request;
    }

    public static CreateBookRequest generateCreateBookRequest(){
        var request= new CreateBookRequest();
        request.setId(1L);
        request.setPublisher("The Russian Messenger");
        request.setTitle("Crime and Punishment");
        request.setAuthor("Fyodor Dostoyevsky");
        request.setCategory("Crime");
        request.setStockCount(20);
        request.setPrice(BigDecimal.valueOf(15.50));
        return request;
    }

    public static Book generateBook(){
        var book = new Book();
        book.setAuthor("Fyodor Dostoyevsky");
        book.setId(1L);
        book.setStatus(StockStatus.IN_STOCK);
        book.setCategory("Crime");
        book.setStockCount(10);
        book.setPrice(BigDecimal.TEN);
        book.setPublisher("The Russian Messenger");
        return book;
    }

    public static Orders generateOrder(){
        var order = new Orders();
        order.setDate(LocalDate.now());
        order.setBookId(1);
        order.setStatus(OrderStatus.CREATE);
        order.setBookCount(2);
        order.setPurchasedAmount(BigDecimal.TEN);
        order.setShipping("Maden mah. Sarıyer / İstanbul");
        return order;
    }

    public static List<MonthlyOrderStatisticsDto> generateMonthlyOrderStatisticsDtoList(){
        var dto= new MonthlyOrderStatisticsDto();
        dto.setMonth("May");
        dto.setTotalOrderCount(110L);
        dto.setTotalBookCount(12);
        dto.setTotalPurchasedAmount(BigDecimal.valueOf(12323.50));
        var list = new ArrayList<MonthlyOrderStatisticsDto>();
        list.add(dto);
        return list;
    }


}
