package webstore.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import webstore.domain.User;
import webstore.repos.UserRepo;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class UserSettingService {

    private final UserRepo userRepo;

    @Value("${upload.path}")
    private String uploadPath;

    public UserSettingService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public String postSetting(User user, String password1, String password2, MultipartFile file, Model model) throws IOException {

        if (!password1.isEmpty() || !password2.isEmpty()) {
            if (password1.length() < 6 || password2.length() < 6) {
                model.addAttribute("message", "Any fields must be longer than 6 letters");
                return "setting";
            }

            if (!password1.equals(password2)) {
                model.addAttribute("message", "Passwords must be equals");
                return "setting";
            }

            user.setPassword(password1);
            model.addAttribute("success", "Password successful has changed");
        }

        if (!file.isEmpty() && !file.getOriginalFilename().isEmpty())
        {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists())
                uploadDir.mkdir();

            String filename = UUID.randomUUID().toString() + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" +filename));
            user.setFilename(filename);
        }

        userRepo.save(user);

        return "redirect:/setting";
    }
}