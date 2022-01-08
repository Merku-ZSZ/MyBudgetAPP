package pl.merkkarol.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.merkkarol.model.Account;
import pl.merkkarol.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

private static Logger logger = LoggerFactory.getLogger(AccountController.class);
private final AccountService service;

public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping
    ResponseEntity<List<Account>> getAccount(){
    return ResponseEntity.ok(service.getAccount());
    }

}
