package com.example.wsa.roles;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing role entities.
 */
@RestController
@RequestMapping("api/roles")
@Slf4j
public class RolesController {

  private final RolesService rolesService;

  /**
   * Constructs a new RolesController with the specified RolesService.
   *
   * @param rolesService the service used to manage roles
   */
  @Autowired
  public RolesController(RolesService rolesService) {
    this.rolesService = rolesService;
  }

  /**
   * Retrieves all roles.
   *
   * @return a list of all roles
   * @throws RolesException if an error occurs while fetching roles
   */
  @GetMapping
  public List<Roles> getAllRoles() throws RolesException {
    try {
      log.debug("Entering RolesController.getAllRoles()");
      List<Roles> roles = rolesService.getAllRoles();
      log.debug("Successfully retrieved roles: {}", roles);
      return roles;
    } catch (Exception e) {
      log.error("Error fetching roles", e);
      throw new RolesException("Error fetching roles", e);
    }
  }
}
