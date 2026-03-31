package com.budgetapplication.budgetapp.data.repository;

import com.budgetapplication.budgetapp.data.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {
}
