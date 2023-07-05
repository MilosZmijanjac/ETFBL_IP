package ip.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ip.webshop.model.entity.Order;
import java.util.List;


public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByUsername(String username);
}
