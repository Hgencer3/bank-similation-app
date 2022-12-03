package com.cydeo.service.impl;

import com.cydeo.dto.AccountDTO;
import com.cydeo.dto.TransactionDTO;
import com.cydeo.entity.Transaction;
import com.cydeo.enums.AccountType;
import com.cydeo.exception.AccountOwnershipException;
import com.cydeo.exception.BadRequestException;
import com.cydeo.exception.BalanceNotSufficientException;
import com.cydeo.exception.UnderConstructionException;
import com.cydeo.mapper.TransactionMapper;
import com.cydeo.repository.AccountRepository;
import com.cydeo.repository.TransactionRepository;
import com.cydeo.service.AccountService;
import com.cydeo.service.TransactionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class TransactionServiceImpl implements TransactionService {
    @Value("${under_contruction}")
    private boolean underConstruction;
    AccountService accountService;
    TransactionRepository transactionRepository;
    TransactionMapper transactionMapper;
    AccountRepository accountRepository;

    public TransactionServiceImpl(AccountService accountService, TransactionRepository transactionRepository, TransactionMapper transactionMapper, AccountRepository accountRepository) {
        this.accountService = accountService;
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.accountRepository = accountRepository;
    }

    @Override
    public void makeTransfer(AccountDTO sender, AccountDTO receiver, BigDecimal amount, Date creationDate, String message) {

        if (!underConstruction) {
            validateAccount(sender, receiver);
            checkAccountOwnership(sender, receiver);
            executeBalanceAndUpdateIfRequired(amount, sender, receiver);

            TransactionDTO transactionDTO = new TransactionDTO(sender,receiver,amount,message,creationDate);

             transactionRepository.save(transactionMapper.convertToEntity(transactionDTO));
        }else {
            throw new UnderConstructionException("App is under construction, try again later!");
        }
    }

    private void executeBalanceAndUpdateIfRequired(BigDecimal amount, AccountDTO sender, AccountDTO receiver) {

        if (checkSenderBalance(sender,amount)){
            sender.setBalance(sender.getBalance().subtract(amount));
            receiver.setBalance(receiver.getBalance().add(amount));

          AccountDTO senderAccount= accountService.retrieveBuId(sender.getId());
          senderAccount.setBalance(sender.getBalance());
          accountService.updateAccount(senderAccount);

            AccountDTO receiverAccount= accountService.retrieveBuId(receiver.getId());
            receiverAccount.setBalance(receiverAccount.getBalance());
            accountService.updateAccount(receiverAccount);

        }else {
           throw new BalanceNotSufficientException("Not enough balance");
        }
    }

    private boolean checkSenderBalance(AccountDTO sender, BigDecimal amount) {

       return sender.getBalance().subtract(amount).compareTo(BigDecimal.ZERO)>=0;
    }

    private void checkAccountOwnership(AccountDTO sender, AccountDTO receiver) {

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


    private void validateAccount(AccountDTO sender, AccountDTO receiver){

     if(sender==null || receiver==null){
            throw new BadRequestException("Sender or Receiver cannot be null");
        }
        if (sender.getId().equals(receiver.getId())){
            throw new BadRequestException("Sender account need to be different than receiver");
        }
        findAccountById(sender.getId());
        findAccountById(receiver.getId());

    }

    private AccountDTO findAccountById(Long id) {

     return    accountService.retrieveBuId(id);
    }

    @Override
    public List<TransactionDTO> findAllTransaction() {

        return transactionRepository.findAll().stream().map(transactionMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> lastTransactionsList() {

        List<Transaction> transactionList=transactionRepository.findLastTenTransaction();
        return transactionList.stream().map(transactionMapper::convertToDto).collect(Collectors.toList());
        }

    @Override
    public List<TransactionDTO> findTransactionListById(Long id) {
        return transactionRepository.findTransactionListById(id).stream().map(transactionMapper::convertToDto).collect(Collectors.toList());

    }


}
