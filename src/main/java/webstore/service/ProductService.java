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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepo productRepo;
    private List<Product> products;

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

    public String getHello(User user, String nameOfProduct, String typeOfProduct, Product product, Model model) {

        //Получение полного списка товаров или поиск товаров
        if (nameOfProduct.isEmpty() && typeOfProduct.isEmpty())
            products = productRepo.findAll();
        else
        {
            if (nameOfProduct.isEmpty())
                products = productRepo.findByTypes(Type.isType(typeOfProduct));

            if (typeOfProduct.isEmpty()) {
                ArrayList<Product> deleteProduct = new ArrayList<>();
                products = productRepo.findAll();

                for (Product eachProduct : products)
                    if (!eachProduct.getName().contains(nameOfProduct))
                        deleteProduct.add(eachProduct);

                for (Product eachProduct : deleteProduct)
                    products.remove(eachProduct);
            }

            if (!nameOfProduct.isEmpty() && !typeOfProduct.isEmpty())
                products = productRepo.findByNameAndTypes(nameOfProduct, Type.isType(typeOfProduct));
        }

        if (products.isEmpty())
            model.addAttribute("alert", "we can not find any product with your request");

        for (Product product1 : products)
            if (product1.getAuthor() == null)
                productRepo.delete(product1);

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