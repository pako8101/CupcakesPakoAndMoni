package kamenov.cupcakespakoandmoni.services;

import kamenov.cupcakespakoandmoni.models.ShoppingCartItem;

import java.util.ArrayList;
import java.util.List;

public interface CartService {
    List<ShoppingCartItem> getCartItems();

    void addToCart(ShoppingCartItem item);

    void removeFromCart(Long id);

    double calculateTotal();


}
