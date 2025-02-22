package kamenov.cupcakespakoandmoni.repos;

import kamenov.cupcakespakoandmoni.models.ShoppingCartItem;
import kamenov.cupcakespakoandmoni.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<ShoppingCartItem,Long> {

}
