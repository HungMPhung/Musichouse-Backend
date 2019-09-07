package com.codegym.musichouse.service;

import com.codegym.musichouse.model.Role;
import com.codegym.musichouse.model.RoleName;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(RoleName roleName);

}
