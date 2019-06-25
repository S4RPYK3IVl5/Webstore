package webstore.controllet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import webstore.domain.User;
import webstore.service.MainService;

import javax.validation.Valid;

@Controller
public class MainController {

    private final MainService mainService;

    @Autowired
    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/login")
    public String getIndexPage(User user){
        return "login";
    }

    @PostMapping("/login")
    public String checkInfo(@Valid User user, BindingResult bindingResult)
    {
        return mainService.checkInfo(user, bindingResult);
    }
}
