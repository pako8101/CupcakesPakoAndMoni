package kamenov.cupcakespakoandmoni.repos;

import kamenov.cupcakespakoandmoni.models.ShoppingBasket;
import kamenov.cupcakespakoandmoni.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<ShoppingBasket,Long> {
    Optional<ShoppingBasket> findByUserEntityAndCompletedFalse(UserEntity user);
}
