package pl.merkkarol.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Planner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private CategoriesOfExpense category;
    private double assumedValue;
    private double availableFunds;

public Planner(){}
    public Planner(CategoriesOfExpense category, double assumedValue){
    this.category = category;
    this.assumedValue = assumedValue;
    this.availableFunds = assumedValue;
    }

    public int getId() {
        return id;
    }

    public CategoriesOfExpense getCategory() {
        return category;
    }

    public double getAssumedValue() {
        return assumedValue;
    }

    public double getAvailableFunds() {
        return availableFunds;
    }

    public void setAvailableFunds(double availableFunds) {
        this.availableFunds = availableFunds;
    }
}
