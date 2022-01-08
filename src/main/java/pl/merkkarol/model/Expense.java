package pl.merkkarol.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "expenses")
public class Expense{
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

    public Expense(){}

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

    public Date getDate() {
        return date;
    }

    public CategoriesOfExpense getCategory() {
        return category;
    }


}
