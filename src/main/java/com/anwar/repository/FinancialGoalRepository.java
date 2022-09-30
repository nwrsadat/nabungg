package com.anwar.repository;

import com.anwar.entity.FinancialGoal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialGoalRepository extends JpaRepository<FinancialGoal, Long> {
}
