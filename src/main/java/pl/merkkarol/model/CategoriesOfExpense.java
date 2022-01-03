package pl.merkkarol.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "expense_category")
public class CategoriesOfExpense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String categoryName;
    @OneToMany
    private Set<Expenses> expensesFromCategory;
}
