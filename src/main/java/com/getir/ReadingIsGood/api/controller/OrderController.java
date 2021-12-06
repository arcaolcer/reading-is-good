package com.getir.ReadingIsGood.api.controller;

import com.getir.ReadingIsGood.model.constants.ApiEndpoints;
import com.getir.ReadingIsGood.model.contracts.baseapi.BaseApiResponse;
import com.getir.ReadingIsGood.model.contracts.createneworder.CreateNewOrderRequest;
import com.getir.ReadingIsGood.model.contracts.getorderbyid.GetOrderByIdResponse;
import com.getir.ReadingIsGood.model.contracts.listordersbydateinterval.ListOrdersByDateIntervalRequest;
import com.getir.ReadingIsGood.model.contracts.listordersbydateinterval.ListOrdersByDateIntervalResponse;
import com.getir.ReadingIsGood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = ApiEndpoints.ORDER_URL)
public class OrderController {


    @Autowired
    OrderService orderService;

    @PostMapping("/create")
    @Transactional(rollbackFor = Throwable.class)
    public BaseApiResponse create(@RequestBody @Validated CreateNewOrderRequest request) {
        return orderService.createNewOrder(request);
    }

    @GetMapping
    @Transactional(rollbackFor = Throwable.class)
    public GetOrderByIdResponse getById(@RequestParam("orderId") long orderId) {
        return orderService.getOrderById(orderId);
    }

    @GetMapping("/listordersbydateinterval")
    @Transactional(rollbackFor = Throwable.class)
    public ListOrdersByDateIntervalResponse listOrdersByDateInterval(@Validated @RequestBody ListOrdersByDateIntervalRequest request) {
        return orderService.listOrdersByDateInterval(request);
    }
}
