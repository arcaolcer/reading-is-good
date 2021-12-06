package com.getir.ReadingIsGood.service;


import com.getir.ReadingIsGood.model.contracts.baseapi.BaseApiResponse;
import com.getir.ReadingIsGood.model.contracts.createneworder.CreateNewOrderRequest;
import com.getir.ReadingIsGood.model.contracts.getorderbyid.GetOrderByIdResponse;
import com.getir.ReadingIsGood.model.contracts.listordersbydateinterval.ListOrdersByDateIntervalRequest;
import com.getir.ReadingIsGood.model.contracts.listordersbydateinterval.ListOrdersByDateIntervalResponse;
import com.getir.ReadingIsGood.model.dto.OrderDto;
import com.getir.ReadingIsGood.model.dto.enumarated.OrderStatus;
import com.getir.ReadingIsGood.model.jpa.Orders;
import com.getir.ReadingIsGood.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static com.getir.ReadingIsGood.model.constants.ExceptionConstants.*;

@Service
@Slf4j
public class OrderService {


    private final OrderRepository orderRepository;
    private final BookService bookService;

    @Autowired
    public OrderService(OrderRepository orderRepository, BookService bookService){
        this.orderRepository=orderRepository;
        this.bookService = bookService;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public BaseApiResponse createNewOrder(CreateNewOrderRequest request) {
        var purchasedAmount = bookService.calculateOrderAmount(request.getBookCount(),request.getBookId());
        if(purchasedAmount == null){
            return generateResponse(false,UNSUCCESSFUL_ORDER_MESSAGE,UNSUCCESSFUL_ORDER_MESSAGE_CODE);
        }
        Orders orders = Orders.builder()
                .purchasedAmount(purchasedAmount)
                .customerId(request.getCustomerId())
                .bookCount(request.getBookCount())
                .bookId(request.getBookId())
                .shipping(request.getShipping())
                .date(request.getDate())
                .status(OrderStatus.CREATE)
                .build();
        orderRepository.saveAndFlush(orders);

        try {
            bookService.updateBooksStock(request.getBookCount(),request.getBookId(), true);
        } catch (Exception e) {
            log.error("save() : " + e.getLocalizedMessage());
            log.info(UNSUCCESSFUL_ORDER_MESSAGE);
            orders.setStatus(OrderStatus.ERROR);
            orderRepository.saveAndFlush(orders);
            return generateResponse(false,UNSUCCESSFUL_ORDER_MESSAGE,UNSUCCESSFUL_ORDER_MESSAGE_CODE);
        }
        log.info(SUCCESSFUL_ORDER_MESSAGE);
        orders.setStatus(OrderStatus.SUCCESS);
        orderRepository.saveAndFlush(orders);
        return generateResponse(true, SUCCESSFUL_ORDER_MESSAGE,SUCCESSFUL_ORDER_MESSAGE_CODE);
    }

    public GetOrderByIdResponse getOrderById(long id){
        var response = new GetOrderByIdResponse();
        var order = orderRepository.findById(id).orElse(null);
        if(order != null) {
            OrderDto orderDto = OrderDto.builder()
                    .customerId(order.getCustomerId())
                    .bookCount(order.getBookCount())
                    .shipping(order.getShipping())
                    .bookId(order.getBookId())
                    .status(order.getStatus())
                    .date(order.getDate())
                    .build();

            response.setOrder(orderDto);
            response.setSuccess(true);
            return response;
        }else{
            response.setSuccess(false);
            response.setMessage(ORDER_NOT_FOUND);
            response.setCode(ORDER_NOT_FOUND_CODE);
            return response;
        }
    }

    public ListOrdersByDateIntervalResponse listOrdersByDateInterval(ListOrdersByDateIntervalRequest request){
        var response = new ListOrdersByDateIntervalResponse();

        var  orderDto = orderRepository.findOrdersByDateInterval(request.getStartDate(),request.getEndDate()).orElse(null);
        if(orderDto != null) {
            response.setOrderDtoList(orderDto);
            response.setSuccess(true);
        }else{
            response.setSuccess(false);
            response.setMessage(ORDER_NOT_FOUND);
            response.setCode(ORDER_NOT_FOUND_CODE);
        }
        return response;
    }

        private BaseApiResponse generateResponse (boolean success, String message, int code){
            var response= new BaseApiResponse();
            response.setSuccess(success);
            response.setMessage(message);
            response.setCode(code);
            return response;
        }
}
