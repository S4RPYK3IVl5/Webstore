package webstore.controllet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webstore.domain.Product;
import webstore.domain.Type;
import webstore.domain.User;
import webstore.repos.ProductRepo;
import webstore.service.MainService;

import javax.validation.Valid;
import java.util.Collections;

@Controller
public class MainController {

    private final MainService mainService;
    private final ProductRepo productRepo;

    @Autowired
    public MainController(MainService mainService, ProductRepo productRepo) {
        this.mainService = mainService;
        this.productRepo = productRepo;
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
    public String getHello(@AuthenticationPrincipal User user, Product product, Model model){
        model.addAttribute("user", user);
        return "main";
    }

    @PostMapping("/hello")
    public String saveMessage(@AuthenticationPrincipal User user,
                              @Valid Product product,
                              BindingResult bindingResult,
                              @RequestParam("type") String type,
                              Model model){

        if (bindingResult.hasErrors())
            return "main";

        product.setTypes(Collections.singleton(Type.isType(type)));
        productRepo.save(product);
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
