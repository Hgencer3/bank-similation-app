package com.cydeo.dto;

import com.cydeo.enums.AccountStatus;
import com.cydeo.enums.AccountType;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor

@Setter
@Getter
public class AccountDTO {

    private Long id;

    @NotNull
    @Positive
    private BigDecimal balance;
    @NotNull
    private AccountType accountType;
    private Date creationDate;
    @NotNull
    private Long userId;
    private AccountStatus accountStatus;

}
