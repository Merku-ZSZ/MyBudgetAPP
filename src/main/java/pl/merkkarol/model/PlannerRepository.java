package pl.merkkarol.model;

import java.util.List;

public interface PlannerRepository {
    boolean existsPlannerByCategoryCategoryName(String name);
    Planner save(Planner planner);
    Planner findPlannerByCategoryCategoryName(String name);
    List<Planner> findAll();
}
