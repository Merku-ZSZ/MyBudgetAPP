package pl.merkkarol.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Planner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private CategoriesOfExpense category;
    private double assumedValue;
    @OneToMany
    private Set<Expense> expens;
    private double availableFunds;
public Planner(){}
}
