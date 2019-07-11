package webstore.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import webstore.domain.Product;
import webstore.domain.Type;
import webstore.domain.User;
import webstore.repos.ProductRepo;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepo productRepo;

    @Value("${upload.path}")
    private String uploadPath;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public String saveProduct(User user, Product product, BindingResult bindingResult, String type, MultipartFile file, Model model) throws IOException {
        //проверка введенных данных на корректность
        if (bindingResult.hasErrors() || Type.isType(type) == null) {
            if (Type.isType(type) == null)
                model.addAttribute("message", "Incorrect type value");
            return "main";
        }
        //Запись файла в директорияю
        if (file != null && !file.getOriginalFilename().isEmpty()){
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            product.setFilename(resultFilename);
        }

        //Сохранение товара в сущность
        product.setTypes(Collections.singleton(Type.isType(type)));
        product.setAuthor(user);
        productRepo.save(product);
        model.addAttribute("user", user);

        return "redirect:/hello";
    }

    public String getHello(User user, Product product, Model model) {
        //Получение полного списка товаров
        List<Product> products = productRepo.findAll();
        model.addAttribute("products", products);
        model.addAttribute("user", user);
        return "main";
    }

    public String saveProductToLiked(User user, Product product) {

        //Добавление лайка на продукт
        if (!product.getLikedByUser().contains(user))
            product.getLikedByUser().add(user);
        else
            product.getLikedByUser().remove(user);

        productRepo.save(product);

        return "redirect:/hello";
    }
}