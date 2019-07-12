package webstore.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import webstore.domain.Product;
import webstore.domain.User;

import java.util.Set;

@Service
public class LikedService {

    public String getLikedProduct(User user, Model model) {

        Set<Product> products = user.getLikedProducts();

        model.addAttribute("products", products);
        model.addAttribute("user", user);

        return "liked";
    }
}
