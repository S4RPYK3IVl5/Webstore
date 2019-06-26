package webstore.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import webstore.domain.User;
import webstore.repos.UserRepo;

@Service
public class MainService {

    private final UserRepo userRepo;

    public MainService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public String checkInfo(User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "authorization";

        userRepo.save(user);

        return "redirect:/login";
    }
}
