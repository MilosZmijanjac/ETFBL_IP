package ip.webshop.controller;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ip.webshop.model.enumeration.LogType;
import ip.webshop.model.request.OrdersRequest;
import ip.webshop.service.LogService;
import ip.webshop.service.OrderService;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrderController {
  @Autowired
  OrderService orderService;
  @Autowired
  LogService logService;

  @GetMapping("/{username}")
  public ResponseEntity<?> getOrdersByUsername(@PathVariable String username) {
    logService.writeLog(LogType.ERROR, "/api/order/" + username, "Request users orders", Instant.now());
    return ResponseEntity.ok(orderService.getOrders(username));
  }

  @GetMapping("/items/{id}")
  public ResponseEntity<?> getOrderItemsByOrderId(@PathVariable Long id) {
    logService.writeLog(LogType.ERROR, "/api/order/" + id, "Request users orders", Instant.now());
    return ResponseEntity.ok(orderService.getOrderItems(id));
  }

  @PostMapping
  public ResponseEntity<?> addOrders(@RequestBody OrdersRequest orders) {
    orderService.createOrders(orders);
    logService.writeLog(LogType.ERROR, "/api/order/", "Create users orders", Instant.now());
    return ResponseEntity.ok(orders);
  }

}
