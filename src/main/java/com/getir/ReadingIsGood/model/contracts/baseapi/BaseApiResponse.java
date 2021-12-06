package com.getir.ReadingIsGood.model.contracts.baseapi;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseApiResponse {
    public boolean success;
    public String message;
    public int code;

}