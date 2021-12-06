package com.getir.ReadingIsGood.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ReadingIsGoodException extends RuntimeException {
    /*
        private static final long serialVersionUID = 1L;

        public ReadingIsGoodException(String cause){
            super(cause);
        }*/
    private int code;

    public ReadingIsGoodException() {
        super();
    }

    public ReadingIsGoodException(int code) {
        super();
        this.code = code;
    }
    public ReadingIsGoodException(int code, String message){
        super(message);
        this.code=code;
    }

}

