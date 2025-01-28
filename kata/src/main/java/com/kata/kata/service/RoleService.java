package com.kata.kata.service;


import com.kata.kata.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    Role createRole(Role role);

    Optional<Role> findByRoleName(String roleName);

    List<Role> getAllRoles();

    void deleteRole(Long roleId);
}

