package ip.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import ip.webshop.model.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByUserId(Long id);
    List<Product> findByCategoryId(Long id);
}
