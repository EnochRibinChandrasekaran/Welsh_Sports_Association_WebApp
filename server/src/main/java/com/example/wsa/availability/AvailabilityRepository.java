package com.example.wsa.availability;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Availability entities.
 */
@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Integer> {
}
