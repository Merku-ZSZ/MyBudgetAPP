package pl.merkkarol.model;

import java.util.List;

public interface CategoriesOfExpenseRepository {
    boolean existsByCategoryName(String name);
    CategoriesOfExpense getByCategoryName(String name);
    List<CategoriesOfExpense> findAll();
    CategoriesOfExpense save(CategoriesOfExpense entity);
    List<CategoriesOfExpense> findByCategoryName(String name);
}
