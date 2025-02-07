package kamenov.cupcakespakoandmoni.services.impl;

import kamenov.cupcakespakoandmoni.models.CupCakeEntity;
import kamenov.cupcakespakoandmoni.models.ShoppingBasket;
import kamenov.cupcakespakoandmoni.models.ShoppingCartItem;
import kamenov.cupcakespakoandmoni.models.UserEntity;
import kamenov.cupcakespakoandmoni.repos.BasketRepository;
import kamenov.cupcakespakoandmoni.repos.CartRepository;
import kamenov.cupcakespakoandmoni.services.CartService;
import kamenov.cupcakespakoandmoni.services.CupCakeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private final BasketRepository basketRepository;
    private final CartRepository cartRepository;
    private final CupCakeService cupCakeService;
    private List<ShoppingCartItem> cartItems = new ArrayList<>();

    public CartServiceImpl(BasketRepository basketRepository, CartRepository cartRepository, CupCakeService cupCakeService) {
        this.basketRepository = basketRepository;
        this.cartRepository = cartRepository;
        this.cupCakeService = cupCakeService;
    }

    @Override
    public List<ShoppingCartItem> getCartItems() {

            return cartItems != null ? cartItems : new ArrayList<>();
    }
    @Override
    public ShoppingBasket getActiveCartForUser(UserEntity user) {
        return basketRepository.findByUserEntityAndCompletedFalse(user)
                .orElseGet(() -> createNewBasketForUser(user));
    }
@Override
public ShoppingBasket createNewBasketForUser(UserEntity user) {
    ShoppingBasket basket = new ShoppingBasket();
    basket.setUserEntity(user);
    return basketRepository.save(basket);
    }

    @Override
    public void addToCart(UserEntity user, Long cupcakeId, int quantity) {
    ShoppingBasket basket = getActiveCartForUser(user);
        CupCakeEntity cupCake = cupCakeService.findCupcakeById(cupcakeId);
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        shoppingCartItem.setQuantity(quantity);
        shoppingCartItem.setCupCakeEntity(cupCake);
        shoppingCartItem.setBasket(basket);
    cartRepository.save(shoppingCartItem);
    basket.getItems().add(shoppingCartItem);
    basketRepository.save(basket);
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
