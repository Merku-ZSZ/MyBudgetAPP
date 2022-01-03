package pl.merkkarol.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import pl.merkkarol.model.AccountRepository;

@RestController("/account")
public class AccountController {

private static Logger logger = LoggerFactory.getLogger(AccountController.class);
private AccountRepository repository;

public AccountController(AccountRepository repository) {
        this.repository = repository;
    }


}
