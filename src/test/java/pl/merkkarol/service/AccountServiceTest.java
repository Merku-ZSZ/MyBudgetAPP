package pl.merkkarol.service;

import org.apache.catalina.LifecycleState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import pl.merkkarol.model.*;

import javax.validation.constraints.Digits;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
@ActiveProfiles("integration")
class AccountServiceTest {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CategoriesOfExpenseRepository categoriesRepository;
    @Autowired
    private PlannerRepository plannerRepository;

    @Test
    void addExpenseOperation_but_account_is_empty(){
       //given
        CategoriesService categoriesService = new CategoriesService(categoriesRepository);
        PlannerService plannerService = new PlannerService(plannerRepository,categoriesService);
        CategoriesOfExpense category = new CategoriesOfExpense("JEDZENIE");
        Expense expense = new Expense();
        Planner planner = new Planner(category, 500);
        expense.setValue(500);
        expense.setCategory(category);
        expense.setPlanner(plannerService.savePlanner(planner));
        //when
        List<Account> accountList = accountRepository.findAll();
        for(int i = 0; i < accountList.size(); i++){
            accountRepository.deleteById(accountList.get(i).getId());
        }
        AccountService accountService = new AccountService(accountRepository);
        boolean before = accountRepository.findAll().isEmpty();
        accountService.addExpenseOperation(expense);
        boolean after = accountRepository.findAll().size() == 1;
        //then
        assertThat(before).isTrue();
        assertThat(after).isTrue();
    }
    @Test
    void addExceptionOperation_to_account(){
        //given
        CategoriesService categoriesService = new CategoriesService(categoriesRepository);
        PlannerService plannerService = new PlannerService(plannerRepository,categoriesService);
        CategoriesOfExpense category = new CategoriesOfExpense("JEDZENIE");
        Expense expense = new Expense();
        Planner planner = new Planner(category, 500);
        expense.setValue(500);
        expense.setCategory(category);
        expense.setPlanner(plannerService.savePlanner(planner));
        AccountService accountService = new AccountService(accountRepository);
        //when
        Account account = new Account(500,500);
        accountRepository.save(account);
        int before = accountRepository.findAll().size();
        accountService.addExpenseOperation(expense);
        int after = accountRepository.findAll().size();
        //then
        assertThat(before == 1 || after == 2);
        assertThat((accountRepository.findById(accountRepository.findIdOfLastRecord())).getOperations()).isEqualTo(-500);
    }
    @Test
    void addIncomeOperation_to_account(){
        //given
        Income income = new Income(2000, "Pensja");
        AccountService accountService = new AccountService(accountRepository);
        //when
        Account account = new Account(500,500);
        accountRepository.save(account);
        int before = accountRepository.findAll().size();
        accountService.addIncomeOperation(income);
        int after = accountRepository.findAll().size();
        //then
        assertThat(before == 1 || after == 2);
        assertThat((accountRepository.findById(accountRepository.findIdOfLastRecord())).getOperations()).isEqualTo(2000);
    }
    @Test
    void addIncomeOperation_but_account_is_empty(){
        //given
        Income income = new Income(2000, "Pensja");
        List<Account> accountList = accountRepository.findAll();
        for(int i = 0; i < accountList.size(); i++){
            accountRepository.deleteById(accountList.get(i).getId());
        }
        //when
        AccountService accountService = new AccountService(accountRepository);
        boolean before = accountRepository.findAll().isEmpty();
        accountService.addIncomeOperation(income);
        boolean after = accountRepository.findAll().size() == 1;
        //then
        assertThat(before).isTrue();
        assertThat(after).isTrue();
    }
}