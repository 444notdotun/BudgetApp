package com.budgetapplication.budgetapp.data.models;

import com.budgetapplication.budgetapp.utils.IdGenerator;
import com.budgetapplication.budgetapp.utils.Type;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Entity
@Data
public class Users {
    @Id
    private String userId;
    @Column(unique = true)
    private String email;
    private String userPassword;
    @PrePersist
    public void prePersist()
    {
        this.userId= IdGenerator.generate(Type.USER);
    }

}
