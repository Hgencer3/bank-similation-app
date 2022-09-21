package com.cydeo;

import com.cydeo.enums.AccountType;
import com.cydeo.model.Account;
import com.cydeo.service.AccountService;
import com.cydeo.service.TransactionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
public class BankSimulationAppApplication {

    public static void main(String[] args) {
      ApplicationContext container= SpringApplication.run(BankSimulationAppApplication.class, args);

        AccountService accountService=container.getBean(AccountService.class);
        TransactionService transactionService= container.getBean(TransactionService.class);

        Account sender=accountService.createNewAccount(BigDecimal.valueOf(70),new Date(), AccountType.CHECKING,1L);
        Account receiver=accountService.createNewAccount(BigDecimal.valueOf(50),new Date(), AccountType.SAVING,1L);
        Account receiver2=null;
        Account receiver3=accountService.createNewAccount(BigDecimal.valueOf(50),new Date(), AccountType.SAVING,2L);
        Account receiver4=accountService.createNewAccount(BigDecimal.valueOf(50),new Date(), AccountType.CHECKING,2L);
        accountService.listAllAccount().forEach(System.out::println);
        transactionService.makeTransfer(sender,receiver4,new BigDecimal(40),new Date(),"Transaction !");
        System.out.println(transactionService.findAllTransaction().get(0));
        accountService.listAllAccount().forEach(System.out::println);
    }

}