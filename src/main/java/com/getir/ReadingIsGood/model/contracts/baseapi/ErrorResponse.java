package com.getir.ReadingIsGood.model.contracts.baseapi;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class
ErrorResponse {

    private List<String> errorDetails;

    public ErrorResponse(List<String> errorDetails) {
        this.errorDetails = errorDetails;
    }

}
