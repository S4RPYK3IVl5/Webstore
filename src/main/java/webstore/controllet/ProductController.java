package webstore.controllet;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import webstore.domain.Product;
import webstore.domain.User;
import webstore.service.ProductService;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/hello")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public String getHello(@AuthenticationPrincipal User user, Product product, Model model){
        return productService.getHello(user, product, model);
    }

    @PostMapping
    public String saveProduct(@AuthenticationPrincipal User user,
                              @Valid Product product,
                              BindingResult bindingResult,
                              @RequestParam("type") String type,
                              @RequestParam("file") MultipartFile file,
                              Model model) throws IOException {

        return productService.saveProduct(user, product, bindingResult, type, file, model);
    }

    @GetMapping("save/{id}")
    public String saveProductToLiked(@AuthenticationPrincipal User user, @PathVariable("id") Product product){

        return productService.saveProductToLiked(user, product);
    }
}
