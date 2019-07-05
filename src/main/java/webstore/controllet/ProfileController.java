package webstore.controllet;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import webstore.domain.User;
import webstore.service.ProfileService;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("{userProfile}")
    public String getProfile(@AuthenticationPrincipal User user, @PathVariable User userProfile, Model model){

        profileService.getProfile(user, userProfile, model);

        return "profile";
    }
}
