package webstore.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import webstore.domain.Product;
import webstore.domain.Type;
import webstore.domain.User;
import webstore.repos.ProductRepo;

import java.util.Collections;

@Service
public class ProductService {

    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public String saveProduct(User user, Product product, BindingResult bindingResult, String type, Model model) {

        if (bindingResult.hasErrors() || Type.isType(type) == null) {
            if (Type.isType(type) == null)
                model.addAttribute("message", "Incorrect type value");
            return "main";
        }

        product.setTypes(Collections.singleton(Type.isType(type)));
        productRepo.save(product);
        model.addAttribute("user", user);

        return "redirect:/hello";
    }
}
