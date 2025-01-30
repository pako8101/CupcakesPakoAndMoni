package kamenov.cupcakespakoandmoni.web;

import kamenov.cupcakespakoandmoni.services.LogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class StatController {
    private final LogService logService;

    public StatController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("/statistic")
    public String statistics(Model model){

        model.addAttribute("logs",
                logService.findAllLogs());

        return "statistic";
    }
}
