package pl.merkkarol.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double accountBalance;
    private double operations;
    private Date date;
public Account(){ }
public Account(double accountBalance, double operations){
    this.accountBalance = accountBalance;
    this.operations = operations;
    this.date = new Date();
}

    public double getAccountBalance() {
        return accountBalance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getOperations() {
        return operations;
    }

    public Date getDate() {
        return date;
    }

}
