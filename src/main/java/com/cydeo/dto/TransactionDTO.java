package com.cydeo.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TransactionDTO {

    @NotNull
    private AccountDTO sender;
    @NotNull
    private AccountDTO receiver;
    @NotNull
    @Positive
    private BigDecimal amount;
    @NotNull
    @Size(min = 2, max = 12)
    private String message;
    private Date creationDate;


}
