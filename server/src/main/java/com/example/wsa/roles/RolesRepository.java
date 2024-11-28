package com.example.wsa.roles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Roles entities.
 */
@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {
}
