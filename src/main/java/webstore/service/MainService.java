package webstore.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import webstore.domain.User;

@Service
public class MainService {

    public String checkInfo(User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "/login";
        return "redirect:/results";
    }
}
