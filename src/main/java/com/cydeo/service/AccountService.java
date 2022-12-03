package com.cydeo.service;

import com.cydeo.dto.AccountDTO;
import com.cydeo.enums.AccountStatus;
import com.cydeo.enums.AccountType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public interface AccountService {

    void createNewAccount(AccountDTO accountDTO);
    List<AccountDTO> listAllAccount();

    List<AccountDTO> listAllActiveAccount();

    void deleteAccount(Long id);

    AccountDTO retrieveBuId(Long id);

    void updateAccount(AccountDTO accountDTO);
}
