package kamenov.cupcakespakoandmoni.web;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import kamenov.cupcakespakoandmoni.models.UserEntity;
import kamenov.cupcakespakoandmoni.models.dtos.UserRegisterBindingModel;
import kamenov.cupcakespakoandmoni.services.JwtService;
import kamenov.cupcakespakoandmoni.services.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class RegisterController {
    private final UserService userService;
    private final SecurityContextRepository securityContextRepository;
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    private final ModelMapper modelMapper;
    private final JwtService jwtService;

    public RegisterController(UserService userService, SecurityContextRepository securityContextRepository, ModelMapper modelMapper, JwtService jwtService) {
        this.userService = userService;
        this.securityContextRepository = securityContextRepository;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
    }

    @ModelAttribute("registerBindingModel")
    public UserRegisterBindingModel createBindingModel() {
        return new UserRegisterBindingModel();
    }


    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("registerBindingModel")) {
            model.addAttribute("registerBindingModel", new UserRegisterBindingModel());
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserRegisterBindingModel registerBindingModel,
                               HttpServletRequest request,
                               HttpServletResponse response,
                               Model model,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !registerBindingModel.getPassword()
                .equals(registerBindingModel.getConfirmPassword())) {

            redirectAttributes.addFlashAttribute("registerBindingModel",
                    registerBindingModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult." +
                            "registerBindingModel", bindingResult);

            return "redirect:/user/register";
        }

        UserEntity user =
                userService.registerUser(registerBindingModel, successfulAuth -> {
                    SecurityContextHolderStrategy strategy = SecurityContextHolder.getContextHolderStrategy();

                    SecurityContext context = strategy.createEmptyContext();
                    context.setAuthentication(successfulAuth);

                    strategy.setContext(context);
                    securityContextRepository.saveContext(context, request, response);

                });
        Cookie cookie = new Cookie("jwt", jwtService.generateToken(user));
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        model.addAttribute("message", "Registration successful");

        return "redirect:/";

    }
}
