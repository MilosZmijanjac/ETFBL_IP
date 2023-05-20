package ip.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ip.webshop.model.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {
    
}
