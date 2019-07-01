package webstore.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import webstore.domain.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
