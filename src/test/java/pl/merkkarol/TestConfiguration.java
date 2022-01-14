package pl.merkkarol;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import pl.merkkarol.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class TestConfiguration {

    @Bean
    @Primary
    @Profile("integration")
     CategoriesOfExpenseRepository testCategoriesRepository(){
        return new CategoriesOfExpenseRepository() {
            private Map<Integer, CategoriesOfExpense> categories = new HashMap<>();
            @Override
            public boolean existsByCategoryName(String name) {
                for(Map.Entry<Integer, CategoriesOfExpense> map: categories.entrySet()){
                    if(map.getValue().getCategoryName().equals(name)){
                        return true;
                    }
                }
                return false;
            }

            @Override
            public CategoriesOfExpense getByCategoryName(String name) {
                for(Map.Entry<Integer, CategoriesOfExpense> map: categories.entrySet()){
                    if(map.getValue().getCategoryName().equals(name)){
                        return map.getValue();
                    }
                }
                throw new IllegalStateException();
            }

            @Override
            public List<CategoriesOfExpense> findAll() {
                return new ArrayList<>(categories.values());
            }

            @Override
            public CategoriesOfExpense save(CategoriesOfExpense entityTest) {
                int key = categories.size() + 1;
                entityTest.setCategoryName(entityTest.getCategoryName().toUpperCase());
                categories.put(key, entityTest);
                return categories.get(key);

            }

            @Override
            public List<CategoriesOfExpense> findByCategoryName(String name) {
                List<CategoriesOfExpense> foundByCategoryExpense = new ArrayList<>();
                for(Map.Entry<Integer, CategoriesOfExpense> map: categories.entrySet()){
                    if(map.getValue().getCategoryName().equals(name)){
                        foundByCategoryExpense.add(map.getValue());
                    }
                }
                return  foundByCategoryExpense;
            }
        };
    }
    @Bean
    @Primary
    @Profile("integration")
    PlannerRepository testPlannerRepository(){
     return new PlannerRepository() {
      private Map<Integer, Planner> planners = new HashMap<>();
         @Override
         public boolean existsPlannerByCategoryCategoryName(String name) {
             for (Map.Entry<Integer, Planner> map: planners.entrySet()){
                 if(map.getValue().getCategory().getCategoryName().equals(name)){
                     return true;
                 }
             }
             return false;
         }

         @Override
         public Planner save(Planner planner) {
             int key = planners.size() + 1;
             planner.setId(key);
             planners.put(key, planner);
             return planners.get(key);
         }

         @Override
         public Planner findPlannerByCategoryCategoryName(String name) {
             for (Map.Entry<Integer, Planner> map: planners.entrySet()){
                 if(map.getValue().getCategory().getCategoryName().equals(name)){
                     return map.getValue();
                 }
             }
             throw new IllegalStateException();
         }

         @Override
         public List<Planner> findAll() {
             List<Planner> plannerList = new ArrayList<>(planners.values());
            return plannerList;
         }

         @Override
         public boolean existsPlannerById(int id) {
             for(Map.Entry<Integer, Planner> map: planners.entrySet()){
                 if(map.getKey() == id){
                     return true;
                 }
             }
             return false;
         }

         @Override
         public Planner findPlannerById(int id) {
             for (Map.Entry<Integer, Planner> map : planners.entrySet()){
                 if(map.getKey() == id){
                     return map.getValue();
                 }
             }
             throw new IllegalStateException();
            }
     };
    }
    @Bean
    @Primary
    @Profile("integration")
    ExpenseRepository testExpenseRepository(){
        return new ExpenseRepository() {
            private Map<Integer, Expense> expenses = new HashMap<>();
            @Override
            public Expense save(Expense entity) {
                int key = expenses.size() + 1;
                entity.setId(key);
                expenses.put(key, entity);
                return expenses.get(key);
            }

            @Override
            public List<Expense> findAll() {
                List<Expense> list = new ArrayList<>(expenses.values());
                return list;
            }

            @Override
            public List<Expense> findAllByCategoryCategoryName(String categoryName) {
                List<Expense> list = new ArrayList<>();
                for (Map.Entry<Integer,Expense> map: expenses.entrySet()){
                    if(map.getValue().getCategory().getCategoryName().equals(categoryName)){
                        list.add(map.getValue());
                    }
                }
                return list;
            }

            @Override
            public boolean existsByCategoryCategoryName(String categoryName) {
                for (Map.Entry<Integer,Expense> map: expenses.entrySet()){
                    if(map.getValue().getCategory().getCategoryName().equals(categoryName)){
                        return true;
                    }
                }
                return false;
            }

            @Override
            public void deleteById(int id) {
                expenses.remove(id);
            }

            @Override
            public boolean existsById(int id) {
                return expenses.containsKey(id);
            }

            @Override
            public Expense findById(int id) {
                return expenses.get(id);
            }
        };
    }
    @Bean
    @Primary
    @Profile("integration")
    AccountRepository testAccountRepository(){
        return new AccountRepository() {
            private Map<Integer, Account> accountMap = new HashMap<>();
            @Override
            public Account save(Account account) {
                int key = accountMap.size() + 1;
                Account accountToSave =  new Account(account.getAccountBalance(),account.getOperations());
                accountToSave.setId(key);
                accountMap.put(key, accountToSave);
                return accountMap.get(key);
            }

            @Override
            public Account findById(int id) {
                return accountMap.get(id);
            }

            @Override
            public List<Account> findAll() {
                List<Account> list = new ArrayList<>(accountMap.values());
                return list;
            }

            @Override
            public void deleteById(int id) {
            accountMap.remove(id);
            }

            @Override
            public int findIdOfLastRecord() {
                int max = 0;
                for(Map.Entry<Integer, Account> map :accountMap.entrySet()){
                    if(map.getValue().getId() > max){
                        max = map.getValue().getId();
                    }
                }
                return max;
            }
        };
    }
}
