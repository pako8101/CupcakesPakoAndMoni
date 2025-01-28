package kamenov.cupcakespakoandmoni.services.impl;

import kamenov.cupcakespakoandmoni.models.ShoppingCartItem;
import kamenov.cupcakespakoandmoni.services.CartService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CartServiceImpl implements CartService {
    private List<ShoppingCartItem> cartItems = new ArrayList<>();
@Override
    public List<ShoppingCartItem> getCartItems() {
        return cartItems;
    }
@Override
    public void addToCart(ShoppingCartItem item) {
        cartItems.add(item);
    }
@Override
    public void removeFromCart(Long id) {
        cartItems.removeIf(item -> item.getId()==(id));
    }
@Override
    public double calculateTotal() {
        return cartItems.stream().mapToDouble(ShoppingCartItem::getTotal).sum();
    }


}
