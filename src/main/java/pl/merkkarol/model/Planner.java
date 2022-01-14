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
    @JoinColumn(name = "category_name", unique = true)
    private CategoriesOfExpense category;
    private double assumedValue;
    private double availableFunds;
    @OneToMany(mappedBy = "id")
    private List<Expense> expenseList = new ArrayList<>();

public Planner(){}
    public Planner(CategoriesOfExpense category, double assumedValue){
    this.category = category;
    this.assumedValue = assumedValue;
    this.availableFunds = assumedValue;
    }
    public int getId() {
        return id;
    }

    public void addExpenseToList(Expense expense){
    this.expenseList.add(expense);
    }

    public void setAssumedValue(double assumedValue) {
        this.assumedValue = assumedValue;
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

    public void setId(int id) {
        this.id = id;
    }

    public List<Expense> getExpenseList() {
        return expenseList;
    }
}
