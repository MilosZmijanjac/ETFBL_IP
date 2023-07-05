package ip.webshop.service.implementation;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ip.webshop.model.entity.Order;
import ip.webshop.model.entity.OrderItem;
import ip.webshop.model.enumeration.PaymentMethod;
import ip.webshop.model.request.OrdersItemRequest;
import ip.webshop.model.request.OrdersRequest;
import ip.webshop.repository.OrderItemRepository;
import ip.webshop.repository.OrderRepository;
import ip.webshop.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderItemRepository orderItemRepository;
    @Override
    public List<OrderItem> getOrderItems(Long orderId) {
        return orderItemRepository.findAllByOrderId(orderId);
    }
    @Override
    public List<Order> getOrders(String username) {
        return orderRepository.findByUsername(username);
    }
    @Override
    public void createOrders(OrdersRequest order){
        Order newOrder=new Order();
        newOrder.setPaymentMethod(PaymentMethod.valueOf(order.getPaymentMethod()));
        newOrder.setTimestamp(Instant.now());
        newOrder.setUsername(order.getUsername());
        newOrder.setTotalPrice(order.getTotalPrice());
        newOrder=orderRepository.save(newOrder);
        ArrayList<OrderItem>orderItems=new ArrayList<OrderItem>();
        for (OrdersItemRequest item : order.getItems()) {
            OrderItem orderItem=new OrderItem();
            orderItem.setOrderId(newOrder.getId());
            orderItem.setPrice(item.getPrice());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setProductName(item.getProductName());
            orderItems.add(orderItem);
        }
        orderItemRepository.saveAll(orderItems);

    }
    
}
