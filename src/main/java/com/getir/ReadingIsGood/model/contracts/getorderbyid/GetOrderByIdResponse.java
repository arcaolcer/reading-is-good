package com.getir.ReadingIsGood.model.contracts.getorderbyid;

import com.getir.ReadingIsGood.model.contracts.baseapi.BaseApiResponse;
import com.getir.ReadingIsGood.model.dto.OrderDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetOrderByIdResponse extends BaseApiResponse {

    public OrderDto order;
}
