package com.getir.ReadingIsGood.model.jpa;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
@IdClass(CustomerPK.class)
public class Customer extends BaseEntity {

    @Id
    @SequenceGenerator(name = "customerSeq",allocationSize = 1)
    @GeneratedValue(
            generator = "customerSeq",strategy = GenerationType.SEQUENCE)
    private Long id;
    @Id
    @NotNull
    @Column(unique = true,length=200)
    private String email;
    @Id
    @NotNull
    @Column(unique = true)
    private String username;
    private String fistName;
    private String secondName;
    private String surname;
    private String password;
    private String address;


}
