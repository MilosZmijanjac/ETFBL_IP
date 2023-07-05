package ip.webshop.model.request;

import java.math.BigDecimal;

import lombok.Data;
@Data
public class OrdersRequest {
    OrdersItemRequest[] items;
    BigDecimal totalPrice;
    String username;
    String paymentMethod;
}
