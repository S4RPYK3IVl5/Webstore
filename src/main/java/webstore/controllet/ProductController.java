package webstore.controllet;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webstore.domain.Product;
import webstore.domain.Type;
import webstore.domain.User;
import webstore.service.ProductService;

import javax.validation.Valid;
import java.util.Collections;

@Controller
@RequestMapping("/hello")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public String getHello(@AuthenticationPrincipal User user, Product product, Model model){
        model.addAttribute("user", user);
        return "main";
    }

    @PostMapping
    public String saveProduct(@AuthenticationPrincipal User user,
                              @Valid Product product,
                              BindingResult bindingResult,
                              @RequestParam("type") String type,
                              Model model){

        return productService.saveProduct(user, product, bindingResult, type, model);
    }

}
