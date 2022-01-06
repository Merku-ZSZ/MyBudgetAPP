package pl.merkkarol.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.merkkarol.model.CategoriesOfExpense;
import pl.merkkarol.model.CategoriesOfExpenseRepository;

import java.util.List;

@Service
public class CategoriesService {
    private final CategoriesOfExpenseRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(CategoriesService.class);

    public CategoriesService(CategoriesOfExpenseRepository repository) {
        this.repository = repository;
    }

    public CategoriesOfExpense createCategory(CategoriesOfExpense category){
        category.setCategoryName(category.getCategoryName().toUpperCase());
       if(!repository.existsByCategoryName(category.getCategoryName())){
          CategoriesOfExpense result = repository.save(category);
           logger.info("Create new category: "+ result.getCategoryName());
            return result;
        }else {
           logger.warn("Category " + category.getCategoryName() + " already exists.");
            return repository.getByCategoryName(category.getCategoryName());
        }
    }
    public CategoriesOfExpense getCategory(String name){
        if(repository.existsByCategoryName(name)){
            return repository.getByCategoryName(name);
        }
        else {
            throw new IllegalArgumentException("Category " + name +" not fount");
        }
    }
    public List<CategoriesOfExpense> findAll(){
        return repository.findAll();
    }

}
