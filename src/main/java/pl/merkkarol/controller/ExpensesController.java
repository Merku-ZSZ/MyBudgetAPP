package pl.merkkarol.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.merkkarol.model.Expense;
import pl.merkkarol.service.ExpensesService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpensesController {
    private static Logger logger = LoggerFactory.getLogger(ExpensesController.class);
    private final ExpensesService service;

    public ExpensesController(ExpensesService service){
        this.service = service;
    }

    @PostMapping
    ResponseEntity<Expense> addExpense(@RequestBody @Valid Expense input){
        logger.info("Going into service");
        Expense result = service.createExpense(input);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }
    @GetMapping
    ResponseEntity<List<Expense>> findAll(){
       return ResponseEntity.ok(service.findAllExpense());
    }
    @GetMapping("/{category}")
    ResponseEntity<List<Expense>> getExpenseByCategory(@PathVariable String category){
        String categoryName = category.toUpperCase();
        if(!service.existsByCategoryName(categoryName)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.findByCategoryName(categoryName));
    }
    @DeleteMapping("/{id}")
    ResponseEntity deleteExpenseById(@PathVariable int id){
        if(!service.existsById(id)){
            return  ResponseEntity.notFound().build();
        }
        service.deleteExpense(id);
        return ResponseEntity.ok().build();
    }
}
