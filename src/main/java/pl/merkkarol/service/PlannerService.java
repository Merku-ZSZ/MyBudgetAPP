package pl.merkkarol.service;

import org.springframework.stereotype.Service;
import pl.merkkarol.model.Expense;
import pl.merkkarol.model.Planner;
import pl.merkkarol.model.PlannerRepository;

import java.util.List;


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
                result = new Planner(toCreate.getCategory(), toCreate.getAssumedValue());
                return   plannerRepository.save(result);
            }
            //Throw an exception when position in planner already exists.
            else {
                throw new IllegalStateException("Planner for this category already exists!");
            }
            //If category of new plan position does not exists: create new category -next-> create new position in planner
        }
            else {
                serviceCategory.createCategory(toCreate.getCategory());
                result = new Planner(toCreate.getCategory(), toCreate.getAssumedValue());
                return  plannerRepository.save(result);
            }
        }
    public List<Planner> getPlanner() {
        return plannerRepository.findAll();
    }

    public Planner addExpenseToPlanner(Expense expense) {
        String categoryName = expense.getCategory().getCategoryName().toUpperCase();
        //Check if planner with given category exists
        if(plannerRepository.existsPlannerByCategoryCategoryName(categoryName)){
            Planner planner = plannerRepository.findPlannerByCategoryCategoryName(categoryName);
            planner.addExpenseToList(expense);
            double balance = planner.getAvailableFunds() - expense.getValue();
            planner.setAvailableFunds(balance);
            return plannerRepository.save(planner);
        }
        else{
         throw new IllegalArgumentException("Planner with given id does not exists!");
        }
    }

    public Planner updatePlan(int id, double toUpdate) {
        //Update assumed value if plan with given id exists.
        if(plannerRepository.existsPlannerById(id)){
            Planner planner = plannerRepository.findPlannerById(id);
            planner.setAssumedValue(toUpdate + planner.getAssumedValue());
            planner.setAvailableFunds(planner.getAvailableFunds() + toUpdate);
            return plannerRepository.save(planner);
        }
        else {
            throw new IllegalArgumentException("Plan with given id does not exists!");
        }
    }
    public Planner getPlannerById(int id){
       return plannerRepository.findPlannerById(id);
    }
    public boolean existsPlannerById(int id){
        return plannerRepository.existsPlannerById(id);
    }
    public Planner savePlanner(Planner planner){
        return plannerRepository.save(planner);
    }
}
