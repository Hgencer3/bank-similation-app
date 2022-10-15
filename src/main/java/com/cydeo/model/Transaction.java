package com.cydeo.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Builder
@Data
public class Transaction {

    @NotNull
    private UUID sender;
    @NotNull
    private UUID receiver;
    @NotNull
    @Positive
    private BigDecimal amount;
    @NotNull
    @Size(min = 2, max = 12)
    private String message;
    private Date creationDate;


}
