package kamenov.cupcakespakoandmoni.web;

import kamenov.cupcakespakoandmoni.models.user.AppUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

@GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal AppUserDetails appUserDetails){

    if (appUserDetails != null) {
        model.addAttribute("fullName", appUserDetails.getFullName());

    }
    return "index";
}
@GetMapping("/about")
public String about() {
    return "about";
}
@GetMapping("/products")
public String products() {
    return "products";
}
@GetMapping("/keto-cakes")
    public String ketoCakes() {
    return "keto-cakes";
}
    @GetMapping("/cream-cakes")
    public String cream() {
        return "cream-cakes";
    }
    @GetMapping("/fruity-cakes")
    public String fruity() {
        return "fruity-cakes";
    }
    @GetMapping("/chocolate-cakes")
    public String choco() {
        return "chocolate-cakes";
    }
    @GetMapping("/pumpkin-cakes")
    public String pumpkin() {
        return "pumpkin-cakes";
    }

}
