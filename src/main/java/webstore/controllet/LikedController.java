package webstore.controllet;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webstore.domain.User;
import webstore.service.LikedService;

@Controller
@RequestMapping("/liked")
public class LikedController {

    private final LikedService likedService;

    public LikedController(LikedService likedService) {
        this.likedService = likedService;
    }

    @GetMapping
    public String getLikedProduct(@AuthenticationPrincipal User user, Model model){
        return likedService.getLikedProduct(user, model);
    }
}
