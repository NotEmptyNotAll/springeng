package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.ERole;
import com.vshvet.firstrelease.Entity.Role;

import java.util.Optional;

public interface RoleDao extends Dao<Role>{
    Optional<Role> findByName(ERole roleUser);
}
