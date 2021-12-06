package com.getir.ReadingIsGood.service;

import com.getir.ReadingIsGood.model.contracts.monthlyorderstatistics.MonthlyOrderStatisticsResponse;
import com.getir.ReadingIsGood.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.getir.ReadingIsGood.model.constants.ExceptionConstants.ORDER_NOT_FOUND;
import static com.getir.ReadingIsGood.model.constants.ExceptionConstants.ORDER_NOT_FOUND_CODE;

@Service
public class StatisticsService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    public StatisticsService(OrderRepository orderRepository){
        this.orderRepository=orderRepository;

    }

    public MonthlyOrderStatisticsResponse monthlyOrderStatistics(int year,long customerNo){
        var response = new MonthlyOrderStatisticsResponse();

        var  dto = orderRepository.getMonthlyOrderStatistics(customerNo,year).orElse(null);
        if(dto != null) {
            response.setMonthlyOrderStatisticsDto(dto);
            response.setSuccess(true);
        }else{
            response.setSuccess(false);
            response.setMessage(ORDER_NOT_FOUND);
            response.setCode(ORDER_NOT_FOUND_CODE);
        }
        return response;
    }

}
