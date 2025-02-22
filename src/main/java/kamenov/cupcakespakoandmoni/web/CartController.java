package kamenov.cupcakespakoandmoni.web;

import jakarta.transaction.NotSupportedException;
import jakarta.transaction.Transactional;
import kamenov.cupcakespakoandmoni.exceptions.EmptyBasketException;
import kamenov.cupcakespakoandmoni.exceptions.EmptyCartException;
import kamenov.cupcakespakoandmoni.models.CupCakeEntity;
import kamenov.cupcakespakoandmoni.models.ShoppingBasket;
import kamenov.cupcakespakoandmoni.models.ShoppingCartItem;
import kamenov.cupcakespakoandmoni.models.UserEntity;
import kamenov.cupcakespakoandmoni.services.CartService;
import kamenov.cupcakespakoandmoni.services.CupCakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
@RequestMapping("/cart")

public class CartController {
    private final CupCakeService cupCakeService;

    private final CartService cartService;
    @Autowired
    public CartController(CupCakeService cupCakeService, CartService cartService) {
        this.cupCakeService = cupCakeService;
        this.cartService = cartService;
    }
    @ModelAttribute
    public ShoppingCartItem getItem() {
        return new ShoppingCartItem();

    }
    @ModelAttribute("cart")
    public ShoppingBasket getCart() {
        return new ShoppingBasket();
    }
    @GetMapping
    public String viewCart(Model model,@AuthenticationPrincipal UserEntity user) {
        ShoppingBasket cart = cartService.getActiveCartForUser(user);

        List<ShoppingCartItem> cartItems = cartService.getCartItems(user);
        if (cartItems == null){
            cartItems = new ArrayList<>();
        }
        if (cartItems.isEmpty()){
            throw new EmptyCartException("Your cart is empty");
        }

//        try {
//
//            model.addAttribute("cartItems", items);
//            model.addAttribute("totalPrice", cartService.calculateTotal());
//        } catch (EmptyBasketException e) {
//            model.addAttribute("cartEmpty", true);
//        }
//        List<ShoppingCartItem> items = cartService.getCartItems();
//        if (items.isEmpty()) {
//            model.addAttribute("cartEmpty", true);
//        } else {
//            model.addAttribute("cartItems", items);
//            model.addAttribute("totalPrice", cartService.calculateTotal());
//        }
//        List<ShoppingCartItem> cartItems = cartService.getActiveCartForUser(user).getItems();
//        if (cartItems == null) {
//            cartItems = new ArrayList<>();
//        }
//        if (cartItems.isEmpty()) {
//            throw new EmptyBasketException("Your cart is empty.");
//
//        }
        //    List<CartItem> cartItems = cartService.getCartItems();
//        double total = cartItems.stream()
//                .mapToDouble(ShoppingCartItem::getTotal).sum();

//       add date -----
        LocalDate currentDate = LocalDate.now();

        LocalDate futureDate = currentDate.plusDays(10);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = futureDate.format(formatter);
        model.addAttribute("futureDate", formattedDate);
        model.addAttribute("cart", cart);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", cartService.calculateTotal());
        return "/cart";
    }
@Transactional
    @PostMapping("/add/{id}")
    public String addToCart(@AuthenticationPrincipal UserEntity user,
                            @PathVariable("id") Long cupcakeId,
                            @RequestParam(defaultValue = "1")int quantity) throws NotSupportedException {
    cartService.addToCart(user, cupcakeId, quantity);
//    CupCakeEntity cupCake = cupCakeService.findCupcakeById(cupcakeId);
//    if (cupCake != null && cupCake.getQuantity() >= quantity) {
//
//
//    }


    return "redirect:/cart";
    }
@Transactional
    @PostMapping("/remove/{id}")
    public String removeFromCart(@PathVariable Long id) {
        cartService.removeFromCart(id);
        return "redirect:/cart";
    }
    @ExceptionHandler(EmptyCartException.class)
    public String handleEmptyCartException(EmptyCartException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "/cart";
    }
    }
