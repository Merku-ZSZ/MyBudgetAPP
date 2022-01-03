package pl.merkkarol.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Income")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double incomeAmount;
    private Date date;
    private String incomeCategory;


    public Income(){}
    public Income(double incomeAmount, String incomeCategory){
        this.incomeAmount = incomeAmount;
        this.incomeCategory = incomeCategory;
    }
    @PrePersist
    private void addDate(){
        this.date = new Date();
    }

    public int getId() {
        return id;
    }

    public double getIncomeAmount() {
        return incomeAmount;
    }

    public Date getDate() {
        return date;
    }

    public String getIncomeCategory() {
        return incomeCategory;
    }
}
