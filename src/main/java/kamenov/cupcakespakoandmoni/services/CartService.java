package kamenov.cupcakespakoandmoni.services;

import jakarta.transaction.NotSupportedException;
import kamenov.cupcakespakoandmoni.models.ShoppingBasket;
import kamenov.cupcakespakoandmoni.models.ShoppingCartItem;
import kamenov.cupcakespakoandmoni.models.UserEntity;

import java.util.ArrayList;
import java.util.List;

public interface CartService {
   // List<ShoppingCartItem> getCartItems();

   // void addToCart(ShoppingCartItem item);

    List<ShoppingCartItem> getCartItems(UserEntity user);

    ShoppingBasket getActiveCartForUser(UserEntity user);

    ShoppingBasket createNewBasketForUser(UserEntity user);

    void addToCart(UserEntity user, Long cupcakeId, int quantity) throws NotSupportedException;;

    void removeFromCart(Long id);

    double calculateTotal();


}
