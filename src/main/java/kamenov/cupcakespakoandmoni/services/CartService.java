package kamenov.cupcakespakoandmoni.services;

import kamenov.cupcakespakoandmoni.models.ShoppingBasket;
import kamenov.cupcakespakoandmoni.models.ShoppingCartItem;
import kamenov.cupcakespakoandmoni.models.UserEntity;

import java.util.ArrayList;
import java.util.List;

public interface CartService {
    List<ShoppingCartItem> getCartItems();

   // void addToCart(ShoppingCartItem item);

    ShoppingBasket getActiveCartForUser(UserEntity user);

    ShoppingBasket createNewBasketForUser(UserEntity user);

    void addToCart(UserEntity user, Long cupcakeId, int quantity);

    void removeFromCart(Long id);

    double calculateTotal();


}
