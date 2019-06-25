package webstore.controllet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @GetMapping("/greeting")
    public String getGreeting(@RequestParam(defaultValue = "World", name = "name", required = false) String name, Model model){
        model.addAttribute("name", name);
        return "main";
    }
}
