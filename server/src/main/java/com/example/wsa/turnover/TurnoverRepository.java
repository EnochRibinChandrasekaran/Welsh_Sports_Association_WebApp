package com.example.wsa.turnover;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Turnover entities.
 */
@Repository
public interface TurnoverRepository extends JpaRepository<Turnover, Integer> {
}
