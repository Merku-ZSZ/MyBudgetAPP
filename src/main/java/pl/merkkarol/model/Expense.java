package pl.merkkarol.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "expense_value")
    private double  value;
    @Column(name = "expense_date")
    private Date date;
    @ManyToOne
    @JoinColumn(name = "category_name")
    private CategoriesOfExpense category;
    @ManyToOne
    @JoinColumn(name = "planner_id")
    private Planner planner;
    @OneToOne
    @JoinColumn(name = "account_id", unique = true)
    private Account account;


    public void setPlanner(Planner planner) {
        this.planner = planner;
    }

    public Planner getPlanner() {
        return planner;
    }

    @PrePersist
    public void addDate(){
        this.date = new Date();
    }

    public int getId() {
        return id;
    }

    public double getValue() {
        return value;
    }

    public CategoriesOfExpense getCategory() {
        return category;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }
}
