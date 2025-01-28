package kamenov.cupcakespakoandmoni.repos;

import kamenov.cupcakespakoandmoni.models.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<ShoppingCartItem,Long> {
}
