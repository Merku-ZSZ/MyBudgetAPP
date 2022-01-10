package pl.merkkarol.model;

import java.util.List;

public interface ExpenseRepository {
    Expense save(Expense entity);
    List<Expense> findAll();
    List<Expense> findAllByCategoryCategoryName(String categoryName);
    boolean existsByCategoryCategoryName(String categoryName);
    void deleteById(int id);
    boolean existsById(int id);
    Expense findById(int id);
}
