package pl.merkkarol.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.merkkarol.model.Expense;
import pl.merkkarol.model.ExpenseRepository;

import java.util.List;

@Repository
public interface SqlExpenseRepository extends JpaRepository<Expense,Integer>, ExpenseRepository {

}
