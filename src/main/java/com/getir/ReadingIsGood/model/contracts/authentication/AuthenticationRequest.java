package com.getir.ReadingIsGood.model.contracts.authentication;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class AuthenticationRequest implements Serializable {


    private String username;
    private String password;

}