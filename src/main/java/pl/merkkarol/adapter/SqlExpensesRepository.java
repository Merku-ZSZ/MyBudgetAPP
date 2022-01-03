package pl.merkkarol.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.merkkarol.model.Expenses;
import pl.merkkarol.model.ExpensesRepository;

@Repository
public interface SqlExpensesRepository extends JpaRepository<Expenses,Integer>, ExpensesRepository {
}
