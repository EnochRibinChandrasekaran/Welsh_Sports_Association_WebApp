package com.example.wsa.occupation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Occupation entities.
 */
@Repository
public interface OccupationRepository extends JpaRepository<Occupation, Integer> {
}
