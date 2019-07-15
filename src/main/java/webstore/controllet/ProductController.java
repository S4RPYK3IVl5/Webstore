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
    public String getHello(@AuthenticationPrincipal User user,
                           @RequestParam(value = "searchName", defaultValue = "", required = false) String nameOfProduct,
                           @RequestParam(value = "searchType", defaultValue = "", required = false) String typeOfProduct,
                           Product product, Model model){
        return productService.getHello(user, nameOfProduct, typeOfProduct, product, model);
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

    @PostMapping("search")
    public String searchProduct(@RequestParam("name") String name, @RequestParam("type") String type){

        if (name.isEmpty())
            return "redirect:/hello?searchType="+type;

        if (type.isEmpty())
            return "redirect:/hello?searchName="+name;

        return "redirect:/hello?searchName="+name+"&"+"searchType="+type;
    }

    @GetMapping("save/{id}")
    public String saveProductToLiked(@AuthenticationPrincipal User user, @PathVariable("id") Product product){

        return productService.saveProductToLiked(user, product);
    }
}
