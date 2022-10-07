package com.cydeo.controller;

import com.cydeo.enums.AccountType;
import com.cydeo.model.Account;
import com.cydeo.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.UUID;

@Controller
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /*
        localhost:8080/index
        write a method to return index.html page including account list
        endpoint: index
     */

    @GetMapping("/index")
    public String getIndex(Model model){
        model.addAttribute("accountList",accountService.listAllAccount());

        return "account/index";
    }
    @GetMapping("/create-form")
    public String getCreateForm(Model model){

        model.addAttribute("account", Account.builder().build());
        model.addAttribute("accountTypes", AccountType.values());
        return "account/create-account";
    }

    @PostMapping("/create")
    public String createAccount(@ModelAttribute("account") Account account){

        accountService.createNewAccount(account.getBalance(),new Date(),account.getAccountType(),account.getUserId(),account.getAccountStatus());

        return "redirect:/index";

    }
    @GetMapping("/delete/{id}")
    public String getDeleteAccount(@PathVariable("id")UUID id){

        accountService.deleteAccount(id);

        System.out.println("id = " + id);
        return "redirect:/index";
    }

}
