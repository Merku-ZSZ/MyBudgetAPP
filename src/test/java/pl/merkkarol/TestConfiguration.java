package pl.merkkarol;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import pl.merkkarol.model.CategoriesOfExpense;
import pl.merkkarol.model.CategoriesOfExpenseRepository;

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
}
