package pl.merkkarol.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.merkkarol.model.Expense;
import pl.merkkarol.model.Planner;
import pl.merkkarol.model.PlannerRepository;

import java.util.List;


@Repository
public interface SqlPlannerRepository extends JpaRepository<Planner, Integer>, PlannerRepository {
}
