package webstore.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import webstore.domain.Product;
import webstore.domain.Type;
import webstore.domain.User;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findByAuthor(User user);
    List<Product> findByTypes(Type type);
    List<Product> findByNameAndTypes(String name, Type type);
}
