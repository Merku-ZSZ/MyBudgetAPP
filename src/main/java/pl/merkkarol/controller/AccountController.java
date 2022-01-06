package pl.merkkarol.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.merkkarol.model.AccountRepository;

@RestController
@RequestMapping("/account")
public class AccountController {

private static Logger logger = LoggerFactory.getLogger(AccountController.class);
private final AccountRepository repository;

public AccountController(AccountRepository repository) {
        this.repository = repository;
    }


}
