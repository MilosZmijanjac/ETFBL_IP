package ip.webshop.model.request;

import java.math.BigDecimal;
import java.util.List;

import ip.webshop.model.entity.Attribute;

import lombok.Data;

@Data
public class ProductRequest{
    private String title;
    private String description;
    private BigDecimal price;
    private String imagesPath;
    private String address;
    private String city;
    private String country;
    private String status;
    private String state;
    private List<Attribute> extendedAttributes;
    private String username;
    private String categoryId;
}