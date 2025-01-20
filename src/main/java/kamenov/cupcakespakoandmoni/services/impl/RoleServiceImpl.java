package kamenov.cupcakespakoandmoni.services.impl;

import kamenov.cupcakespakoandmoni.models.UserRoleEntity;
import kamenov.cupcakespakoandmoni.models.enums.UserRoleEnum;
import kamenov.cupcakespakoandmoni.repos.RoleRepository;
import kamenov.cupcakespakoandmoni.services.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public UserRoleEntity findByName(UserRoleEnum name) {
        return roleRepository.findByRole(name).orElse(null);
    }

    @Override
    public void saveAll(List<UserRoleEntity> roles) {
        this.roleRepository.saveAll(roles);
    }

    @Override
    public List<UserRoleEntity> findAll() {
        return roleRepository.findAll();
    }}
