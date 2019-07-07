package webstore.controllet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webstore.domain.User;
import webstore.service.UserSettingService;

@Controller
@RequestMapping("/setting")
public class UserSettingController {

    private final UserSettingService userSettingService;

    @Autowired
    public UserSettingController(UserSettingService userSettingService) {
        this.userSettingService = userSettingService;
    }

    @GetMapping
    public String getSetting(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", user);
        return "setting";
    }

    @PostMapping
    public String postSetting(@AuthenticationPrincipal User user, @RequestParam("password1") String password1,
                              @RequestParam("password2") String password2, Model model){
        return userSettingService.postSetting(user, password1, password2, model);
    }
}