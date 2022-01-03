package pl.merkkarol.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.merkkarol.model.Income;
import pl.merkkarol.model.IncomeRepository;

import java.util.List;

@Repository
public interface SqlIncomeRepository extends JpaRepository<Income,Integer>, IncomeRepository {

}
