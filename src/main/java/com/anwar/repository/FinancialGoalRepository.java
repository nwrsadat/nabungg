package com.anwar.repository;

import com.anwar.entity.FinancialGoal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FinancialGoalRepository extends JpaRepository<FinancialGoal, Long> {
    @Query(value = """
            SELECT COUNT(*)
            FROM FinancialGoal
            WHERE Id = :id
            """, nativeQuery = true)
    Long countGoalById(Long id);

    @Query(value = """
            SELECT *
            FROM FinancialGoal
            WHERE Username = :username
            """, nativeQuery = true)
    Page<FinancialGoal> findAllGoals(String username, Pageable pagination);
}
