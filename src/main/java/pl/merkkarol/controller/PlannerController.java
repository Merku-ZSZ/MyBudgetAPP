package pl.merkkarol.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.merkkarol.model.Expense;
import pl.merkkarol.model.Planner;
import pl.merkkarol.service.PlannerService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/planner")
public class PlannerController {
private final PlannerService service;

    public PlannerController(PlannerService service) {
        this.service = service;
    }
    @PostMapping
    ResponseEntity<Planner> addPlan(@RequestBody @Valid Planner toCreate){
    Planner result = service.addPositionToPlanner(toCreate);
    return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }
    @GetMapping
    ResponseEntity<List<Planner>> getPlanner(){
        return ResponseEntity.ok(service.getPlanner());
    }
    @PatchMapping("/{id}")
    ResponseEntity<Planner> updatePlan(@PathVariable int id, @RequestBody double toUpdate){
        if(!service.existsPlannerById(id)){
            return  ResponseEntity.notFound().build();
        }
     return ResponseEntity.ok(service.updatePlan(id, toUpdate));

    }
}
