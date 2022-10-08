package com.cydeo.service;

import com.cydeo.enums.AccountStatus;
import com.cydeo.enums.AccountType;
import com.cydeo.model.Account;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public interface AccountService {

    Account createNewAccount(BigDecimal balance, Date creationDate, AccountType accountType, Long userId,AccountStatus accountStatus);
    List<Account> listAllAccount();

    void deleteAccount(UUID id);

    Account retrieveBuId(UUID id);
}
