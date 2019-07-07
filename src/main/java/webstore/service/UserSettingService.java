package webstore.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import webstore.domain.User;
import webstore.repos.UserRepo;

@Service
public class UserSettingService {

    private final UserRepo userRepo;

    public UserSettingService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public String postSetting(User user, String password1, String password2, Model model) {

        if (password1.isEmpty() || password2.isEmpty()){
            model.addAttribute("message", "Any fields must't be empty");
            return "setting";
        }

        if (password1.length() < 6 || password2.length() < 6){
            model.addAttribute("message", "Any fields must be longer than 6 letters");
            return "setting";
        }

        if (!password1.equals(password2)){
            model.addAttribute("message", "Passwords must be equals");
            return "setting";
        }

        user.setPassword(password1);
        userRepo.save(user);
        model.addAttribute("success", "Password successful has changed");

        return "redirect:/setting";
    }
}