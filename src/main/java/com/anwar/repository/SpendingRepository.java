package com.anwar.repository;

import com.anwar.entity.Spending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SpendingRepository extends JpaRepository<Spending, Long> {
    @Query(value = """
            SELECT COUNT(*)
            FROM Spending AS spd
            WHERE spd.Id = :id
            """, nativeQuery = true)
    Long countById(@Param("id") Long id);
}
