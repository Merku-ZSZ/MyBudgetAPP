package pl.merkkarol.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.merkkarol.model.Income;
import pl.merkkarol.model.IncomeRepository;
import pl.merkkarol.service.IncomeService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/income")
public class IncomeController {

private final IncomeService incomeService;
private static Logger logger = LoggerFactory.getLogger(IncomeController.class);

    public IncomeController(IncomeService service) {
        this.incomeService = service;
    }

    @PostMapping
    ResponseEntity<Income> bookIncome(@RequestBody @Valid Income toCreate){
        Income result = incomeService.addIncome(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }
    @GetMapping
    ResponseEntity<List<Income>> readAllIncome() {
        logger.info("Read all incomes");
        return ResponseEntity.ok(incomeService.findAllIncome());
    }


}
