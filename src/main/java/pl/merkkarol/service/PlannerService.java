package pl.merkkarol.service;

import org.springframework.stereotype.Service;
import pl.merkkarol.model.Expense;
import pl.merkkarol.model.Planner;
import pl.merkkarol.model.PlannerRepository;

import java.util.List;
import java.util.Map;

@Service
public class PlannerService {
    private final PlannerRepository plannerRepository;
    private final CategoriesService serviceCategory;

    public PlannerService(PlannerRepository repository, CategoriesService serviceCategory) {
        this.plannerRepository = repository;
        this.serviceCategory = serviceCategory;
    }

    public Planner addPositionToPlanner(Planner toCreate) {
        String categoryName = toCreate.getCategory().getCategoryName().toUpperCase();
        Planner result;
        if(serviceCategory.existsByCategoryName(categoryName)) {
            //Create new position in planner
            if (!plannerRepository.existsPlannerByCategoryCategoryName(categoryName)) {
                result =  plannerRepository.save(toCreate);
                return result;
            }
            //Throw an exception when position in planner already exists.
            else {
                throw new IllegalStateException("Planner for this category already exists!");
            }
            //If category of new plan position does not exists: create new category -next-> create new position in planner
        }
            else {
                serviceCategory.createCategory(toCreate.getCategory());
                result = plannerRepository.save(toCreate);
                return result;
            }
        }
    public List<Planner> getPlanner() {
        return plannerRepository.findAll();
    }
}
