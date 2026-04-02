package com.budgetapplication.budgetapp.data.models;

import com.budgetapplication.budgetapp.utils.IdGenerator;
import com.budgetapplication.budgetapp.utils.Type;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity(name = "BudgetCategories")
@Table(indexes = {
        @Index(name = "idx_Category_Monthly_budget",
        columnList = "categoryType, monthlyBudgetId")
})
@Data
public class BudgetCategories {
    @Id
    private String categoryId;
    private String categoryName;
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name = "monthlyBudgetId")
    private MonthlyBudget monthlyBudgetId;
    private CategoryType categoryType;

    @PrePersist
    public void prePersist()
    {
        this.categoryId=IdGenerator.generate(Type.CATEGORY);
    }
}
