package webstore.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import webstore.domain.User;
import webstore.repos.ProductRepo;

@Service
public class ProfileService {

    private final ProductRepo productRepo;

    public ProfileService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }


    public void getProfile(User user, User userProfile, Model model) {

        model.addAttribute("products", productRepo.findByAuthor(userProfile));
        model.addAttribute("user", user);
        model.addAttribute("userProfile", userProfile);

    }
}
