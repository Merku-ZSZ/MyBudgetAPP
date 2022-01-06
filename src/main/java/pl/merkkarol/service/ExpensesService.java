package pl.merkkarol.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.merkkarol.model.Expense;
import pl.merkkarol.model.ExpenseRepository;

import java.util.List;

@Service
public class ExpensesService {
    private final ExpenseRepository repository;
    private final CategoriesService categoriesService;
    private final AccountService accountService;
    private static final Logger logger = LoggerFactory.getLogger(ExpensesService.class);
    public ExpensesService(ExpenseRepository repository, CategoriesService categoriesService, AccountService accountService) {
        this.repository = repository;
        this.categoriesService = categoriesService;
        this.accountService = accountService;
    }
    public Expense createExpense(Expense expense){
        logger.info("In expense service");
        categoriesService.createCategory(expense.getCategory());
        accountService.addExpenseOperation(expense);
        return repository.save(expense);
    }
    public List<Expense> findAllExpense(){
        return repository.findAll();
    }
}
