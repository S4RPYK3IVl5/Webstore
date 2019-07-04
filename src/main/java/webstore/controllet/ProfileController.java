package webstore.controllet;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import webstore.domain.User;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @GetMapping("{userProfile}")
    public String getProfile(@AuthenticationPrincipal User user, @PathVariable User userProfile, Model model){
        model.addAttribute("user", user);
        model.addAttribute("userProfile", userProfile);
        return "profile";
    }
}
