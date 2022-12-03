package com.cydeo.service.impl;

import com.cydeo.dto.AccountDTO;
import com.cydeo.entity.Account;
import com.cydeo.enums.AccountStatus;
import com.cydeo.enums.AccountType;
import com.cydeo.mapper.AccountMapper;
import com.cydeo.repository.AccountRepository;
import com.cydeo.service.AccountService;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public void createNewAccount(AccountDTO accountDTO) {
        accountDTO.setAccountStatus(AccountStatus.ACTIVE);
        accountDTO.setCreationDate(new Date());

        accountRepository.save(accountMapper.convertToEntity(accountDTO));
    }

    @Override
    public List<AccountDTO> listAllAccount() {
        List<Account> accountList=accountRepository.findAll();
        return accountList.stream().map(accountMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<AccountDTO> listAllActiveAccount() {
        List<Account> accountList=accountRepository.findAllByAccountStatus(AccountStatus.ACTIVE);
        return accountList.stream().map(accountMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
       Account account=accountRepository.findById(id).get();
       account.setAccountStatus(AccountStatus.DELETED);
       accountRepository.save(account);
    }

    @Override
    public AccountDTO retrieveBuId(Long id) {

        Account account=accountRepository.findById(id).get();
        return accountMapper.convertToDTO(account);
    }

    @Override
    public void updateAccount(AccountDTO accountDTO) {
        accountRepository.save(accountMapper.convertToEntity(accountDTO));
    }
}
