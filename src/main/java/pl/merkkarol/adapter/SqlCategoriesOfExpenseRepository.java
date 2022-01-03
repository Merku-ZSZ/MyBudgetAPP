package pl.merkkarol.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.merkkarol.model.CategoriesOfExpense;
import pl.merkkarol.model.CategoriesOfExpenseRepository;

@Repository
public interface SqlCategoriesOfExpenseRepository extends JpaRepository<CategoriesOfExpense, Integer>, CategoriesOfExpenseRepository {
}
