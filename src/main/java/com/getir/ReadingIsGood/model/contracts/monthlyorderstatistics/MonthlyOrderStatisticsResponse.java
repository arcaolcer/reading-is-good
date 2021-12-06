package com.getir.ReadingIsGood.model.contracts.monthlyorderstatistics;

import com.getir.ReadingIsGood.model.contracts.baseapi.BaseApiResponse;
import com.getir.ReadingIsGood.model.dto.MonthlyOrderStatisticsDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MonthlyOrderStatisticsResponse extends BaseApiResponse {

    public List<MonthlyOrderStatisticsDto> monthlyOrderStatisticsDto;
}
