package com.cydeo.service.impl;

import com.cydeo.enums.AccountType;
import com.cydeo.exception.AccountOwnershipException;
import com.cydeo.exception.BadRequestException;
import com.cydeo.exception.BalanceNotSufficientException;
import com.cydeo.model.Account;
import com.cydeo.model.Transaction;
import com.cydeo.repository.AccountRepository;
import com.cydeo.service.AccountService;
import com.cydeo.service.TransactionService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Component
public class TransactionServiceImpl implements TransactionService {
    AccountRepository accountRepository;

    public TransactionServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Transaction makeTransfer(Account sender, Account receiver, BigDecimal amount, Date creationDate, String message) {

        validateAccount(sender,receiver);
        checkAccountOwnership(sender,receiver);
        executeBalanceAndUpdateIfRequired(amount,sender,receiver);

        return null;
    }

    private void executeBalanceAndUpdateIfRequired(BigDecimal amount, Account sender, Account receiver) {

        if (checkSenderBalance(sender,amount)){
            sender.setBalance(sender.getBalance().subtract(amount));
            receiver.setBalance(receiver.getBalance().add(amount));
        }else {
           throw new BalanceNotSufficientException("Not enough balance");
        }

    }

    private boolean checkSenderBalance(Account sender, BigDecimal amount) {

       return sender.getBalance().subtract(amount).compareTo(BigDecimal.ZERO)>=0;

    }

    private void checkAccountOwnership(Account sender, Account receiver) {

        /*
         write an if statement that checks if one of the account is saving,
         and user if of sender or receiver is not the same, throw AccountOwnershipException
         */

        if((sender.getAccountType().equals(AccountType.SAVING)
                ||receiver.getAccountType().equals(AccountType.SAVING))&& !sender.getUserId().equals(receiver.getUserId()))
        {
            throw new AccountOwnershipException("One of the accounts is Savings. Transactions between savings and checking " +
                    "account are allowed between same user accounts only. Account Id's dont match");
        }
    }


    private void validateAccount(Account sender, Account receiver){



        if(sender==null || receiver==null){
            throw new BadRequestException("Sender or Receiver cannot be null");
        }

        if (sender.getId().equals(receiver.getId())){
            throw new BadRequestException("Sender account need to be different than receiver");

        }
        findAccountById(sender.getId());
        findAccountById(receiver.getId());

    }
    private Account findAccountById(UUID id) {
     return    accountRepository.findById(id);
    }

    @Override
    public List<Transaction> findAllTransaction() {
        return null;
    }
}