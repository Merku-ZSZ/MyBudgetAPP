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

    public int getId() {
        return id;
    }
}
