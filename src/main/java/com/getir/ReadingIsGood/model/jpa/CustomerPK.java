package com.getir.ReadingIsGood.model.jpa;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter

public class CustomerPK implements Serializable {


    private Long id;
    private String email;
    private String username;
}
