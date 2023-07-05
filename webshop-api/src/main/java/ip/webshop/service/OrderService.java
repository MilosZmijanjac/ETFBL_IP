package ip.webshop.service;

import java.util.List;

import ip.webshop.model.entity.Order;
import ip.webshop.model.entity.OrderItem;
import ip.webshop.model.request.OrdersRequest;

public interface OrderService {
    List<OrderItem> getOrderItems(Long orderId);
    List<Order> getOrders(String username);
    void createOrders(OrdersRequest order);
}
