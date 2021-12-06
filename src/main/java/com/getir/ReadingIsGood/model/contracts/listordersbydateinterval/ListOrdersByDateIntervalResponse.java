package com.getir.ReadingIsGood.model.contracts.listordersbydateinterval;

import com.getir.ReadingIsGood.model.contracts.baseapi.BaseApiResponse;
import com.getir.ReadingIsGood.model.jpa.Orders;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListOrdersByDateIntervalResponse extends BaseApiResponse {
    public List<Orders> orderDtoList;
}
