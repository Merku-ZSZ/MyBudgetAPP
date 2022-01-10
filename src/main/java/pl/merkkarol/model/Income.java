package pl.merkkarol.model;


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
    @OneToOne
    @JoinColumn(name = "account_id", unique = true)
    private Account account;


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

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }
}
