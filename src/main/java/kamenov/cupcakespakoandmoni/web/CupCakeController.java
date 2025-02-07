package kamenov.cupcakespakoandmoni.web;

import jakarta.validation.Valid;
import kamenov.cupcakespakoandmoni.models.CupCakeEntity;
import kamenov.cupcakespakoandmoni.models.ShoppingCartItem;
import kamenov.cupcakespakoandmoni.models.dtos.CupCakeAddDto;
import kamenov.cupcakespakoandmoni.models.enums.CupCakeTypeEnum;
import kamenov.cupcakespakoandmoni.services.CartService;
import kamenov.cupcakespakoandmoni.services.CupCakeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cupcakes")
public class CupCakeController {

    private final CupCakeService cupcakeService;
    private final CartService cartService;
    private final ModelMapper modelMapper;
    @Autowired
    public CupCakeController(CupCakeService cupcakeService, CartService cartService, ModelMapper modelMapper) {
        this.cupcakeService = cupcakeService;
        this.cartService = cartService;
        this.modelMapper = modelMapper;
    }


    @ModelAttribute("cupCakeAddDto")
public CupCakeAddDto getCupCakeAddDto() {
    return new CupCakeAddDto();
}



    @GetMapping("/all")
    public String getAllCupcakes(Model model) {
        model.addAttribute("cupcakes", cupcakeService.getAllCupcakes());
        return "cupcakes";
    }
//    @GetMapping("/type/{type}")
//    public String getCupcakesByType(@PathVariable String type, Model model) {
//        CupCakeTypeEnum cupcakeType = CupCakeTypeEnum.valueOf(type.toUpperCase());
//        model.addAttribute("cupcakes", cupcakeService.getCupcakesByType(cupcakeType));
//        model.addAttribute("type", cupcakeType);
//        return "products";
//    }
    @GetMapping("/add")
public String addCupcake(Model model) {
        model.addAttribute("cupcake", new CupCakeAddDto());
        return "cupcake-add";
    }
    @PostMapping("/add")
    public String addCupcake(@Valid CupCakeAddDto cupCakeAddDto,
                             BindingResult bindingResult,
                             Model model,
                             RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal UserDetails principal) {

    if (principal.getUsername()==null){
        throw new UsernameNotFoundException("Username not found");
    }
    if (bindingResult.hasErrors()) {
        redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.cupCakeAddDto", bindingResult);
        redirectAttributes.addFlashAttribute("cupCakeAddDto",cupCakeAddDto);
        return "cupcake-add";
    }



    cupcakeService.addCupcake(cupCakeAddDto);
    model.addAttribute("message", "Cupcake added");
        return "redirect:/cupcakes/all";
    }

//    @PostMapping("/cart/add")
//    public String addToCart(@RequestParam Long id,
//                            @RequestParam int quantity) {
//        CupCakeEntity cupcake = cupcakeService.getCupcakeById(id);
////        if (cupcake != null && cupcake.getQuantity() >= quantity) {
////            ShoppingCartItem item = new ShoppingCartItem();
////            item.setId(cupcake.getId());
////            item.setName(cupcake.getName());
////            item.setType(cupcake.getType());
////            item.setPrice(cupcake.getPrice());
////            item.setQuantity(quantity);
////            cartService.addToCart(item);
////            cupcakeService.updateStock(id, quantity);
//            if (cupcake != null) {
//                if (cupcake.getQuantity() >= quantity) { // ???????? ?? ?????????
//                    ShoppingCartItem item = new ShoppingCartItem();
//                    item.setId(cupcake.getId());
//                    item.setName(cupcake.getName());
//                    item.setType(cupcake.getType());
//                    item.setPrice(cupcake.getPrice());
//                    item.setQuantity(quantity);
//
//                    cartService.addToCart(item);
//
//                    // ?????????? ?? ???????????
//                    cupcakeService.updateStock(id, cupcake.getQuantity() - quantity);
//                } else {
//
//                    return "redirect:/cupcakes";
//                }
//
//        }
//        return "redirect:/cart";
//    }
//    @PostMapping("/updateStock")
//    public String updateStock(@RequestParam Long id, @RequestParam int stock) {
//        cupcakeService.updateStock(id, stock);
//        return "redirect:/cupcakes";
//    }

}
