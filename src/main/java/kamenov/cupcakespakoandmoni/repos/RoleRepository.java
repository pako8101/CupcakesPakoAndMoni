package kamenov.cupcakespakoandmoni.repos;

import kamenov.cupcakespakoandmoni.models.UserRoleEntity;
import kamenov.cupcakespakoandmoni.models.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<UserRoleEntity, Long> {

    Optional<UserRoleEntity> findByRole(UserRoleEnum role);

    Optional<UserRoleEntity> findUserRoleEntityByRole(UserRoleEnum userRoleEnum);

}
