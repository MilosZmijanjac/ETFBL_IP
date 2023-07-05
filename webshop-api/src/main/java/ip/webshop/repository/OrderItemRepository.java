package ip.webshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ip.webshop.model.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
    List<OrderItem> findAllByOrderId(Long id);
}
