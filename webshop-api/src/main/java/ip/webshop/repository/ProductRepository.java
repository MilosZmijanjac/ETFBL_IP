package ip.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import ip.webshop.model.entity.Product;
import ip.webshop.model.entity.User;

import ip.webshop.model.entity.Category;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByUser(User user);
    List<Product> findByCategory(Category category);
}
