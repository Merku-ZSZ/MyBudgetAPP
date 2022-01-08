package pl.merkkarol.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.merkkarol.model.Planner;
import pl.merkkarol.model.PlannerRepository;


@Repository
public interface SqlPlannerRepository extends JpaRepository<Planner, Integer>, PlannerRepository {
}
