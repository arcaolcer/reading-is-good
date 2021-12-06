package com.getir.ReadingIsGood.model.contracts.createcustomer;


import com.getir.ReadingIsGood.validation.StrongPassword;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class CreateCustomerRequest {
    @Pattern(regexp = "^[A-Z][a-z]+$")
    private String fistName;
    @Pattern(regexp = "^[A-Z][a-z]+$")
    private String secondName;
    @Pattern(regexp = "^[A-Z][a-z]+$")
    private String surname;
    @StrongPassword
    private String password;
    @NotNull
    private String address;
    private String email;
    private String username;
}
