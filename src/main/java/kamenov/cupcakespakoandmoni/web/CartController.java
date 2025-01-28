package kamenov.cupcakespakoandmoni.web;

import kamenov.cupcakespakoandmoni.models.CupCakeEntity;
import kamenov.cupcakespakoandmoni.models.ShoppingCartItem;
import kamenov.cupcakespakoandmoni.services.CartService;
import kamenov.cupcakespakoandmoni.services.CupCakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public String viewCart(Model model) {
        model.addAttribute("cartItems", cartService.getCartItems());
        model.addAttribute("totalPrice", cartService.calculateTotal());
        return "cart";
    }

    @PostMapping("/add")
    public String addToCart(@ModelAttribute ShoppingCartItem item,Long cupcakeId) {
        CupCakeEntity cupcake = cupCakeService.getCupcakeById(cupcakeId);
        if (cupcake != null) {
            item = new ShoppingCartItem();
            item.setId(cupcake.getId());
            item.setName(cupcake.getName());
            item.setType(cupcake.getType());
            item.setPrice(cupcake.getPrice());
            item.setQuantity(cupcake.getQuantity());
            cartService.addToCart(item);
        }
        cartService.addToCart(item);
        return "redirect:/cart";
    }

    @PostMapping("/remove/{id}")
    public String removeFromCart(@PathVariable Long id) {
        cartService.removeFromCart(id);
        return "redirect:/cart";
    }
}
