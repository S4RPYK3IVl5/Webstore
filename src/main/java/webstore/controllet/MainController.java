package webstore.controllet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import webstore.domain.User;
import webstore.service.MainService;

import javax.validation.Valid;

@Controller
public class MainController {

    private final MainService mainService;

    @Autowired
    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/")
    public String getIndexPage(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/login")
    public String getLoginPage(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", user);
        return "login";
    }

    @GetMapping("/hello")
    public String getHello(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", user);
        return "main";
    }

    @GetMapping("/authorization")
    public String getAuthorizationPage(User user){
        return "authorization";
    }

    // Add user in DB if input value is correct
    @PostMapping("/authorization")
    public String checkInfo(@Valid User user, BindingResult bindingResult, Model model)
    {
        return mainService.checkInfo(user, bindingResult, model);
    }
}
