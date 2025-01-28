package kamenov.cupcakespakoandmoni.web;

import kamenov.cupcakespakoandmoni.models.dtos.CupCakeAddDto;
import kamenov.cupcakespakoandmoni.models.enums.CupCakeTypeEnum;
import kamenov.cupcakespakoandmoni.services.CupCakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cupcakes")
public class CupCakeController {
    @Autowired
    private CupCakeService cupcakeService;

    @GetMapping
    public String getAllCupcakes(Model model) {
        model.addAttribute("cupcakes", cupcakeService.getAllCupcakes());
        return "cream-cakes";
    }
    @GetMapping("/type/{type}")
    public String getCupcakesByType(@PathVariable String type, Model model) {
        CupCakeTypeEnum cupcakeType = CupCakeTypeEnum.valueOf(type.toUpperCase());
        model.addAttribute("cupcakes", cupcakeService.getCupcakesByType(cupcakeType));
        model.addAttribute("type", cupcakeType);
        return "cupcakes";
    }

    @PostMapping("/add")
    public String addCupcake(@ModelAttribute CupCakeAddDto form) {
        cupcakeService.addCupcake(form);
        return "redirect:/cupcakes";
    }
}
