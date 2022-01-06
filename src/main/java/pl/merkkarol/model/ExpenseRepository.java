package pl.merkkarol.model;

import java.util.List;

public interface ExpenseRepository {
    Expense save(Expense entity);
    List<Expense> findAll();
}
