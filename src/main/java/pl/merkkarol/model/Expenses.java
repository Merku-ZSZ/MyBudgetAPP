package pl.merkkarol.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "expenses")
public class Expenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double  value;
    private Date date;
    @ManyToOne
    private CategoriesOfExpense category;
}
