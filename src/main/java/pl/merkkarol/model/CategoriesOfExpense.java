package pl.merkkarol.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categories_of_expense")
public class CategoriesOfExpense {
    @Id
    private String categoryName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    @JsonIgnore
    private Set<Expense> expenseFromCategory;
    public CategoriesOfExpense(){}
    public CategoriesOfExpense(String categoryName){
        this.categoryName = categoryName;
    }




    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Set<Expense> getExpenseFromCategory() {
        return expenseFromCategory;
    }
}
