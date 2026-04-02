package com.budgetapplication.budgetapp.data.models;

import com.budgetapplication.budgetapp.utils.IdGenerator;
import com.budgetapplication.budgetapp.utils.Type;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;

@Entity
@Data
@Table(indexes = {
        @Index(name = "idx_userId_templateId",
        columnList = "userId,templateId")
})
public class MonthlyBudget {
    @Id
    private String monthlyBudgetId;
    private Month month;
    private int year;
    private BigDecimal income;
    @ManyToOne
    @JoinColumn(name = "userId" ,nullable = false)
    private Users userId;
    @ManyToOne
    @JoinColumn(name = "budgetTemplateId")
    private BudgetTemplate budgetTemplateId;
    @CreatedDate
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist()
    {
        this.monthlyBudgetId= IdGenerator.generate(Type.TEMPLATE);
    }

}
