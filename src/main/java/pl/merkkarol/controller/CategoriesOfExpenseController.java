package pl.merkkarol.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.merkkarol.model.CategoriesOfExpense;
import pl.merkkarol.model.Expense;
import pl.merkkarol.service.CategoriesService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesOfExpenseController {
    private final CategoriesService service;
    private static Logger logger = LoggerFactory.getLogger(CategoriesOfExpenseController.class);

    public CategoriesOfExpenseController(CategoriesService service) {
        this.service = service;
    }
    @PostMapping
    ResponseEntity<CategoriesOfExpense> createCategory(@RequestBody @Valid CategoriesOfExpense input){
       CategoriesOfExpense category = service.createCategory(input);
       return ResponseEntity.created(URI.create("/" + category.getCategoryName())).body(category);
    }
    @GetMapping
    ResponseEntity<List<CategoriesOfExpense>> readAllCategories(){
        logger.info("Exposing all the categories");
        return ResponseEntity.ok(service.findAll());
    }

}
