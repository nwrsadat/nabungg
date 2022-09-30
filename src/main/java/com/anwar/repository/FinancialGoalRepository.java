package com.anwar.repository;

import com.anwar.entity.FinancialGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FinancialGoalRepository extends JpaRepository<FinancialGoal, Long> {
    @Query(value = """
            SELECT COUNT(*)
            FROM FinancialGoal
            WHERE Id = :id
            """, nativeQuery = true)
    Long countGoalById(Long id);
}
