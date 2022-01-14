package pl.merkkarol.service;

import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import pl.merkkarol.model.*;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("integration")
class PlannerServiceTest {

    @Autowired
    private CategoriesOfExpenseRepository categoriesRepository;
    @Autowired
    private PlannerRepository plannerRepository;
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Test
    void addPositionToPlanner_but_planner_with_given_category_already_exists(){
        //given
        CategoriesService categoriesService = new CategoriesService(categoriesRepository);
        CategoriesOfExpense category = new CategoriesOfExpense("woda");
        categoriesRepository.save(category);
        Planner planner = new Planner(categoriesRepository.getByCategoryName("WODA"), 500);
        plannerRepository.save(planner);
        CategoriesOfExpense toTestCategory = new CategoriesOfExpense("woda");
        Planner toTest = new Planner(toTestCategory, 700);
        PlannerService plannerService = new PlannerService(plannerRepository, categoriesService);
        //when
        var exception = catchThrowable(()-> plannerService.addPositionToPlanner(toTest));
        //then
        assertThat(exception)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Planner for this category already exists");
    }
    @Test
    void addPositionToPlanner_but_given_categoryOfExpense_does_not_exists(){
        //given
        CategoriesOfExpense category = new CategoriesOfExpense("RACHUNKI");
        Planner planner = new Planner(category, 500);
        CategoriesService categoriesService = new CategoriesService(categoriesRepository);
        PlannerService plannerService = new PlannerService(plannerRepository,categoriesService);
        //when
        boolean before = categoriesRepository.existsByCategoryName(category.getCategoryName());
        plannerService.addPositionToPlanner(planner);
        boolean after = categoriesRepository.existsByCategoryName(category.getCategoryName());
        //then
        assertThat(after != before);
        assertThat(plannerRepository.existsPlannerByCategoryCategoryName(planner.getCategory().getCategoryName())).isTrue();
    }
    @Test
    void addPositionToPlanner_create_new_planner_with_existing_category(){
        CategoriesOfExpense category = new CategoriesOfExpense("ZABAWA");
        categoriesRepository.save(category);
        CategoriesService categoriesService = new CategoriesService(categoriesRepository);
        PlannerService plannerService = new PlannerService(plannerRepository,categoriesService);
        Planner planner = new Planner(category,500);
        int before = plannerRepository.findAll().size();
        int beforeSizeCategory = categoriesRepository.findAll().size();
        plannerService.addPositionToPlanner(planner);
        int after = plannerRepository.findAll().size();
        int afterSizeCategory = categoriesRepository.findAll().size();
        assertThat(before != after);
        assertThat(beforeSizeCategory == afterSizeCategory);
        assertThat(plannerRepository.findPlannerByCategoryCategoryName(category.getCategoryName()));
    }
    @Test
    void addExpenseToPlanner_OK(){
        //given
        CategoriesService categoriesService = new CategoriesService(categoriesRepository);
        PlannerService plannerService = new PlannerService(plannerRepository,categoriesService);
        CategoriesOfExpense category = new CategoriesOfExpense("PALIWO");
        AccountService accountService = new AccountService(accountRepository);
        Expense expense = new Expense();
        categoriesRepository.save(category);
        Planner planner = new Planner(category, 500);
        //when
        expense.setValue(500);
        expense.setCategory(category);
        expense.setPlanner(plannerService.savePlanner(planner));
        expense.setAccount(accountService.addExpenseOperation(expense));
        expenseRepository.save(expense);
        plannerService.addExpenseToPlanner(expense);
        List<Expense> expenseList = new ArrayList<>(planner.getExpenseList());
        //then
        assertThat(expenseList.contains(expense)).isTrue();
    }
}