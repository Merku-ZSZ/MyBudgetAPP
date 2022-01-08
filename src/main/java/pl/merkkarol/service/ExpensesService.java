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
    private final PlannerService plannerService;
    private static final Logger logger = LoggerFactory.getLogger(ExpensesService.class);
    public ExpensesService(ExpenseRepository repository, CategoriesService categoriesService, AccountService accountService, PlannerService plannerService) {
        this.repository = repository;
        this.categoriesService = categoriesService;
        this.accountService = accountService;
        this.plannerService = plannerService;
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
    public List<Expense> findByCategoryName(String name){
        String categoryName = name.toUpperCase();
        if(repository.existsByCategoryCategoryName(categoryName)){
            return repository.findAllByCategoryCategoryName(categoryName);
        }
        else {
            throw new IllegalArgumentException("Category " + categoryName + " is no exists!");
        }
    }
    public void deleteExpense(int id){
      if(repository.existsById(id)){
        repository.deleteById(id);}
      else{
          throw new IllegalArgumentException("Expense with id: " + id + " does not exists!" );
      }
    }
}
