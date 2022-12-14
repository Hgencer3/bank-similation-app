package com.cydeo;

import com.cydeo.entity.Account;
import com.cydeo.enums.AccountStatus;
import com.cydeo.enums.AccountType;
import com.cydeo.service.AccountService;
import com.cydeo.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
public class BankSimulationAppApplication {

    public static void main(String[] args) {
      ApplicationContext container= SpringApplication.run(BankSimulationAppApplication.class, args);

//        AccountService accountService=container.getBean(AccountService.class);
//        TransactionService transactionService= container.getBean(TransactionService.class);
//
//        Account sender=accountService.createNewAccount(BigDecimal.valueOf(70),new Date(), AccountType.CHECKING,1L, AccountStatus.ACTIVE);
//        Account receiver=accountService.createNewAccount(BigDecimal.valueOf(50),new Date(), AccountType.SAVING,1L,AccountStatus.ACTIVE);
//        Account receiver2=null;
//        Account receiver3=accountService.createNewAccount(BigDecimal.valueOf(50),new Date(), AccountType.SAVING,2L,AccountStatus.ACTIVE);
//        Account receiver4=accountService.createNewAccount(BigDecimal.valueOf(50),new Date(), AccountType.CHECKING,2L,AccountStatus.ACTIVE);
//        accountService.listAllAccount().forEach(System.out::println);
//        transactionService.makeTransfer(sender,receiver4,new BigDecimal(40),new Date(),"Transaction !");
//        System.out.println(transactionService.findAllTransaction().get(0));
//        accountService.listAllAccount().forEach(System.out::println);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
