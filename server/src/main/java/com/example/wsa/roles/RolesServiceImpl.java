package com.example.wsa.roles;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing roles.
 */
@Service
@Slf4j
public class RolesServiceImpl implements RolesService {

  private final RolesRepository rolesRepository;

  /**
   * Constructs a new RolesServiceImpl with the specified RolesRepository.
   *
   * @param rolesRepository the repository used to access role data
   */
  @Autowired
  public RolesServiceImpl(RolesRepository rolesRepository) {
    this.rolesRepository = rolesRepository;
  }

  /**
   * Retrieves all roles from the database.
   *
   * @return a list of all roles
   */
  @Override
  public List<Roles> getAllRoles() {
    log.debug("Entering RolesServiceImpl.getAllRoles()");
    List<Roles> roles = rolesRepository.findAll();
    log.debug("Retrieved roles: {}", roles);
    return roles;
  }
}
