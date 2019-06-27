package webstore.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import webstore.domain.Role;
import webstore.domain.User;
import webstore.repos.UserRepo;

import java.util.Collections;

@Service
public class MainService {

    private final UserRepo userRepo;

    public MainService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public String checkInfo(User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "authorization";

        User userFromDB = userRepo.findByUsername(user.getUsername());

        if (userFromDB != null){
            model.addAttribute("message", "User exists!");
            return "authorization";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));

        userRepo.save(user);

        return "redirect:/login";
    }
}
