package kamenov.cupcakespakoandmoni.services;

import kamenov.cupcakespakoandmoni.models.UserRoleEntity;
import kamenov.cupcakespakoandmoni.models.enums.UserRoleEnum;

import java.util.List;

public interface RoleService {
    UserRoleEntity findByName(UserRoleEnum name);

    void saveAll(List<UserRoleEntity> roles);

    List<UserRoleEntity> findAll();
}
