package ip.webshop.model.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrdersItemRequest {
    private String productName;
    private BigDecimal quantity;
    private BigDecimal price;
}
